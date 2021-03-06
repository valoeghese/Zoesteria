package valoeghese.valoeghesesbe.world.biomes.alpha3.mini;

import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSand;
import valoeghese.valoeghesesbe.world.trees.WorldGenBrush;
import valoeghese.valoeghesesbe.world.worldtype.ILakeRemover;

public class BiomeAustralianOutback extends Biome implements ILakeRemover
{
	
	public BiomeAustralianOutback(String name)
	{
		
		super(new BiomeProperties(name).setBaseHeight(0.18F).setHeightVariation(0.05F).setRainDisabled().setRainfall(0.0F).setTemperature(2.0F));
		
		this.decorator.flowersPerChunk = -999;
		this.decorator.mushroomsPerChunk = -999;
		this.decorator.reedsPerChunk = -999;
		this.decorator.treesPerChunk = 1;
		
		this.topBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
		this.decorator.sandGen = new WorldGenSand(Blocks.RED_SANDSTONE, 7);
		this.decorator.generateFalls = false;
		this.decorator.grassPerChunk = 3;
		this.decorator.deadBushPerChunk = 1;
		this.decorator.cactiPerChunk = 2;
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 1, 1, 2));
		
	}
	
	@Override
	public float getSpawningChance()
    {
        return 0.001F;
    }
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return new WorldGenBrush();
	}
	
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
	{
		
		this.fillerBlock = Blocks.HARDENED_CLAY.getDefaultState();
		
		boolean flag = rand.nextInt(3) == 0;
		boolean flag1 = rand.nextInt(3) == 0;
		if (flag || flag1)
		{
			this.topBlock = Blocks.GRASS.getDefaultState();
		} else {
			
			if (noiseVal > 5)
			{
				this.topBlock = Blocks.HARDENED_CLAY.getDefaultState();
			} else {
				this.topBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
			}
		}
		
		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
		
	}

}
