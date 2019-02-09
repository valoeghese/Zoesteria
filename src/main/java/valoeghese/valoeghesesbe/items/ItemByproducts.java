package valoeghese.valoeghesesbe.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import valoeghese.valoeghesesbe.init.ModItems;

public class ItemByproducts extends ItemBase
{

	public ItemByproducts(String name)
	{
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if (!playerIn.isCreative())
		{
			ItemStack item = playerIn.getHeldItem(handIn);
			item.shrink(1);
			collectItem(playerIn, ModItems.FUEL_COKE);
			collectItem(playerIn, ModItems.TOLUENE);
			
			playerIn.addItemStackToInventory(new ItemStack(ModItems.TOLUENE));
			
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		} else {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
		}
        
    }
	
	protected static void collectItem(EntityPlayer playerIn, Item itemIn)
	{
		collectItem(playerIn, itemIn, 1);
	}
	
	protected static void collectItem(EntityPlayer playerIn, Item itemIn, int stack)
	{
		if (!playerIn.addItemStackToInventory(new ItemStack(itemIn, stack)))
		{
			//playerIn.entityDropItem(new ItemStack(itemIn, stack), 0.0F);
			playerIn.dropItem(new ItemStack(itemIn, stack), false);
		}
	}

}
