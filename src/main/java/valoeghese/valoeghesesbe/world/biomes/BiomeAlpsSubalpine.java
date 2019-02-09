package valoeghese.valoeghesesbe.world.biomes;

import java.util.Random;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeAlpsSubalpine extends Biome
{
	
	private boolean alpsTrees;
	protected static final WorldGenAbstractTree SPRUCE_GENERATOR = new WorldGenTaiga2(false);

	public BiomeAlpsSubalpine(String name, float heightVariation, float BaseHeight, boolean hasTrees)
	{
		
		super( new BiomeProperties(name).setHeightVariation(heightVariation).setBaseHeight(BaseHeight).setRainfall(0.3F).setTemperature(0.3F));
		
        this.decorator.flowersPerChunk = -999;
        
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        
        this.spawnableMonsterList.add(new SpawnListEntry( EntitySkeleton.class, 2, 1, 1 ));
        
        this.alpsTrees = hasTrees;
        
        if (hasTrees)
        {
			this.decorator.treesPerChunk = 4;
			this.decorator.extraTreeChance = 0.1F;
			this.decorator.grassPerChunk = 1;

			this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 14, 3, 7));
			this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 2, 3, 7));
        }
	}
	
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
		if (this.alpsTrees)
		{
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = Blocks.STONE.getDefaultState();

			if (noiseVal > 4.95D)
			{
				this.topBlock = Blocks.STONE.getDefaultState();
			}
			
		} else {
			
			this.topBlock = Blocks.SNOW.getDefaultState();
    		this.fillerBlock = Blocks.SNOW.getDefaultState();

            if ( (noiseVal > 4.12D) || (noiseVal < -0.1D && noiseVal > -0.17D) )
            {
                this.topBlock = Blocks.STONE.getDefaultState();
                this.fillerBlock = Blocks.STONE.getDefaultState();
            }
			
		}
		
		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		
		return (WorldGenAbstractTree)SPRUCE_GENERATOR;
		
	}

}
