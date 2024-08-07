package PotentPotables.potions;

import basemod.abstracts.CustomPotion;
import PotentPotables.ModFile;
import PotentPotables.potions.interfaces.PreBattlePotion;
import PotentPotables.util.Wiz;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BobEffect;

public class PoisedPerfume extends CustomPotion implements PreBattlePotion {


    public static final String POTION_ID = ModFile.makeID(PoisedPerfume.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final Color liquid = new Color(Color.PINK);
    public static final Color hybrid = new Color(Color.CYAN);
    public static final Color spots = null;

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 2;

    private static final TextureAtlas.AtlasRegion vfx = new TextureAtlas(Gdx.files.internal("powers/powers.atlas")).findRegion("128/blur");
    private static final Color vfxFrontColor = liquid.cpy().mul(0.9f, 0.9f, 0.9f, 0.45f);
    private static final Color vfxBackColor = liquid.cpy().mul(0.7f, 0.7f, 0.7f, 0.3f);
    private static final float VFX_SCALE = 3/5f;
    private static final float W = vfx.packedWidth;
    private static final float H = vfx.packedHeight;
    private static final float W2 = W/2f;
    private static final float H2 = H/2f;

    private final BobEffect bob = new BobEffect(5f * Settings.scale, 0.5F);

    public PoisedPerfume() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.HEART, PotionColor.FIRE);
        isThrown = true;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        addToBot(new ApplyPowerAction(Wiz.adp(), Wiz.adp(), new StrengthPower(Wiz.adp(), potency)));
        addToBot(new ApplyPowerAction(Wiz.adp(), Wiz.adp(), new LoseStrengthPower(Wiz.adp(), potency)));
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascensionLevel) {
        return EFFECT;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = DESCRIPTIONS[0] + (potency) + DESCRIPTIONS[1] + potency + DESCRIPTIONS[2];
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.STRENGTH.NAMES[0]), GameDictionary.keywords.get(GameDictionary.STRENGTH.NAMES[0])));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new PoisedPerfume();
    }

    @Override
    public void preBattle() {
        flash();
        addToBot(new ApplyPowerAction(Wiz.adp(), Wiz.adp(), new StrengthPower(Wiz.adp(), potency)));
        addToBot(new ApplyPowerAction(Wiz.adp(), Wiz.adp(), new LoseStrengthPower(Wiz.adp(), potency)));
    }

    @Override
    public void render(SpriteBatch sb) {
        bob.update();
        renderVfxBehind(sb);
        super.render(sb);
        renderVfxInfront(sb);
    }

    @Override
    public void labRender(SpriteBatch sb) {
        bob.update();
        renderVfxBehind(sb);
        super.labRender(sb);
        renderVfxInfront(sb);
    }

    @Override
    public void shopRender(SpriteBatch sb) {
        bob.update();
        renderVfxBehind(sb);
        super.shopRender(sb);
        renderVfxInfront(sb);
    }

    private void renderVfxBehind(SpriteBatch sb) {
        sb.setColor(vfxBackColor);
        sb.draw(vfx, posX - W2 - bob.y, posY - H2 - 5f * Settings.scale, W2, H2, W, H, VFX_SCALE * this.scale, -VFX_SCALE * this.scale, 0);
    }

    private void renderVfxInfront(SpriteBatch sb) {
        sb.setColor(vfxFrontColor);
        sb.draw(vfx, posX - W2 + bob.y, posY - H2 - 5f * Settings.scale, W2, H2, W, H, VFX_SCALE * this.scale, VFX_SCALE * this.scale, 0);
    }
}