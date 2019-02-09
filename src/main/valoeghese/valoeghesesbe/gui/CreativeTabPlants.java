package valoeghese.valoeghesesbe.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import valoeghese.valoeghesesbe.init.ModBlocks;

public class CreativeTabPlants extends CreativeTabs
{
	
	public CreativeTabPlants() 
	{
		super("zbiome_world");
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ModBlocks.SHRUB_DUNE_LARGE);
	}
	
}
