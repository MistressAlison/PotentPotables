package code.patches;

import code.powers.interfaces.PotionPotencyPower;
import code.util.Wiz;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PotionPotencyPowerPatches {
    @SpirePatch(clz = AbstractPotion.class, method = "getPotency", paramtypez = {})
    public static class PotencyPatch{
        @SpirePostfixPatch
        public static int plz(int __result, AbstractPotion __instance) {
            if (Wiz.adp() != null) {
                for (AbstractPower pow : Wiz.adp().powers) {
                    if (pow instanceof PotionPotencyPower) {
                        __result= ((PotionPotencyPower) pow).changePotency(__result);
                    }
                }
            }
            return __result;
        }
    }
}
