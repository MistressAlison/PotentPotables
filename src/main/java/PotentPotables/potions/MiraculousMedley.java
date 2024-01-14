package PotentPotables.potions;

import basemod.abstracts.CustomPotion;
import PotentPotables.ModFile;
import PotentPotables.potions.interfaces.PreBattlePotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class MiraculousMedley extends CustomPotion implements PreBattlePotion {


    public static final String POTION_ID = ModFile.makeID(MiraculousMedley.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(Color.YELLOW);
    public static final Color hybrid = new Color(Color.ORANGE);
    public static final Color spots = null;

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 1;
    public static final int USE_MULTI = 2;

    public MiraculousMedley() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.FAIRY, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
        initializeData();
    }

    @Override
    public void use(AbstractCreature target) {
        flash();
        addToBot(new GainEnergyAction(potency*USE_MULTI));
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascensionLevel) {
        return EFFECT;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        if (potency == 1) {
            description = DESCRIPTIONS[0] + (potency * USE_MULTI) + DESCRIPTIONS[1] + potency + DESCRIPTIONS[2];
        } else {
            description = DESCRIPTIONS[0] + (potency * USE_MULTI) + DESCRIPTIONS[1] + potency + DESCRIPTIONS[3];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new MiraculousMedley();
    }

    @Override
    public void preBattle() {
        flash();
        this.addToBot(new MakeTempCardInHandAction(new Miracle(), this.potency));
    }
}