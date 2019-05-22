package valoeghese.valoeghesesbe.world.biomes.alpha4;

import java.util.Random;

import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.init.ModBlocks;

public class BiomeGrassyFen extends Biome
{
	
	private final boolean isMarshland = false;
	
	public BiomeGrassyFen(String name, float baseHeight, float heightVariation)
	{
		super(new BiomeProperties(name).setBaseHeight(baseHeight).setHeightVariation(heightVariation).setRainfall(1.3F).setTemperature(0.5F).setWaterColor(14745518).setBaseBiome("Grassy Fen"));
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 20, 2, 4));
		
		this.decorator.andesiteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), 5);
		this.decorator.grassPerChunk = 20;
		this.decorator.flowersPerChunk = 1;
		this.decorator.waterlilyPerChunk = 2;
	}
	
	@Override
	public int getSkyColorByTemp(float currentTemp)
	{
		return 0x95f0e8;
	}
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer primerIn, int x, int z, double noiseVal)
	{
		
		if (noiseVal > 2)
		{
			if (noiseVal > 3.6D)
			{
				this.topBlock = ModBlocks.SOIL_WET.getDefaultState();
			} else {
				this.topBlock = Blocks.GRASS.getDefaultState();
			}
			this.fillerBlock = ModBlocks.SOIL_WET.getDefaultState();
		} else {
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = Blocks.DIRT.getDefaultState();
		}
		
		double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);

        if (d0 > 0.0D)
        {
            int i = x & 15;
            int j = z & 15;

            for (int k = 255; k >= 0; --k)
            {
                if (primerIn.getBlockState(j, k, i).getMaterial() != Material.AIR)
                {
                    if (k == 62 && primerIn.getBlockState(j, k, i).getBlock() != Blocks.WATER)
                    {
                    	primerIn.setBlockState(j, k, i, WATER);
                    }

                    break;
                }
            }
        }
		
		this.generateBiomeTerrain(worldIn, rand, primerIn, x, z, noiseVal);
		
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return new WorldGenShrub(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		return 0x1fa559;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos)
	{
		return 0x1fa559;
	}

}
