package valoeghese.valoeghesesbe.world.trees;

import java.util.Random;

import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import valoeghese.valoeghesesbe.blocks.BlockCoconut;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.world.trees.enumTypes.EnumDirection;

public class WorldGenIslandPalm extends WorldGenAbstractTree
{
	
	public final IBlockState TRUNK;
	public static final IBlockState LEAF = ModBlocks.LEAVES_ISLAND_PALM.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
	
	private final int minheight;
	public WorldGenIslandPalm()
	{
		super(false);
		
		TRUNK = ModBlocks.WOOD_LOOKUP.get("LOG_PALM").getDefaultState();
		this.minheight = 7;
	}
	public WorldGenIslandPalm(boolean notify, int minHeight)
	{
		super(notify);
		TRUNK = ModBlocks.WOOD_LOOKUP.get("LOG_PALM").getDefaultState();
		this.minheight = minHeight;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		if (worldIn.getBiome(pos).getTempCategory() == Biome.TempCategory.COLD)
			return false;
		
		int height = this.minheight + rand.nextInt(6);
		
		EnumDirection leaning0 = EnumDirection.getRandomEnumDirectional(rand);
		EnumDirection leaning1 = EnumDirection.getRandomEnumDirectional(rand);
		
		if (leaning0 == leaning1 || leaning0 == leaning1.getFullOpposite())
		{
			leaning1 = EnumDirection.VERTICAL_TRUE;
		}
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		boolean flag = true;
		
		for (int yPos = 0; yPos <= height + 3; yPos++)
		{
			if (yPos >= 0 && yPos < worldIn.getHeight())
			{
				BlockPos pos21 = yPos > 0 ? (yPos > 2 ? pos.add(2*leaning0.getXOffset() + 2*leaning1.getXOffset(), yPos, 2*leaning0.getZOffset() + 2*leaning1.getZOffset()) : pos.add(leaning0.getXOffset() + leaning1.getXOffset(), yPos, leaning0.getZOffset() + leaning1.getZOffset())) : pos.up(yPos);
				
				if(!(this.isReplaceable(worldIn, pos21)))
				{
					flag = false;
				}
			} else {
				flag = false;
			}
		}
		
		if (!flag)
			return false;
		else
		{
			BlockPos down = pos.down();
			IBlockState state = worldIn.getBlockState(down);
			boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, EnumFacing.UP, (IPlantable)(ModBlocks.SAPLING_PALM)) || state.getBlock() == Blocks.SAND;
			
			if (isSoil && y < worldIn.getHeight() - height - 1)
			{
				state.getBlock().onPlantGrow(state, worldIn, down, pos);
				
				BlockPos generateLeavesFrom = pos.add(2*leaning0.getXOffset() + 2*leaning1.getXOffset(), height - 1, 2*leaning0.getZOffset() + 2*leaning1.getZOffset());
				this.generateLeaves(worldIn, generateLeavesFrom);
				this.addCoconutSpread(worldIn, generateLeavesFrom.down(), rand, 1);
				BlockPos pos21;
				for (int lHeight = 0; lHeight <= height; ++lHeight)
				{
					pos21 = new BlockPos(0,0,0); //Create BlockPos
					pos21 = lHeight > 0 ? (lHeight > 2 ? pos.add(2*leaning0.getXOffset() + 2*leaning1.getXOffset(), lHeight, 2*leaning0.getZOffset() + 2*leaning1.getZOffset()) : pos.add(leaning0.getXOffset() + leaning1.getXOffset(), lHeight, leaning0.getZOffset() + leaning1.getZOffset())) : pos.up(lHeight);
					this.setBlockAndNotifyAdequately(worldIn, pos21, TRUNK);
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	private void placeCoconut(World worldIn, BlockPos pos, Random rand)
    {
        this.setBlockAndNotifyAdequately(worldIn, pos, ModBlocks.COCONUT.getDefaultState().withProperty(BlockCoconut.AGE, Integer.valueOf(rand.nextInt(2))));
    }
	
	private void generateLeaves(World worldIn, BlockPos pos)
	{
		this.setLeafPoints(worldIn, pos.down(2), 2);
		
		this.setLeafPoints(worldIn, pos.down(), 1);
		this.setLeafPoints(worldIn, pos.down(), 2);
		this.setLeafSpread(worldIn, pos.down(), 2);
		
		this.setLeafPoints(worldIn, pos, 1);
		this.setLeafSpread(worldIn, pos, 1);
		
		this.setLeafPoints(worldIn, pos.up(), 1);
		
		this.setLeafPoints(worldIn, pos.up(2), 1);
		this.setLeafPoints(worldIn, pos.up(2), 2);
	}
	private void setLeaves(World worldIn, BlockPos pos)
	{
		if (this.canGrowInto(worldIn.getBlockState(pos).getBlock())) this.setBlockAndNotifyAdequately(worldIn, pos, LEAF);
	}
	private void setLeafPoints(World worldIn, BlockPos pos, int range)
	{
		this.setLeaves(worldIn, pos.add(range, 0, 0));
		this.setLeaves(worldIn, pos.add(-range, 0, 0));
		this.setLeaves(worldIn, pos.add(0, 0, range));
		this.setLeaves(worldIn, pos.add(0, 0, -range));
	}
	private void setLeafSpread(World worldIn, BlockPos pos, int range)
	{
		this.setLeaves(worldIn, pos.add(range, 0, range));
		this.setLeaves(worldIn, pos.add(-range, 0, range));
		this.setLeaves(worldIn, pos.add(range, 0, -range));
		this.setLeaves(worldIn, pos.add(-range, 0, -range));
	}
	private void addCoconutSpread(World worldIn, BlockPos pos, Random rand, int range)
	{
		this.placeCoconut(worldIn, pos.add(range, 0, range), rand);
		this.placeCoconut(worldIn, pos.add(-range, 0, range), rand);
		this.placeCoconut(worldIn, pos.add(range, 0, -range), rand);
		this.placeCoconut(worldIn, pos.add(-range, 0, -range), rand);
	}

}
