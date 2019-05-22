package valoeghese.valoeghesesbe.world.trees.evil;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.world.trees.enumTypes.EnumDirection;

public class WorldGenEvilTree extends WorldGenAbstractTree
{
	
	public WorldGenEvilTree()
	{
		super(false);
		
	}

	private static final int minBasicHeight = 10;
	
	private static final IBlockState LEAVES = ModBlocks.LEAVES_EVIL.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));;
	private static final IBlockState TRUNK = ModBlocks.LOG_EVIL.getDefaultState();
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		int theight = rand.nextInt(15) + minBasicHeight + 1;
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
						
						if (lHeight > trunk && lHeight < height - 4)
						{
							if (rand.nextInt(3) > 0) this.generateBranch(worldIn, pos.up(lHeight), rand);
							if (rand.nextInt(3) == 0) this.generateBranch(worldIn, pos.up(lHeight), rand);
							if (rand.nextInt(4) == 0) this.generateBranch(worldIn, pos.up(lHeight), rand);
							if (rand.nextInt(5) == 0) this.generateBranch(worldIn, pos.up(lHeight), rand);
						}
						
						if (lHeight < height - 1)
						{
							this.setBlockAndNotifyAdequately(worldIn, pos.up(lHeight), TRUNK);
							this.setBlockAndNotifyAdequately(worldIn, pos.add(1, lHeight, 0), TRUNK);
							this.setBlockAndNotifyAdequately(worldIn, pos.add(1, lHeight, 1), TRUNK);
							this.setBlockAndNotifyAdequately(worldIn, pos.add(0, lHeight, 1), TRUNK);
						} else {
							this.setBlockAndNotifyAdequately(worldIn, pos.up(lHeight), LEAVES);
							this.setBlockAndNotifyAdequately(worldIn, pos.add(1, lHeight, 0), LEAVES);
							this.setBlockAndNotifyAdequately(worldIn, pos.add(1, lHeight, 1), LEAVES);
							this.setBlockAndNotifyAdequately(worldIn, pos.add(0, lHeight, 1), LEAVES);
							
							this.generateLeafNode(worldIn, pos.up(lHeight), rand);
						}
						
					}
				}
				
				return true;
			}
			
		}
		
		return true;
	}
	
	private void generateBranch(World worldIn, BlockPos origin, Random rand)
	{
		EnumDirection d1 = EnumDirection.getRandomEnumDirectional(rand);
		EnumDirection d2 = d1.getRandomOppAxisEnum(rand);
		
		//vertical
		EnumDirection d3 = EnumDirection.getRandomEnumVertical(rand);
		int d4 = 2 + rand.nextInt(2);
		
		int branchLength = 3 + rand.nextInt(5);
		for (int distance = 1; distance <= branchLength; ++distance)
		{
			int xOffset = d1.getXOffset() + Math.floorDiv(d2.getXOffset(), 2);
			int zOffset = d1.getZOffset() + Math.floorDiv(d2.getZOffset(), 2);
			int yOffset = Math.floorDiv(d3.getYOffset(), d4);
			
			this.setBlockAndNotifyAdequately(worldIn, origin.add(xOffset * distance, yOffset * distance, zOffset * distance), TRUNK.withProperty(BlockLog.LOG_AXIS, d1.getLogAxisOf()));
		}
		
		int xOffset = d1.getXOffset() + Math.floorDiv(d2.getXOffset(), 2);
		int zOffset = d1.getZOffset() + Math.floorDiv(d2.getZOffset(), 2);
		int yOffset = Math.floorDiv(d3.getYOffset(), d4);
		
		this.generateLeafNode(worldIn, origin.add(xOffset * branchLength, yOffset * branchLength, zOffset * branchLength), rand);
	}
	
	private void generateLeafNode(World worldIn, BlockPos pos, Random rand)
	{
		this.setBlockAndNotifyAdequately(worldIn, pos.up(), LEAVES);
		this.setBlockAndNotifyAdequately(worldIn, pos.add(-1, 1, 0), LEAVES);
		this.setBlockAndNotifyAdequately(worldIn, pos.add(1, 1, 0), LEAVES);
		this.setBlockAndNotifyAdequately(worldIn, pos.add(0, 1, -1), LEAVES);
		this.setBlockAndNotifyAdequately(worldIn, pos.add(0, 1, 1), LEAVES);
		
		for (int y = 0; y > -2; --y)
		{
			for (int x = -2; x < 3; ++x)
			{
				for (int z = -1; z < 2; ++z)
				{
					if (x != 0 || z != 0)
					{
						this.setBlockAndNotifyAdequately(worldIn, pos.add(x, y, z), LEAVES);
					}
				}

				if (Math.abs(x) < 2)
				{
					this.setBlockAndNotifyAdequately(worldIn, pos.add(x, y, -2), LEAVES);
					this.setBlockAndNotifyAdequately(worldIn, pos.add(x, y, 2), LEAVES);
				}
			}
		}
	}

}
