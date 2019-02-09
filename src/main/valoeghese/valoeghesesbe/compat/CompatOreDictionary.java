package valoeghese.valoeghesesbe.compat;

import net.minecraftforge.oredict.OreDictionary;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.init.ModItems;

public class CompatOreDictionary
{

	public static void oreDictionaryMats()
	{
		//ores
		OreDictionary.registerOre("oreVanadium", ModBlocks.ORE_VANADIUM);
		OreDictionary.registerOre("oreSulfur", ModBlocks.ORE_SULPHUR);
		OreDictionary.registerOre("oreSaltpeter", ModBlocks.ORE_SALTPETER);
		
		//items
		OreDictionary.registerOre("dustSulfur", ModItems.SULPHURROCK);
		OreDictionary.registerOre("dustSaltpeter", ModItems.SALTPETER);
		OreDictionary.registerOre("ingotVanadium", ModItems.INGOT_VANADIUM);
		
		//tree
		OreDictionary.registerOre("treeLeaves", ModBlocks.LEAVES_PALM);
		OreDictionary.registerOre("treeSapling", ModBlocks.SAPLING_PALM);
		OreDictionary.registerOre("treeLeaves", ModBlocks.LEAVES_BLUFF);
		OreDictionary.registerOre("treeSapling", ModBlocks.SAPLING_BLUFF);
		OreDictionary.registerOre("treeLeaves", ModBlocks.LEAVES_POHUTUKAWA);
		OreDictionary.registerOre("treeLeaves", ModBlocks.LEAVES_POHUTUKAWA_BUD);
		OreDictionary.registerOre("treeLeaves", ModBlocks.LEAVES_POHUTUKAWA_FLOWER);
		OreDictionary.registerOre("treeLeaves", ModBlocks.LEAVES_EVIL);
		OreDictionary.registerOre("treeSapling", ModBlocks.SAPLING_EVIL);
		OreDictionary.registerOre("logWood", ModBlocks.LOG_EVIL);
		OreDictionary.registerOre("logWood", ModBlocks.LOG_PINE);
		
		OreDictionary.registerOre("zoesteria1Plank", ModBlocks.PLANKS_PINE);
		OreDictionary.registerOre("zoesteria1Plank", ModBlocks.PLANKS_GHOST);
		OreDictionary.registerOre("plankWood", ModBlocks.PLANKS_PINE);
		OreDictionary.registerOre("plankWood", ModBlocks.PLANKS_GHOST);
		
		OreDictionary.registerOre("treeLeaves", ModBlocks.LEAVES_MANUKA);
		OreDictionary.registerOre("treeSapling", ModBlocks.SAPLING_MANUKA);
	}
	
}
