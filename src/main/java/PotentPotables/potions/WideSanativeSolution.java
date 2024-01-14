package PotentPotables.potions;

import PotentPotables.potions.interfaces.PreBattlePotion;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class WideSanativeSolution extends WidePotion implements PreBattlePotion {

    public WideSanativeSolution() {
        super(new SanativeSolution());
    }

    @Override
    public AbstractPotion makeCopy() {
        return new WideSanativeSolution();
    }

    @Override
    public void preBattle() {
        flash();
        addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, potency));
    }
}