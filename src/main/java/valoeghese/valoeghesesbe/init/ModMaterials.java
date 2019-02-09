package valoeghese.valoeghesesbe.init;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import valoeghese.valoeghesesbe.util.Reference;

public class ModMaterials
{
	
	private static final ArmorMaterial VANADIUM_CHAIN = EnumHelper.addArmorMaterial("chain_vanadium", Reference.MOD_ID + ":vanad", 38, new int[]{3,5,7,2}, 16, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 4.0F);
	
	public static ArmorMaterial getVanadium()
	{
		return VANADIUM_CHAIN;
	}

}
