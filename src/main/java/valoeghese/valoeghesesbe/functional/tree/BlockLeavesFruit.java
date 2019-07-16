package valoeghese.valoeghesesbe.functional.tree;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import valoeghese.valoeghesesbe.init.ModItems;

public class BlockLeavesFruit extends BlockLeavesBase
{
	public BlockLeavesFruit(String name, Block saplingIn)
	{
		super(name, saplingIn);
	}

	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
	{
		spawnAsEntity(worldIn, pos, new ItemStack(ModItems.DATE));
	}
}
