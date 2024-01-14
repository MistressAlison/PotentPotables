package PotentPotables.potions;

import basemod.abstracts.CustomPotion;
import PotentPotables.ModFile;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class ExoticElixir extends CustomPotion {


    public static final String POTION_ID = ModFile.makeID(ExoticElixir.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(Color.WHITE);
    public static final Color hybrid = new Color(Color.LIGHT_GRAY);
    public static final Color spots = new Color(Settings.HALF_TRANSPARENT_BLACK_COLOR);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 1;

    public ExoticElixir() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.T, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int hits = 0;
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
                        addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
                        hits++;
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
                        addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
                        hits++;
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
                        addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                        hits++;
                    }
                }
                if (hits > 0) {
                    addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, hits * potency));
                }
                this.isDone = true;
            }
        });
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascensionLevel) {
        return EFFECT;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.EXHAUST.NAMES[0]), GameDictionary.keywords.get(GameDictionary.EXHAUST.NAMES[0])));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new ExoticElixir();
    }
}