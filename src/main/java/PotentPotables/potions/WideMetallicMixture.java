package PotentPotables.potions;

import PotentPotables.potions.interfaces.OnLoseHPPotion;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.potions.AbstractPotion;

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