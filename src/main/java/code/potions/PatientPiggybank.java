package code.potions;

import basemod.abstracts.CustomPotion;
import basemod.abstracts.CustomSavable;
import code.ModFile;
import code.potions.interfaces.PostBattlePotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

public class PatientPiggybank extends CustomPotion implements PostBattlePotion, CustomSavable<Integer> {


    public static final String POTION_ID = ModFile.makeID(PatientPiggybank.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(Settings.GOLD_COLOR);
    public static final Color hybrid = new Color(Color.ORANGE);
    public static final Color spots = null;

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 6;

    public int goldAmount;

    public PatientPiggybank() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.EYE, PotionColor.FIRE);
        isThrown = true;
        targetRequired = false;
        goldAmount = EFFECT * 2;
        initializeData();
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new VFXAction(new RainingGoldEffect(goldAmount)));
        addToBot(new GainGoldAction(goldAmount));
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascensionLevel) {
        return EFFECT;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = DESCRIPTIONS[0] + goldAmount + DESCRIPTIONS[1] + potency + DESCRIPTIONS[2];
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    public boolean canUse() {
        if (AbstractDungeon.actionManager.turnHasEnded && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            return false;
        } else {
            return AbstractDungeon.getCurrRoom().event == null || !(AbstractDungeon.getCurrRoom().event instanceof WeMeetAgain);
        }
    }

    @Override
    public AbstractPotion makeCopy() {
        PatientPiggybank copy = new PatientPiggybank();
        copy.goldAmount = goldAmount;
        copy.initializeData();
        return copy;
    }

    @Override
    public void postBattle() {
        flash();
        this.goldAmount += potency;
        initializeData();
    }

    @Override
    public Integer onSave() {
        return goldAmount;
    }

    @Override
    public void onLoad(Integer integer) {
        if (integer != null) {
            this.goldAmount = integer;
            initializeData();
        }
    }
}