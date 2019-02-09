package valoeghese.valoeghesesbe.world.trees;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenBrush extends WorldGenAbstractTree
{
	public static final IBlockState TRUNK = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
	public static final IBlockState LEAF = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	
	public final int minTreeHeight = 2;
	public WorldGenBrush()
	{
		super(false);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		int up = rand.nextInt(3);
		
		if (up == 2)
		{
			up = 0;
		}
		int i = this.minTreeHeight + up;
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight())
        {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j)
            {
                int k = 1;

                if (j == position.getY())
                {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2)
                {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l)
                {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < worldIn.getHeight())
                        {
                            if (!this.isReplaceable(worldIn,blockpos$mutableblockpos.setPos(l, j, i1)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            } else {
            	
            	IBlockState downState = worldIn.getBlockState(position.down());
            	boolean soilDown = downState.getBlock().canSustainPlant(downState, worldIn, position.down(), net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling)Blocks.SAPLING);
            	
            	if (soilDown || downState.getBlock() == Blocks.SAND)
            	{
            		
            		this.setBlockAndNotifyAdequately(worldIn, position.add(0, 1 + up, 0), LEAF);
            		this.setBlockAndNotifyAdequately(worldIn, position.add(0, 0 + up, 1), LEAF);
            		this.setBlockAndNotifyAdequately(worldIn, position.add(1, 0 + up, 0), LEAF);
            		this.setBlockAndNotifyAdequately(worldIn, position.add(0, 0 + up, -1), LEAF);
            		this.setBlockAndNotifyAdequately(worldIn, position.add(-1, 0 + up, 0), LEAF);
            		
            		this.setBlockAndNotifyAdequately(worldIn, position, TRUNK);
            		if (up == 1)
            		{
            			this.setBlockAndNotifyAdequately(worldIn, position.up(), TRUNK);
            		}
            		
            		return true;
                } else {
            		return false;
            	}
            }
        } else {
        	return false;
        }
	}

}