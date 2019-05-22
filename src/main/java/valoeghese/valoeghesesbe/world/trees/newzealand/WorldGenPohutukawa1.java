package valoeghese.valoeghesesbe.world.trees.newzealand;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import valoeghese.valoeghesesbe.init.ModBlocks;


public class WorldGenPohutukawa1 extends WorldGenAbstractTree
{
	
	public static final int minTreeHeight = 5;
	public static final IBlockState TRUNK = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
	public WorldGenPohutukawa1()
	{
		this(false);
	}
	public WorldGenPohutukawa1(boolean notify)
	{
		super(notify);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		int i = rand.nextInt(3) + minTreeHeight;
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
			}
			else
			{
				IBlockState state = worldIn.getBlockState(position.down());
				boolean canSustainPlant = state.getBlock().canSustainPlant(state, worldIn, position.down(), net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling)Blocks.SAPLING);
				if ((canSustainPlant || state.getBlock() == Blocks.SAND) && position.getY() < worldIn.getHeight() - i - 1)
				{
					if (canSustainPlant) state.getBlock().onPlantGrow(state, worldIn, position.down(), position);
					
					for (int upval = i - 3; upval <= i; ++upval)
					{
						if (upval > i - 2)
						{
							for (int xOffset = -1; xOffset <= 1; ++xOffset)
							{
								for (int zOffset = -1; zOffset <= 1; ++zOffset)
								{
									if (upval == i)
									{
										if ((Math.abs(xOffset) == Math.abs(zOffset)) && (xOffset != 0)) continue;
									}
									if (worldIn.getBlockState(position.add(xOffset, upval, zOffset)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, position.add(xOffset, upval, zOffset), this.getLeaves2(rand));
								}
							}
						} else {
							
							for (int xOffset = -2; xOffset <= 2; ++xOffset)
							{
								for (int zOffset = -2; zOffset <= 2; ++zOffset)
								{
									if (worldIn.getBlockState(position.add(xOffset, upval, zOffset)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, position.add(xOffset, upval, zOffset), this.getLeaves1(rand));
								}
							}
							
						}						
					}
					
					for (int y = 0; y < i; ++y)
					{
						if (y != i) if (worldIn.getBlockState(position.up(y)).getBlock() == Blocks.AIR || worldIn.getBlockState(position.up(y)).getBlock().isLeaves(worldIn.getBlockState(position.up(y)), worldIn, position.up(y))) this.setBlockAndNotifyAdequately(worldIn, position.up(y), TRUNK);
					}
					
					return true;
				} else {
					return false;
				}
			}
				
		}
		return false;
	}
	
	private IBlockState getLeaves1(Random rand)
	{
		switch (rand.nextInt(4))
		{
		case 0:
			return ModBlocks.LEAVES_POHUTUKAWA.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
		case 1:
			return ModBlocks.LEAVES_POHUTUKAWA_BUD.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
		default:
			return ModBlocks.LEAVES_POHUTUKAWA_FLOWER.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
		}
	}
	
	private IBlockState getLeaves2(Random rand)
	{
		switch (rand.nextInt(5))
		{
		case 0:
		case 1:
			return ModBlocks.LEAVES_POHUTUKAWA_FLOWER.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
		case 2:
			return ModBlocks.LEAVES_POHUTUKAWA.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
		default:
			return ModBlocks.LEAVES_POHUTUKAWA_BUD.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
		}
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
	} */
	
}

