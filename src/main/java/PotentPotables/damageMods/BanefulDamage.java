package PotentPotables.damageMods;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BanefulDamage extends AbstractDamageModifier {
    final int amount;

    public BanefulDamage(int amount) {
        this.amount = amount;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        if (target != null) {
            return damage + (amount * target.powers.stream().filter(p -> p.type == AbstractPower.PowerType.DEBUFF).count());
        }
        return damage;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new BanefulDamage(amount);
    }
}
