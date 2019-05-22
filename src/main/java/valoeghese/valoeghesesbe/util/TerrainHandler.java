package valoeghese.valoeghesesbe.util;

import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import valoeghese.valoeghesesbe.init.ModBiomes;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.world.worldtype.ILakeRemover;

public class TerrainHandler
{
	/*
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
	*/
	
	@SubscribeEvent
	public static void populationEvent(Populate event)
	{
		if (event.getType() == Populate.EventType.LAKE)
		if (event.getWorld().getBiome(new BlockPos(event.getChunkX() * 16, 64, event.getChunkZ() * 16)) instanceof ILakeRemover)
		{
			event.setResult(Result.DENY);
		}
	}
}
