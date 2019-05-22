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

public class WorldGenOceanPalm extends WorldGenAbstractTree
{
	
	public final IBlockState TRUNK;
	public static final IBlockState LEAF = ModBlocks.LEAVES_OCEAN_PALM.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
	
	private final int minheight;
	public WorldGenOceanPalm()
	{
		this(false, 6);
	}
	public WorldGenOceanPalm(boolean notify, int minHeight)
	{
		super(notify);
		
		this.TRUNK = ModBlocks.WOOD_LOOKUP.get("LOG_PALM_DARK").getDefaultState();
		this.minheight = minHeight;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		
		int height = this.minheight + rand.nextInt(5);
		
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
				
				this.generateLeaves(worldIn, pos.up(height));
				
				for (int lHeight = 0; lHeight < height; ++lHeight)
				{
					this.setBlockAndNotifyAdequately(worldIn, pos.up(lHeight), TRUNK);
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	private void generateLeaves(World worldIn, BlockPos pos)
	{
		this.setLeafSpread(worldIn, pos.down(2), 2);
		
		this.setLeafSpread(worldIn, pos.down(), 1);
		this.setLeafPoints(worldIn, pos.down(), 2);
		
		this.setLeafPoints(worldIn, pos, 1);
		
		this.setBlockAndNotifyAdequately(worldIn, pos.up(), LEAF);
		this.setLeafSpread(worldIn, pos.up(), 1);
		
		this.setLeafPoints(worldIn, pos.up(2), 2);
		
		
	}
	private void setLeaves(World worldIn, BlockPos pos)
	{
		if (this.canGrowInto(worldIn.getBlockState(pos).getBlock())) this.setBlockAndNotifyAdequately(worldIn, pos, LEAF);
	}
	
	private void setLeafPoints(World worldIn, BlockPos pos, int range)
	{
		this.setLeaves(worldIn, pos.add(range, 0, 0));
		this.setLeaves(worldIn, pos.add(-range, 0, 0));
		this.setLeaves(worldIn, pos.add(0, 0, range));
		this.setLeaves(worldIn, pos.add(0, 0, -range));
	}
	private void setLeafSpread(World worldIn, BlockPos pos, int range)
	{
		this.setLeaves(worldIn, pos.add(range, 0, range));
		this.setLeaves(worldIn, pos.add(-range, 0, range));
		this.setLeaves(worldIn, pos.add(range, 0, -range));
		this.setLeaves(worldIn, pos.add(-range, 0, -range));
	}

}
