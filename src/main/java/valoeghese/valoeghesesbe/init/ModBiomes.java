package valoeghese.valoeghesesbe.init;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.config.ConfigHandler;
import valoeghese.valoeghesesbe.config.ZfgHelper;
import valoeghese.valoeghesesbe.util.Console;
import valoeghese.valoeghesesbe.util.handlers.RegistryHandler;
import valoeghese.valoeghesesbe.world.biomes.BiomeAlpsNorthern;
import valoeghese.valoeghesesbe.world.biomes.BiomeAlpsSubalpine;
import valoeghese.valoeghesesbe.world.biomes.BiomeOakHighVillage;
import valoeghese.valoeghesesbe.world.biomes.BiomeOakWHills;
import valoeghese.valoeghesesbe.world.biomes.BiomeOakWoodsBase;
import valoeghese.valoeghesesbe.world.biomes.BiomeWoodsBase;
import valoeghese.valoeghesesbe.world.biomes.alpha2.BiomeFlowerField;
import valoeghese.valoeghesesbe.world.biomes.alpha2.BiomeWasteland;
import valoeghese.valoeghesesbe.world.biomes.alpha3.BiomeOakTallWoodlands;
import valoeghese.valoeghesesbe.world.biomes.alpha3.macro.BiomeGrasslands;
import valoeghese.valoeghesesbe.world.biomes.alpha3.macro.BiomeGrasslandsLakeless;
import valoeghese.valoeghesesbe.world.biomes.alpha3.macro.BiomeSouthernAlps;
import valoeghese.valoeghesesbe.world.biomes.alpha3.mini.BiomeAustralianOutback;
import valoeghese.valoeghesesbe.world.biomes.alpha3.mini.BiomeBluff;
import valoeghese.valoeghesesbe.world.biomes.alpha3.mini.BiomeMire;
import valoeghese.valoeghesesbe.world.biomes.alpha4.BiomeChristmasForest;
import valoeghese.valoeghesesbe.world.biomes.alpha4.BiomeGrassyFen;
import valoeghese.valoeghesesbe.world.biomes.alpha4.BiomeGrassyMarshland;
import valoeghese.valoeghesesbe.world.biomes.alpha4.BiomeReef;
import valoeghese.valoeghesesbe.world.biomes.alpha4.BiomeSandDunes;
import valoeghese.valoeghesesbe.world.biomes.alpha4.BiomeSnowRocks;
import valoeghese.valoeghesesbe.world.biomes.alpha5.BiomeBarelands;
import valoeghese.valoeghesesbe.world.biomes.alpha5.BiomeBrushlands;
import valoeghese.valoeghesesbe.world.biomes.alpha5.BiomeGhostForest;
import valoeghese.valoeghesesbe.world.biomes.alpha5.BiomeLushJungle;
import valoeghese.valoeghesesbe.world.biomes.alpha5.BiomeReefStony;
import valoeghese.valoeghesesbe.world.biomes.alpha5.bush.BiomeBush;
import valoeghese.valoeghesesbe.world.biomes.alpha5.bush.BiomeBushCanyon;

public class ModBiomes
{
	public static final ArrayList<Biome> BIOMES = new ArrayList<Biome>();
	
	public static Biome VBE_WOODLANDS = new BiomeWoodsBase("Low Spruce Woodlands", 0.06F, 0.43F, 8, 0.05F);
	public static Biome VBE_WOODLANDS_OAK = new BiomeOakWoodsBase("Low Woodlands", 0.07F, 0.5F, 9, 0.05F);
	public static Biome VBE_WOODLANDS_OAK_HIGH = new BiomeOakWoodsBase("High Woodlands", 0.03F, 0.5F, 7, 0.6F, 1.3F);
	public static Biome VBE_WOODLANDS_OAK_HILLY = new BiomeOakWHills("Woodlands Hills", 0.38F, 0.5F, 6, 0.07F, 0.45F);
	public static Biome VBE_WOODLANDS_OAK_HIGH_INHABITED = new BiomeOakHighVillage("High Inhabited Woodlands", 0.025F, 0.48F, 6, 0.04F, 1.28F);
	public static Biome VBE_WOODLANDS_TALL = new BiomeOakTallWoodlands("Low Tall Woodlands", 0.07F, 0.5F, 14, 0.05F);
	
	public static Biome VBE_ALPS_NORTHERN_SNOWLESS = new BiomeAlpsSubalpine("Northern Alps", 0.24F, 3.2F, false);
	public static Biome VBE_ALPS_NORTHERN = new BiomeAlpsNorthern("Northern Snow Alps", 0.34F, 3.2F, false);
	public static Biome VBE_NORTHERN_SUBALPINE = new BiomeAlpsNorthern("Northern Snow Subalpine Alps", 0.2F, 1.7F, true);
	public static Biome VBE_NORTHERN_SUBALPINE_ALPS = new BiomeAlpsSubalpine("Northern Subalpine Alps", 0.3F, 1.55F, true);
	
	public static Biome VBE_WASTELAND = new BiomeWasteland("Wasteland Flats", 0.01F, -0.02F, 1);
	public static Biome VBE_WASTELAND_LUSH = new BiomeWasteland("Wasteland Flats Trees", 0.03F, 0.08F, 1);
	public static Biome VBE_WASTELAND_OASIS = new BiomeWasteland("Wasteland Flats Oasis", 0.02F, -0.1F, 2);
	public static Biome VBE_WASTELAND_HILLS = new BiomeWasteland("Wasteland Flats Slopes", 0.24F, 0.23F, 1);
	
