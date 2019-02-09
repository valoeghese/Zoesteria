package valoeghese.valoeghesesbe.util;

import net.minecraft.util.math.BlockPos;

public class BlockMathHelper
{
	public static class BlockPosMath
	{
		public static BlockPos add(BlockPos posIn, int x, int z)
		{
			return posIn.add(x, 0, z);
		}
	}
}
