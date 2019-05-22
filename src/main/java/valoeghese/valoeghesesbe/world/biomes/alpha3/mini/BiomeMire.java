package valoeghese.valoeghesesbe.world.biomes.alpha3.mini;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTNT;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.world.trees.WorldGenStrippedOak;

public class BiomeMire extends Biome
{
	
	private static final WorldGenAbstractTree MIRE_FEATURE = new WorldGenStrippedOak(false);
	
	public BiomeMire()
	{
		super(new BiomeProperties("Mire").setBaseHeight(-0.15F).setHeightVariation(0.14F).setRainfall(1.0F).setTemperature(0.3F));
		
		this.decorator.flowersPerChunk = 1;
		this.decorator.clayPerChunk = 6;
		this.decorator.treesPerChunk = 6;
		this.decorator.extraTreeChance = 0.01F;
		this.decorator.mushroomsPerChunk = 4;
		this.decorator.grassPerChunk = -999;
		this.decorator.sandPatchesPerChunk = -999;
		
		this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
	}
	
	@Override
	public int getSkyColorByTemp(float temp)
	{
		return 0xacd0e0;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return MIRE_FEATURE;
	}
	
	@Override
	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        if (rand.nextBoolean())
        {
        	return BlockFlower.EnumFlowerType.BLUE_ORCHID;
        } else {
        	return BlockFlower.EnumFlowerType.HOUSTONIA;
        }
    }
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer primer, int x, int z, double noiseVal)
	{
		if (noiseVal > 2.5D)
		{
			this.topBlock = ModBlocks.SOIL_WET.getDefaultState();
		} else if (noiseVal > 0.5D){
			this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
		} else if (noiseVal > -4D){
			this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
		} else {
			this.topBlock = Blocks.GRASS.getDefaultState();
		}
		
		super.generateBiomeTerrain(worldIn, rand, primer, x, z, noiseVal);
	}

}
