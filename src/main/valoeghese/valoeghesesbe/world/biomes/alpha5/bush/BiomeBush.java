package valoeghese.valoeghesesbe.world.biomes.alpha5.bush;

import java.util.Random;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import valoeghese.valoeghesesbe.world.biomes.alpha4.BiomeAssets;
import valoeghese.valoeghesesbe.world.trees.WorldGenManukaTree;

public class BiomeBush extends Biome implements BiomeAssets
{

	public BiomeBush(BiomeProperties properties) {
		super(properties);
		
		this.decorator.treesPerChunk = 20;
		this.decorator.grassPerChunk = 22;
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
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return rand.nextInt(6) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }
}
