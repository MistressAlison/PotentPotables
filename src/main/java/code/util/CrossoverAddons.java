package code.util;

import basemod.BaseMod;
import code.potions.*;
import com.evacipated.cardcrawl.mod.widepotions.WidePotionsMod;
import com.evacipated.cardcrawl.modthespire.Loader;

public class CrossoverAddons {
    public static void loadCrossoverContent() {
        if (Loader.isModLoaded("CardAugments")) {
            BaseMod.addPotion(ChimericCompound.class, ChimericCompound.liquid, ChimericCompound.hybrid, ChimericCompound.spots, ChimericCompound.POTION_ID);
        }

        if (Loader.isModLoaded("widepotions")) {
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

            //Complex Potions
            //WidePotionsMod.whitelistComplexPotion(ProlificPotion.POTION_ID, new WideProlificPotion());
            WidePotionsMod.whitelistComplexPotion(PatientPiggybank.POTION_ID, new WidePatientPiggybank());
            //WidePotionsMod.whitelistComplexPotion(PoisedPerfume.POTION_ID, new WidePoisedPerfume());
            WidePotionsMod.whitelistComplexPotion(TenaciousTea.POTION_ID, new WideTenaciousTea());
            WidePotionsMod.whitelistComplexPotion(MetallicMixture.POTION_ID, new WideMetallicMixture());
            WidePotionsMod.whitelistComplexPotion(SanativeSolution.POTION_ID, new WideSanativeSolution());

            if (Loader.isModLoaded("CardAugments")) {
                WidePotionsMod.whitelistSimplePotion(ChimericCompound.POTION_ID);
            }
        }
    }
}
