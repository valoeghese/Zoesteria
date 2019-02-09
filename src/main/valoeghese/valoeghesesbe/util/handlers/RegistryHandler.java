package valoeghese.valoeghesesbe.util.handlers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import valoeghese.valoeghesesbe.config.ConfigHandler;
import valoeghese.valoeghesesbe.config.FileHandler;
import valoeghese.valoeghesesbe.config.ZfgHelper;
import valoeghese.valoeghesesbe.entity.EntityTrueTNT;
import valoeghese.valoeghesesbe.init.ModBiomes;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.init.ModItems;
import valoeghese.valoeghesesbe.items.ItemFertilizer;
import valoeghese.valoeghesesbe.util.Console;
import valoeghese.valoeghesesbe.util.IHasModel;
import valoeghese.valoeghesesbe.util.Reference;

@EventBusSubscriber
public class RegistryHandler
{
	private static class RegistryFiles
	{
		private static FileHandler configOre = new FileHandler("oreChances.cfg", "container Chances(int)", "{", "    Saltpeter = 2;", "    Sulphur = 8;", "}");
		private static FileHandler configAllowOre = new FileHandler("allowOres.cfg", "container ConfigOre(boolean)", "{", "    MasterGenVanadium = true;", "    MasterGenSulphur = true;", "    MasterGenSaltpeter = true;", "}");
		private static FileHandler configWoodlands = new FileHandler("biomeWoodlandWeights.cfg", "container BiomeWeights(int)", "{", "    VBEWoodlandSpruce = 2;", "    VBEWoodlandOakLow = 7;", "    VBEWoodlandOakHigh = 4;", "    VBEWoodlandOakHilly = 4;", "    VBEWoodlandOakHighInhabited = 2;", "    VBEWoodlandOakTall = 4;", "}");
		private static FileHandler configAlps = new FileHandler("biomeAlpsWeights.cfg", "container BiomeWeights(int)", "{", "    VBEAlpsNorthern = 5;", "    VBEAlpsNorthernSnow = 3;", "    VBEAlpsNorthernSubalpine = 4;", "    VBEAlpsNorthernSubalpineSnow = 3;", "    VBEAlpsSouthern = 2;", "    VBEAlpsSouthernExtreme = 1;", "    VBEAlpsSouthernSubalpine = 2;", "}");
		private static FileHandler configWasteland = new FileHandler("biomeWastelandWeights.cfg", "container BiomeWeights(int)", "{", "    VBEWasteland = 7;", "    VBEWastelandLush = 3;", "    VBEWastelandOasis = 1;", "    VBEWastelandHills = 4;", "}");
		private static FileHandler configChristmas = new FileHandler("biomeWhiteForestWeights.cfg", "container BiomeWeights(int)", "{", "    VBEChristmasWoods = 7;", "    VBEChristmasWoodsOak = 3;", "    VBEChristmasWoodsHills = 5;", "    VBEChristmasWoodsHillsOak = 2;", "}");
		private static FileHandler configGrassland = new FileHandler("biomeGrasslandsWeights.cfg", "container BiomeWeights(int)", "{", "    VBEGrassLowlands = 6;", "    VBEGrassMoorlands = 3;", "    VBEGrassHighlands = 3;", "    VBEGrassChapparal = 6;", "}");
		private static FileHandler configM1 = new FileHandler("biomeAlphaMinorWeights.cfg", "container BiomeWeights(int)", "{", "    VBEAustralianOutback = 6;", "    VBEBluff = 3;", "    VBEMire = 6;", "    VBEOrchidField = 6;", "}");
		
		private static FileHandler configStructure = new FileHandler("structureConfig.cfg", "container ConfigStructure(boolean)", "{", "    MasterGenStructure = true;", "}");
		private static FileHandler configStructure1 = new FileHandler("structureConfigUluru.cfg", "container StructureRarity(int)", "{", "    SandstoneUluruRarity = 360;", "}");
		private static FileHandler configStructure2 = new FileHandler("structureConfigShips.cfg", "container StructureRarity(int)", "{", "    ShipsArchipelagoRarity = 980;", "    ShipsOceanRarity = 1080;", "    ShipsRiverRarity = 780;", "}");
		private static FileHandler biomesCompat = new FileHandler("allowNewBiomes.cfg", "container NewBiomes(boolean)", "{", "    MasterGenAlpha13 = true;", "    MasterGenAlpha14 = true;", "    MasterGenBeta10 = true;", "    MasterGenBetaHigher = true;", "}");
		
		private static FileHandler configDunes = new FileHandler("biomeSandDuneWeights.cfg", "container BiomeWeights(int)", "{", "    VBEDunes = 6;", "    VBEDunesRed = 4;", "    VBEDunesMutated = 3;", "    VBEDunesOasis = 1;", "}");
		private static FileHandler configSnowRocks = new FileHandler("biomeSnowRocksWeights.cfg", "container BiomeWeights(int)", "{", "    VBESnowRocks = 8;", "    VBESnowRocksHilly = 5;", "    VBESnowRocksPlateau = 2;", "}");
		private static FileHandler configM2 = new FileHandler("biomeAlphaMinor2Weights.cfg", "container BiomeWeights(int)", "{", "    VBEArchipelago = 3;", "    VBEReefStony = 5;", "    VBEGhostForest = 1;", "    VBEBarelands = 2;", "    VBELushJungle = 6;", "}");
		private static FileHandler configGrassyWetland = new FileHandler("biomeGrassyWetlandWeights.cfg", "container BiomeWeights(int)", "{", "    VBEFen = 5;", "    VBEMarsh = 5;", "    VBEBetaEstuary = 4;", "    VBEBetaFenTrees = 3;", "}");
		private static FileHandler configBushland = new FileHandler("biomeBushlandWeights.cfg", "container BiomeWeights(int)", "{", "    VBEForestedBushland = 7;", "    VBEForestedBushlandHills = 4;", "    VBEForestedPlateau = 4;", "    VBEForestedPlateauM = 2;", "    VBEForestedPillars = 2;", "}");
		private static FileHandler configBrush = new FileHandler("biomeBrushWeights.cfg", "container BiomeWeights(int)", "{", "    VBEBrushlands = 7;", "    VBEBrushlandsHills = 4;", "    VBEBrushlandsDense = 3;", "    VBEBrushlandsDesert = 6;", "}");
	}
	
