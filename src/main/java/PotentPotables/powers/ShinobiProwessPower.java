package PotentPotables.powers;

import PotentPotables.ModFile;
import PotentPotables.util.TexLoader;
import PotentPotables.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class ShinobiProwessPower extends TwoAmountPower {

    public static final String POWER_ID = ModFile.makeID(ShinobiProwessPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int cardsPerActivataion;

    public ShinobiProwessPower(AbstractCreature owner, int amount, int cardsPerActivataion) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.amount2 = cardsPerActivataion;
        this.cardsPerActivataion = cardsPerActivataion;
        region128 = TexLoader.getTextureAsAtlasRegion(ModFile.makePowerPath("ProwessPower") + "84.png");
        region48 = TexLoader.getTextureAsAtlasRegion(ModFile.makePowerPath("ProwessPower")  + "32.png");
        updateDescription();

    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL) {
            amount2--;
            if (amount2 == 0) {
                amount2 = cardsPerActivataion;
                flash();
                Wiz.atb(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount)));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + cardsPerActivataion + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
