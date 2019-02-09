package valoeghese.valoeghesesbe.world.biomes.alpha2;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;


public class BiomeFlowerField extends Biome
{
	
	/*
	 * Idk why I still use this. Probably will remove in future.
	 */
	public ZoesteriaDecoratorAlpha3 dec = new ZoesteriaDecoratorAlpha3();
	
	public BiomeFlowerField()
	{
		super(new Biome.BiomeProperties("Orchid Fields").setHeightVariation(0.001F).setRainfall(0.8F).setTemperature(0.7F).setBaseHeight(0.15F));
		
		this.dec.grassPerChunk = 0;
		this.dec.treesPerChunk = 0;
		this.dec.extraTreeChance = 0.05F;
		this.dec.flowersPerChunk = 150;
		
	}
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		this.dec.decorate(worldIn, rand, this, pos);
	}
	
	@Override
	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        return BlockFlower.EnumFlowerType.BLUE_ORCHID;
    }
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return new WorldGenShrub(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState());
	}
}