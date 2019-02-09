package valoeghese.valoeghesesbe.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import valoeghese.valoeghesesbe.init.ModItems;


public class ItemFertilizer extends ItemBase
{

	public ItemFertilizer(String name) 
	{
		super(name);
	}

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			if (applyBonemeal(itemstack, worldIn, pos, player, hand))
			{
				if (!worldIn.isRemote)
				{
					worldIn.playEvent(2005, pos, 0);
				}

				return EnumActionResult.SUCCESS;
			}
		}

		return EnumActionResult.PASS;
	}
	public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos target)
	{
		if (worldIn instanceof net.minecraft.world.WorldServer)
			return applyBonemeal(stack, worldIn, target, net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((net.minecraft.world.WorldServer)worldIn), null);
		return false;
	}

	public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos target, EntityPlayer player, @javax.annotation.Nullable EnumHand hand)
	{
		IBlockState iblockstate = worldIn.getBlockState(target);

		int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, target, iblockstate, stack, hand);
		if (hook != 0) return hook > 0;

		if (iblockstate.getBlock() instanceof IGrowable)
		{
			IGrowable igrowable = (IGrowable)iblockstate.getBlock();

			if (igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote))
			{
				if (!worldIn.isRemote)
				{
					if (igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate))
					{
						igrowable.grow(worldIn, worldIn.rand, target, iblockstate);
					}

					stack.shrink(1);
				}

				return true;
			}
		}

		return false;
	}
	/*
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
	 */
}
