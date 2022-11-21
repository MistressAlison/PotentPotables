package code.potions;

import basemod.abstracts.CustomPotion;
import code.ModFile;
import code.potions.interfaces.PreBattlePotion;
import code.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class WideTenaciousTea extends WidePotion implements PreBattlePotion {

    public WideTenaciousTea() {
        super(new TenaciousTea());
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new WideTenaciousTea();
    }

    @Override
    public void preBattle() {
        flash();
        addToBot(new GainBlockAction(Wiz.adp(), potency));
    }
}