package PotentPotables.potions;

import PotentPotables.potions.interfaces.PreBattlePotion;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

public class WideVolcanicVapors extends WidePotion implements PreBattlePotion {

    public WideVolcanicVapors() {
        super(new VolcanicVapors());
    }

    @Override
    public AbstractPotion makeCopy() {
        return new WideVolcanicVapors();
    }

    @Override
    public void preBattle() {
        flash();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
            }
        }
        this.addToBot(new WaitAction(0.5F));
        this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.potency, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
    }
}