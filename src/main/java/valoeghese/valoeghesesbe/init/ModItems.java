package valoeghese.valoeghesesbe.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.functional.equipable.ArmourBase;
import valoeghese.valoeghesesbe.items.ItemBase;
import valoeghese.valoeghesesbe.items.ItemByproducts;
import valoeghese.valoeghesesbe.items.ItemFertilizer;
import valoeghese.valoeghesesbe.items.ItemFoodBase;
import valoeghese.valoeghesesbe.items.ItemFuel;

public class ModItems
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item INGOT_VANADIUM = new ItemBase("ingot_vanadium");
	public static final Item NUGGET_VANADIUM = new ItemBase("nugget_vanadium");
	public static final Item FLOWER_POHUTUKAWA = new ItemBase("flower_pohutukawa");
	public static final Item SULPHURROCK = new ItemBase("sulphurrock");
	public static final Item SALTPETER = new ItemFertilizer("saltpeter");
	public static final Item CHAIN_VANADIUM = new ItemBase("chain_vanadium");
	public static final Item TOLUENE = new ItemBase("toluene") {
		
		@Override
		@SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	    {
			tooltip.add("Obtained from Coal Products");
	    }
	};
	
	public static final Item ACID_NITRIC = new ItemBase("acid_nitric") {
		@Override
		@SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	    {
			tooltip.add("Broken trade ships may carry this");
	    }
	};
	public static final Item TNT = new ItemBase("bottle_trinitrotoluene");
	
	public static final Item COAL2 = new ItemFuel("filtered_coal", 1600);
	
	public static final Item BYPRODUCTS = new ItemByproducts("products_coal")
	{
		@Override
		@SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	    {
			tooltip.add("Right click while holding to sift through the products");
	    }
	};
	public static final Item FUEL_COKE = new ItemFuel("fuel_coke", 2000)
	{
		@Override
		@SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	    {
			tooltip.add("Obtained from Coal Products");
	    }
	};
	
	public static final Item BERRY = new ItemFoodBase("berry_bush", 1, 1, false);
	public static final Item COCONUT_GREEN = new ItemFoodBase("island_coconut_0", 3, 3.4F, false);
	public static final Item COCONUT_BROWN = new ItemFoodBase("island_coconut_1", 4, 1.4F, false);
	public static final Item PEACH = new ItemFoodBase("fruit_peach", 4, 0.4F, false);
	public static final Item NECTARINE = new ItemFoodBase("fruit_nectarine", 4, 0.3F, false);
	public static final Item PLUM = new ItemFoodBase("fruit_plum", 3, 0.4F, false);
	
	//Chestplate
	public static final Item VANADIUM_CHEST = new ArmourBase("armour_chestplate_vanadium", ModMaterials.getVanadium(), 1, EntityEquipmentSlot.CHEST);
	
}
