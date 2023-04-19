package code.potions;

import basemod.abstracts.CustomPotion;
import code.ModFile;
import code.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;

public class InfernalInfusion extends CustomPotion {


    public static final String POTION_ID = ModFile.makeID(InfernalInfusion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(Color.FIREBRICK);
    public static final Color hybrid = new Color(Color.RED);
    public static final Color spots = new Color(Settings.HALF_TRANSPARENT_BLACK_COLOR);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 1;

    public InfernalInfusion() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.BOLT, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new ApplyPowerAction(Wiz.adp(), Wiz.adp(), new DoubleDamagePower(Wiz.adp(), potency, false)));
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
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + potency + DESCRIPTIONS[2];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new InfernalInfusion();
    }
}