	public static Biome VBE_ORCHID_FIELD = new BiomeFlowerField();
	public static Biome VBE_AUSTRALIAN_OUTBACK = new BiomeAustralianOutback("Australian Outback");
	
	public static Biome VBE_GRASS_LOWLANDS = new BiomeGrasslands(new BiomeProperties("Lowlands").setBaseBiome("GrasslandsBiome").setBaseHeight(0.15F).setHeightVariation(0.16F).setTemperature(0.62F).setRainfall(0.6F), BiomeGrasslands.Variant.LOWLANDS);
	public static Biome VBE_GRASS_MOORLANDS = new BiomeGrasslands(new BiomeProperties("Moorlands").setBaseHeight(2.25F).setHeightVariation(0.16F).setTemperature(0.56F).setRainfall(0.6F), BiomeGrasslands.Variant.MOORLANDS);
	public static Biome VBE_GRASS_HIGHLANDS = new BiomeGrasslandsLakeless(new BiomeProperties("Highlands").setBaseBiome("GrasslandsBiome").setBaseHeight(2.25F).setHeightVariation(0.23F).setTemperature(0.64F).setRainfall(0.6F), BiomeGrasslands.Variant.HIGHLANDS);
	public static Biome VBE_GRASS_CHAPPARAL = new BiomeGrasslands(new BiomeProperties("Lowlands Chapparal").setBaseBiome("GrasslandsBiome").setBaseHeight(0.33F).setHeightVariation(0.45F).setTemperature(0.75F).setRainfall(0.34F), BiomeGrasslands.Variant.CHAPPARAL);
	
	public static Biome VBE_BLUFF = new BiomeBluff();
	
	public static Biome VBE_ALPS_SOUTHERN = new BiomeSouthernAlps(new BiomeProperties("Southern Alps").setBaseHeight(3.1F).setHeightVariation(0.45F).setRainfall(0.2F).setTemperature(0.45F), BiomeSouthernAlps.Variant.DEFAULT);
	public static Biome VBE_ALPS_SOUTHERN_SUBALPINE = new BiomeSouthernAlps(new BiomeProperties("Southern Alps Subalpine").setBaseHeight(1.6F).setHeightVariation(0.15F).setRainfall(0.1F).setTemperature(0.5F).setBaseBiome("Southern Alps"), BiomeSouthernAlps.Variant.SUBALPINE);
	public static Biome VBE_ALPS_SOUTHERN_EXTREME = new BiomeSouthernAlps(new BiomeProperties("Extreme Southern Alps").setBaseHeight(3.0F).setHeightVariation(0.69F).setRainfall(0.1F).setTemperature(0.5F).setBaseBiome("Southern Alps"), BiomeSouthernAlps.Variant.EXTREME);
	
	public static Biome VBE_MIRE = new BiomeMire();
	
	public static Biome VBE_CHRISTMAS_WOODS = new BiomeChristmasForest(new BiomeProperties("White Woods").setHeightVariation(0.17F).setBaseHeight(0.25F).setSnowEnabled().setRainfall(0.35F).setTemperature(-0.3F), false);
	public static Biome VBE_CHRISTMAS_WOODS_OAK = new BiomeChristmasForest(new BiomeProperties("White Oaks").setBaseBiome("White Woods").setHeightVariation(0.17F).setBaseHeight(0.25F).setSnowEnabled().setRainfall(0.35F).setTemperature(-0.1F), true);
	public static Biome VBE_CHRISTMAS_WOODS_HILLS = new BiomeChristmasForest(new BiomeProperties("White Woods Hills").setBaseBiome("White Woods").setHeightVariation(0.35F).setBaseHeight(0.65F).setSnowEnabled().setRainfall(0.5F).setTemperature(-0.3F), false);
	public static Biome VBE_CHRISTMAS_WOODS_HILLS_OAK = new BiomeChristmasForest(new BiomeProperties("White Oaks Hills").setBaseBiome("White Woods").setHeightVariation(0.35F).setBaseHeight(0.65F).setSnowEnabled().setRainfall(0.5F).setTemperature(-0.1F), true);
	
	public static Biome VBE_SNOW_ROCKS = new BiomeSnowRocks(new BiomeProperties("Snow Rocks").setSnowEnabled().setRainfall(0.4F).setTemperature(-0.05F).setBaseHeight(0.135F).setHeightVariation(0.08F));
	public static Biome VBE_SNOW_ROCKS_HILLY = new BiomeSnowRocks(new BiomeProperties("Snow Rocks Mountains").setSnowEnabled().setRainfall(0.4F).setTemperature(-0.05F).setBaseHeight(0.35F).setHeightVariation(0.3F).setBaseBiome("Snow Rocks"));
	public static Biome VBE_SNOW_ROCKS_HIGH = new BiomeSnowRocks(new BiomeProperties("Snow Rocks Plateau").setSnowEnabled().setRainfall(0.4F).setTemperature(-0.05F).setBaseHeight(1.4F).setHeightVariation(0.04F).setBaseBiome("Snow Rocks"));
	
