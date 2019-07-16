package valoeghese.valoeghesesbe.world.biomes.alpha4;

import java.util.Random;

import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.world.trees.WorldGenIslandPalm;
import valoeghese.valoeghesesbe.world.trees.WorldGenOceanPalm;
import valoeghese.valoeghesesbe.world.trees.queenpalm.WorldGenQueenPalm;

public class BiomeReef extends Biome
{

	public BiomeReef()
	{
		super(new Biome.BiomeProperties("Archipelago").setBaseBiome("ocean").setBaseHeight(-0.85F).setHeightVariation(0.5F).setRainfall(1.5F));
		
		this.decorator.treesPerChunk = 2;
		this.decorator.grassPerChunk = 5;
        this.decorator.flowersPerChunk = 4;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		if (rand.nextInt(10) > 0)
		{
			return rand.nextInt(3) == 0 ? new WorldGenOceanPalm() : (rand.nextInt(10) == 0 ? new WorldGenQueenPalm() : new WorldGenIslandPalm());
		} else {
			return new WorldGenTrees(false, 4 + rand.nextInt(7), Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE), true);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos)
	{
		return 0x2aad13;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		//return 0x30dd53;
		return 0x5dd834;
	}

}
