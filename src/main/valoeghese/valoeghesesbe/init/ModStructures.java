package valoeghese.valoeghesesbe.init;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import api.valoeghese.valoeghesesbe.BushGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeBeach;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.biome.BiomeRiver;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.blocks.BlockSmallBush;
import valoeghese.valoeghesesbe.config.ConfigHandler;
import valoeghese.valoeghesesbe.util.MCWorld;
import valoeghese.valoeghesesbe.util.handlers.RegistryHandler;
import valoeghese.valoeghesesbe.world.biomes.BiomeOakHighVillage;
import valoeghese.valoeghesesbe.world.biomes.BiomeOakWHills;
import valoeghese.valoeghesesbe.world.biomes.BiomeOakWoodsBase;
import valoeghese.valoeghesesbe.world.biomes.BiomeWoodsBase;
import valoeghese.valoeghesesbe.world.biomes.alpha3.mini.BiomeAustralianOutback;
import valoeghese.valoeghesesbe.world.biomes.alpha4.BiomeReef;
import valoeghese.valoeghesesbe.world.gen.WorldGenFallenLog;
import valoeghese.valoeghesesbe.world.structures.StructureBase;
import valoeghese.valoeghesesbe.world.trees.WorldGenModdedShrub;
import valoeghese.valoeghesesbe.world.trees.WorldGenOceanPalm;
import valoeghese.valoeghesesbe.world.trees.newzealand.WorldGenPohutukawa1;
import valoeghese.valoeghesesbe.world.trees.newzealand.WorldGenPohutukawa2;

public class ModStructures implements IWorldGenerator
{
	
	//BEGIN CONFIGSTRUCTURE
	private static FileReader staticStructureReader;
	private static FileReader staticStructure1Reader;
	private static FileReader staticStructure2Reader;
	
	private static char[] readerStructureChars = new char[Short.MAX_VALUE];
	private static ConfigHandler structureWeightConfig;
	private static Map<String, String> structureWeightsTrue;
	
	private static char[] readerStructure1Chars = new char[Short.MAX_VALUE];
	private static ConfigHandler structure1WeightConfig;
	private static Map<String, String> structure1WeightsTrue;
	
	private static char[] readerStructure2Chars = new char[Short.MAX_VALUE];
	private static ConfigHandler structure2WeightConfig;
	private static Map<String, String> structure2WeightsTrue;

	public static final StructureBase ULURU = new StructureBase("uluru");
	
	//ships
	public static final StructureBase SHIP1 = new StructureBase("v_boat_1");
	public static final StructureBase SHIP2 = new StructureBase("v_boat_2");
	public static final StructureBase SHIP3 = new StructureBase("v_boat_3");
	public static final StructureBase SHIP4 = new StructureBase("v_boat_4");
	public static final StructureBase SHIP5 = new StructureBase("v_boat_5");
	
	static
	{
		try
		{

			if ( staticStructureReader == null )
			{

				staticStructureReader = new FileReader(RegistryHandler.getConfigStructureFile());

				staticStructureReader.read(readerStructureChars);
				structureWeightConfig = new ConfigHandler(readerStructureChars, false);

				structureWeightsTrue = structureWeightConfig.getContainer("ConfigStructure");

				System.out.println("Zoesteria has loaded Module STRUCTURE_CONFIG");
				staticStructureReader.close();

			}
			
			if ( staticStructure1Reader == null )
			{

				staticStructure1Reader = new FileReader(RegistryHandler.getConfigStructure1File());

				staticStructure1Reader.read(readerStructure1Chars);
				structure1WeightConfig = new ConfigHandler(readerStructure1Chars, false);

				structure1WeightsTrue = structure1WeightConfig.getContainer("StructureRarity");

				System.out.println("Zoesteria has loaded Module STRUCTURE_CONFIG_ULURU");
				staticStructure1Reader.close();

			}
			
			if ( staticStructure2Reader == null )
			{

				staticStructure2Reader = new FileReader(RegistryHandler.getConfigStructure2File());

				staticStructure2Reader.read(readerStructure2Chars);
				structure2WeightConfig = new ConfigHandler(readerStructure2Chars, false);

				structure2WeightsTrue = structure2WeightConfig.getContainer("StructureRarity");

				System.out.println("Zoesteria has loaded Module STRUCTURE_CONFIG_SHIPS");
				staticStructure2Reader.close();

			}

		} catch (IOException e) {

			e.printStackTrace();
			
		}
	}
	//END CONFIGSTRUCTURE
	
	//Shrub
	private final WorldGenerator DUNE_SHRUB = new WorldGenModdedShrub(ModBlocks.SHRUB_DUNE.getDefaultState(), true);
	private final WorldGenerator DUNE_SHRUB_2 = new WorldGenModdedShrub(ModBlocks.SHRUB_DUNE_LARGE.getDefaultState(), true);
	private final WorldGenerator POHUTUKAWA = new WorldGenPohutukawa1();
	private final WorldGenerator POHUTUKAWA_LARGE = new WorldGenPohutukawa2(false);
	private final WorldGenerator OCEAN_PALM = new WorldGenOceanPalm(false, 4);
	
