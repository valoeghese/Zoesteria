package valoeghese.valoeghesesbe.world;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import valoeghese.valoeghesesbe.config.ConfigHandler;
import valoeghese.valoeghesesbe.config.ZfgHelper;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.util.Console;
import valoeghese.valoeghesesbe.util.exception.SyntaxException;
import valoeghese.valoeghesesbe.util.handlers.RegistryHandler;

public class ModWorldGeneration implements IWorldGenerator
{
	
	private static FileReader staticReader;
	private static ArrayList<Integer> oreChances = new ArrayList<Integer>();
	
	@Deprecated
	private static ArrayList<String> oreChanceComponentLines = new ArrayList<String>();
	
	private static char[] readerChars = new char[Short.MAX_VALUE];
	private static ConfigHandler oreChanceConfig;
	
	private static boolean loadedFiles = false;
	private static Map<String, String> chances;
	private static Map<String, String> allow;
	static {
		
		try
		{
			
			if ( staticReader == null )
			{
				
				staticReader = new FileReader(RegistryHandler.getConfigFile());
				
				staticReader.read(readerChars);
				oreChanceConfig = new ConfigHandler(readerChars, false);
				
				chances = oreChanceConfig.getContainer("Chances");
				
				System.out.println(String.format("Zoesteria has loaded Module ORE_CONFIG: Saltpeter:%s, Sulphur:%s.", chances.get("Saltpeter"), chances.get("Sulphur")));
				staticReader.close();
				
			}
			
			if (!loadedFiles)
			{
				loadedFiles = true;
				
				allow = ZfgHelper.getMapOf(RegistryHandler.getAllowOres(), "ConfigOre");
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		
		if (world.provider.getDimension() == 0)
		{
			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
		
	}
	
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if (Boolean.parseBoolean(allow.get("MasterGenVanadium"))) generateOre(ModBlocks.ORE_VANADIUM.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 4, 32, random.nextInt(5) + 2, 6);
		if (Boolean.parseBoolean(allow.get("MasterGenSulphur"))) generateOre(ModBlocks.ORE_SULPHUR.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 30, 100, random.nextInt(12) + 5, Integer.parseInt(chances.get("Sulphur")));
		if (Boolean.parseBoolean(allow.get("MasterGenSaltpeter"))) generateOre(ModBlocks.ORE_SALTPETER.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 50, 80, random.nextInt(6) + 3, Integer.parseInt(chances.get("Saltpeter")));
	}
	
	private void generateOre(IBlockState ore, World world, Random rand, int x, int z, int minY, int maxY, int size, int chances)
	{
		
		int deltaY = maxY - minY;
		
		for (int i = 0; i < chances; i++)
		{
			
			BlockPos pos = new BlockPos(x + rand.nextInt(16), minY + rand.nextInt(deltaY), z + rand.nextInt(16));
			
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, rand, pos);
			
		}
		
	}

}
