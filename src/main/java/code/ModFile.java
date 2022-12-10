package code;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import code.potions.*;
import code.potions.interfaces.PostBattlePotion;
import code.potions.interfaces.PreBattlePotion;
import code.powers.interfaces.PotionPotencyPower;
import code.util.CrossoverAddons;
import code.util.TexLoader;
import code.util.Wiz;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ModFile implements
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        PostInitializeSubscriber, PostBattleSubscriber, OnStartBattleSubscriber {

    public static final String modID = "PotentPotables";

    public static final String BADGE_IMAGE = modID + "Resources/images/Badge.png";
    public static UIStrings uiStrings;
    public static String[] TEXT;
    public static String[] EXTRA_TEXT;

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
    };

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

    public ModFile() {
        BaseMod.subscribe(this);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        ModFile thismod = new ModFile();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");

        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/" + getLangString() + "/Potionstrings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + getLangString() + "/UIstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostInitialize() {
        //Add Potions
        BaseMod.addPotion(ProlificPotion.class, ProlificPotion.liquid, ProlificPotion.hybrid, ProlificPotion.spots, ProlificPotion.POTION_ID);
        BaseMod.addPotion(AttackAugmenter.class, AttackAugmenter.liquid, AttackAugmenter.hybrid, AttackAugmenter.spots, AttackAugmenter.POTION_ID);
        BaseMod.addPotion(BlockBooster.class, BlockBooster.liquid, BlockBooster.hybrid, BlockBooster.spots, BlockBooster.POTION_ID);
        BaseMod.addPotion(PatientPiggybank.class, PatientPiggybank.liquid, PatientPiggybank.hybrid, PatientPiggybank.spots, PatientPiggybank.POTION_ID);
        BaseMod.addPotion(SpeedySpirit.class, SpeedySpirit.liquid, SpeedySpirit.hybrid, SpeedySpirit.spots, SpeedySpirit.POTION_ID);
        BaseMod.addPotion(DopingDraught.class, DopingDraught.liquid, DopingDraught.hybrid, DopingDraught.spots, DopingDraught.POTION_ID);
        BaseMod.addPotion(MarvelousMilk.class, MarvelousMilk.liquid, MarvelousMilk.hybrid, MarvelousMilk.spots, MarvelousMilk.POTION_ID);
        BaseMod.addPotion(WitchWater.class, WitchWater.liquid, WitchWater.hybrid, WitchWater.spots, WitchWater.POTION_ID);
        BaseMod.addPotion(PoisedPerfume.class, PoisedPerfume.liquid, PoisedPerfume.hybrid, PoisedPerfume.spots, PoisedPerfume.POTION_ID);
        BaseMod.addPotion(TenaciousTea.class, TenaciousTea.liquid, TenaciousTea.hybrid, TenaciousTea.spots, TenaciousTea.POTION_ID);
        BaseMod.addPotion(BallisticBrew.class, BallisticBrew.liquid, BallisticBrew.hybrid, BallisticBrew.spots, BallisticBrew.POTION_ID);
        BaseMod.addPotion(MagicManipulator.class, MagicManipulator.liquid, MagicManipulator.hybrid, MagicManipulator.spots, MagicManipulator.POTION_ID);
        BaseMod.addPotion(MetallicMixture.class, MetallicMixture.liquid, MetallicMixture.hybrid, MetallicMixture.spots, MetallicMixture.POTION_ID);
        BaseMod.addPotion(SanativeSolution.class, SanativeSolution.liquid, SanativeSolution.hybrid, SanativeSolution.spots, SanativeSolution.POTION_ID);
        BaseMod.addPotion(BanefulBlend.class, BanefulBlend.liquid, BanefulBlend.hybrid, BanefulBlend.spots, BanefulBlend.POTION_ID);

        if (Loader.isModLoaded("widepotions")) {
            CrossoverAddons.loadCrossoverContent();
        }

        uiStrings = CardCrawlGame.languagePack.getUIString(makeID("ModConfigs"));
        EXTRA_TEXT = uiStrings.EXTRA_TEXT;
        TEXT = uiStrings.TEXT;
        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();

        // Load the Mod Badge
        Texture badgeTexture = TexLoader.getTexture(BADGE_IMAGE);
        BaseMod.registerModBadge(badgeTexture, EXTRA_TEXT[0], "Mistress Alison", EXTRA_TEXT[1], settingsPanel);
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        for (AbstractPotion p : Wiz.getAllPotions()) {
            if (p instanceof PostBattlePotion) {
                ((PostBattlePotion) p).postBattle();
            }
        }

        Wiz.adp().powers.removeIf(p -> p instanceof PotionPotencyPower);
        refreshPotions();
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        for (AbstractPotion p : Wiz.getAllPotions()) {
            if (p instanceof PreBattlePotion) {
                ((PreBattlePotion) p).preBattle();
            }
        }
    }

    public static void refreshPotions() {
        for (AbstractPotion p : Wiz.getAllPotions()) {
            p.initializeData();
        }
    }
}
