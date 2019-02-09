package valoeghese.valoeghesesbe.init;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class ModRecipes
{
	
	public static void init()
	{
		
		GameRegistry.addSmelting(ModBlocks.ORE_VANADIUM, new ItemStack(ModItems.INGOT_VANADIUM, 1), 4.5F);
		GameRegistry.addSmelting(ModBlocks.ORE_SULPHUR, new ItemStack(ModItems.SULPHURROCK, 1), 3.0F);
		GameRegistry.addSmelting(ModBlocks.ORE_SALTPETER, new ItemStack(ModItems.SALTPETER, 2), 3.0F);
		
		GameRegistry.addSmelting(ModItems.COAL2, new ItemStack(ModItems.BYPRODUCTS, 1), 0.4F);
	}
	
}
