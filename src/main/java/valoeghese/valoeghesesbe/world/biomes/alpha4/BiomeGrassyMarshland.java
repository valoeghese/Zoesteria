package valoeghese.valoeghesesbe.world.biomes.alpha4;

import java.util.Random;

import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.init.ModBlocks;

public class BiomeGrassyMarshland extends Biome
{
	
	private final boolean isMarshland = true;
	
	public BiomeGrassyMarshland(String name, float baseHeight, float heightVariation)
	{
		super(new BiomeProperties(name).setBaseHeight(baseHeight).setHeightVariation(heightVariation).setRainfall(1.3F).setTemperature(0.5F).setWaterColor(14745518).setBaseBiome("Grassy Fen"));
		this.decorator.andesiteGen = new WorldGenMinable(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), 5);
		this.decorator.grassPerChunk = 20;
		this.decorator.flowersPerChunk = 1;
		this.decorator.waterlilyPerChunk = 2;
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
	}
	
	protected boolean treatAsAir(IBlockState state)
	{
		if (state.getMaterial() == Material.AIR || state.getMaterial() == Material.WATER) return true;
		else return false;
	}
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer primerIn, int x, int z, double noiseVal)
	{

		if (noiseVal > 2)
		{
			if (noiseVal > 3.6D)
			{
				this.topBlock = ModBlocks.SOIL_WET.getDefaultState();
			} else {
				this.topBlock = Blocks.GRASS.getDefaultState();
			}
			this.fillerBlock = ModBlocks.SOIL_WET.getDefaultState();
		} else {
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = Blocks.DIRT.getDefaultState();
		}
		
		double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);

		int x1 = x & 15;
        int z1 = z & 15;
        int i2 = worldIn.getSeaLevel();
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        boolean flag = Math.cos(noiseVal / 3.0D * Math.PI) > 0.0D;
        int l = -1;
        boolean flag1 = false;
        int i1 = 0;

        for (int j1 = 255; j1 >= 0; --j1)
        {
        	
        	if (rand.nextInt(5) >= j1)
        	{
        		primerIn.setBlockState(x1, j1, z1, BEDROCK);
        		continue;
        	}
        	IBlockState iblockstate1 = primerIn.getBlockState(x1, j1, z1);
        	
        	if (iblockstate1.getBlock() == Blocks.STONE)
        	{
        		if(j1 < 50)
        		{
        			primerIn.setBlockState(x1, j1, z1, Blocks.STONE.getDefaultState());
        		} else {
        			if (primerIn.getBlockState(x1, j1 + 1, z1).getMaterial() == Material.AIR ) primerIn.setBlockState(x1, j1, z1, this.topBlock);
            		else if (primerIn.getBlockState(x1, j1 + 2, z1).getMaterial() == Material.AIR ) primerIn.setBlockState(x1, j1, z1, this.fillerBlock);
            		else if (primerIn.getBlockState(x1, j1 + 3, z1).getMaterial() == Material.AIR ) primerIn.setBlockState(x1, j1, z1, this.fillerBlock);
            		else primerIn.setBlockState(x1, j1, z1, Blocks.STONE.getDefaultState());
        		}
        	}
        	else if (this.treatAsAir(iblockstate1))
        	{
        		if (j1 < 62 && j1 > 43)
        		{
        			primerIn.setBlockState(x1, j1, z1, this.fillerBlock);
        		} else if (j1 == 62)
        		{
        			if (rand.nextInt(2) == 0) primerIn.setBlockState(x1, j1, z1, this.topBlock);
        			else primerIn.setBlockState(x1, j1, z1, Blocks.WATER.getDefaultState());
        		} else {
        			primerIn.setBlockState(x1, j1, z1, Blocks.AIR.getDefaultState());
        		}
        	}
        }
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return new WorldGenShrub(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		return 0x1fa559;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos)
	{
		return 0x1fa559;
	}

}
