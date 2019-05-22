package valoeghese.valoeghesesbe.world.biomes.alpha2;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.world.trees.WorldGenStrippedOak;
import valoeghese.valoeghesesbe.world.trees.oasispalm.WorldGenOasisPalm;
import valoeghese.valoeghesesbe.world.trees.oasispalm.WorldGenOasisPalm2;
import valoeghese.valoeghesesbe.world.worldtype.ILakeRemover;

public class BiomeWasteland extends Biome implements ILakeRemover
{
	
	private boolean isOasis;
	private boolean isTrees = false;
	
	protected static final WorldGenAbstractTree SWAMP = new WorldGenSwamp();
	private static final WorldGenAbstractTree OASIS = new WorldGenOasisPalm(false);
	private static final WorldGenAbstractTree OASIS2 = new WorldGenOasisPalm2();
	
	@Override
	public int getSkyColorByTemp(float temp)
	{
		if (isOasis) return super.getSkyColorByTemp(temp);
		else if (isTrees) return 0x8baab5;
		else return 0xeac072;
	}
	/*
	 * a name b heightvariation c baseheight d treesperchunk
	 */
	public BiomeWasteland(String p_20_a, float p_20_b, float p_20_c, int p_20_d)
	{
		super(new BiomeProperties(p_20_a).setHeightVariation(p_20_b).setBaseBiome("Wasteland Flats").setBaseHeight(p_20_c).setRainDisabled().setRainfall(0.05F).setTemperature(1.8F).setWaterColor(9783882));
		
		this.decorator.clayGen = new WorldGenMinable(ModBlocks.SOIL_WET.getDefaultState(), 10);
		this.decorator.clayPerChunk = 1;
		
		if (p_20_a == "Wasteland Flats Trees")
		{
			this.decorator.deadBushPerChunk = 1;
			this.isTrees = true;
		}
		this.decorator.extraTreeChance = (float)(p_20_d / 15);
		
		if (p_20_a == "Wasteland Flats Oasis")
		{
			
			this.isOasis = true;
			this.decorator.flowersPerChunk = 1;
			this.decorator.treesPerChunk = p_20_d;
			this.decorator.reedsPerChunk = 10;
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = ModBlocks.SOIL_WET.getDefaultState();
			
		} else {
			
			this.isOasis = false;
			this.decorator.flowersPerChunk = 0;
			if (p_20_a == "Wasteland Flats Trees")
			{
				this.decorator.treesPerChunk = p_20_d;
			} else {
				this.decorator.treesPerChunk = 0;
			}
			this.decorator.grassPerChunk = 0;
			this.decorator.reedsPerChunk = 0;
			this.topBlock = ModBlocks.SOIL_DRY.getDefaultState();
			this.fillerBlock = ModBlocks.SOIL_DRY.getDefaultState();
			
			this.spawnableCreatureList.clear();
			
			if(p_20_a == "Wasteland Flats Trees")
			{
				
				this.spawnableCreatureList.add(new SpawnListEntry(EntitySpider.class, 2, 1, 2));
				this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 1, 1, 3));
				
			}
			
		}
		
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		
		if (this.isOasis && rand.nextInt(2) == 0)
		{
			
			return new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)));
			
		} else if(this.isOasis) {
			
			if (rand.nextInt(4) == 0)
			{
				return (WorldGenAbstractTree)OASIS2;
			} else {
				return (WorldGenAbstractTree)OASIS;
			}
			
		} else if(!this.isOasis) {
			
			if (this.isTrees && rand.nextInt(3) == 0)
			{
				return (WorldGenAbstractTree)SWAMP;
			} else {
				return (WorldGenAbstractTree) new WorldGenStrippedOak(false);
			}
				
		}
		
		if (rand.nextInt(4) == 0)
		{
			return new WorldGenShrub(ModBlocks.SOIL_DRY.getDefaultState(), ModBlocks.SOIL_DRY.getDefaultState());
		}
		
		return new WorldGenShrub(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState());
		
	}
	
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
	{
		
		if (isOasis)
		{
			
			double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);

	        if (d0 > 0.1D)
	        {
	            int i = x & 15;
	            int j = z & 15;

	            for (int k = 255; k >= 0; --k)
	            {
	                if (chunkPrimerIn.getBlockState(j, k, i).getMaterial() != Material.AIR)
	                {
	                    if (k == 62 && chunkPrimerIn.getBlockState(j, k, i).getBlock() != Blocks.WATER)
	                    {
	                        chunkPrimerIn.setBlockState(j, k, i, WATER);
	                    }

	                    break;
	                }
	            }
	        }
			
		}
		
		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getGrassColorAtPos(BlockPos pos)
	{
		
		if (isOasis)
	    {
			return 0x00A037;
	    } else {
	    	
	    	return super.getGrassColorAtPos(pos);
	    }
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos)
	{
		
		if (isOasis)
	    {
			return 0x00A037;
	    } else {
	    	
	    	return super.getFoliageColorAtPos(pos);
	    }
		
	}

}
