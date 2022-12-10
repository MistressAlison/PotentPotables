package code.potions;

import code.potions.interfaces.PostBattlePotion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
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

    public void updateColors() {
        this.liquidColor.r = (MathUtils.cosDeg((float)(System.currentTimeMillis() / 10L % 360L)) + 1.25F) / 2.3F;
        this.liquidColor.g = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 1000L) / 10L % 360L)) + 1.25F) / 2.3F;
        this.liquidColor.b = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 2000L) / 10L % 360L)) + 1.25F) / 2.3F;
        this.liquidColor.a = 1.0F;
    }

    @Override
    public void render(SpriteBatch sb) {
        updateColors();
        super.render(sb);
    }

    @Override
    public void labRender(SpriteBatch sb) {
        updateColors();
        super.labRender(sb);
    }

    @Override
    public void shopRender(SpriteBatch sb) {
        updateColors();
        super.shopRender(sb);
    }
}