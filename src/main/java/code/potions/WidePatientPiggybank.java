package code.potions;

import basemod.abstracts.CustomSavable;
import code.potions.interfaces.PostBattlePotion;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotion;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;

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