package valoeghese.valoeghesesbe.world.trees.newzealand;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.util.BlockMathHelper;
import valoeghese.valoeghesesbe.world.trees.enumTypes.EnumDirection;

public class WorldGenPohutukawa2 extends WorldGenAbstractTree
{
	private static final IBlockState TRUNK = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
	private static final int minBasicCheckHeight = 10;
	public WorldGenPohutukawa2(boolean notify)
	{
		super(notify);
	}

	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		int height = minBasicCheckHeight;
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
				boolean canSustainPlant = state.getBlock().canSustainPlant(state, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling)Blocks.SAPLING);
				if ((canSustainPlant || state.getBlock() == Blocks.SAND) && pos.getY() < worldIn.getHeight() - height - 1)
				{
					if (canSustainPlant) state.getBlock().onPlantGrow(state, worldIn, pos.down(), pos);
					
					this.setBlockAndNotifyAdequately(worldIn, pos, TRUNK);
					this.setBlockAndNotifyAdequately(worldIn, BlockMathHelper.BlockPosMath.add(pos, 1, 0), TRUNK);
					this.setBlockAndNotifyAdequately(worldIn, BlockMathHelper.BlockPosMath.add(pos, 1, 1), TRUNK);
					this.setBlockAndNotifyAdequately(worldIn, BlockMathHelper.BlockPosMath.add(pos, 0, 1), TRUNK);
					
					this.setBlockAndNotifyAdequately(worldIn, pos.up(), TRUNK);
					this.setBlockAndNotifyAdequately(worldIn, BlockMathHelper.BlockPosMath.add(pos, 1, 0).up(), TRUNK);
					this.setBlockAndNotifyAdequately(worldIn, BlockMathHelper.BlockPosMath.add(pos, 1, 1).up(), TRUNK);
					this.setBlockAndNotifyAdequately(worldIn, BlockMathHelper.BlockPosMath.add(pos, 0, 1).up(), TRUNK);
					
					EnumDirection dr1 = EnumDirection.getRandomEnumX(rand);
					EnumDirection dr2 = EnumDirection.getRandomEnumZ(rand);
					this.generateBranch(worldIn, pos.up(), rand, dr1, dr2);
					this.generateBranch(worldIn, this.getRandomFalseOrigin(pos.up(), rand), rand, dr1.getFullOpposite(), dr2.getFullOpposite());
					
					for (int i = 0; i < (2 + rand.nextInt(4)); ++i)
					{
						this.generateBranch(worldIn, this.getRandomOrigin(pos.up(), rand), rand);
					}
					
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	private void generateBranch(World worldIn, BlockPos origin, Random rand, EnumDirection d1in, EnumDirection d2in)
	{
		EnumDirection d1 = d1in;
		EnumDirection d2 = d2in;
		//vertical
		EnumDirection d3 = EnumDirection.UP;

		int branchLength = 3 + rand.nextInt(3);
		for (int distance = 1; distance <= branchLength; ++distance)
		{
			int xOffset = d1.getXOffset() + Math.floorDiv(d2.getXOffset(), 2);
			int zOffset = d1.getZOffset() + Math.floorDiv(d2.getZOffset(), 2);
			int yOffset = d3.getYOffset();

			this.setBlockAndNotifyAdequately(worldIn, origin.add(xOffset * distance, yOffset * distance, zOffset * distance), TRUNK.withProperty(BlockLog.LOG_AXIS, d1.getLogAxisOf()));
		}

		int xOffset = d1.getXOffset() + Math.floorDiv(d2.getXOffset(), 2);
		int zOffset = d1.getZOffset() + Math.floorDiv(d2.getZOffset(), 2);
		int yOffset = d3.getYOffset();

		this.generateLeafNode(worldIn, origin.add(xOffset * branchLength, yOffset * branchLength, zOffset * branchLength), rand);
	}
	
	private void generateBranch(World worldIn, BlockPos origin, Random rand)
	{
		EnumDirection d1 = EnumDirection.getRandomEnumDirectional(rand);
		EnumDirection d2 = d1.getRandomOppAxisEnum(rand);
		
		this.generateBranch(worldIn, origin, rand, d1, d2);
	}

	private void generateLeafNode(World worldIn, BlockPos pos, Random rand)
	{
		this.setBlockAndNotifyAdequately(worldIn, pos.up(), this.getLeaves(rand));
		this.setBlockAndNotifyAdequately(worldIn, pos.add(-1, 1, 0), this.getLeaves(rand));
		this.setBlockAndNotifyAdequately(worldIn, pos.add(1, 1, 0), this.getLeaves(rand));
		this.setBlockAndNotifyAdequately(worldIn, pos.add(0, 1, -1), this.getLeaves(rand));
		this.setBlockAndNotifyAdequately(worldIn, pos.add(0, 1, 1), this.getLeaves(rand));

		for (int y = 0; y > -2; --y)
		{
			for (int x = -2; x < 3; ++x)
			{
				for (int z = -1; z < 2; ++z)
				{
					if (x != 0 || z != 0)
					{
						this.setBlockAndNotifyAdequately(worldIn, pos.add(x, y, z), this.getLeaves(rand));
					}
				}

				if (Math.abs(x) < 2)
				{
					this.setBlockAndNotifyAdequately(worldIn, pos.add(x, y, -2), this.getLeaves(rand));
					this.setBlockAndNotifyAdequately(worldIn, pos.add(x, y, 2), this.getLeaves(rand));
				}
			}
		}
	}
	
	private IBlockState getLeaves(Random rand)
	{
		switch (rand.nextInt(6))
		{
		case 0:
		case 1:
			return ModBlocks.LEAVES_POHUTUKAWA_FLOWER.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
		case 2:
			return ModBlocks.LEAVES_POHUTUKAWA.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
		default:
			return ModBlocks.LEAVES_POHUTUKAWA_BUD.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
		}
	}
	
	private BlockPos getRandomOrigin(BlockPos pos, Random rand)
	{
		switch (rand.nextInt(4))
		{
		case 0:
			return BlockMathHelper.BlockPosMath.add(pos, 0, 0);
		case 1:
			return BlockMathHelper.BlockPosMath.add(pos, 1, 0);
		case 2:
			return BlockMathHelper.BlockPosMath.add(pos, 1, 1);
		default:
			return BlockMathHelper.BlockPosMath.add(pos, 0, 1);
		}
	}
	
	private BlockPos getRandomFalseOrigin(BlockPos pos, Random rand)
	{
		switch (rand.nextInt(3))
		{
		case 0:
			return BlockMathHelper.BlockPosMath.add(pos, 1, 0);
		case 1:
			return BlockMathHelper.BlockPosMath.add(pos, 1, 1);
		default:
			return BlockMathHelper.BlockPosMath.add(pos, 0, 1);
		}
	}

}
