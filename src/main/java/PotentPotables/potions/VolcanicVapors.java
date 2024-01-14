package PotentPotables.potions;

import basemod.abstracts.CustomPotion;
import PotentPotables.ModFile;
import PotentPotables.potions.interfaces.PreBattlePotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

public class VolcanicVapors extends CustomPotion implements PreBattlePotion {


    public static final String POTION_ID = ModFile.makeID(VolcanicVapors.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(Color.ORANGE);
    public static final Color hybrid = new Color(Color.FIREBRICK);
    public static final Color spots = null;

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 5;
    public static final int USE_MULTI = 3;

    public VolcanicVapors() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.HEART, PotionColor.FIRE);
        isThrown = true;
        targetRequired = false;
        initializeData();
    }

    @Override
    public void use(AbstractCreature target) {
        flash();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
            }
        }
        this.addToBot(new WaitAction(0.5F));
        this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.potency * USE_MULTI, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascensionLevel) {
        return EFFECT;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = DESCRIPTIONS[0] + (potency * USE_MULTI) + DESCRIPTIONS[1] + potency + DESCRIPTIONS[2];
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new VolcanicVapors();
    }

    @Override
    public void preBattle() {
        flash();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
            }
        }
        this.addToBot(new WaitAction(0.5F));
        this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.potency, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
    }
}