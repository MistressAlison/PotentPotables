package code.potions;

import basemod.abstracts.CustomPotion;
import code.ModFile;
import code.potions.interfaces.OnLoseHPPotion;
import code.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.BufferPower;

public class WideMetallicMixture extends WidePotion implements OnLoseHPPotion {

    public WideMetallicMixture() {
        super(new MetallicMixture());
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new WideMetallicMixture();
    }

    @Override
    public int getPotency(int ascensionLevel) {
        return getPotion().getPotency(ascensionLevel) * 2;
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