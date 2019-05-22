package valoeghese.valoeghesesbe.world.trees.fruittree;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import valoeghese.valoeghesesbe.init.ModBlocks;

public class WorldGenPlum extends WorldGenAbstractTree
{
	
	private final boolean notify;
	protected IBlockState wood;
	protected IBlockState leaves;
	
	public WorldGenPlum(boolean notify)
	{
		this(notify, ModBlocks.WOOD_LOOKUP.get("LOG_PLUM").getDefaultState(), ModBlocks.LEAVES_PLUM.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true)));
	}
	
	private WorldGenPlum(boolean notify, IBlockState woodMeta, IBlockState leaves)
	{
		super(notify);
		
		this.notify = notify;
		this.wood = woodMeta;
		this.leaves = leaves;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		if (rand.nextInt(4) > 0)
			return new WorldGenTrees(this.notify, 4, this.wood, this.leaves, false).generate(worldIn, rand, position);
		else
			return new WorldGenTrees(this.notify, 2, this.wood, this.leaves, false).generate(worldIn, rand, position);
	}

}
