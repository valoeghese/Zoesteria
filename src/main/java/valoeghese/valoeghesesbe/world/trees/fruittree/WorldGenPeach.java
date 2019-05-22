package valoeghese.valoeghesesbe.world.trees.fruittree;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.feature.WorldGenTrees;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesPeach;
import valoeghese.valoeghesesbe.init.ModBlocks;

public class WorldGenPeach extends WorldGenTrees
{
	
	public WorldGenPeach(BlockLeavesPeach.EnumGenotype genotype, boolean notify)
	{
		this(notify, 4, ModBlocks.WOOD_LOOKUP.get("LOG_PEACH").getDefaultState(), BlockLeavesPeach.getBlockByGenotype(genotype).getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true)), false);
	}
	
	private WorldGenPeach(boolean notify, int minTreeHeightIn, IBlockState woodMeta, IBlockState p_i46446_4_, boolean growVines)
	{
		super(notify, minTreeHeightIn, woodMeta, p_i46446_4_, growVines);
	}

}
