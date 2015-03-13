package shukaro.warptheory;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;
import shukaro.warptheory.block.WarpBlocks;
import shukaro.warptheory.entity.EntityPassiveCreeper;
import shukaro.warptheory.gui.WarpTab;
import shukaro.warptheory.handlers.ConfigHandler;
import shukaro.warptheory.handlers.WarpCommand;
import shukaro.warptheory.handlers.WarpEventHandler;
import shukaro.warptheory.handlers.WarpHandler;
import shukaro.warptheory.items.WarpItems;
import shukaro.warptheory.net.CommonProxy;
import shukaro.warptheory.recipe.WarpRecipes;
import shukaro.warptheory.research.WarpResearch;
import shukaro.warptheory.util.Constants;
import shukaro.warptheory.util.NameGenerator;


import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

@Mod(modid = Constants.modID, name = Constants.modName, version = Constants.modVersion, guiFactory = "shukaro.warptheory.gui.GuiFactory",
        dependencies = "required-after:Forge@[10.13.2,);required-after:Baubles;required-after:Thaumcraft")
public class WarpTheory {
    @SidedProxy(clientSide = "shukaro.warptheory.net.ClientProxy", serverSide = "shukaro.warptheory.net.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    public static CreativeTabs mainTab = new WarpTab(StatCollector.translateToLocal("warptheory.tab"));

    public static NameGenerator normalNames;

    @Mod.Instance(Constants.modID)
    public static WarpTheory instance;

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent evt) {
        evt.registerServerCommand(new WarpCommand());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        try {
            File folder = new File(event.getModConfigurationDirectory() + "/warptheory/");
            if (!folder.exists())
                folder.mkdirs();
            File normalFile = new File(event.getModConfigurationDirectory() + "/warptheory/normal.txt");
            if (!normalFile.exists()) {
                InputStream in = WarpTheory.class.getResourceAsStream("/assets/warptheory/names/normal.txt");
                Files.copy(in, normalFile.getAbsoluteFile().toPath());
            }
            normalNames = new NameGenerator(normalFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ConfigHandler.init(event.getSuggestedConfigurationFile());

        FMLCommonHandler.instance().bus().register(new ConfigHandler());

        WarpBlocks.initBlocks();

        WarpItems.initItems();

        if (ConfigHandler.allowWarpEffects)
            MinecraftForge.EVENT_BUS.register(new WarpEventHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent evt) {
        WarpRecipes.init();
        WarpHandler.initEvents();
        EntityRegistry.registerModEntity(EntityPassiveCreeper.class, "creeperPassive", 0, this, 160, 4, true);
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent evt) {
        WarpHandler.tcReflect();
        WarpResearch.init();
    }
}