	public static Biome VBE_DUNES = new BiomeSandDunes(new BiomeProperties("Sand Dunes").setRainDisabled().setRainfall(0.0F).setTemperature(2.0F).setBaseHeight(0.75F).setHeightVariation(0.45F), false, false);
	public static Biome VBE_DUNES_RED = new BiomeSandDunes(new BiomeProperties("Red Sand Dunes").setRainDisabled().setRainfall(0.0F).setTemperature(2.0F).setBaseBiome("Sand Dunes").setBaseHeight(0.75F).setHeightVariation(0.4F), true, false);
	public static Biome VBE_DUNES_MUTATED = new BiomeSandDunes(new BiomeProperties("Sand Dunes M").setRainDisabled().setRainfall(0.0F).setTemperature(2.0F).setBaseBiome("Sand Dunes").setBaseHeight(1.3F).setHeightVariation(0.6F), false, false);
	public static Biome VBE_DUNES_OASIS = new BiomeSandDunes(new BiomeProperties("Sand Dunes Oasis").setRainfall(0.4F).setTemperature(2.0F).setBaseBiome("Sand Dunes").setBaseHeight(-0.06F).setHeightVariation(0.09F), false, true);
	
	public static Biome VBE_FEN = new BiomeGrassyFen("Grassy Fen", -0.18F, 0.13F);
	public static Biome VBE_MARSH = new BiomeGrassyMarshland("Grassy Marshland", /*-0.06F*/ -0.37F, 0.00003F);
	
	//oceans and islands
	public static Biome VBE_REEF = new BiomeReef();
	public static Biome VBE_REEF_STONY = new BiomeReefStony();
	
	public static Biome VBE_GHOST_FOREST = new BiomeGhostForest();
	
	public static Biome VBE_BARELANDS = new BiomeBarelands();
	
	public static Biome VBE_FORESTED_CANYON = new BiomeBushCanyon(new Biome.BiomeProperties("Forested Canyon Pillars").setBaseBiome("Forested Canyon Plateau").setRainfall(0.9F).setTemperature(0.6F));
	public static Biome VBE_FORESTED_PLATEAU = new BiomeBush(new Biome.BiomeProperties("Forested Canyon Plateau").setRainfall(0.9F).setTemperature(0.6F).setBaseHeight(2F).setHeightVariation(0.005F));
	public static Biome VBE_FORESTED_PLATEAU_M = new BiomeBush(new Biome.BiomeProperties("Forested Canyon Plateau M").setRainfall(0.9F).setTemperature(0.6F).setBaseHeight(2.3F).setHeightVariation(0.25F));
	public static Biome VBE_FORESTED_BUSHLAND = new BiomeBush(new Biome.BiomeProperties("Bush").setRainfall(0.9F).setTemperature(0.6F).setBaseHeight(0.4F).setHeightVariation(0.005F));
	public static Biome VBE_FORESTED_BUSHLAND_HILLS = new BiomeBush(new Biome.BiomeProperties("Bushland Hills").setBaseBiome("Bush").setRainfall(0.9F).setTemperature(0.6F).setBaseHeight(0.55F).setHeightVariation(0.35F));
	
	public static Biome VBE_BRUSHLANDS = new BiomeBrushlands(new Biome.BiomeProperties("Brushlands").setBaseHeight(0.2F).setHeightVariation(0.05F).setRainfall(0.2F).setTemperature(0.9F), 2);
	public static Biome VBE_BRUSHLANDS_DESERT = new BiomeBrushlands(new Biome.BiomeProperties("Hot Brushlands").setBaseHeight(0.18F).setHeightVariation(0.05F).setRainfall(0.1F).setTemperature(1.8F).setBaseBiome("Brushlands"), 1, true);
	public static Biome VBE_BRUSHLANDS_DENSE = new BiomeBrushlands(new Biome.BiomeProperties("Dense Brushlands").setBaseHeight(0.2F).setHeightVariation(0.05F).setRainfall(0.2F).setTemperature(0.9F).setBaseBiome("Brushlands"), 4);
	public static Biome VBE_BRUSHLANDS_HILLS = new BiomeBrushlands(new Biome.BiomeProperties("Brushlands Hills").setBaseHeight(0.4F).setHeightVariation(0.3F).setRainfall(0.2F).setTemperature(0.9F).setBaseBiome("Brushlands"), 2);
	
	public static Biome VBE_LUSH_JUNGLE = new BiomeLushJungle();
	
	//BEGIN CONF
	private static FileReader staticBiomesReader;
	private static FileReader staticGrasslandsReader;
	private static FileReader staticM1Reader;
	
	private static char[] readerBiomeChars = new char[Short.MAX_VALUE];
	private static ConfigHandler biomeAllowConfig;
	
	private static char[] readerGrasslandsChars = new char[Short.MAX_VALUE];
	private static ConfigHandler grasslandsAllowConfig;
	
	private static char[] readerM1Chars = new char[Short.MAX_VALUE];
	private static ConfigHandler m1WeightConfig;
	
	private static Map<String, String> woodWeightsTrue;
	private static Map<String, String> alpsWeightsTrue;
	private static Map<String, String> flatsWeightsTrue;
	private static Map<String, String> xmasWeightsTrue;
	private static Map<String, String> biomesAllowTrue;
	private static Map<String, String> grasslandsWeightsTrue;
	private static Map<String, String> m1WeightsTrue;
	private static Map<String, String> m2WeightsTrue;
	private static Map<String, String> dunesWeightsTrue;
	private static Map<String, String> snowRocksWeightsTrue;
	private static Map<String, String> bushWeightsTrue;
	private static Map<String, String> brushWeightsTrue;
	
