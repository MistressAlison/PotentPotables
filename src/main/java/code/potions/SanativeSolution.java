package code.potions;

import basemod.abstracts.CustomPotion;
import code.ModFile;
import code.potions.interfaces.PreBattlePotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class SanativeSolution extends CustomPotion implements PreBattlePotion {


    public static final String POTION_ID = ModFile.makeID(SanativeSolution.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(Settings.RED_TEXT_COLOR);
    public static final Color hybrid = new Color(Color.FIREBRICK);
    public static final Color spots = null;

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 2;

    public SanativeSolution() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.BOTTLE, PotionColor.FIRE);
        isThrown = true;
        targetRequired = false;
        initializeData();
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, potency * 5));
        } else {
            AbstractDungeon.player.heal(potency * 5);
        }
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascensionLevel) {
        return EFFECT;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = DESCRIPTIONS[0] + (potency * 5) + DESCRIPTIONS[1] + potency + DESCRIPTIONS[2];
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
        return new SanativeSolution();
    }

    @Override
    public void preBattle() {
        flash();
        addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, potency));
    }
}