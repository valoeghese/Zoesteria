package valoeghese.valoeghesesbe.functional.equipable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.init.ModItems;
import valoeghese.valoeghesesbe.util.IHasModel;

public class ArmourBase extends ItemArmor implements IHasModel
{

	public ArmourBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{
		
		super(materialIn, renderIndexIn, equipmentSlotIn);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		
		ModItems.ITEMS.add(this);
		
	}

	@Override
	public void registerModels()
	{
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}

}