	private static boolean hasAppended14 = false;
	
	static
	{
		if (!hasAppended14)
		{
			hasAppended14 = true;
			
			try
			{
				char[] reader14Chars = new char[Short.MAX_VALUE];
				FileReader static14Reader = new FileReader(RegistryFiles.biomesCompat.getFile());
				
				static14Reader.read(reader14Chars);
				ConfigHandler fourteenConfig = new ConfigHandler(reader14Chars, false);
				
				Map<String, String> fourteenTrue = fourteenConfig.getContainer("NewBiomes");
				
				static14Reader.close();
				
				try
				{
					String oldData = fourteenTrue.get("MasterGenAlpha13");
					
					try
					{
						boolean containsGivenKey = fourteenTrue.containsKey("MasterGenAlpha14");
						
						if (!containsGivenKey)
						{
							ZfgHelper.overrideWriteFile("./Zoesteria/allowNewBiomes.cfg", "container NewBiomes(boolean)", "{", "MasterGenAlpha13 = " + oldData + ";", "    MasterGenAlpha14 = true;", "    MasterGenBeta10 = true;", "    MasterGenBetaHigher = true;", "}");
						}
					} catch (NullPointerException | ExceptionInInitializerError e) {
						
						e.printStackTrace();
						ZfgHelper.overrideWriteFile("./Zoesteria/allowNewBiomes.cfg", "container NewBiomes(boolean)", "{", "MasterGenAlpha13 = " + oldData + ";", "    MasterGenAlpha14 = true;", "    MasterGenBeta10 = true;", "    MasterGenBetaHigher = true;", "}");
						
					}
				} catch (NullPointerException | ExceptionInInitializerError e) {
					e.printStackTrace();
					Console.WriteLine("[Zoesteria] I kinda didn't expect this so please report it ^^^.\n[Zoesteria] Did you mess with the code O_O?");
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	
	{
		for(Item item : ModItems.ITEMS)
		{
			
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
			
		}
		
		for(Block block : ModBlocks.BLOCKS)
		{
			
			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
			
		}
		
	}
	
	@SubscribeEvent
	public static void onEntityRegister(RegistryEvent.Register<EntityEntry> event)
	{
		event.getRegistry().register(EntityEntryBuilder.create().entity(EntityTrueTNT.class).id(new ResourceLocation(Reference.MOD_ID, "tnt_true"), 0).tracker(160, 10, true).name("tnt_true").build());
	}
	
	/*
	@SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRightClick(PlayerInteractEvent.RightClickBlock e){
        if(e.getItemStack().getItem() instanceof ItemFertilizer && e.getWorld().getBlockState(e.getPos()).getBlock() instanceof BlockDirt)
        {
        	e.getWorld().setBlockState(e.getPos(), Blocks.FARMLAND.getDefaultState());
        	e.getItemStack().shrink(1);
        }
    } */
	
	public static File getConfigFile()
	{
		return RegistryFiles.configOre.getFile();
	}
	public static File getWeightWoodlandFile()
	{
		return RegistryFiles.configWoodlands.getFile();
	}
	public static File getWeightAlpsFile()
	{
		return RegistryFiles.configAlps.getFile();
	}
	public static File getWeightFlatsFile()
	{
		return RegistryFiles.configWasteland.getFile();
	}
	public static File getWeightChristmasFile()
	{
		return RegistryFiles.configChristmas.getFile();
	}
	public static File getWeightGrasslandsFile()
	{
		return RegistryFiles.configGrassland.getFile();
	}
	public static File getWeightM2File()
	{
		return RegistryFiles.configM2.getFile();
	}
	public static File getWeightSandDunesFile()
	{
		return RegistryFiles.configDunes.getFile();
	}
	public static File getWeightWetlandsFile()
	{
		return RegistryFiles.configGrassyWetland.getFile();
	}
	public static File getWeightSnowRocksFile()
	{
		return RegistryFiles.configSnowRocks.getFile();
	}
	public static File getWeightBushlandFile()
	{
		return RegistryFiles.configBushland.getFile();
	}
	public static File getWeightBrushFile()
	{
		return RegistryFiles.configBrush.getFile();
	}
	public static File getWeightM1File()
	{
		return RegistryFiles.configM1.getFile();
	}
	public static File getConfigStructureFile()
	{
		return RegistryFiles.configStructure.getFile();
	}
	public static File getBiomeFile()
	{
		return RegistryFiles.biomesCompat.getFile();
	}
	public static File getConfigStructure1File()
	{
		return RegistryFiles.configStructure1.getFile();
	}
	public static File getConfigStructure2File()
	{
		return RegistryFiles.configStructure2.getFile();
	}

	public static File getAllowOres()
	{
		return RegistryFiles.configAllowOre.getFile();
	}
	
}
