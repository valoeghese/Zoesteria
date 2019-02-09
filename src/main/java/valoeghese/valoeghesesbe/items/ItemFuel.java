package valoeghese.valoeghesesbe.items;

import net.minecraft.item.ItemStack;

public class ItemFuel extends ItemBase
{
	private final int burnTicks;
	
	public ItemFuel(String name, int burnTicksIn)
	{
		super(name);
		
		this.burnTicks = burnTicksIn;
	}
	
	@Override
	public int getItemBurnTime(ItemStack itemStackIn)
	{
		return this.burnTicks;
	}

}
