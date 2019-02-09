package valoeghese.valoeghesesbe.world.trees;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesBase;
import valoeghese.valoeghesesbe.init.ModBlocks;

public class WorldGenBluffPine extends WorldGenAbstractTree
{
	
	private static final IBlockState TRUNK = ModBlocks.LOG_PINE.getDefaultState();
	private static final IBlockState LEAF = ModBlocks.LEAVES_BLUFF.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	
	private static final int minHeight = 7;
	public WorldGenBluffPine() { this(false); }
	
	public WorldGenBluffPine(boolean notify)
	{
		super(notify);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		int theight = 3 * (rand.nextInt(3) + 1) + minHeight + 1;
		int trunk = rand.nextInt(2);
		int height = theight + trunk;
		
		boolean flag = true;
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		if (y >=1 && y + height + 2 <= worldIn.getHeight())
		{
			for (int j = y; j < y + 2 + height; ++j)
			{
				int k = 1;
				
				if (j == y)
				{
					k = 0;
				}
				
				if (j >= y + 2 + height - 2)
				{
					k = 2;
				}
				
				MutableBlockPos b1 = new MutableBlockPos();
				
				for (int l = x - k; l <= x + k && flag; ++l)
				{
					for (int i1 = z - k; i1 <= z + k && flag; ++i1)
					{
						if (j >= 0 && j < worldIn.getHeight())
						{
							if (!this.isReplaceable(worldIn, b1.setPos(l, j, i1)))
							{
								flag = false;
							}
						} else {
							
							flag = false;
						}
					}
				}
			}
			
			if (!flag)
			{
				return false;
			} else {
				IBlockState state = worldIn.getBlockState(pos.down());
				
				if ( (state.getBlock().canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, (BlockSapling)Blocks.SAPLING) || state.getBlock() == Blocks.MOSSY_COBBLESTONE) && y < worldIn.getHeight() - height - 1)
				{
					state.getBlock().onPlantGrow(state, worldIn, pos.down(), pos);
					
					for (int lHeight = 0; lHeight <= height; ++lHeight)
					{
						BlockPos up = pos.up(lHeight);
						IBlockState lState = worldIn.getBlockState(up);
						
						if (lHeight > trunk && lHeight < height - 1)
						{
							switch (Integer.remainderUnsigned(lHeight - trunk - 1, 4))
							{
							case 0:
								if (height > 11)
								{
									if (lHeight < trunk + 4)
									{
										this.growBigLeaves1(worldIn, LEAF, x, lHeight + y, z, rand);
									} else {
										this.growBigLeaves2(worldIn, LEAF, x, lHeight + y, z, rand);
									}
								} else {
									this.growBigLeaves2(worldIn, LEAF, x, lHeight + y, z, rand);
								}
								break;
							case 2:
								if (rand.nextBoolean())
								{
									this.growSmallLeaves2(worldIn, LEAF, x, lHeight + y, z, rand);
								} else {
									this.growSmallLeaves1(worldIn, LEAF, x, lHeight + y, z, rand);
								}
								break;
							default:
								this.growSmallLeaves3(worldIn, LEAF, x, lHeight + y, z, rand);
								break;
							}
						} else if (lHeight == height - 1) {
							this.growSmallLeaves3(worldIn, LEAF, x, lHeight + y, z, rand);
						}
						
						if (lHeight == height)
						{
							this.setBlockAndNotifyAdequately(worldIn, pos.up(lHeight), LEAF);
						} else {
							this.setBlockAndNotifyAdequately(worldIn, pos.up(lHeight), TRUNK);
						}
					}
					
					return true;
				} else {
					return false;
				}
				
			}
			
		}
		
