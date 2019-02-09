package valoeghese.valoeghesesbe.world.biomes.alpha3;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import valoeghese.valoeghesesbe.world.biomes.biomeutil.IBiomeFog;
import valoeghese.valoeghesesbe.world.trees.WorldGenLargeTree;

public class BiomeOakTallWoodlands extends Biome
{
	
	public BiomeOakTallWoodlands(String name, float HeightVariation, float Temperature, int TreesPChunk, float pRandom)
	{
		
		this(name, HeightVariation, Temperature, TreesPChunk, pRandom, 0.3F);
		
	}
	
	public BiomeOakTallWoodlands(String name, float HeightVariation, float Temperature, int TreesPChunk, float pRandom, float BaseHeight)
	{
		
		super( new BiomeProperties(name).setHeightVariation(HeightVariation).setTemperature(Temperature).setRainfall(0.68F).setBaseHeight(BaseHeight) );
		
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.treesPerChunk = TreesPChunk;
		this.decorator.extraTreeChance = pRandom;
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 1, 3, 8));
	}
	
	/**
	 * Alpha 1.2.3 +
	 */
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{	
		switch(rand.nextInt(21))
		{
		case 0:
			return BIG_TREE_FEATURE;
		default:
			return (WorldGenAbstractTree)new WorldGenLargeTree();
		}
	}
	
}
