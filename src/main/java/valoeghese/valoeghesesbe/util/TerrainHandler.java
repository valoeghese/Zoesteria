package valoeghese.valoeghesesbe.util;

import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import valoeghese.valoeghesesbe.init.ModBiomes;
import valoeghese.valoeghesesbe.init.ModBlocks;

public class TerrainHandler
{
	@SubscribeEvent
	public static void genBlocks(GetVillageBlockID event)
	{
		if (event.getBiome() == ModBiomes.VBE_CHRISTMAS_WOODS_OAK)
		{
			Console.WriteLine("GRWGWRBWRBWRBWBWRBWR");
			//event.setCanceled(true);
			
			if (event.getOriginal().getBlock() == Blocks.PLANKS)
			{
				event.setReplacement(ModBlocks.PLANKS_PINE.getDefaultState());
			}
			else if (event.getOriginal().getBlock() == Blocks.LOG)
			{
				event.setReplacement(ModBlocks.LOG_PINE.getDefaultState());
			}
			else if (event.getOriginal().getBlock() == Blocks.COBBLESTONE)
			{
				event.setReplacement(ModBlocks.PLANKS_PINE.getDefaultState());
			}
		}
	}
}
