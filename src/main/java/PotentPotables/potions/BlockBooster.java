package PotentPotables.potions;

import basemod.abstracts.CustomPotion;
import PotentPotables.ModFile;
import PotentPotables.actions.BoostValuesInHandAction;
import PotentPotables.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class BlockBooster extends CustomPotion {


    public static final String POTION_ID = ModFile.makeID(BlockBooster.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(0x0f5abdff);
    public static final Color hybrid = new Color(0x3f3f40ff);
    public static final Color spots = null;

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 4;

    public BlockBooster() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.ANVIL, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        Wiz.atb(new BoostValuesInHandAction(Wiz.adp().hand.size(), potency, BoostValuesInHandAction.StatBoost.BLOCK));
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
        tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.BLOCK.NAMES[0]), GameDictionary.keywords.get(GameDictionary.BLOCK.NAMES[0])));
        /*tips.add(new PowerTip(
                BaseMod.getKeywordTitle(potionStrings.DESCRIPTIONS[2].toLowerCase()),
                BaseMod.getKeywordDescription(potionStrings.DESCRIPTIONS[2].toLowerCase())
        ));*/
    }

    @Override
    public AbstractPotion makeCopy() {
        return new BlockBooster();
    }
}