	private static boolean hasRegisteredWeights = false;
	public static void RegisterBiomes()
	{
		try
		{
			if (!hasRegisteredWeights)
			{
				hasRegisteredWeights = true;
				
				woodWeightsTrue = ZfgHelper.getMapOf(RegistryHandler.getWeightWoodlandFile(), "BiomeWeights");
				Main.announceLoadedModule("WOODLAND_CONFIG");
				
				alpsWeightsTrue = ZfgHelper.getMapOf(RegistryHandler.getWeightAlpsFile(), "BiomeWeights");
				Main.announceLoadedModule("ALPS_CONFIG");
				
				flatsWeightsTrue = ZfgHelper.getMapOf(RegistryHandler.getWeightFlatsFile(), "BiomeWeights");
				Main.announceLoadedModule("FLATS_WASTELAND_CONFIG");
				
				xmasWeightsTrue = ZfgHelper.getMapOf(RegistryHandler.getWeightChristmasFile(), "BiomeWeights");
				Main.announceLoadedModule("WHITE_FOREST_CONFIG");
				
				m2WeightsTrue = ZfgHelper.getMapOf(RegistryHandler.getWeightM2File(), "BiomeWeights");
				Main.announceLoadedModule("M2_CONFIG");
				
				dunesWeightsTrue = ZfgHelper.getMapOf(RegistryHandler.getWeightSandDunesFile(), "BiomeWeights");
				Main.announceLoadedModule("DUNES_CONFIG");
				
				snowRocksWeightsTrue = ZfgHelper.getMapOf(RegistryHandler.getWeightSnowRocksFile(), "BiomeWeights");
				Main.announceLoadedModule("SNOW_ROCKS_CONFIG");
				
				bushWeightsTrue = ZfgHelper.getMapOf(RegistryHandler.getWeightBushlandFile(), "BiomeWeights");
				Main.announceLoadedModule("BUSHLAND_CONFIG");
				
				brushWeightsTrue = ZfgHelper.getMapOf(RegistryHandler.getWeightBrushFile(), "BiomeWeights");
				Main.announceLoadedModule("BRUSH_CONFIG");
			}
			
			if ( staticBiomesReader == null )
			{
				
				staticBiomesReader = new FileReader(RegistryHandler.getBiomeFile());
				
				staticBiomesReader.read(readerBiomeChars);
				biomeAllowConfig = new ConfigHandler(readerBiomeChars, false);

				biomesAllowTrue = biomeAllowConfig.getContainer("NewBiomes");
				
				System.out.println("Zoesteria has loaded Module NEW_BIOME_MASTER_CONFIG");
				staticBiomesReader.close();
				
			}
			
			if ( staticGrasslandsReader == null )
			{
				
				staticGrasslandsReader = new FileReader(RegistryHandler.getWeightGrasslandsFile());
				
				staticGrasslandsReader.read(readerGrasslandsChars);
				grasslandsAllowConfig = new ConfigHandler(readerGrasslandsChars, false);
				
				grasslandsWeightsTrue = grasslandsAllowConfig.getContainer("BiomeWeights");
				
				System.out.println("Zoesteria has loaded Module GRASSLANDS_CONFIG");
				staticGrasslandsReader.close();
				
			}
			
			if ( staticM1Reader == null )
			{
				
				staticM1Reader = new FileReader(RegistryHandler.getWeightM1File());
				
				staticM1Reader.read(readerM1Chars);
				m1WeightConfig = new ConfigHandler(readerM1Chars, false);
				
				m1WeightsTrue = m1WeightConfig.getContainer("BiomeWeights");
				
				System.out.println("Zoesteria has loaded Module M1_CONFIG");
				staticM1Reader.close();
				
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
		//END CONFIG
		
		//Woodlands
		initBiome(VBE_WOODLANDS, "Low Spruce Woodlands", BiomeType.WARM, Integer.parseInt(woodWeightsTrue.get("VBEWoodlandSpruce")), Type.FOREST, Type.LUSH, Type.CONIFEROUS);
		initBiome(VBE_WOODLANDS_OAK, "Low Woodlands", BiomeType.WARM, Integer.parseInt(woodWeightsTrue.get("VBEWoodlandOakLow")), Type.FOREST, Type.LUSH);
		initBiome(VBE_WOODLANDS_OAK_HIGH, "High Woodlands", BiomeType.WARM, Integer.parseInt(woodWeightsTrue.get("VBEWoodlandOakHigh")), Type.FOREST, Type.LUSH, Type.MOUNTAIN);
		initBiome(VBE_WOODLANDS_OAK_HILLY, "Woodlands Hills", BiomeType.WARM, Integer.parseInt(woodWeightsTrue.get("VBEWoodlandOakHilly")), Type.FOREST, Type.LUSH, Type.HILLS);
		initBiome(VBE_WOODLANDS_OAK_HIGH_INHABITED, "High Inhabited Woodlands", BiomeType.WARM, Integer.parseInt(woodWeightsTrue.get("VBEWoodlandOakHighInhabited")), true, Type.FOREST, Type.LUSH, Type.MOUNTAIN);
		initBiome(VBE_WOODLANDS_TALL, "Low Tall Woodlands", BiomeType.WARM, Integer.parseInt(woodWeightsTrue.get("VBEWoodlandOakTall")), Type.FOREST, Type.LUSH, Type.SPOOKY);
		
		//Alps
			//northern
		initBiome(VBE_ALPS_NORTHERN, "Northern Snow Alps", BiomeType.ICY, Integer.parseInt(alpsWeightsTrue.get("VBEAlpsNorthernSnow")), Type.COLD, Type.MOUNTAIN, Type.SNOWY, Type.SPARSE);
		initBiome(VBE_ALPS_NORTHERN_SNOWLESS, "Northern Alps", BiomeType.ICY, Integer.parseInt(alpsWeightsTrue.get("VBEAlpsNorthern")), Type.COLD, Type.MOUNTAIN, Type.SNOWY, Type.SPARSE);
		initBiome(VBE_NORTHERN_SUBALPINE, "Northern Snow Subalpine Alps", BiomeType.ICY, Integer.parseInt(alpsWeightsTrue.get("VBEAlpsNorthernSubalpineSnow")), Type.COLD, Type.MOUNTAIN, Type.SNOWY);
		initBiome(VBE_NORTHERN_SUBALPINE_ALPS, "Northern Subalpine Alps", BiomeType.ICY, Integer.parseInt(alpsWeightsTrue.get("VBEAlpsNorthernSubalpine")), Type.COLD, Type.MOUNTAIN);
		   //southern
		initBiome(VBE_ALPS_SOUTHERN, "Southern Alps", BiomeType.COOL, Integer.parseInt(alpsWeightsTrue.get("VBEAlpsSouthern")), Type.HILLS, Type.MAGICAL);
		initBiome(VBE_ALPS_SOUTHERN_SUBALPINE, "Southern Alps Subalpine", BiomeType.COOL, Integer.parseInt(alpsWeightsTrue.get("VBEAlpsSouthernSubalpine")), Type.HILLS, Type.MAGICAL);
		initBiome(VBE_ALPS_SOUTHERN_EXTREME, "Extreme Southern Alps", BiomeType.COOL, Integer.parseInt(alpsWeightsTrue.get("VBEAlpsSouthernExtreme")), Type.HILLS, Type.MAGICAL);
		
		//Wasteland Flats
		initBiome(VBE_WASTELAND, "Wasteland Flats", BiomeType.DESERT, Integer.parseInt(flatsWeightsTrue.get("VBEWasteland")), Type.DEAD, Type.DRY, Type.HOT, Type.PLAINS, Type.SPARSE, Type.WASTELAND);
		initBiome(VBE_WASTELAND_LUSH, "Wasteland Flats Trees", BiomeType.WARM, Integer.parseInt(flatsWeightsTrue.get("VBEWastelandLush")), Type.DEAD, Type.DRY, Type.HOT, Type.PLAINS, Type.WASTELAND);
		initBiome(VBE_WASTELAND_OASIS, "Wasteland Flats Oasis", BiomeType.DESERT, Integer.parseInt(flatsWeightsTrue.get("VBEWastelandOasis")), Type.HOT, Type.WASTELAND, Type.RARE, Type.PLAINS, Type.DRY);
		initBiome(VBE_WASTELAND_HILLS, "Wasteland Flats Slopes", BiomeType.DESERT, Integer.parseInt(flatsWeightsTrue.get("VBEWastelandHills")), Type.DEAD, Type.DRY, Type.HOT, Type.HILLS, Type.SPARSE, Type.WASTELAND);
		
		//Fields
		initBiome(VBE_ORCHID_FIELD, "Orchid Fields", BiomeType.WARM, Integer.parseInt(m1WeightsTrue.get("VBEOrchidField")), Type.LUSH, Type.PLAINS, Type.SPARSE);
		initBiome(VBE_AUSTRALIAN_OUTBACK, "Australian Outback", BiomeType.DESERT, Integer.parseInt(m1WeightsTrue.get("VBEAustralianOutback")), Type.DRY, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		
		//Grasslands
		initBiome(VBE_GRASS_LOWLANDS, "Lowlands", BiomeType.WARM, Integer.parseInt(grasslandsWeightsTrue.get("VBEGrassLowlands")), Type.LUSH, Type.PLAINS, Type.SPARSE);
		if (Boolean.parseBoolean(biomesAllowTrue.get("MasterGenAlpha13")))
		{
			initBiome(VBE_GRASS_CHAPPARAL, "Lowlands Chapparal", BiomeType.WARM, Integer.parseInt(grasslandsWeightsTrue.get("VBEGrassChapparal")), true, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		} else {
			registerImpossibleBiome(VBE_GRASS_CHAPPARAL, "Lowlands Chapparal", BiomeType.WARM, Integer.parseInt(grasslandsWeightsTrue.get("VBEGrassChapparal")), true, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		}
		initBiome(VBE_GRASS_MOORLANDS, "Moorlands", BiomeType.WARM, Integer.parseInt(grasslandsWeightsTrue.get("VBEGrassMoorlands")), Type.LUSH, Type.PLAINS, Type.SPARSE, Type.SPOOKY);
		initBiome(VBE_GRASS_HIGHLANDS, "Highlands", BiomeType.WARM, Integer.parseInt(grasslandsWeightsTrue.get("VBEGrassHighlands")), true, Type.LUSH, Type.PLAINS, Type.SPARSE, Type.MOUNTAIN);
		
		//Cliffs
		initBiome(VBE_BLUFF, "Bluff", BiomeType.COOL, Integer.parseInt(m1WeightsTrue.get("VBEBluff")), Type.COLD, Type.HILLS, Type.MOUNTAIN);
		
		//Marshes
		initBiome(VBE_MIRE, "Mire", BiomeType.COOL, Integer.parseInt(m1WeightsTrue.get("VBEMire")), Type.DEAD, Type.PLAINS, Type.SPOOKY, Type.SWAMP);
		
		if (Boolean.parseBoolean(biomesAllowTrue.get("MasterGenAlpha13")))
		{
			//White Woods
			initBiome(VBE_CHRISTMAS_WOODS, "White Woods", BiomeType.ICY, Integer.parseInt(xmasWeightsTrue.get("VBEChristmasWoods")), Type.COLD, Type.CONIFEROUS, Type.DENSE, Type.FOREST, Type.MAGICAL, Type.SNOWY);
			initBiome(VBE_CHRISTMAS_WOODS_OAK, "White Oaks", BiomeType.ICY, Integer.parseInt(xmasWeightsTrue.get("VBEChristmasWoodsOak")), true, Type.COLD, Type.DENSE, Type.FOREST, Type.MAGICAL, Type.SNOWY);
			initBiome(VBE_CHRISTMAS_WOODS_HILLS, "White Woods Hills", BiomeType.ICY, Integer.parseInt(xmasWeightsTrue.get("VBEChristmasWoodsHills")), Type.COLD, Type.CONIFEROUS, Type.DENSE, Type.FOREST, Type.HILLS, Type.MAGICAL, Type.SNOWY);
			initBiome(VBE_CHRISTMAS_WOODS_HILLS_OAK, "White Oaks Hills", BiomeType.ICY, Integer.parseInt(xmasWeightsTrue.get("VBEChristmasWoodsHillsOak")), Type.COLD, Type.DENSE, Type.FOREST, Type.HILLS, Type.MAGICAL, Type.SNOWY);
			
			//Snow Rocks
			initBiome(VBE_SNOW_ROCKS, "Snow Rocks", BiomeType.ICY, Integer.parseInt(snowRocksWeightsTrue.get("VBESnowRocks")), Type.COLD, Type.DRY, Type.PLAINS, Type.SPARSE, Type.WASTELAND);
			initBiome(VBE_SNOW_ROCKS_HILLY, "Snow Rocks Mountains", BiomeType.ICY, Integer.parseInt(snowRocksWeightsTrue.get("VBESnowRocksHilly")), Type.COLD, Type.DRY, Type.PLAINS, Type.SPARSE, Type.WASTELAND, Type.HILLS);
			initBiome(VBE_SNOW_ROCKS_HIGH, "Snow Rocks Plateau", BiomeType.ICY, Integer.parseInt(snowRocksWeightsTrue.get("VBESnowRocksPlateau")), Type.COLD, Type.DRY, Type.PLAINS, Type.SPARSE, Type.WASTELAND, Type.HILLS);
			
			//Sand Dunes
			initBiome(VBE_DUNES, "Sand Dunes", BiomeType.DESERT, Integer.parseInt(dunesWeightsTrue.get("VBEDunes")), Type.DRY, Type.HILLS, Type.HOT, Type.SPARSE);
			initBiome(VBE_DUNES_RED, "Red Sand Dunes", BiomeType.DESERT, Integer.parseInt(dunesWeightsTrue.get("VBEDunesRed")), Type.DRY, Type.HILLS, Type.HOT, Type.SPARSE);
			initBiome(VBE_DUNES_MUTATED, "Sand Dunes M", BiomeType.DESERT, Integer.parseInt(dunesWeightsTrue.get("VBEDunesMutated")), Type.DRY, Type.HILLS, Type.HOT, Type.SPARSE);
			initBiome(VBE_DUNES_OASIS, "Sand Dunes Oasis", BiomeType.DESERT, Integer.parseInt(dunesWeightsTrue.get("VBEDunesOasis")), true, Type.DRY, Type.HILLS, Type.HOT, Type.SPARSE);
			
			//Wetlands
			initBiome(VBE_FEN, "Grassy Fen", BiomeType.WARM, 5, Type.LUSH, Type.SPARSE, Type.SWAMP, Type.WET);
			initBiome(VBE_MARSH, "Grassy Marshland", BiomeType.WARM, 5, Type.SPARSE, Type.SWAMP, Type.WET);
			
			//Reef
			initBiome(VBE_REEF, "Archipelago", BiomeType.WARM, Integer.parseInt(m2WeightsTrue.get("VBEArchipelago")), Type.OCEAN);
		} else {
			//White Woods
			registerImpossibleBiome(VBE_CHRISTMAS_WOODS, "White Woods", BiomeType.ICY, Integer.parseInt(xmasWeightsTrue.get("VBEChristmasWoods")), Type.COLD, Type.CONIFEROUS, Type.DENSE, Type.FOREST, Type.MAGICAL, Type.SNOWY);
			registerImpossibleBiome(VBE_CHRISTMAS_WOODS_OAK, "White Oaks", BiomeType.ICY, Integer.parseInt(xmasWeightsTrue.get("VBEChristmasWoodsOak")), true, Type.COLD, Type.DENSE, Type.FOREST, Type.MAGICAL, Type.SNOWY);
			registerImpossibleBiome(VBE_CHRISTMAS_WOODS_HILLS, "White Woods Hills", BiomeType.ICY, Integer.parseInt(xmasWeightsTrue.get("VBEChristmasWoodsHills")), Type.COLD, Type.CONIFEROUS, Type.DENSE, Type.FOREST, Type.HILLS, Type.MAGICAL, Type.SNOWY);
			registerImpossibleBiome(VBE_CHRISTMAS_WOODS_HILLS_OAK, "White Oaks Hills", BiomeType.ICY, Integer.parseInt(xmasWeightsTrue.get("VBEChristmasWoodsHillsOak")), Type.COLD, Type.DENSE, Type.FOREST, Type.HILLS, Type.MAGICAL, Type.SNOWY);
			
			registerImpossibleBiome(VBE_SNOW_ROCKS, "Snow Rocks", BiomeType.ICY, Integer.parseInt(snowRocksWeightsTrue.get("VBESnowRocks")), Type.COLD, Type.DRY, Type.PLAINS, Type.SPARSE, Type.WASTELAND);
			registerImpossibleBiome(VBE_SNOW_ROCKS_HILLY, "Snow Rocks Mountains", BiomeType.ICY, Integer.parseInt(snowRocksWeightsTrue.get("VBESnowRocksHilly")), Type.COLD, Type.DRY, Type.PLAINS, Type.SPARSE, Type.WASTELAND, Type.HILLS);
			registerImpossibleBiome(VBE_SNOW_ROCKS_HIGH, "Snow Rocks Plateau", BiomeType.ICY, Integer.parseInt(snowRocksWeightsTrue.get("VBESnowRocksPlateau")), Type.COLD, Type.DRY, Type.PLAINS, Type.SPARSE, Type.WASTELAND, Type.HILLS);
			
			//Sand Dunes
			registerImpossibleBiome(VBE_DUNES, "Sand Dunes", BiomeType.DESERT, Integer.parseInt(dunesWeightsTrue.get("VBEDunes")), Type.DRY, Type.HILLS, Type.HOT, Type.SPARSE);
			registerImpossibleBiome(VBE_DUNES_RED, "Red Sand Dunes", BiomeType.DESERT, Integer.parseInt(dunesWeightsTrue.get("VBEDunesRed")), Type.DRY, Type.HILLS, Type.HOT, Type.SPARSE);
			registerImpossibleBiome(VBE_DUNES_MUTATED, "Sand Dunes M", BiomeType.DESERT, Integer.parseInt(dunesWeightsTrue.get("VBEDunesMutated")), Type.DRY, Type.HILLS, Type.HOT, Type.SPARSE);
			registerImpossibleBiome(VBE_DUNES_OASIS, "Sand Dunes Oasis", BiomeType.DESERT, Integer.parseInt(dunesWeightsTrue.get("VBEDunesOasis")), true, Type.DRY, Type.HILLS, Type.HOT, Type.SPARSE);
			
			//Wetlands
			registerImpossibleBiome(VBE_FEN, "Grassy Fen", BiomeType.WARM, 5, Type.LUSH, Type.SPARSE, Type.SWAMP, Type.WET);
			registerImpossibleBiome(VBE_MARSH, "Grassy Marshland", BiomeType.WARM, 5, Type.SPARSE, Type.SWAMP, Type.WET);
			
			//Reef
			registerImpossibleBiome(VBE_REEF, "Archipelago", BiomeType.WARM, Integer.parseInt(m2WeightsTrue.get("VBEArchipelago")), Type.OCEAN);
			
			System.out.println("Impossible biomes registered and generation disabled (1.3)");
		}
		if (Boolean.parseBoolean(biomesAllowTrue.get("MasterGenAlpha14"))) 
		{
			initRemoteBiome(VBE_GHOST_FOREST, "Ghost Forest", BiomeType.WARM, Integer.parseInt(m2WeightsTrue.get("VBEGhostForest")), Type.HOT, Type.WET, Type.DENSE, Type.JUNGLE, Type.RARE);
			initRemoteBiome(VBE_BARELANDS, "Barelands", BiomeType.COOL, Integer.parseInt(m2WeightsTrue.get("VBEBarelands")), Type.DEAD, Type.PLAINS, Type.RARE);
			
			initRemoteBiome(VBE_FORESTED_CANYON, "Forested Canyon Pillars", BiomeType.WARM, Integer.parseInt(bushWeightsTrue.get("VBEForestedPillars")), Type.DENSE, Type.FOREST, Type.MOUNTAIN, Type.MESA);
			initRemoteBiome(VBE_FORESTED_PLATEAU, "Forested Canyon Plateau", BiomeType.WARM, Integer.parseInt(bushWeightsTrue.get("VBEForestedPlateau")), Type.DENSE, Type.FOREST, Type.MOUNTAIN, Type.MESA);
			initRemoteBiome(VBE_FORESTED_PLATEAU_M, "Forested Canyon Plateau M", BiomeType.WARM, Integer.parseInt(bushWeightsTrue.get("VBEForestedPlateauM")), Type.DENSE, Type.FOREST, Type.MOUNTAIN, Type.MESA);
			initBiome(VBE_FORESTED_BUSHLAND, "Bush", BiomeType.WARM, Integer.parseInt(bushWeightsTrue.get("VBEForestedBushland")), Type.DENSE, Type.FOREST, Type.LUSH, Type.JUNGLE);
			initBiome(VBE_FORESTED_BUSHLAND_HILLS, "Bushland Hills", BiomeType.WARM, Integer.parseInt(bushWeightsTrue.get("VBEForestedBushlandHills")), Type.DENSE, Type.FOREST, Type.LUSH, Type.JUNGLE);
			
			initBiome(VBE_BRUSHLANDS, "Brushlands", BiomeType.WARM, Integer.parseInt(brushWeightsTrue.get("VBEBrushlands")), Type.DRY, Type.PLAINS);
			initBiome(VBE_BRUSHLANDS_DENSE, "Dense Brushlands", BiomeType.WARM, Integer.parseInt(brushWeightsTrue.get("VBEBrushlandsDense")), Type.DRY, Type.PLAINS);
			initBiome(VBE_BRUSHLANDS_DESERT, "Hot Brushlands", Main.isDDSSEdition ? BiomeType.WARM : BiomeType.DESERT, Integer.parseInt(brushWeightsTrue.get("VBEBrushlandsDesert")), Type.DRY, Type.HOT, Type.PLAINS);
			initBiome(VBE_BRUSHLANDS_HILLS, "Brushlands Hills", BiomeType.WARM, Integer.parseInt(brushWeightsTrue.get("VBEBrushlandsHills")), Type.DRY, Type.PLAINS, Type.HILLS);
			
			initBiome(VBE_REEF_STONY, "Stony Reef", BiomeType.WARM, Integer.parseInt(m2WeightsTrue.get("VBEReefStony")), Type.OCEAN);
			
			initBiome(VBE_LUSH_JUNGLE, "Tropical Jungle", BiomeType.WARM, Integer.parseInt(m2WeightsTrue.get("VBELushJungle")), Type.JUNGLE, Type.HOT, Type.WET, Type.DENSE);
			
		} else {
			registerImpossibleBiome(VBE_GHOST_FOREST, "Ghost Forest", BiomeType.WARM, Integer.parseInt(m2WeightsTrue.get("VBEGhostForest")), Type.HOT, Type.WET, Type.DENSE, Type.JUNGLE, Type.RARE);
			registerImpossibleBiome(VBE_BARELANDS, "Barelands", BiomeType.COOL, Integer.parseInt(m2WeightsTrue.get("VBEBarelands")), Type.DEAD, Type.PLAINS, Type.RARE);
			
			registerImpossibleBiome(VBE_FORESTED_CANYON, "Forested Canyon Pillars", BiomeType.WARM, Integer.parseInt(bushWeightsTrue.get("VBEForestedPillars")), Type.DENSE, Type.FOREST, Type.MOUNTAIN, Type.MESA);
			registerImpossibleBiome(VBE_FORESTED_PLATEAU, "Forested Canyon Plateau", BiomeType.WARM, Integer.parseInt(bushWeightsTrue.get("VBEForestedPlateau")), Type.DENSE, Type.FOREST, Type.MOUNTAIN, Type.MESA);
			registerImpossibleBiome(VBE_FORESTED_PLATEAU_M, "Forested Canyon Plateau M", BiomeType.WARM, Integer.parseInt(bushWeightsTrue.get("VBEForestedPlateauM")), Type.DENSE, Type.FOREST, Type.MOUNTAIN, Type.MESA);
			registerImpossibleBiome(VBE_FORESTED_BUSHLAND, "Bush", BiomeType.WARM, Integer.parseInt(bushWeightsTrue.get("VBEForestedBushland")), Type.DENSE, Type.FOREST, Type.LUSH, Type.JUNGLE);
			registerImpossibleBiome(VBE_FORESTED_BUSHLAND_HILLS, "Bushland Hills", BiomeType.WARM, Integer.parseInt(bushWeightsTrue.get("VBEForestedBushlandHills")), Type.DENSE, Type.FOREST, Type.LUSH, Type.JUNGLE);
			
			registerImpossibleBiome(VBE_BRUSHLANDS, "Brushlands", BiomeType.WARM, Integer.parseInt(brushWeightsTrue.get("VBEBrushlands")), Type.DRY, Type.PLAINS);
			registerImpossibleBiome(VBE_BRUSHLANDS_DENSE, "Dense Brushlands", BiomeType.WARM, Integer.parseInt(brushWeightsTrue.get("VBEBrushlandsDense")), Type.DRY, Type.PLAINS);
			registerImpossibleBiome(VBE_BRUSHLANDS_DESERT, "Hot Brushlands", Main.isDDSSEdition ? BiomeType.WARM : BiomeType.DESERT, Integer.parseInt(brushWeightsTrue.get("VBEBrushlandsDesert")), Type.DRY, Type.HOT, Type.PLAINS);
			registerImpossibleBiome(VBE_BRUSHLANDS_HILLS, "Brushlands Hills", BiomeType.WARM, Integer.parseInt(brushWeightsTrue.get("VBEBrushlandsHills")), Type.DRY, Type.PLAINS, Type.HILLS);
			
			registerImpossibleBiome(VBE_REEF_STONY, "Stony Reef", BiomeType.WARM, Integer.parseInt(m2WeightsTrue.get("VBEReefStony")), Type.OCEAN);
			
			registerImpossibleBiome(VBE_LUSH_JUNGLE, "Tropical Jungle", BiomeType.WARM, Integer.parseInt(m2WeightsTrue.get("VBELushJungle")), Type.JUNGLE, Type.HOT, Type.WET, Type.DENSE);
			
			System.out.println("Impossible biomes registered and generation disabled (1.4)");
		}
		
		System.out.println("Zoesteria Biomes has loaded module \"BIOME_EXPANSIONS\"");
		
	}
	
	private static void initBiome(Biome biome, String name, BiomeType biomeType, int weight, Type... types)
	{
		initBiome(biome, name, biomeType, weight, false, types);
	}
	private static void initRemoteBiome(Biome biome, String name, BiomeType biomeType, int weight, Type... types)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
		
		/*
		if (includesVillages)
		{
			BiomeManager.addVillageBiome(biome, true);
		}
		*/
	}
	private static void initBiome(Biome biome, String name, BiomeType biomeType, int weight, boolean includesVillages, Type... types)
	{
		
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
		BiomeManager.addSpawnBiome(biome);
		
		if (includesVillages)
		{
			BiomeManager.addVillageBiome(biome, true);
		}
	}
	
	private static void registerImpossibleBiome(Biome biome, String name, BiomeType biomeType, int weight, Type...types)
	{
		registerImpossibleBiome(biome, name, biomeType, weight, false, types);
	}
	private static void registerImpossibleBiome(Biome biome, String name, BiomeType biomeType, int weight, boolean includesVillages, Type...types)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		//BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
		BiomeManager.removeSpawnBiome(biome);
		
		if (includesVillages)
		{
			BiomeManager.addVillageBiome(biome, true);
		}
	}

}
