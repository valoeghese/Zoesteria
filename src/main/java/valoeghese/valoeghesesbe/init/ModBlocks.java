package valoeghese.valoeghesesbe.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.blocks.BlockCoconut;
import valoeghese.valoeghesesbe.blocks.BlockDeadSoil;
import valoeghese.valoeghesesbe.blocks.BlockLeafLitter;
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
import valoeghese.valoeghesesbe.functional.BlockSaplingPeach;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesBase;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesEvil;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesFruit;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesPeach;
import valoeghese.valoeghesesbe.functional.tree.BlockLogBase;
import valoeghese.valoeghesesbe.functional.tree.BlockSaplingBase;
import valoeghese.valoeghesesbe.functional.tree.nz.BlockLeavesPohutukawa;

public class ModBlocks
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	/**
	 * Ghost and Pine wood is not registered with this lookup. Access them manually.
	 */
	public static final Map<String, Block> WOOD_LOOKUP = new HashMap<String, Block>()
	{
		
		{
			this.addWoodForWithPrefix("palm", "zoesteria_");
			this.addWoodForWithPrefix("palm_dark", "zoesteria_", true);
			this.addWoodForWithPrefix("peach", "zoesteria_");
			this.addWoodForWithPrefix("plum", "zoesteria_");
		}

		Map addWoodFor(String wood_name)
		{
			Block LOG = new BlockLogBase("log_".concat(wood_name), wood_name);
			Block PLANK = new BlockPlanksBase("planks_".concat(wood_name));
			Block STAIR = new BlockStairsBase("stairs_".concat(wood_name));
			Block SLAB = new BlockSlabBase("slab_".concat(wood_name), PLANK.getDefaultState(), false);

			this.put("LOG_".concat(wood_name.toUpperCase()), LOG);
			this.put("PLANKS_".concat(wood_name.toUpperCase()), PLANK);
			this.put("PLANKS_STAIRS_".concat(wood_name.toUpperCase()), STAIR);
			this.put("PLANKS_SLAB_".concat(wood_name.toUpperCase()), SLAB);

			return this;
		}
		Map addWoodForWithPrefix(String wood_name, String prefix)
		{
			return this.addWoodForWithPrefix(wood_name, prefix, false);
		}
		Map addWoodForWithPrefix(String wood_name, String prefix, boolean logOnly)
		{
			Block LOG = new BlockLogBase("log_".concat(prefix.concat(wood_name)), prefix.concat(wood_name));
			this.put("LOG_".concat(wood_name.toUpperCase()), LOG);
			if (!logOnly)
			{
				Block PLANK = new BlockPlanksBase("planks_".concat(prefix.concat(wood_name)));
				Block STAIR = new BlockStairsBase("stairs_".concat(prefix.concat(wood_name)));
				Block SLAB = new BlockSlabBase("slab_".concat(prefix.concat(wood_name)), PLANK.getDefaultState(), false);
				this.put("PLANKS_".concat(wood_name.toUpperCase()), PLANK);
				this.put("PLANKS_STAIRS_".concat(wood_name.toUpperCase()), STAIR);
				this.put("PLANKS_SLAB_".concat(wood_name.toUpperCase()), SLAB);
			}

			return this;
		}
	};

	public static final Block VANADIUM_BLOCK = new VanadiumBlock("vanadium_block", Material.IRON);

	public static final Block ORE_VANADIUM = new RockOreBase("ore_vanadium", Material.ROCK, 2);
	public static final Block ORE_SULPHUR = new OreSulphur("ore_sulphur", Material.ROCK, 1);
	public static final Block ORE_SALTPETER = new OreSaltpeter("ore_saltpeter", Material.SAND);

	public static final Block SOIL_DRY = new BlockDeadSoil("soil_dry", Material.GROUND);
	public static final Block SOIL_DRY_GRASS = new BlockDeadSoil("brushland_grass", Material.GRASS /*, Item.getItemFromBlock(SOIL_DRY)*/);
	public static final Block SOIL_WASTE = new BlockDeadSoil("soil_waste", Material.GROUND);
	public static final Block SOIL_WET = new BlockDeadSoil("soil_wet", Material.CLAY);

	public static final Block SAPLING_PALM = new BlockSaplingBase("sapling_oasis_palm", "oasis_palm");
	public static final Block LEAVES_PALM = new BlockLeavesBase("leaves_oasis_palm", SAPLING_PALM);
	
	public static final Block TRUE_TNT = new BlockTrueTNT("tnt_true");

	public static final Block LOG_EVIL = new BlockLogBase("log_forest_evil", "forest_evil");
	public static final Block LEAVES_EVIL = new BlockLeavesEvil("leaves_forest_evil");

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

	public static final Block SHRUB_DUNE = new BlockModdedShrub("shrub_dune", true, 0.54D);
	public static final Block SHRUB_DUNE_LARGE = new BlockModdedShrub("shrub_dune_large", true);

	public static final Block SAPLING_OCEAN_PALM = new BlockSaplingBase("sapling_ocean_palm", "ocean_palm");
	public static final Block LEAVES_OCEAN_PALM = new BlockLeavesBase("leaves_ocean_palm", SAPLING_OCEAN_PALM);
	
	public static final Block SAPLING_DATE_PALM = new BlockSaplingBase("sapling_date_palm", "date_palm");
	public static final Block LEAVES_DATE_PALM = new BlockLeavesFruit("leaves_date_palm", SAPLING_DATE_PALM);
	
	public static final Block LEAVES_POHUTUKAWA = new BlockLeavesPohutukawa("leaves_pohutukawa");
	public static final Block LEAVES_POHUTUKAWA_BUD = new BlockLeavesPohutukawa("leaves_pohutukawa_bud");
	public static final Block LEAVES_POHUTUKAWA_FLOWER = new BlockLeavesPohutukawa("leaves_pohutukawa_flower");
	public static final Block SAPLING_POHUTUKAWA = new BlockSaplingBase("sapling_pohutukawa", "pohutukawa");

	public static final Block SAPLING_MANUKA = new BlockSaplingBase("sapling_manuka", "manuka");
	public static final Block LEAVES_MANUKA = new BlockLeavesBase("leaves_manuka", SAPLING_MANUKA);

	public static final Block SAPLING_ISLAND_PALM = new BlockSaplingBase("sapling_island_palm", "island_palm")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
		{
			tooltip.add("Cannot grow in cold climates");
		}
	};
	public static final Block LEAVES_ISLAND_PALM = new BlockLeavesBase("leaves_island_palm", SAPLING_ISLAND_PALM);

	public static final Block SMALL_BUSH = new BlockSmallBush("bush_small");
	
	public static final Block LEAFCARPET_OAK = new BlockLeafLitter("oak");
	public static final Block LEAFCARPET_SPRUCE = new BlockLeafLitter("spruce");

	public static final Block SAPLING_PEACH_PP = new BlockSaplingPeach("sapling_peach_homozygous_dominant", BlockLeavesPeach.EnumGenotype.PP);
	public static final Block SAPLING_PEACH_Pp = new BlockSaplingPeach("sapling_peach_heterozygous_0", BlockLeavesPeach.EnumGenotype.Pp);
	public static final Block SAPLING_PEACH_pP = new BlockSaplingPeach("sapling_peach_heterozygous_1", BlockLeavesPeach.EnumGenotype.pP);
	public static final Block SAPLING_PEACH_pp = new BlockSaplingPeach("sapling_peach_homozygous_reccessive", BlockLeavesPeach.EnumGenotype.pp);
	
	public static final Block LEAVES_PEACH_PP = new BlockLeavesPeach("leaves_peach_homozygous_dominant", BlockLeavesPeach.EnumGenotype.PP);
	public static final Block LEAVES_PEACH_Pp = new BlockLeavesPeach("leaves_peach_heterozygous_0", BlockLeavesPeach.EnumGenotype.Pp);
	public static final Block LEAVES_PEACH_pP = new BlockLeavesPeach("leaves_peach_heterozygous_1", BlockLeavesPeach.EnumGenotype.pP);
	public static final Block LEAVES_PEACH_pp = new BlockLeavesPeach("leaves_peach_homozygous_reccessive", BlockLeavesPeach.EnumGenotype.pp);

	public static final Block SAPLING_PLUM = new BlockSaplingBase("sapling_plum", "plum");
	public static final Block LEAVES_PLUM = new BlockLeavesBase("leaves_plum", SAPLING_PLUM);

	public static final Block COCONUT = new BlockCoconut("island_coconut_block");
}
