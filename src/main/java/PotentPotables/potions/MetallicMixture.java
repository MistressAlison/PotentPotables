package PotentPotables.potions;

import basemod.abstracts.CustomPotion;
import PotentPotables.ModFile;
import PotentPotables.potions.interfaces.OnLoseHPPotion;
import PotentPotables.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.BufferPower;

public class MetallicMixture extends CustomPotion implements OnLoseHPPotion {


    public static final String POTION_ID = ModFile.makeID(MetallicMixture.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(Color.DARK_GRAY);
    public static final Color hybrid = new Color(Color.GRAY);
    public static final Color spots = new Color(Color.LIGHT_GRAY);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 1;

    public MetallicMixture() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.H, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        Wiz.applyToSelf(new BufferPower(Wiz.adp(), potency));
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
        description += DESCRIPTIONS[3] + potency + DESCRIPTIONS[4];
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new MetallicMixture();
    }

    @Override
    public int onLoseHP(int amount) {
        if (amount > 0) {
            flash();
        }
        if (amount < potency) {
            return 0;
        }
        return amount - potency;
    }
}