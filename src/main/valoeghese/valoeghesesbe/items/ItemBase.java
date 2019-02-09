package valoeghese.valoeghesesbe.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.init.ModItems;
import valoeghese.valoeghesesbe.util.IHasModel;

public class ItemBase extends Item implements IHasModel
{
	
	public ItemBase(String name)
	{
		
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Main.tabMisc);
		
		ModItems.ITEMS.add(this);
		
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
