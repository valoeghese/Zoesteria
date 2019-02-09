package valoeghese.valoeghesesbe.world.biomes.alpha4;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeChristmasForest extends Biome implements BiomeAssets
{
	
//	private static final IBlockState COLD_BUSH_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
//  private static final IBlockState BUSH_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    
	private final boolean isOak;
	public BiomeChristmasForest(BiomeProperties properties, boolean isOak)
	{
		super(properties);
		
		this.isOak = isOak;
		
		this.decorator.treesPerChunk = 6;
		this.decorator.extraTreeChance = 0.1F;
		this.decorator.flowersPerChunk = 2;
		this.decorator.grassPerChunk = 1;
		
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		if ((this.isOak && rand.nextInt(10) > 0) || rand.nextInt(12) == 0)
		{
			return (WorldGenAbstractTree)(rand.nextInt(10) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE);
		} else if ((rand.nextInt(6) > 0) && !this.isOak){
			return (WorldGenAbstractTree) new WorldGenTaiga2(false);
		} else {
			return new WorldGenShrub(COLD_BUSH_LOG, BUSH_LEAVES);
		}
	}
	
}
