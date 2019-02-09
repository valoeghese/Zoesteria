package valoeghese.valoeghesesbe.world.biomes.alpha5;

import java.util.Random;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.world.trees.WorldGenBrush;

public class BiomeBrushlands extends Biome
{
	
	public BiomeBrushlands(BiomeProperties properties, int tpc)
	{
		this(properties, tpc, false);
	}
	public BiomeBrushlands(BiomeProperties properties, int tpc, boolean weirdSoil)
	{
		super(properties);
		
		if (weirdSoil)
		{
			this.topBlock = ModBlocks.SOIL_DRY_GRASS.getDefaultState();
			this.fillerBlock = ModBlocks.SOIL_DRY.getDefaultState();
		}
		
		this.decorator.treesPerChunk = tpc;
		this.decorator.flowersPerChunk = -999;
		
		this.spawnableCreatureList.clear();
		
		if (tpc > 2) this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 30, 2, 4));
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return new WorldGenBrush();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos)
	{
		if (this.getRainfall() == 0.1F)
		{
			return 0xc6a478;
			//return 0x78aa3a;
		} else {
			//return 0xbe9c73;
			return 0x679542; 
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		if (this.getRainfall() == 0.1F)
		{
			return 0xdae086;
			//return 0xb4d37a;
		} else {
			//return 0xafe086;
			return 0xa1b477; 
		}
	}
	
}