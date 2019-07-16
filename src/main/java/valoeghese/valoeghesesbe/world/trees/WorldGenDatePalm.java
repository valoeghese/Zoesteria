package valoeghese.valoeghesesbe.world.trees;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import valoeghese.valoeghesesbe.init.ModBlocks;

public class WorldGenDatePalm extends WorldGenAbstractTree
{
	
	public final IBlockState TRUNK;
	public static final IBlockState LEAF = ModBlocks.LEAVES_DATE_PALM.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
	
	private final int minheight = 7;
	
	public WorldGenDatePalm()
	{
		this(false);
	}
	public WorldGenDatePalm(boolean notify)
	{
		super(notify);
		
		this.TRUNK = ModBlocks.WOOD_LOOKUP.get("LOG_PALM_DARK").getDefaultState();
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		int height = this.minheight + rand.nextInt(4);
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		boolean flag = true;
		
		for (int yPos = 0; yPos <= height + 2; yPos++)
		{
			
			if (yPos >= 0 && yPos < worldIn.getHeight())
			{
				BlockPos pos21 = pos.up(yPos);
				
				if(!(this.isReplaceable(worldIn, pos21)))
				{
					flag = false;
				}
			} else {
				flag = false;
			}
		}
		
		if (!flag)
			return false;
		else
		{
			BlockPos down = pos.down();
			IBlockState state = worldIn.getBlockState(down);
			boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, EnumFacing.UP, (IPlantable)(ModBlocks.SAPLING_PALM)) || state.getBlock() == Blocks.SAND;
			
			if (isSoil && y < worldIn.getHeight() - height - 1)
			{
				state.getBlock().onPlantGrow(state, worldIn, down, pos);
				
				BlockPos origin = pos.up(height - 1);
				
				// leaves
				for (int i = -1; i < 2; ++i)
				{
					setLeaves(worldIn, origin.add(1, i, 0));
					setLeaves(worldIn, origin.add(-1, i, 0));
					setLeaves(worldIn, origin.add(0, i, 1));
					setLeaves(worldIn, origin.add(0, i, -1));
				}
				
				setLeaves(worldIn, origin.add(1, 0, 1));
				setLeaves(worldIn, origin.add(-1, 0, 1));
				setLeaves(worldIn, origin.add(1, 0, -1));
				setLeaves(worldIn, origin.add(-1, 0, -1));
				
				for (int i = -2; i < 2; ++i)
				{
					if (i == 0) continue;
					setLeaves(worldIn, origin.add(2, i, 0));
					setLeaves(worldIn, origin.add(-2, i, 0));
					setLeaves(worldIn, origin.add(0, i, 2));
					setLeaves(worldIn, origin.add(0, i, -2));
				}
				
				setLeaves(worldIn, origin.add(0, 1, 0));
				
				
				for (int lHeight = 0; lHeight < height; ++lHeight)
				{
					this.setBlockAndNotifyAdequately(worldIn, pos.up(lHeight), TRUNK);
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	private void setLeaves(World worldIn, BlockPos pos)
	{
		if (this.canGrowInto(worldIn.getBlockState(pos).getBlock())) this.setBlockAndNotifyAdequately(worldIn, pos, LEAF);
	}

}
