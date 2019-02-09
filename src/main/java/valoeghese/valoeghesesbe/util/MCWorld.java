package valoeghese.valoeghesesbe.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MCWorld
{
	/**
	 * 
	 * @param world
	 * @param x
	 * @param z
	 * @param topBlocks
	 * @return Y of first instance of a block in topBlocks found going down from the world height
	 */
	public static int calculateGenHeight(World world, int x, int z, Block...topBlocks)
	{
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = false;
			for (Block topBlock : topBlocks)
			{
				foundGround = block == topBlock;
				if (foundGround)
				{
					break;
				}
			}
		}
		
		return y;
	}

}
