package code.potions;

import CardAugments.CardAugmentsMod;
import CardAugments.commands.Chimera;
import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.CustomPotion;
import basemod.helpers.CardModifierManager;
import code.ModFile;
import code.actions.BetterSelectCardsCenteredAction;
import code.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ChimericCompound extends CustomPotion {


    public static final String POTION_ID = ModFile.makeID(ChimericCompound.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(Color.PURPLE);
    public static final Color hybrid = new Color(Color.PINK);
    public static final Color spots = null;

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 1;

    public ChimericCompound() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.SNECKO, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                CardCrawlGame.sound.play("GHOST_ORB_IGNITE_1", 0.3F);
                for (AbstractCard card : Wiz.adp().hand.group) {
                    for (int i = 0 ; i < potency ; i++) {
                        ArrayList<AbstractCardModifier> mods = CardAugmentsMod.modMap.values().stream().filter(mon -> mon.canApplyTo(card)).collect(Collectors.toCollection(ArrayList::new));
                        if (!mods.isEmpty()) {
                            CardModifierManager.addModifier(card, mods.get(AbstractDungeon.potionRng.random(mods.size()-1)).makeCopy());
                            card.applyPowers();
                            card.superFlash();
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
        if (potency == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + potency + DESCRIPTIONS[2];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));
        /*tips.add(new PowerTip(
                BaseMod.getKeywordTitle(potionStrings.DESCRIPTIONS[2].toLowerCase()),
                BaseMod.getKeywordDescription(potionStrings.DESCRIPTIONS[2].toLowerCase())
        ));*/
    }

    @Override
    public AbstractPotion makeCopy() {
        return new ChimericCompound();
    }
}