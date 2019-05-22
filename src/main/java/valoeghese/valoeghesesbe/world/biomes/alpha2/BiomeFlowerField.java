package valoeghese.valoeghesesbe.world.biomes.alpha2;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import valoeghese.valoeghesesbe.world.worldtype.ILakeRemover;


public class BiomeFlowerField extends Biome implements ILakeRemover
{
	
	public final EnumFlowerType flower;
	
	public BiomeFlowerField()
	{
		this(EnumFlowerType.BLUE_ORCHID);
	}
	
	public BiomeFlowerField(EnumFlowerType type)
	{
		super(new Biome.BiomeProperties("Orchid Fields").setHeightVariation(0.001F).setRainfall(0.8F).setTemperature(0.7F).setBaseHeight(0.15F));
		
		this.decorator.grassPerChunk = 0;
		this.decorator.treesPerChunk = 0;
		this.decorator.extraTreeChance = 0.05F;
		this.decorator.flowersPerChunk = 150;
		
		this.flower = type;
	}
	
	@Override
	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        return this.flower;
    }
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return new WorldGenShrub(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState());
	}
}