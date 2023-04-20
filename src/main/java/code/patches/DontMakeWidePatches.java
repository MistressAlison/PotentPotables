package code.patches;

import com.evacipated.cardcrawl.mod.widepotions.patches.WideRewards;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;

public class DontMakeWidePatches {
    public static boolean skipNextPotion = false;

    @SpirePatch2(clz = WideRewards.class, method = "Prefix")
    public static class SkipThisPotion {
        @SpirePrefixPatch
        public static SpireReturn<?> plz() {
            if (skipNextPotion) {
                skipNextPotion = false;
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
