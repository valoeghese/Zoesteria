package valoeghese.valoeghesesbe.world.biomes.alpha4;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public interface BiomeAssets
{
	public static final IBlockState COLD_BUSH_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
    public static final IBlockState BUSH_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    public static final IBlockState ROCK = Blocks.STONE.getDefaultState();
    public static final IBlockState DIRT = Blocks.DIRT.getDefaultState();
    public static final IBlockState COARSE_DIRT = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
    public static final IBlockState GRASS = Blocks.GRASS.getDefaultState();
    
    public static final WorldGenBlockBlob STONE_ROCK = new WorldGenBlockBlob(Blocks.STONE, 1);
    public static final WorldGenBlockBlob STONE_ROCK_LARGE = new WorldGenBlockBlob(Blocks.STONE, 2);
}
