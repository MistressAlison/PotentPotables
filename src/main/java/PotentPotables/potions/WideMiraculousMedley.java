package PotentPotables.potions;

import PotentPotables.potions.interfaces.PreBattlePotion;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class WideMiraculousMedley extends WidePotion implements PreBattlePotion {

    public WideMiraculousMedley() {
        super(new MiraculousMedley());
    }

    @Override
    public AbstractPotion makeCopy() {
        return new WideMiraculousMedley();
    }

    @Override
    public void preBattle() {
        flash();
        this.addToBot(new MakeTempCardInHandAction(new Miracle(), this.potency));
    }
}