	private final WorldGenerator BUSH_0 = new WorldGenModdedShrub(ModBlocks.SMALL_BUSH.getDefaultState().withProperty(BlockSmallBush.VARIANT, BlockSmallBush.EnumBushType.STANDARD));
	private final WorldGenerator BUSH_1 = new WorldGenModdedShrub(ModBlocks.SMALL_BUSH.getDefaultState().withProperty(BlockSmallBush.VARIANT, BlockSmallBush.EnumBushType.BERRY));
	private final WorldGenerator BUSH_2 = new WorldGenModdedShrub(ModBlocks.SMALL_BUSH.getDefaultState().withProperty(BlockSmallBush.VARIANT, BlockSmallBush.EnumBushType.CONIFEROUS));
	
	public enum EnumBiomeType
	{
		DEFAULT,
		WATER,
		AUSTRALIAN;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{

		if (Boolean.parseBoolean(structureWeightsTrue.get("MasterGenStructure")))
		{
			switch(world.provider.getDimension())
			{
			case 0:
				
				//uluru
				this.generateStructure(ULURU, world, random, chunkX, chunkZ, Integer.parseInt(structure1WeightsTrue.get("SandstoneUluruRarity")), EnumBiomeType.AUSTRALIAN, BiomeAustralianOutback.class);
				
				//ship
				  //shiplow
				this.generateStructure(SHIP1, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsArchipelagoRarity")), EnumBiomeType.WATER, BiomeReef.class);
				this.generateStructure(SHIP2, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsArchipelagoRarity")), EnumBiomeType.WATER, BiomeReef.class);
				this.generateStructure(SHIP3, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsArchipelagoRarity")), EnumBiomeType.WATER, BiomeReef.class);
				  //shiphigh
				this.generateStructure(SHIP4, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsArchipelagoRarity")), EnumBiomeType.WATER, BiomeReef.class);
				this.generateStructure(SHIP5, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsArchipelagoRarity")), EnumBiomeType.WATER, BiomeReef.class);
				  //shipocean
				this.generateStructure(SHIP1, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsOceanRarity")), EnumBiomeType.WATER, BiomeOcean.class);
				this.generateStructure(SHIP2, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsOceanRarity")), EnumBiomeType.WATER, BiomeOcean.class);
				this.generateStructure(SHIP3, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsOceanRarity")), EnumBiomeType.WATER, BiomeOcean.class);
				this.generateStructure(SHIP4, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsOceanRarity")), EnumBiomeType.WATER, BiomeOcean.class);
				this.generateStructure(SHIP5, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsOceanRarity")), EnumBiomeType.WATER, BiomeOcean.class);
				//shipriver
				this.generateStructure(SHIP1, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsRiverRarity")), EnumBiomeType.WATER, BiomeRiver.class);
				this.generateStructure(SHIP2, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsRiverRarity")), EnumBiomeType.WATER, BiomeRiver.class);
				this.generateStructure(SHIP3, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsRiverRarity")), EnumBiomeType.WATER, BiomeRiver.class);
				this.generateStructure(SHIP4, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsRiverRarity")), EnumBiomeType.WATER, BiomeRiver.class);
				this.generateStructure(SHIP5, world, random, chunkX, chunkZ, Integer.parseInt(structure2WeightsTrue.get("ShipsRiverRarity")), EnumBiomeType.WATER, BiomeRiver.class);
				break;
			default:
				break;
			}
		}
		
		WorldGenerator FALLEN_LOG_0 = new WorldGenFallenLog(Blocks.LOG.getDefaultState(), 3 + random.nextInt(3), false);
		WorldGenerator FALLEN_LOG_1 = new WorldGenFallenLog(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), 4 + random.nextInt(3), false);
		
		//Trees
		switch (world.provider.getDimension())
		{
		case 0:
			
			generateTree(DUNE_SHRUB, world, random, chunkX, chunkZ, 7D, -1, 0, BiomeBeach.class);
			generateTree(DUNE_SHRUB_2, world, random, chunkX, chunkZ, 4D, -1, 0, BiomeBeach.class);
			generateTree(FALLEN_LOG_0, world, random, chunkX, chunkZ, 0.4D, -1, 0, BiomeOakWoodsBase.class, BiomeOakWHills.class, BiomeOakHighVillage.class);
			generateTree(FALLEN_LOG_1, world, random, chunkX, chunkZ, 0.25D, -1, 0, BiomeWoodsBase.class);
			
			for (BushGen bg : Main.bushes)
			{
				bg.load();
				generateTree(new WorldGenModdedShrub(bg.gen), world, random, chunkX, chunkZ, 0.9D, -1, 0, bg.getAllowedBiomeClasses());
			}
			
			if (((chunkX * 16) > 1200) && ((chunkZ * 16) > 1500))
			{
				generateTree(POHUTUKAWA_LARGE, world, random, chunkX, chunkZ, 0.2D, -1, 0, BiomeBeach.class);
				generateTree(POHUTUKAWA, world, random, chunkX, chunkZ, 0.4D, -1, 0, BiomeBeach.class);
			}
			if (((chunkX * 16) > 1200) && ((chunkZ * 16) < 1520) && ((chunkZ * 16) > -1520))
			{
				generateTree(OCEAN_PALM, world, random, chunkX, chunkZ, 0.3D, -1, 0, BiomeBeach.class);
			}
			break;
		default:
			break;
		}
	}
	
	private void generateTree(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, double chancesToSpawn, int minHeight, int maxHeight, Class<?>...classes)
	{
		if (chancesToSpawn < 1)
		{
			if (rand.nextDouble() < chancesToSpawn) chancesToSpawn = 1;
			else chancesToSpawn = 0;
		}
		
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		int dHeight = maxHeight - minHeight;
		
		for (int i = 0; i < chancesToSpawn; ++i)
		{
			BlockPos pos = new BlockPos(chunkX * 16 + 10 + rand.nextInt(15), minHeight + rand.nextInt(dHeight), chunkZ * 16 + 10 + rand.nextInt(15));
			
			if (minHeight < 0) pos = world.getHeight(pos);
			
			Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
			if (classesList.contains(biome) || classes.length == 0) generator.generate(world, rand, pos);
		}
	}
	private void generateTree(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, double chancesToSpawn, int minHeight, int maxHeight, Set<Class> classes)
	{
		if (chancesToSpawn < 1)
		{
			if (rand.nextDouble() < chancesToSpawn) chancesToSpawn = 1;
			else chancesToSpawn = 0;
		}
		
		int dHeight = maxHeight - minHeight;
		
		for (int i = 0; i < chancesToSpawn; ++i)
		{
			BlockPos pos = new BlockPos(chunkX * 16 + 10 + rand.nextInt(15), minHeight + rand.nextInt(dHeight), chunkZ * 16 + 10 + rand.nextInt(15));
			
			if (minHeight < 0) pos = world.getHeight(pos);
			
			Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
			if (classes.contains(biome) || classes.size() == 0) generator.generate(world, rand, pos);
		}
	}
	private void generateStructure(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int rarity, EnumBiomeType btype, Class<?>...classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + rand.nextInt(15) + 8;
		int z = (chunkZ * 16) + rand.nextInt(15) + 8;
		
		int y;
		if (btype == EnumBiomeType.AUSTRALIAN)
		{
			y = MCWorld.calculateGenHeight(world, x, z, Blocks.SAND, Blocks.GRASS) - 3;
		} else if (btype == EnumBiomeType.WATER) {
			y = MCWorld.calculateGenHeight(world, x, z, Blocks.WATER);
		} else {
			y = MCWorld.calculateGenHeight(world, x, z, Blocks.GRASS) - 3;
		}
		
		BlockPos pos = new BlockPos(x, y, z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
	
		if ((world.getWorldType() != WorldType.FLAT))
		{
			if (classesList.contains(biome))
			{
				if (rand.nextInt(rarity) == 0)
				{
					generator.generate(world, rand, pos);
				}
			}
		}
	}
	private void generateTree(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, double chancesToSpawn, int minHeight, int maxHeight, Type...types)
	{
		if (chancesToSpawn < 1)
		{
			if (rand.nextDouble() < chancesToSpawn) chancesToSpawn = 1;
			else chancesToSpawn = 0;
		}
		
		ArrayList<Type> classesList = new ArrayList<Type>(Arrays.asList(types));
		int dHeight = maxHeight - minHeight;
		
		for (int i = 0; i < chancesToSpawn; ++i)
		{
			BlockPos pos = new BlockPos(chunkX * 16 + 10 + rand.nextInt(15), minHeight + rand.nextInt(dHeight), chunkZ * 16 + 10 + rand.nextInt(15));
			
			if (minHeight < 0) pos = world.getHeight(pos);
			
			Set<Type> typeset = BiomeDictionary.getTypes(world.provider.getBiomeForCoords(pos));
			
			boolean canGenerateInBiome = false;
			for (Type t: typeset)
			{
				for (Type t1 : types)
				{
					if (t == t1)
						canGenerateInBiome = true;
					break;
				}
				if (canGenerateInBiome) break;
			}
			
			if (canGenerateInBiome) generator.generate(world, rand, pos);
		}
	}
	
	
	/**
	 * 
	 * @deprecated
	 * 
	 * @param world
	 * @param x
	 * @param z
	 * @param topBlocks
	 * @return Y of first instance of a block in topBlocks found going down from the world height
	 */
	private static int calculateGenHeight(World world, int x, int z, Block...topBlocks)
	{
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = false;
			for (Block topBlock : topBlocks)
			{
				foundGround = block == topBlock;
				if (foundGround)
				{
					break;
				}
			}
		}
		
		return y;
	}

}
