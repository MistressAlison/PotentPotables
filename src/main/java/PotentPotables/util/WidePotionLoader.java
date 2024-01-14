package PotentPotables.util;

import PotentPotables.potions.*;
import com.evacipated.cardcrawl.mod.widepotions.WidePotionsMod;
import com.evacipated.cardcrawl.mod.widepotions.potions.WidePotionSlot;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.List;

public class WidePotionLoader {
    public static void loadCrossoverContent() {
        //Simple Potions
        WidePotionsMod.whitelistSimplePotion(AttackAugmenter.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(BlockBooster.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(SpeedySpirit.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(DopingDraught.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(MarvelousMilk.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(WitchWater.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(BallisticBrew.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(MagicManipulator.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(BanefulBlend.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(InfernalInfusion.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(ExoticElixir.POTION_ID);
        WidePotionsMod.whitelistSimplePotion(LivelyLiquor.POTION_ID);

        //Complex Potions
        WidePotionsMod.whitelistComplexPotion(ProlificPotion.POTION_ID, new WideProlificPotion());
        WidePotionsMod.whitelistComplexPotion(PatientPiggybank.POTION_ID, new WidePatientPiggybank());
        WidePotionsMod.whitelistComplexPotion(PoisedPerfume.POTION_ID, new WidePoisedPerfume());
        WidePotionsMod.whitelistComplexPotion(TenaciousTea.POTION_ID, new WideTenaciousTea());
        WidePotionsMod.whitelistComplexPotion(MetallicMixture.POTION_ID, new WideMetallicMixture());
        WidePotionsMod.whitelistComplexPotion(SanativeSolution.POTION_ID, new WideSanativeSolution());
        WidePotionsMod.whitelistComplexPotion(VolcanicVapors.POTION_ID, new WideVolcanicVapors());
        WidePotionsMod.whitelistComplexPotion(MiraculousMedley.POTION_ID, new WideMiraculousMedley());

        if (Loader.isModLoaded("CardAugments")) {
            WidePotionsMod.whitelistSimplePotion(ChimericCompound.POTION_ID);
        }
    }

    public static List<AbstractPotion> getWidePotions() {
        return WidePotionSlot.Field.widepotions.get(Wiz.adp());
    }
}
