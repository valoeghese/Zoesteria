package valoeghese.valoeghesesbe.world.biomes.alpha5;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.archived.BlockLeavesPalm;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.world.biomes.biomeutil.IBiomeFog;
import valoeghese.valoeghesesbe.world.trees.evil.WorldGenEvilTree;
import valoeghese.valoeghesesbe.world.trees.evil.WorldGenEvilTree2;

public class BiomeGhostForest extends Biome implements IBiomeFog
{

	public BiomeGhostForest()
	{
		super(new Biome.BiomeProperties("Ghost Forest").setBaseHeight(0.45F).setHeightVariation(0.4F).setRainfall(0.8F).setTemperature(0.95F).setWaterColor(0x3c3c3c));
		
		//spawnable lists
		this.spawnableCreatureList.clear();
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntityStray.class, 60, 10, 10));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityCreeper.class, 50, 10, 10));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 80, 10, 10));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 180, 10, 10));
		
		//decorator
		
		this.decorator.treesPerChunk = 50;
		this.decorator.grassPerChunk = 7;
		this.decorator.flowersPerChunk = -999;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		
		if (rand.nextInt(9) == 0)
		{
			if (rand.nextInt(3) == 0)
			{
				return new WorldGenEvilTree2();
			} else {
				return new WorldGenEvilTree();
			}
		}
		else if (rand.nextInt(5) > 3)
		{
			return new WorldGenShrub(ModBlocks.LOG_EVIL.getDefaultState(), ModBlocks.LEAVES_EVIL.getDefaultState().withProperty(BlockLeavesPalm.CHECK_DECAY, Boolean.valueOf(false)));
		}
		else {
			if (rand.nextBoolean())
			{
				return new WorldGenTrees(false, 6, ModBlocks.LOG_EVIL.getDefaultState(), ModBlocks.LEAVES_EVIL.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)), true);
			} else {
				return new WorldGenTrees(false, 4, ModBlocks.LOG_EVIL.getDefaultState(), ModBlocks.LEAVES_EVIL.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)), true);
			}
		}
	}
	
	@Override
	public int getSkyColorByTemp(float temp)
	{
		return 0xa1afac;
	}
	@Override
	public int getFogStrength()
	{
		return 4;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x7b8b7b;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x5b635b;
    }
}
