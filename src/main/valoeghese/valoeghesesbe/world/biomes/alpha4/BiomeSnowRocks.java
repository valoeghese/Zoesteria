package valoeghese.valoeghesesbe.world.biomes.alpha4;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;

public class BiomeSnowRocks extends Biome implements BiomeAssets
{

	public BiomeSnowRocks(Biome.BiomeProperties properties)
	{
		super(properties);
		
		this.decorator.treesPerChunk = -999;
		this.decorator.extraTreeChance = -999F;
		this.decorator.grassPerChunk = -999;
	}
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		
		int i = rand.nextInt(3);
		
		if ((net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ROCK)))
		{
			for (int j = 0; j < i; ++j)
			{

				int k = rand.nextInt(16) + 8;
				int l = rand.nextInt(16) + 8;

				BlockPos blockpos = this.relativeHeight(worldIn, pos, -2);
				if (rand.nextInt(3) == 0)
				{
					STONE_ROCK_LARGE.generate(worldIn, rand, blockpos.add(0, -1, 0));
				} else {
					STONE_ROCK.generate(worldIn, rand, blockpos);
				}

			}
		}
		
		super.decorate(worldIn, rand, pos);
	}
	
	private BlockPos relativeHeight(World world, BlockPos loc, int shift)
	{
		return new BlockPos(loc.getX(), world.getHeight(loc.getX(), loc.getZ()) + shift, loc.getZ());
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random randIn)
	{
		return new WorldGenShrub(Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState());
	}
	
}
