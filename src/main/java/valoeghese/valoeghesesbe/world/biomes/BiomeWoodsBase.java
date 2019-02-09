package valoeghese.valoeghesesbe.world.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeWoodsBase extends Biome
{
	
	protected static final WorldGenAbstractTree SPRUCE_GENERATOR = new WorldGenTaiga2(false);
	
	public BiomeWoodsBase(String name, float HeightVariation, float Temperature, int TreesPChunk, float pRandom)
	{
		
		this(name, HeightVariation, Temperature, TreesPChunk, pRandom, 0.3F);
		
	}
	
	public BiomeWoodsBase(String name, float HeightVariation, float Temperature, int TreesPChunk, float pRandom, float BaseHeight)
	{
		
		super(new BiomeProperties(name).setHeightVariation(HeightVariation).setTemperature(Temperature).setRainfall(0.68F).setBaseHeight(BaseHeight).setBaseBiome("Low Woodlands"));
		
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.treesPerChunk = TreesPChunk;
		this.decorator.extraTreeChance = pRandom;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		
		return (WorldGenAbstractTree)SPRUCE_GENERATOR;
		
	}
	
}