		return false;
	}
	
	private void growSmallLeaves3(World worldIn, IBlockState leaves, int x, int y, int z, Random rand)
	{
		
		for (int xoff = -1; xoff < 2; ++xoff)
		{
			if (worldIn.getBlockState(new BlockPos(x+xoff, y, z)).getBlock() == Blocks.AIR) if (xoff != 0) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+xoff, y, z), leaves);
		}
		for (int zoff = -1; zoff < 2; ++zoff)
		{
			if (worldIn.getBlockState(new BlockPos(x, y, z+zoff)).getBlock() == Blocks.AIR) if (zoff != 0) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y, z + zoff), leaves);
		}
	}
	
	private void growSmallLeaves2(World worldIn, IBlockState leaves, int x, int y, int z, Random rand)
	{
		this.growSmallLeaves3(worldIn, leaves, x, y, z, rand);
		
		//Corners
		if (worldIn.getBlockState(new BlockPos(x+1, y, z+1)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+1,y,z+1), leaves);
		if (worldIn.getBlockState(new BlockPos(x+1, y, z-1)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+1,y,z-1), leaves);
		if (worldIn.getBlockState(new BlockPos(x-1, y, z+1)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-1,y,z+1), leaves);
		if (worldIn.getBlockState(new BlockPos(x-1, y, z-1)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-1,y,z-1), leaves);
	}
	
	private void growSmallLeaves1(World worldIn, IBlockState leaves, int x, int y, int z, Random rand)
	{
		this.growSmallLeaves2(worldIn, leaves, x, y, z, rand);
		
		//Peaks
		if (worldIn.getBlockState(new BlockPos(x+2, y, z)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+2, y, z), leaves);
		if (worldIn.getBlockState(new BlockPos(x-2, y, z)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-2, y, z), leaves);
		if (worldIn.getBlockState(new BlockPos(x, y, z+2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y, z+2), leaves);
		if (worldIn.getBlockState(new BlockPos(x, y, z-2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y, z-2), leaves);
	}
	private void growBigLeaves2(World worldIn, IBlockState leaves, int x, int y, int z, Random rand)
	{
		
		//Inner, Outer
		for(int fdsdffs = 1; fdsdffs <= 2; ++fdsdffs)
		{
			if (worldIn.getBlockState(new BlockPos(x, y, z+fdsdffs)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y, z+fdsdffs), leaves);
			if (worldIn.getBlockState(new BlockPos(x, y, z-fdsdffs)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y, z-fdsdffs), leaves);
			if (worldIn.getBlockState(new BlockPos(x+fdsdffs, y, z)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+fdsdffs, y, z), leaves);
			if (worldIn.getBlockState(new BlockPos(x-fdsdffs, y, z)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-fdsdffs, y, z), leaves);
		}
		
		//Diagonal Z
		if (worldIn.getBlockState(new BlockPos(x-1, y, z+2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-1, y, z+2), leaves);
		if (worldIn.getBlockState(new BlockPos(x+1, y, z+2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+1, y, z+2), leaves);
		if (worldIn.getBlockState(new BlockPos(x-1, y, z-2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-1, y, z-2), leaves);
		if (worldIn.getBlockState(new BlockPos(x+1, y, z-2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+1, y, z-2), leaves);
		
		//Diagonal X
		if (worldIn.getBlockState(new BlockPos(x+2, y, z-1)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+2, y, z-1), leaves);
		if (worldIn.getBlockState(new BlockPos(x+2, y, z+1)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+2, y, z+1), leaves);
		if (worldIn.getBlockState(new BlockPos(x-2, y, z-1)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-2, y, z-1), leaves);
		if (worldIn.getBlockState(new BlockPos(x-2, y, z+1)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-2, y, z+1), leaves);
	}
	
	private void growBigLeaves1(World worldIn, IBlockState leaves, int x, int y, int z, Random rand)
	{
		//Inner
		this.growBigLeaves2(worldIn, leaves, x, y, z, rand);
		
		//Outer Z, Outer X
		for (int edna = -1; edna < 2; ++edna)
		{
			if (worldIn.getBlockState(new BlockPos(x+edna, y, z+3)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+edna, y, z+3), leaves);
			if (worldIn.getBlockState(new BlockPos(x+edna, y, z-3)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+edna, y, z-3), leaves);
			if (worldIn.getBlockState(new BlockPos(x+3, y, z+edna)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+3, y, z+edna), leaves);
			if (worldIn.getBlockState(new BlockPos(x-3, y, z+edna)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-3, y, z+edna), leaves);
		}
		
		//Diagonals
		if (worldIn.getBlockState(new BlockPos(x+2, y, z+2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+2, y, z+2), leaves);
		if (worldIn.getBlockState(new BlockPos(x-2, y, z+2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-2, y, z+2), leaves);
		if (worldIn.getBlockState(new BlockPos(x+2, y, z-2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+2, y, z-2), leaves);
		if (worldIn.getBlockState(new BlockPos(x-2, y, z-2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-2, y, z-2), leaves);
	}
	
	/*
	@Override
	protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState stateIn)
	{
        IBlockState state = worldIn.getBlockState(pos);

        if (state.getBlock().isAir(state, worldIn, pos) || state.getBlock().isLeaves(state, worldIn, pos) || state.getMaterial() == Material.VINE)
        {
        	super.setBlockAndNotifyAdequately(worldIn, pos, state);
        }
	}
	*/

}
