package valoeghese.valoeghesesbe.world.trees;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import valoeghese.valoeghesesbe.world.trees.enumTypes.EnumDirection;

public class WorldGenStrippedOak extends WorldGenAbstractTree
{
	
	public WorldGenStrippedOak(boolean notify)
	{
		super(notify);
		// TODO Auto-generated constructor stub
	}

	private static final int minBasicHeight = 6;
	private static final IBlockState TRUNK = Blocks.LOG.getDefaultState();
	
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		int theight = rand.nextInt(6) + minBasicHeight + 1;
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
				
				boolean canBlockSustainTree = state.getBlock().canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, (BlockSapling)Blocks.SAPLING);
				if ( canBlockSustainTree && y < worldIn.getHeight() - height - 1)
				{
					state.getBlock().onPlantGrow(state, worldIn, pos.down(), pos);
					
					for (int lHeight = 0; lHeight <= height; ++lHeight)
					{
						BlockPos up = pos.up(lHeight);
						IBlockState lState = worldIn.getBlockState(up);
						
						if (lHeight > trunk && lHeight < height - 1)
						{
							if (rand.nextInt(4) == 0)
							{
								this.generateBranch(worldIn, rand, new BlockPos(x, y + lHeight, z));
							}
						}
						
						this.setBlockAndNotifyAdequately(worldIn, pos.up(lHeight), TRUNK);
						
					}
				}
				
				return true;
			}
			
		}
		
		return true;
	}

	private void generateBranch(World worldIn, Random rand, BlockPos blockPos)
	{
		EnumDirection out = EnumDirection.getRandomEnumDirectional(rand);
		
		int branchLength = rand.nextInt(4);
		
		for (int dist = 1; dist < branchLength; ++dist)
		{
			int up = rand.nextInt(3);
			if (up == 2) up = 0;
			
			this.setBlockAndNotifyAdequately(worldIn, blockPos.add(out.getXOffset() * dist, up, out.getZOffset() * dist), TRUNK);
		}
		
		if (rand.nextInt(4) == 0)
		{
			this.setBlockAndNotifyAdequately(worldIn, blockPos.add(out.getXOffset() * branchLength, 0, out.getZOffset() * branchLength), Blocks.LEAVES.getDefaultState());
		}
	}
	
}
