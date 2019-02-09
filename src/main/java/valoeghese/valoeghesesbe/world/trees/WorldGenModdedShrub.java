package valoeghese.valoeghesesbe.world.trees;

import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenModdedShrub extends WorldGenAbstractTree
{
	
    private final IBlockState SHRUB;
    private final boolean masterEnableSand;
    
    public WorldGenModdedShrub(IBlockState SHRUB)
    {
    	this(SHRUB, false);
    }
    
	public WorldGenModdedShrub(IBlockState SHRUB, boolean allowedOnSand)
	{
		super(false);
		
		this.SHRUB = SHRUB;
		this.masterEnableSand = allowedOnSand;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		int height = 1;
		
		boolean flag = true;
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		if (y >=1 && y + height + 2 <= worldIn.getHeight())
		{
			for (int j = y; j < y + 1 + height; ++j)
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
				
				if (j >= 0 && j < worldIn.getHeight())
				{
					if (!this.isReplaceable(worldIn, b1.setPos(x, j, z)))
					{
						flag = false;
					}
				} else {
					
					flag = false;
				}
			}
			
			if (!flag)
			{
				
				return false;
				
			} else {
				IBlockState state = worldIn.getBlockState(pos.down());
				
				boolean canSustainPlant = (state.getBlock().canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, (BlockSapling)Blocks.SAPLING));
				boolean isSandBelow = (state.getBlock() == Blocks.SAND) && this.masterEnableSand;
				if ( (canSustainPlant || isSandBelow) && y < worldIn.getHeight() - height - 1)
				{
					
					this.setBlockAndNotifyAdequately(worldIn, pos, SHRUB);
					
				}
				
				return true;
			}
		}
		
		return true;
	}
}
