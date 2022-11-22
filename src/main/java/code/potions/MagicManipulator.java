package code.potions;

import basemod.abstracts.CustomPotion;
import code.ModFile;
import code.actions.BoostValuesInHandAction;
import code.powers.PotencyPower;
import code.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class MagicManipulator extends CustomPotion {


    public static final String POTION_ID = ModFile.makeID(MagicManipulator.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(0x550fbdff);
    public static final Color hybrid = new Color(0x3f3f40ff);
    public static final Color spots = null;

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 100;

    public MagicManipulator() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.ANVIL, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        Wiz.applyToSelf(new PotencyPower(Wiz.adp(), potency));
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascensionLevel) {
        return EFFECT;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
        /*tips.add(new PowerTip(
                BaseMod.getKeywordTitle(potionStrings.DESCRIPTIONS[2].toLowerCase()),
                BaseMod.getKeywordDescription(potionStrings.DESCRIPTIONS[2].toLowerCase())
        ));*/
    }

    @Override
    public AbstractPotion makeCopy() {
        return new MagicManipulator();
    }
}