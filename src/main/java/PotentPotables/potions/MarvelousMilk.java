package PotentPotables.potions;

import basemod.abstracts.CustomPotion;
import PotentPotables.ModFile;
import PotentPotables.powers.ShinobiProwessPower;
import PotentPotables.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class MarvelousMilk extends CustomPotion {


    public static final String POTION_ID = ModFile.makeID(MarvelousMilk.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(0xffffffff);
    public static final Color hybrid = new Color();
    public static final Color spots = null;

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 1;
    public static final int CARDS_PER = 5;

    public MarvelousMilk() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.BOTTLE, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
    }

    public void use(AbstractCreature target) {
        Wiz.applyToSelf(new DexterityPower(Wiz.adp(), potency));
        Wiz.applyToSelf(new ShinobiProwessPower(Wiz.adp(), potency, CARDS_PER));
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascensionLevel) {
        return EFFECT;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1] + CARDS_PER + DESCRIPTIONS[2] + potency + DESCRIPTIONS[3];
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.DEXTERITY.NAMES[0]), GameDictionary.keywords.get(GameDictionary.DEXTERITY.NAMES[0])));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new MarvelousMilk();
    }
}