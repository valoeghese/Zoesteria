package valoeghese.valoeghesesbe.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.init.ModItems;

public class CreativeTabMisc extends CreativeTabs
{

	public CreativeTabMisc() 
	{
		super("zbiome_misc");
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ModItems.BYPRODUCTS);
	}
	
}
