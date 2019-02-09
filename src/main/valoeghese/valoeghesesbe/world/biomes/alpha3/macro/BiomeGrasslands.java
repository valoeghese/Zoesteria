package valoeghese.valoeghesesbe.world.biomes.alpha3.macro;

import java.util.Random;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.util.OpenSimplexNoise;
import valoeghese.valoeghesesbe.world.gen.CustomDecorator;
import valoeghese.valoeghesesbe.world.structures.WorldGenMoorlandLake;

public class BiomeGrasslands extends Biome
{
	public static enum Variant
	{
		LOWLANDS,
		MOORLANDS,
		CHAPPARAL,
		HIGHLANDS;

		public boolean isLowland()
		{
			if (this.equals(LOWLANDS) || this.equals(CHAPPARAL))
			{
				return true;
			} else {
				return false;
			}
		}
	}

	private final Variant type;
	protected static final WorldGenBlockBlob MOORLANDS_ROCK = new WorldGenBlockBlob(Blocks.STONE, 1);
	
	/*
	@Override
	public BiomeDecorator createBiomeDecorator()
	{
		//if (this.type == Variant.MOORLANDS) return new CustomDecorator().setLakeChances(150, 20);
		else return super.createBiomeDecorator();
	}
	*/
	
	public BiomeGrasslands(BiomeProperties properties, Variant plainsType)
	{
		super(properties);
		
		this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = Blocks.DIRT.getDefaultState();

		this.decorator.generateFalls = true;

		this.type = plainsType;

		if (this.type == Variant.CHAPPARAL)
		{
			this.decorator.treesPerChunk = 5;
		} else {
			this.decorator.treesPerChunk = 0;
		}

		if (this.type == Variant.MOORLANDS)
		{

			this.decorator.flowersPerChunk = 9;
			this.decorator.grassPerChunk = 30;
			this.decorator.reedsPerChunk = 0;
			this.decorator.extraTreeChance = 0.001F;
			this.decorator.waterlilyPerChunk = 1;

		} else {

			if (this.type == Variant.HIGHLANDS)
			{

				this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 6, 2, 3));
				this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 1));

				this.decorator.grassPerChunk = 10;
				this.decorator.flowersPerChunk = 0;
			}
			else if (this.type == Variant.CHAPPARAL)
			{
				this.decorator.flowersPerChunk = 6;
				this.decorator.grassPerChunk = 8;
			} else {
				this.decorator.flowersPerChunk = 2;
				this.decorator.grassPerChunk = 2;
			}
			this.decorator.reedsPerChunk = 1;
			this.decorator.extraTreeChance = 0.0F;

		}

	}

	@SuppressWarnings("unused")
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer primerIn, int x, int z, double noiseVal)
	{
		if (this.type.isLowland() || !Main.newGenerationBoolean)
			super.generateBiomeTerrain(worldIn, rand, primerIn, x, z, noiseVal);
		else
			this.generateNewHighlandTerrain(worldIn, rand, primerIn, x, z, noiseVal);
	}

	public void generateNewHighlandTerrain(World worldIn, Random rand, ChunkPrimer primerIn, int x, int z, double noiseVal)
	{
		OpenSimplexNoise noise = new OpenSimplexNoise(worldIn.getSeed());

		final double scaleFactor = 20D;

		int x1 = x & 15;
		int z1 = z & 15;

		double height = this.getPrimerHeight(primerIn, x1, z1);
		double x2 = (double)x; double z2 = (double)z;

		if (this.getPrimerHeight(primerIn, x1, z1) > 100)
		{
			height = 0.25D * ((2 * height) + (100 + 10*(noise.eval(x2 / scaleFactor, z2 / scaleFactor))));
		}

		for (int y = 255; y >= 0; --y)
		{
			if (rand.nextInt(5) >= y) primerIn.setBlockState(x1, y, z1, BEDROCK);
			else
			{
				if (y > height)
					primerIn.setBlockState(x1, y, z1, AIR);
				else if (y == height)
					primerIn.setBlockState(x1, y, z1, topBlock);
				else if (y > height - 5)
					primerIn.setBlockState(x1, y, z1, fillerBlock);
				else
					primerIn.setBlockState(x1, y, z1, rand.nextInt(500) == 0 ? WATER : STONE);
			}
		}
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{

		if ((this.type == Variant.MOORLANDS))
		{
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ROCK))
			{
				int i = rand.nextInt(3);
				
				for (int j = 0; j < i; ++j)
				{
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(16) + 8;
					
					BlockPos blockpos = this.relativeHeight(worldIn, pos, -2);
					
					MOORLANDS_ROCK.generate(worldIn, rand, blockpos.add(k, 0, l));
				}
				
			}
		}
		if (!this.type.isLowland())
		{
			DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS) && (rand.nextInt(3) == 0))
			{
				for (int i1 = 0; i1 < 7; ++i1)
				{
					int j1 = rand.nextInt(16) + 8;
					int k1 = rand.nextInt(16) + 8;
					int l1 = rand.nextInt(worldIn.getHeight(pos.add(j1, 0, k1)).getY() + 32);
					DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j1, l1, k1));
				}
			}
		}
		if (this.type == Variant.CHAPPARAL)
		{
			DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.ROSE);

			if (rand.nextBoolean()) DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.SYRINGA);

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS) && (rand.nextInt(3) == 0))
			{
				for (int i1 = 0; i1 < 7; ++i1)
				{
					int j1 = rand.nextInt(16) + 8;
					int k1 = rand.nextInt(16) + 8;
					int l1 = rand.nextInt(worldIn.getHeight(pos.add(j1, 0, k1)).getY() + 32);
					DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j1, l1, k1));
				}
			}
		}

		super.decorate(worldIn, rand, pos);
	}	

	@Override
	public void addDefaultFlowers()
	{
		BlockFlower red = net.minecraft.init.Blocks.RED_FLOWER;
		BlockFlower yel = net.minecraft.init.Blocks.YELLOW_FLOWER;

		if (this.type == Variant.MOORLANDS)
		{
			addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.ORANGE_TULIP), 3);
			addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.RED_TULIP), 3);
			addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.PINK_TULIP), 3);
			addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.WHITE_TULIP), 3);
			addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.BLUE_ORCHID), 10);
			addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.HOUSTONIA), 20);

		} else {

			addFlower(yel.getDefaultState().withProperty(yel.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION), 30);
			addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.POPPY), 20);
			addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.HOUSTONIA), 20);
			addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.OXEYE_DAISY), 20);

		}

	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		if (this.type == Variant.MOORLANDS || this.type == Variant.CHAPPARAL)
		{

			return new WorldGenShrub(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState());

		} else {

			return new WorldGenShrub(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState());

		}
	}

	private BlockPos relativeHeight(World world, BlockPos loc, int shift)
	{
		return new BlockPos(loc.getX(), world.getHeight(loc.getX(), loc.getZ()) + shift, loc.getZ());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos)
	{
		if (this.type == Variant.MOORLANDS || this.type == Variant.CHAPPARAL)
		{
			return super.getFoliageColorAtPos(pos);
		} else {
			return 6351458;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		if (this.type == Variant.MOORLANDS || this.type == Variant.CHAPPARAL)
		{
			return super.getGrassColorAtPos(pos);
		} else {
			return 7529844;
		}
	}

	/**
	 * 
	 * Still a useful method.
	 * 
	 *
	 * @param pIn primerIn
	 * @param xA15 x & 15
	 * @param zA15 z & 15
	 * @return
	 */
	public static int getPrimerHeight(ChunkPrimer pIn, int xA15, int zA15)
	{
		for (int j2 = 255; j2 >= 0; --j2)
		{
			if (pIn.getBlockState(xA15, j2, zA15) != Blocks.AIR) return j2;
		}

		return -1;
	}

	@Deprecated
	private static int sigmoidedHeight(double distanceToLowChunk, double bound1, double bound2)
	{
		final double a;
		final double b;
		if (bound1 > bound2)
		{
			a = bound2;
			b = bound1;
		} else {
			a = bound1;
			b = bound2;
		}

		return (int) (a + (OpenSimplexNoise.SigmoidFunctions.sigmoidScale(distanceToLowChunk, 16D, 10) * (b - a)));
	}

	@Deprecated
	private static int lowChunk(double value)
	{
		return (int) (16 * Math.floor((value / 16)));
	}

	@Deprecated
	private static int highChunk(double value)
	{
		return (int) (16 * Math.ceil((value / 16)));
	}
}
