package valoeghese.valoeghesesbe.world.biomes.alpha3.macro;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.util.MCWorld;

public class BiomeSouthernAlps extends Biome
{
	
	public static enum Variant
	{
		EXTREME,
		DEFAULT,
		SUBALPINE;
		
		public boolean isAlpine()
		{
			if (this == SUBALPINE)
			{
				return false;
			} else {
				return true;
			}
		}
	}
	
	private Variant variant;
	public BiomeSouthernAlps(BiomeProperties properties, Variant variant)
	{
		super(properties);
		this.variant = variant;
		
		//Default Blocks
		if (variant.isAlpine())
		{
			this.topBlock = Blocks.SNOW.getDefaultState();
			this.fillerBlock = Blocks.STONE.getDefaultState();
		} else {
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = Blocks.DIRT.getDefaultState();
		}
		
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		//Decorator
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 32, 2, 4));
		if (variant.isAlpine())
		{
			this.decorator.extraTreeChance = 0.001F;
			this.decorator.flowersPerChunk = 0;
			this.decorator.treesPerChunk = 0;
			this.decorator.grassPerChunk = 1;
			if (variant == Variant.EXTREME)
			{
				this.spawnableMonsterList.add(new SpawnListEntry(EntityStray.class, 1, 1, 1));
				this.decorator.generateFalls = false;
			}
		} else {
			this.decorator.extraTreeChance = 0.15F;
			this.decorator.flowersPerChunk = 2;
			this.decorator.treesPerChunk = 1;
			this.decorator.grassPerChunk = 20;
			
			this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 14, 4, 6));
			this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 1, 1, 2));
		}
	}
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
		if (this.variant.isAlpine())
		{
			this.topBlock = Blocks.SNOW.getDefaultState();
		} else {
			
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = Blocks.DIRT.getDefaultState();
			
		}
		
		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
		
    }
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		if (this.variant.isAlpine())
		{
			return new WorldGenShrub(Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)), Blocks.AIR.getDefaultState());
		} else {
			return new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), Blocks.LEAVES.getDefaultState());
		}
	}
	
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		if (this.variant.isAlpine())
		{
			return 10022350;
		} else {
			return super.getGrassColorAtPos(pos);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos)
	{
		if (this.variant.isAlpine())
		{
			return 8511164;
		} else {
			return super.getFoliageColorAtPos(pos);
		}
	} 

}
