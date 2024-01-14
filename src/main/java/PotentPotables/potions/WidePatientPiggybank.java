package PotentPotables.potions;

import basemod.abstracts.CustomSavable;
import PotentPotables.potions.interfaces.PostBattlePotion;
import PotentPotables.util.Wiz;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

public class WidePatientPiggybank extends WidePotion implements PostBattlePotion, CustomSavable<Integer> {
    public int goldAmount;

    public WidePatientPiggybank() {
        super(new PatientPiggybank());
        isThrown = false;
        targetRequired = false;
        goldAmount = 30;
        initializeData();
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new VFXAction(new RainingGoldEffect(goldAmount)));
            addToBot(new GainGoldAction(goldAmount));
        } else {
            AbstractDungeon.effectList.add(new RainingGoldEffect(goldAmount));
            Wiz.adp().gainGold(goldAmount);
        }
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = PatientPiggybank.DESCRIPTIONS[0] + goldAmount + PatientPiggybank.DESCRIPTIONS[1] + potency + PatientPiggybank.DESCRIPTIONS[2];
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public AbstractPotion makeCopy() {
        WidePatientPiggybank copy = new WidePatientPiggybank();
        copy.goldAmount = goldAmount;
        copy.initializeData();
        return copy;
    }

    @Override
    public void postBattle() {
        flash();
        goldAmount += potency;
        initializeData();
    }

    @Override
    public Integer onSave() {
        return goldAmount;
    }

    @Override
    public void onLoad(Integer integer) {
        if (integer != null) {
            this.goldAmount = integer;
            initializeData();
        }
    }
}