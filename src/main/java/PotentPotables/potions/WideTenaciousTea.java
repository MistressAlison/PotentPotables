package PotentPotables.potions;

import PotentPotables.potions.interfaces.PreBattlePotion;
import PotentPotables.util.Wiz;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class WideTenaciousTea extends WidePotion implements PreBattlePotion {

    public WideTenaciousTea() {
        super(new TenaciousTea());
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new WideTenaciousTea();
    }

    @Override
    public void preBattle() {
        flash();
        addToBot(new GainBlockAction(Wiz.adp(), potency));
    }
}