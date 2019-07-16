package valoeghese.valoeghesesbe.world.biomes.alpha4;

import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.world.trees.WorldGenDatePalm;
import valoeghese.valoeghesesbe.world.trees.queenpalm.WorldGenQueenPalm;
import valoeghese.valoeghesesbe.world.worldtype.ILakeRemover;

public class BiomeSandDunes extends Biome implements ILakeRemover
{
	
	private final boolean isOasis;
	
	public BiomeSandDunes(BiomeProperties properties, boolean isSandRed, boolean isOasis)
	{
		super(properties);
		
		if (isOasis)
		{
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = ModBlocks.SOIL_WET.getDefaultState();
		} else if (isSandRed) {
			this.topBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
			this.fillerBlock = Blocks.RED_SANDSTONE.getDefaultState();
		} else {
			this.topBlock = Blocks.SAND.getDefaultState();
			this.fillerBlock = Blocks.SANDSTONE.getDefaultState();
		}
		
		this.decorator.generateFalls = false;
		
		this.isOasis = isOasis;
		
		if (isOasis)
		{
			this.decorator.treesPerChunk = 1;
		} else {
			this.decorator.treesPerChunk = -999;
			this.decorator.flowersPerChunk = -999;
			this.decorator.extraTreeChance = -999F;
			this.decorator.grassPerChunk = -999;
		}
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 10, 2, 6));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 7, 1, 6));
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityHusk.class, 3, 1, 6));
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		if (this.isOasis)
		{
			return (WorldGenAbstractTree)(rand.nextInt(100) == 0 ? new WorldGenQueenPalm() : new WorldGenDatePalm());
		} else {
			return new WorldGenShrub(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState());
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getGrassColorAtPos(BlockPos pos)
	{
		
		if (this.isOasis)
	    {
			return 0x21bc24;
	    } else {
	    	
	    	return super.getGrassColorAtPos(pos);
	    }
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos)
	{
		
		if (this.isOasis)
	    {
			return 0x00A037;
	    } else {
	    	
	    	return super.getFoliageColorAtPos(pos);
	    }
		
	}

}
