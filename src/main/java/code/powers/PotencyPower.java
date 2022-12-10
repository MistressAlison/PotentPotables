package code.powers;

import code.ModFile;
import code.powers.interfaces.PotionPotencyPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PotencyPower extends AbstractPower implements PotionPotencyPower {

    public static final String POWER_ID = ModFile.makeID(PotencyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PotencyPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("anger");
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        ModFile.refreshPotions();
    }

    @Override
    public void onRemove() {
        ModFile.refreshPotions();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        ModFile.refreshPotions();
    }

    @Override
    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        ModFile.refreshPotions();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public int changePotency(int potency) {
        return (int) (potency * (1 + amount/100F));
    }
}
