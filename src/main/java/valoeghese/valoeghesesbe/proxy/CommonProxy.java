package valoeghese.valoeghesesbe.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class CommonProxy
{
	
	public void registerItemRenderer(Item item, int meta, String id) {}
	public void registerModel(Item item, int metadata) {}
	
	public void postInit(FMLPostInitializationEvent event) {}
	public void registerModelWithCustomResourceLocation(Item item, String resourceRegistryName, int metadata) {}
}
