package valoeghese.valoeghesesbe.world.biomes.alpha5.bush;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import valoeghese.valoeghesesbe.world.biomes.alpha4.BiomeAssets;
import valoeghese.valoeghesesbe.world.trees.WorldGenManukaTree;

public class BiomeBushCanyon extends Biome implements BiomeAssets
{
	private long worldSeed;
	private NoiseGeneratorPerlin pillarNoise;
	private NoiseGeneratorPerlin pillarRoofNoise;
	private NoiseGeneratorPerlin clayBandsOffsetNoise;

	public BiomeBushCanyon(BiomeProperties properties)
	{
		super(properties);
		
		this.decorator.treesPerChunk = 20;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		if (rand.nextInt(4) == 0)
		{
			return new WorldGenShrub(Blocks.LOG.getDefaultState(), BUSH_LEAVES);
		} else {
			return new WorldGenManukaTree();
		}
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
	{
		if (this.pillarNoise == null || this.pillarRoofNoise == null || this.worldSeed != worldIn.getSeed())
		{
			Random random = new Random(this.worldSeed);
			this.pillarNoise = new NoiseGeneratorPerlin(random, 4);
			this.pillarRoofNoise = new NoiseGeneratorPerlin(random, 1);
		}

		this.worldSeed = worldIn.getSeed();
		double d4 = 0.0D;
		int i = (x & -16) + (z & 15);
		int j = (z & -16) + (x & 15);
		double d0 = Math.min(Math.abs(noiseVal), this.pillarNoise.getValue((double)i * 0.25D, (double)j * 0.25D));

		if (d0 > 0.0D)
		{
			double d1 = 0.001953125D;
			double d2 = Math.abs(this.pillarRoofNoise.getValue((double)i * 0.001953125D, (double)j * 0.001953125D));
			d4 = d0 * d0 * 2.5D;
			double d3 = Math.ceil(d2 * 50.0D) + 14.0D;

			if (d4 > d3)
			{
				d4 = d3;
			}

			d4 = d4 + 64.0D;
		}

		int k1 = x & 15;
		int l1 = z & 15;
		int i2 = worldIn.getSeaLevel();
		IBlockState iblockstate = ROCK;
		IBlockState iblockstate3 = this.fillerBlock;
		int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		boolean flag = Math.cos(noiseVal / 3.0D * Math.PI) > 0.0D;
		int l = -1;
		boolean flag1 = false;
		int i1 = 0;

		for (int j1 = 255; j1 >= 0; --j1)
		{
			if (chunkPrimerIn.getBlockState(l1, j1, k1).getMaterial() == Material.AIR && j1 < (int)d4)
			{
				chunkPrimerIn.setBlockState(l1, j1, k1, ROCK);
			}

			if (j1 <= rand.nextInt(5))
			{
				chunkPrimerIn.setBlockState(l1, j1, k1, BEDROCK);
			}
			else
			{
				IBlockState iblockstate1 = chunkPrimerIn.getBlockState(l1, j1, k1);

				if (iblockstate1.getMaterial() == Material.AIR)
				{
					l = -1;
				}
				else if (iblockstate1.getBlock() == Blocks.STONE)
				{
					if (l == -1)
					{
						flag1 = false;

						if (k <= 0)
						{
							iblockstate = AIR;
							iblockstate3 = ROCK;
						}
						else if (j1 >= i2 - 4 && j1 <= i2 + 1)
						{
							iblockstate = ROCK;
							iblockstate3 = this.fillerBlock;
						}

						l = k + Math.max(0, j1 - i2);

						if (j1 >= i2 - 1)
						{
							if (j1 > 86 + k * 2)
							{
								if (flag)
								{
									chunkPrimerIn.setBlockState(l1, j1, k1, COARSE_DIRT);
								}
								else
								{
									chunkPrimerIn.setBlockState(l1, j1, k1, GRASS);
								}
							}
							else if (j1 > i2 + 3 + k)
							{
								IBlockState iblockstate2;

								if (j1 >= 64 && j1 <= 127)
								{
									if (rand.nextBoolean()) 
									{
										iblockstate2 = GRASS;
									} else {
										iblockstate2 = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
									}
								}
								else
								{
									iblockstate2 = ROCK;
								}

								chunkPrimerIn.setBlockState(l1, j1, k1, iblockstate2);
							}
							else
							{
								chunkPrimerIn.setBlockState(l1, j1, k1, this.topBlock);
								flag1 = true;
							}
						}
						else
						{
							chunkPrimerIn.setBlockState(l1, j1, k1, iblockstate3);
						}
					}
					else if (l > 0)
					{
						--l;
						chunkPrimerIn.setBlockState(l1, j1, k1, ROCK);
					}

					++i1;
				}
			}
		}
	}
}
