package code.potions;

import code.potions.interfaces.PostBattlePotion;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class WideProlificPotion extends WidePotion implements PostBattlePotion {

    public WideProlificPotion() {
        super(new ProlificPotion());
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