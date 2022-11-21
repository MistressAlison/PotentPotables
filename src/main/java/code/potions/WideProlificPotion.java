package code.potions;

import code.actions.BetterSelectCardsCenteredAction;
import code.potions.interfaces.PostBattlePotion;
import code.util.Wiz;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class WideProlificPotion extends WidePotion implements PostBattlePotion {

    public WideProlificPotion() {
        super(new ProlificPotion());
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new BetterSelectCardsCenteredAction(Wiz.adp().hand.group, ProlificPotion.DESCRIPTIONS[4], l -> {
            for (AbstractCard card : l) {
                Wiz.makeInHand(card, potency);
            }
        }));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new WideProlificPotion();
    }

    @Override
    public void postBattle() {
        flash();
        AbstractDungeon.getCurrRoom().addPotionToRewards(makeCopy());
    }
}