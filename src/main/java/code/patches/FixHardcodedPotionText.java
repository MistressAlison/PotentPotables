package code.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.*;
import javassist.CtBehavior;

import java.util.ArrayList;

public class FixHardcodedPotionText {
    @SpirePatch2(clz = DuplicationPotion.class, method = "initializeData")
    @SpirePatch2(clz = AttackPotion.class, method = "initializeData")
    @SpirePatch2(clz = SkillPotion.class, method = "initializeData")
    @SpirePatch2(clz = PowerPotion.class, method = "initializeData")
    @SpirePatch2(clz = ColorlessPotion.class, method = "initializeData")
    public static class InsertActualPotency {
        @SpireInsertPatch(locator = Locator.class)
        public static void plz(AbstractPotion __instance, PotionStrings ___potionStrings) {
            int potency = ReflectionHacks.getPrivate(__instance, AbstractPotion.class, "potency");
            if (potency == 2 && __instance.description.equals(___potionStrings.DESCRIPTIONS[0])) {
                __instance.description = ___potionStrings.DESCRIPTIONS[1];
            } else if (potency > 2) {
                __instance.description = ___potionStrings.DESCRIPTIONS[1].replace("#b2", "#b"+potency);
            }
        }
    }

    public static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher m = new Matcher.MethodCallMatcher(ArrayList.class, "clear");
            return LineFinder.findInOrder(ctBehavior, m);
        }
    }
}
