package valoeghese.valoeghesesbe.world.biomes.alpha5;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.world.trees.WorldGenOceanPalm;

public class BiomeReefStony extends Biome
{

	public BiomeReefStony()
	{
		super(new Biome.BiomeProperties("Stony Reef").setBaseBiome("ocean").setBaseHeight(-0.55F).setHeightVariation(0.08F).setRainfall(1.5F));
		
		this.decorator.treesPerChunk = 1;
		this.decorator.grassPerChunk = 5;
        this.decorator.flowersPerChunk = 1;
        
        this.topBlock = Blocks.STONE.getDefaultState();
        this.fillerBlock = Blocks.STONE.getDefaultState();
    }
	
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
	{
		if (noiseVal < 1.5D)
		{
			this.topBlock = Blocks.STONE.getDefaultState();
		} else {
			this.topBlock = Blocks.SAND.getDefaultState();
		}
		
		super.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return new WorldGenOceanPalm();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos)
	{
		return 0x2aad13;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		//return 0x30dd53;
		return 0x5dd834;
	}

}
