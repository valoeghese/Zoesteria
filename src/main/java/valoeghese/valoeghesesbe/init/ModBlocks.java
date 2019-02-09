package valoeghese.valoeghesesbe.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import valoeghese.valoeghesesbe.blocks.BlockDeadSoil;
import valoeghese.valoeghesesbe.blocks.BlockModdedShrub;
import valoeghese.valoeghesesbe.blocks.BlockPlanksBase;
import valoeghese.valoeghesesbe.blocks.BlockSlabBase;
import valoeghese.valoeghesesbe.blocks.BlockSmallBush;
import valoeghese.valoeghesesbe.blocks.BlockStairsBase;
import valoeghese.valoeghesesbe.blocks.BlockTrueTNT;
import valoeghese.valoeghesesbe.blocks.OreSaltpeter;
import valoeghese.valoeghesesbe.blocks.OreSulphur;
import valoeghese.valoeghesesbe.blocks.RockOreBase;
import valoeghese.valoeghesesbe.blocks.VanadiumBlock;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesBase;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesEvil;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesPalm;
import valoeghese.valoeghesesbe.functional.tree.BlockLogBase;
import valoeghese.valoeghesesbe.functional.tree.BlockSaplingBase;
import valoeghese.valoeghesesbe.functional.tree.nz.BlockLeavesPohutukawa;

public class ModBlocks
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block VANADIUM_BLOCK = new VanadiumBlock("vanadium_block", Material.IRON);
	
	public static final Block ORE_VANADIUM = new RockOreBase("ore_vanadium", Material.ROCK, 2);
	public static final Block ORE_SULPHUR = new OreSulphur("ore_sulphur", Material.ROCK, 1);
	public static final Block ORE_SALTPETER = new OreSaltpeter("ore_saltpeter", Material.SAND);
	
	public static final Block SOIL_DRY = new BlockDeadSoil("soil_dry", Material.GROUND);
	public static final Block SOIL_DRY_GRASS = new BlockDeadSoil("brushland_grass", Material.GRASS /*, Item.getItemFromBlock(SOIL_DRY)*/);
	public static final Block SOIL_WASTE = new BlockDeadSoil("soil_waste", Material.GROUND);
	public static final Block SOIL_WET = new BlockDeadSoil("soil_wet", Material.CLAY);
	
	public static final Block LEAVES_PALM = new BlockLeavesPalm("leaves_oasis_palm");
	public static final Block SAPLING_PALM = new BlockSaplingBase("sapling_oasis_palm", "oasis_palm");
	
	public static final Block TRUE_TNT = new BlockTrueTNT("tnt_true");
	
	public static final Block LOG_EVIL = new BlockLogBase("log_forest_evil", "forest_evil");
	public static final Block LEAVES_EVIL = new BlockLeavesEvil("leaves_forest_evil");
	//public static final Block LEAVES_EVIL = new BlockBaseTree("leaves_forest_evil");
	public static final Block SAPLING_EVIL = new BlockSaplingBase("sapling_forest_evil", "forest_evil");
	
	public static final Block SAPLING_BLUFF = new BlockSaplingBase("sapling_pine_bluff", "pine_bluff");
	public static final Block LEAVES_BLUFF = new BlockLeavesBase("leaves_pine_bluff", SAPLING_BLUFF);
	public static final Block LOG_PINE = new BlockLogBase("log_zoesteria_pine", "zoesteria_pine");
	public static final Block PLANKS_PINE = new BlockPlanksBase("planks_zoesteria_pine");
	public static final Block PLANKS_STAIRS_PINE = new BlockStairsBase("stairs_zoesteria_pine");
	public static final Block PLANKS_SLAB_PINE = new BlockSlabBase("slab_zoesteria_pine", PLANKS_PINE.getDefaultState(), false);
	
	public static final Block PLANKS_GHOST = new BlockPlanksBase("planks_zoesteria_ghost");
	public static final Block PLANKS_STAIRS_GHOST = new BlockStairsBase("stairs_zoesteria_ghost");
	public static final Block PLANKS_SLAB_GHOST = new BlockSlabBase("slab_zoesteria_ghost", PLANKS_GHOST.getDefaultState(), false);
	
	public static final Block SHRUB_DUNE = new BlockModdedShrub("shrub_dune", true);
	public static final Block SHRUB_DUNE_LARGE = new BlockModdedShrub("shrub_dune_large", true);
	
	public static final Block SAPLING_OCEAN_PALM = new BlockSaplingBase("sapling_ocean_palm", "ocean_palm");
	public static final Block LEAVES_OCEAN_PALM = new BlockLeavesBase("leaves_ocean_palm", SAPLING_OCEAN_PALM);
	
	public static final Block LEAVES_POHUTUKAWA = new BlockLeavesPohutukawa("leaves_pohutukawa");
	public static final Block LEAVES_POHUTUKAWA_BUD = new BlockLeavesPohutukawa("leaves_pohutukawa_bud");
	public static final Block LEAVES_POHUTUKAWA_FLOWER = new BlockLeavesPohutukawa("leaves_pohutukawa_flower");
	public static final Block SAPLING_POHUTUKAWA = new BlockSaplingBase("sapling_pohutukawa", "pohutukawa");
	
	public static final Block SAPLING_MANUKA = new BlockSaplingBase("sapling_manuka", "manuka");
	public static final Block LEAVES_MANUKA = new BlockLeavesBase("leaves_manuka", SAPLING_MANUKA);
	
	public static final Block SMALL_BUSH = new BlockSmallBush("bush_small");
}
