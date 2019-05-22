package valoeghese.valoeghesesbe;

import java.util.ArrayList;

import org.apache.logging.log4j.Logger;

import api.valoeghese.valoeghesesbe.BushGen;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import valoeghese.valoeghesesbe.blocks.BlockSmallBush;
import valoeghese.valoeghesesbe.compat.CompatOreDictionary;
import valoeghese.valoeghesesbe.gui.CreativeTabMisc;
import valoeghese.valoeghesesbe.gui.CreativeTabPlants;
import valoeghese.valoeghesesbe.init.ModBiomes;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.init.ModRecipes;
import valoeghese.valoeghesesbe.init.ModStructures;
import valoeghese.valoeghesesbe.proxy.CommonProxy;
import valoeghese.valoeghesesbe.util.Reference;
import valoeghese.valoeghesesbe.util.TerrainHandler;
import valoeghese.valoeghesesbe.util.handlers.ModelRenderHandler;
import valoeghese.valoeghesesbe.world.ModWorldGeneration;
import valoeghese.valoeghesesbe.world.worldtype.WorldTypeSmooth;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
	public static final boolean newGenerationBoolean = false;
	
	public static final CreativeTabs tabWorld = new CreativeTabPlants();
	public static final CreativeTabs tabMisc = new CreativeTabMisc();
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final boolean isDDSSEdition = false;
	
	public static Logger logger;
	
	public static BushGen genStandard = new BushGen(ModBlocks.SMALL_BUSH.getDefaultState(), Type.FOREST, Type.LUSH).removeBiomeFromSet(ModBiomes.VBE_FEN).removeBiomeFromSet(ModBiomes.VBE_ORCHID_FIELD).removeBiomeFromSet(ModBiomes.VBE_WOODLANDS);
	public static BushGen genBerry = new BushGen(ModBlocks.SMALL_BUSH.getDefaultState().withProperty(BlockSmallBush.VARIANT, BlockSmallBush.EnumBushType.BERRY), Type.FOREST);
	public static BushGen genConiferous = new BushGen(ModBlocks.SMALL_BUSH.getDefaultState().withProperty(BlockSmallBush.VARIANT, BlockSmallBush.EnumBushType.CONIFEROUS), Type.CONIFEROUS);
	
	public static BushGen[] bushes = {genStandard, genBerry, genConiferous};
	
	static
	{		
	}
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		/* Reference For Compatibility Additions
		
    	if(Loader.isModLoaded("modname"))
    	{
    		System.out.println("Zoesteria has detected you have modname installed. Loading compatability...");
    		Reference.installedthingformod = true;
    		
    		new CompatModName().adaptMod();
    	}
    	
    	*/
		
		MinecraftForge.TERRAIN_GEN_BUS.register(TerrainHandler.class);
    	
		ModBiomes.RegisterBiomes();
		
		GameRegistry.registerWorldGenerator(new ModWorldGeneration(), 3);
		GameRegistry.registerWorldGenerator(new ModStructures(), 2);
		
		if (event.getSide() == Side.CLIENT) ModelRenderHandler.preInit(event);
		
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event)
	{
		for (Biome b : BiomeDictionary.getBiomes(Type.PLAINS))
		{
			genStandard.removeBiomeFromSet(b);
		}
		
		CompatOreDictionary.oreDictionaryMats();
		ModRecipes.init();
		
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
		
		//Console.WriteLine(ModBlocks.SMALL_BUSH.getRegistryName().toString());
		
		WorldType SMOOTH = new WorldTypeSmooth("Smooth");
		//WorldType TEST = new WorldTypeTesting("Testing 1, 2", ModBiomes.VBE_REEF);
	}
	
	public static void announceLoadedModule(String moduleNameIn)
	{
		System.out.println("Zoesteria has loaded module " + moduleNameIn);
	}
}
