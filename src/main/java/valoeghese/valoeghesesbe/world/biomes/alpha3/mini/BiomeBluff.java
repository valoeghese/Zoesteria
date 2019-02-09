package valoeghese.valoeghesesbe.world.biomes.alpha3.mini;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import valoeghese.valoeghesesbe.world.trees.WorldGenBluffPine;

public class BiomeBluff extends Biome
{
	
	private static WorldGenAbstractTree bluffPine = new WorldGenBluffPine();
	
	public BiomeBluff()
	{
		super(new BiomeProperties("Bluff").setBaseHeight(0.9F).setHeightVariation(1.7F).setRainfall(0.6F).setTemperature(0.3F));
		
		this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = Blocks.STONE.getDefaultState();
		
		this.decorator.generateFalls = false;
		this.decorator.treesPerChunk = 2;
	}
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer primer, int x, int z, double noiseVal)
	{
		if ( z < 0 )
		{
			
			if (noiseVal > 2 && noiseVal < 2.8)
			{
				
				this.topBlock = Blocks.COBBLESTONE.getDefaultState();
				
			} else {
				
				this.topBlock = Blocks.GRASS.getDefaultState();
				
			}
			
		} else {
			
			if (noiseVal > 2 && noiseVal < 2.6)
			{
				
				this.topBlock = Blocks.COBBLESTONE.getDefaultState();
				
			} else {
				
				this.topBlock = Blocks.GRASS.getDefaultState();
				
			}
			
			if (rand.nextInt(3) == 0)
			{
				if (noiseVal > 2 && noiseVal < 2.7)
				{
					this.topBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
				}
			}
			
		}
		super.generateBiomeTerrain(worldIn, rand, primer, x, z, noiseVal);
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return bluffPine;
	}

}
