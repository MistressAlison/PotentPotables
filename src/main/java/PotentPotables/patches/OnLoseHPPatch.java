package PotentPotables.patches;

import PotentPotables.potions.interfaces.OnLoseHPPotion;
import PotentPotables.util.Wiz;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import javassist.CtBehavior;

public class OnLoseHPPatch {
    @SpirePatch2(clz = AbstractPlayer.class, method = "damage")
    public static class PotionShenanigans {
        @SpireInsertPatch(locator = Locator.class, localvars = {"damageAmount"})
        public static void plz(AbstractPlayer __instance, DamageInfo info, @ByRef int[] damageAmount) {
            for (AbstractPotion p : Wiz.getAllPotions()) {
                if (p instanceof OnLoseHPPotion) {
                    damageAmount[0] = ((OnLoseHPPotion) p).onLoseHP(damageAmount[0]);
                }
            }
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.MethodCallMatcher(Math.class, "min");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }
    }
}
