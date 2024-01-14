package PotentPotables.potions;

import basemod.abstracts.CustomPotion;
import PotentPotables.ModFile;
import PotentPotables.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LivelyLiquor extends CustomPotion {


    public static final String POTION_ID = ModFile.makeID(LivelyLiquor.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(Color.GOLD);
    public static final Color hybrid = new Color(Color.GOLDENROD);
    public static final Color spots = new Color(Settings.HALF_TRANSPARENT_WHITE_COLOR);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 2;

    public LivelyLiquor() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.M, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0 ; i < potency ; i++) {
                    ArrayList<AbstractCard> better = Wiz.adp().hand.group.stream().filter(c -> c.costForTurn > 0).collect(Collectors.toCollection(ArrayList::new));
                    if (!better.isEmpty()) {
                        AbstractCard c = Wiz.getRandomItem(better);
                        c.modifyCostForCombat(-1);
                        c.superFlash(Color.GOLD.cpy());
                    } else {
                        ArrayList<AbstractCard> worse = Wiz.adp().hand.group.stream().filter(c -> c.costForTurn > 0).collect(Collectors.toCollection(ArrayList::new));
                        if (!worse.isEmpty()) {
                            AbstractCard c = Wiz.getRandomItem(worse);
                            c.modifyCostForCombat(-1);
                            c.superFlash(Color.GOLD.cpy());
                        }
                    }
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
    }

    @Override
    public AbstractPotion makeCopy() {
        return new LivelyLiquor();
    }
}