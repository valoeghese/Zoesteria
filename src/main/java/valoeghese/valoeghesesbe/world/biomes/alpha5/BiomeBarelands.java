package valoeghese.valoeghesesbe.world.biomes.alpha5;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.world.worldtype.ILakeRemover;

public class BiomeBarelands extends Biome implements ILakeRemover
{

	public BiomeBarelands()
	{
		super(new BiomeProperties("Barelands").setBaseHeight(0.15F).setHeightVariation(0F).setWaterColor(0x3a3a3a));
		
		this.decorator.flowersPerChunk = -999;
		this.decorator.grassPerChunk = -999;
		this.decorator.generateFalls = false;
		this.topBlock = ModBlocks.SOIL_WASTE.getDefaultState(); this.fillerBlock = ModBlocks.SOIL_WASTE.getDefaultState();
		
		this.spawnableCreatureList.clear();
		
	}
	
	@Override
	public int getSkyColorByTemp(float temp)
	{
		return 0x9bc6c0;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return new WorldGenShrub(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState());
	}
	
	@Override
	public int getFoliageColorAtPos(BlockPos pos)
	{
		return 0x5b5e5b;
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos)
	{
		return 0x5b5e5b;
	}
	
	
}
