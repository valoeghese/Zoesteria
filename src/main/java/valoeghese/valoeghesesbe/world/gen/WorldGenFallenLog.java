package valoeghese.valoeghesesbe.world.gen;

import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import valoeghese.valoeghesesbe.world.trees.enumTypes.EnumDirection;

public class WorldGenFallenLog extends WorldGenAbstractTree
{
	
	protected IBlockState log;
	protected final int length;
	
	public WorldGenFallenLog(IBlockState log, int length, boolean notify)
	{
		super(notify);
		
		this.log = log;
		this.length = length;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		if (pos.getY() < 0 || pos.getY() > world.getHeight()) return false;
		
		boolean flag = true;
		EnumDirection direction = rand.nextBoolean() ? EnumDirection.SOUTH : EnumDirection.EAST;
		
		for (int dist = 0; dist <= this.length; dist++)
		{
			
				BlockPos pos21 = pos.add(direction.getXOffset() * dist, 0, direction.getZOffset() * dist);
				if(!(this.isReplaceable(world, pos21)))
				{
					flag = false;
				}
		}
		
		if (!flag)
		{
			return false;
		}
		else
		{
			BlockPos down = pos.down();
			
			if (world.getBlockState(down).getBlock().canSustainPlant(world.getBlockState(down), world, pos, EnumFacing.UP, (IPlantable) Blocks.SAPLING))
			{
				for (int dist = 0; dist <= this.length; dist++)
				{

					BlockPos pos21 = pos.add(direction.getXOffset() * dist, 0, direction.getZOffset() * dist);
					this.setBlockAndNotifyAdequately(world, pos21, this.log.withProperty(BlockLog.LOG_AXIS, direction.getLogAxisOf()));
				}

				return true;
			}
		}
		return false;
	}
}
