package code.potions;

import basemod.abstracts.CustomPotion;
import code.ModFile;
import code.actions.BetterSelectCardsCenteredAction;
import code.potions.interfaces.PostBattlePotion;
import code.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class ProlificPotion extends CustomPotion implements PostBattlePotion {


    public static final String POTION_ID = ModFile.makeID(ProlificPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color();
    public static final Color hybrid = new Color();
    public static final Color spots = null;

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 1;

    public ProlificPotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.BOTTLE, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new SelectCardsInHandAction(1, DESCRIPTIONS[4], l -> {
            for (AbstractCard card : l) {
                Wiz.makeInHand(card, potency);
            }
        }));
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascensionLevel) {
        return EFFECT;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        if (potency == 1) {
            description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[2];
        }
        description += DESCRIPTIONS[3];
        tips.clear();
        tips.add(new PowerTip(name, description));
        /*tips.add(new PowerTip(
                BaseMod.getKeywordTitle(potionStrings.DESCRIPTIONS[2].toLowerCase()),
                BaseMod.getKeywordDescription(potionStrings.DESCRIPTIONS[2].toLowerCase())
        ));*/
    }

    @Override
    public AbstractPotion makeCopy() {
        return new ProlificPotion();
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