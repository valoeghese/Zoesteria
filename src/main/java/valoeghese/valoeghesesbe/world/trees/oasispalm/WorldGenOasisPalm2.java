package valoeghese.valoeghesesbe.world.trees.oasispalm;

import java.util.Random;

import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.world.trees.enumTypes.EnumDirection;

//Will not generate in Oases
public class WorldGenOasisPalm2 extends WorldGenAbstractTree
{
    private final IBlockState TRUNK;
    private static final IBlockState LEAF = ModBlocks.LEAVES_PALM.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
    
    private static final int minHeight = 7;
    
    public WorldGenOasisPalm2()
    {
        this(false);
    }
    
    public WorldGenOasisPalm2(boolean notify)
    {
    	super(notify);
    	
    	TRUNK = ModBlocks.WOOD_LOOKUP.get("LOG_PALM").getDefaultState();
    }
    
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
    	
    	int height = minHeight + rand.nextInt(5);
    	boolean flag = true;
    	EnumDirection direction = rand.nextInt(3) == 0 ? EnumDirection.getRandomEnumType(rand) : EnumDirection.VERTICAL;
    	
    	int x = pos.getX();
    	int y = pos.getY();
    	int z = pos.getZ();
    	
    	for (int yPos = 0; yPos <= height + 2; yPos++)
		{
			
			if (yPos >= 0 && yPos < world.getHeight())
			{
				BlockPos pos21 = pos.add((int)MathHelper.floor((float)yPos * 0.5 * direction.getXOffsetAsFloat()), yPos, (int)MathHelper.floor((float)yPos * 0.5 * direction.getZOffsetAsFloat()));
				if(!(this.isReplaceable(world, pos21)))
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
			
			BlockPos down = pos.down();
			IBlockState state = world.getBlockState(down);
			boolean isSoil = state.getBlock().canSustainPlant(state, world, down, EnumFacing.UP, (IPlantable)(ModBlocks.SAPLING_PALM)) || state.getBlock() == Blocks.SAND;
			
			if (isSoil && y < world.getHeight() - height - 1)
			{
				
				state.getBlock().onPlantGrow(state, world, down, pos);
				
				int topY = y + 1 + height;
				int topX = x + (int)MathHelper.floor( (float)(1+height) * 0.5 * direction.getXOffsetAsFloat() );
				int topZ = z + (int)MathHelper.floor( (float)(1+height) * 0.5 * direction.getZOffsetAsFloat() );;
				
				this.growPalmLeaves(world, LEAF, topX, topY, topZ, rand);
				
				for (int lHeight = 0; lHeight < height; lHeight++)
				{
					
					BlockPos up = pos.up(lHeight);
					IBlockState lState = world.getBlockState(up);
					BlockPos pos21 = pos.add((int)MathHelper.floor((float)lHeight * 0.5 * direction.getXOffsetAsFloat()), lHeight, (int)MathHelper.floor((float)lHeight * 0.5 * direction.getZOffsetAsFloat()));
					this.setBlockAndNotifyAdequately(world, pos.add((int)MathHelper.floor((float)lHeight * 0.5 * direction.getXOffsetAsFloat()), lHeight, (int)MathHelper.floor((float)lHeight * 0.5 * direction.getZOffsetAsFloat())), TRUNK);
					
				}
				
				return true;
			}
		}
    	
    	return false;
    }
    
    private void growPalmLeaves(World worldIn, IBlockState leavesState, int x, int y, int z, Random rand)
    {
    	for (int xAdd = -1; xAdd < 2; ++xAdd)
    	{
    		for (int zAdd = -1; zAdd < 2; ++zAdd)
        	{
				if (worldIn.getBlockState(new BlockPos(x + xAdd, y, z + zAdd)).getBlock() == Blocks.AIR)
    			this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x + xAdd, y, z + zAdd), leavesState);
        	}
    	}
    	
    	if (worldIn.getBlockState(new BlockPos(x+2, y, z)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+2, y, z), leavesState);
    	if (worldIn.getBlockState(new BlockPos(x-2, y, z)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-2, y, z), leavesState);
    	if (worldIn.getBlockState(new BlockPos(x, y, z+2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y, z+2), leavesState);
    	if (worldIn.getBlockState(new BlockPos(x, y, z-2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y, z-2), leavesState);
    	
    	if (worldIn.getBlockState(new BlockPos(x+2, y-1, z+2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+2, y-1, z+2), leavesState);
    	if (worldIn.getBlockState(new BlockPos(x+2, y-1, z-2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+2, y-1, z-2), leavesState);
    	if (worldIn.getBlockState(new BlockPos(x-2, y-1, z+2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-2, y-1, z+2), leavesState);
    	if (worldIn.getBlockState(new BlockPos(x-2, y-1, z-2)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-2, y-1, z-2), leavesState);
    	
    	for (int yAdd = -2; yAdd < 0; ++yAdd)
    	{
    		if (worldIn.getBlockState(new BlockPos(x+3, y + yAdd, z)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x+3, y + yAdd, z), leavesState);
    		if (worldIn.getBlockState(new BlockPos(x-3, y + yAdd, z)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x-3, y + yAdd, z), leavesState);
    		if (worldIn.getBlockState(new BlockPos(x, y + yAdd, z+3)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y + yAdd, z+3), leavesState);
    		if (worldIn.getBlockState(new BlockPos(x, y + yAdd, z-3)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x,y + yAdd, z-3), leavesState);
    	}
    	
    	if (worldIn.getBlockState(new BlockPos(x, y-1, z)).getBlock() == Blocks.AIR) this.setBlockAndNotifyAdequately(worldIn, new BlockPos(x, y-1, z), TRUNK);
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