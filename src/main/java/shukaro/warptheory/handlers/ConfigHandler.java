package shukaro.warptheory.handlers;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import shukaro.warptheory.recipe.WarpRecipes;
import shukaro.warptheory.research.WarpResearch;
import shukaro.warptheory.util.Constants;

import java.io.File;
/**
 * Created by Ark on 3/7/2015.
 * code in part provided by pahimar and TheOldOne822
 */
public class ConfigHandler {

    public static Configuration config;
    public static boolean wussMode = false;
    public static int permWarpMult = 4;
    public static boolean allowPermWarpRemoval = true;
    public static boolean allowGlobalWarpEffects = false;
    public static boolean allowWarpEffects = false;
    public static boolean allowServerKickWarpEffects = false;
    public static boolean allowWarpEffect1 = false;
    public static boolean allowWarpEffect2 = false;
    public static boolean allowWarpEffect3 = false;
    public static boolean allowWarpEffect4 = false;
    public static boolean allowWarpEffect5 = false;
    public static boolean allowWarpEffect6 = false;
    public static boolean allowWarpEffect7 = false;
    public static boolean allowWarpEffect8 = false;
    public static boolean allowWarpEffect9 = false;
    public static boolean allowWarpEffect10 = false;
    public static boolean allowWarpEffect11 = false;
    public static boolean allowWarpEffect12 = false;
    public static boolean allowWarpEffect13 = false;
    public static boolean allowWarpEffect14 = false;
    public static boolean allowWarpEffect15 = false;
    public static boolean allowWarpEffect16 = false;
    public static boolean allowWarpEffect17 = false;
    public static boolean allowWarpEffect18 = false;
    public static boolean allowWarpEffect19 = false;
    public static boolean allowWarpEffect20 = false;
    public static boolean allowWarpEffect21 = false;
    public static boolean allowWarpEffect22 = false;

    public static void init(File configFile)
    {
        if (config == null) {
            // Create the config object from the given config file
            config = new Configuration(configFile);

            config.addCustomCategoryComment("general", "Change the inner workings of the mod requires restart");
            config.addCustomCategoryComment("warp_effects", "Toggle specific warp effect, If all disabled Pure Tear will not work");
            config.setCategoryRequiresMcRestart("general", true);

            loadConfiguration();

        }
    }

    private static void loadConfiguration()
    {
        wussMode = config.getBoolean("wussMode", "general", false, "enables less expensive recipes");
        permWarpMult = config.getInt("permWarpMult", "general", 4, 0, Integer.MAX_VALUE, "how much more 'expensive' permanent warp is compared to normal warp");
        allowPermWarpRemoval = config.getBoolean("allowPermWarpRemoval", "general", true, "whether items can remove permanent warp or not");
        allowGlobalWarpEffects = config.getBoolean("allowGlobalWarpEffects", "general", true, "whether warp effects that involve the environment are triggered");
        allowWarpEffects = config.getBoolean("addWarpEffects", "general", true, "whether to add general warp effects. If false extra effects will only be seen when using Pure Tear");
        allowServerKickWarpEffects = config.getBoolean("allowServerErrorWarpEffects", "general", false, "whether to allow warp effects known to cause errors on servers");
        allowWarpEffect1 = config.getBoolean("allowBatsEffect", "warp_effects", true, "whether to allow spawn bats warp effect. ");
        allowWarpEffect2 = config.getBoolean("allowBlinkEffect", "warp_effects", true, "whether to allow random teleport warp effect");
        allowWarpEffect3 = config.getBoolean("allowPoisonEffect", "warp_effects", true, "whether to allow poison warp effect");
        allowWarpEffect4 = config.getBoolean("allowNauseaEffect", "warp_effects", true, "whether to allow nausea warp effect");
        allowWarpEffect5 = config.getBoolean("allowJumpEffect", "warp_effects", true, "whether to allow jump boost warp effect");
        allowWarpEffect6 = config.getBoolean("allowBlindEffect", "warp_effects", true, "whether to allow blindness warp effect");
        allowWarpEffect7 = config.getBoolean("allowDecayEffect", "warp_effects", true, "whether to allow decay warp effect");
        allowWarpEffect8 = config.getBoolean("allowDeafEffect", "warp_effects", true, "whether to allow ears (unable to read messages) warp effect");
        allowWarpEffect9 = config.getBoolean("allowSwampEffect", "warp_effects", true, "whether to allow swamp (random trees) warp effect");
        allowWarpEffect10 = config.getBoolean("allowMuteEffect", "warp_effects", true, "whether to allow tongue (unable to send messages) warp effect");
        allowWarpEffect11 = config.getBoolean("allowFriendEffect", "warp_effects", true, "whether to allow friendly creeper warp effect");
        allowWarpEffect12 = config.getBoolean("allowLiveStockRainEffect", "warp_effects", true, "whether to allow livestock rain warp effect");
        allowWarpEffect13 = config.getBoolean("allowWindEffect", "warp_effects", true, "whether to allow wind warp effect");
        allowWarpEffect14 = config.getBoolean("allowChestEffect", "warp_effects", true, "whether to allow chest scramble warp effect");
        allowWarpEffect15 = config.getBoolean("allowBloodEffect", "warp_effects", true, "whether to allow blood warp effect");
        allowWarpEffect16 = config.getBoolean("allowAccelerationEffect", "warp_effects", true, "whether to allow acceleration warp effect");
        allowWarpEffect17 = config.getBoolean("allowLightningEffect", "warp_effects", true, "whether to allow lightning warp effect");
        allowWarpEffect18 = config.getBoolean("allowWorldHoleEffect", "warp_effects", false, "whether to allow world hole warp effect. may cause Server errors.");
        allowWarpEffect19 = config.getBoolean("allowRainEffect", "warp_effects", true, "whether to allow rain warp effect");
        allowWarpEffect20 = config.getBoolean("allowWitherSpawnEffect", "warp_effects", true, "whether to allow spawn wither warp effect");
        allowWarpEffect21 = config.getBoolean("allowFakeBoomEffect", "warp_effects", true, "whether to allow fake explosion warp effect");
        allowWarpEffect22 = config.getBoolean("allowFakeBoomerEffect", "warp_effects", true, "whether to allow fake creeper warp effect");

        if (config.hasChanged())
            config.save();
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Constants.modID)) {
            loadConfiguration();
        }
    }
}