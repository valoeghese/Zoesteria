package valoeghese.valoeghesesbe.items;

import net.minecraft.item.ItemFood;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.init.ModItems;
import valoeghese.valoeghesesbe.util.IHasModel;

public class ItemFoodBase extends ItemFood implements IHasModel
{

	public ItemFoodBase(String name, int amount, float saturation, boolean isWolfFood)
	{
		super(amount, saturation, isWolfFood);
		
		this.setUnlocalizedName(name); this.setRegistryName(name);
		
		this.setCreativeTab(Main.tabWorld);
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels()
	{
		Main.proxy.registerModel(this, 0);
	}

}
