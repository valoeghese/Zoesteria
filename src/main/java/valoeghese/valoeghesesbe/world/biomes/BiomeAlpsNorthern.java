package valoeghese.valoeghesesbe.world.biomes;

import java.util.Random;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeAlpsNorthern extends Biome
{
	
	private boolean alpsTrees;
	protected static final WorldGenAbstractTree SPRUCE_GENERATOR = new WorldGenTaiga2(false);
	protected static final WorldGenBlockBlob ALPS_ROCK = new WorldGenBlockBlob(Blocks.STONE, 1);
	
	public BiomeAlpsNorthern(String name, float heightVariation, float BaseHeight, boolean hasTrees)
	{
		
		super( new BiomeProperties(name).setSnowEnabled().setHeightVariation(heightVariation).setBaseHeight(BaseHeight).setRainfall(0.3F).setTemperature(0.1F).setBaseBiome("Northern Alps"));
		
		this.decorator.grassPerChunk = -999;
        this.decorator.flowersPerChunk = -999;
        
        this.alpsTrees = hasTrees;
        
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        
        this.spawnableMonsterList.add(new SpawnListEntry( EntitySkeleton.class, 2, 1, 1 ));
        
        if (hasTrees)
        {
        	
        	this.decorator.treesPerChunk = 5;
        	this.decorator.extraTreeChance = 0.1F;
        	this.decorator.grassPerChunk = 1;
        	
        	this.spawnableCreatureList.add(new SpawnListEntry( EntityWolf.class, 14, 3, 7 ));
        	this.spawnableCreatureList.add(new SpawnListEntry( EntitySheep.class, 2, 3, 7 ));
        	
        }
		
	}
	
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        if (!alpsTrees)
        {
        	this.topBlock = Blocks.SNOW.getDefaultState();
    		this.fillerBlock = Blocks.SNOW.getDefaultState();

            if ( (noiseVal > 4.15D + 1.5D*rand.nextDouble()) || (noiseVal < -0.1D && noiseVal > -0.17D) )
            {
                this.topBlock = Blocks.STONE.getDefaultState();
                this.fillerBlock = Blocks.STONE.getDefaultState();
            }
            
        } else {
        	
        	this.topBlock = Blocks.GRASS.getDefaultState();
    		this.fillerBlock = Blocks.DIRT.getDefaultState();
    		
    		if (noiseVal > 4.98D)
            {
                this.topBlock = Blocks.STONE.getDefaultState();
            }
        	
        }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		
		return (WorldGenAbstractTree)SPRUCE_GENERATOR;
		
	}
	
	
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		
		if ((net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ROCK)))
		{
			int i = rand.nextInt(3);

			for (int j = 0; j < i; ++j)
			{

				int k = rand.nextInt(16) + 8;
				int l = rand.nextInt(16) + 8;

				BlockPos blockpos = this.relativeHeight(worldIn, pos, -1);
				if (this.alpsTrees)
				{
					ALPS_ROCK.generate(worldIn, rand, blockpos.add(0, -1, 0));
				} else {
					ALPS_ROCK.generate(worldIn, rand, blockpos);
				}

			}
		}
		
		super.decorate(worldIn, rand, pos);
	}
	
	private BlockPos relativeHeight(World world, BlockPos loc, int shift)
	{
		return new BlockPos(loc.getX(), world.getHeight(loc.getX(), loc.getZ()) + shift, loc.getZ());
	}
	
	
	/*
	@Override
	public int getFogColourAtPos(BlockPos pos) {
		return 0xaaaaba;
	}
	*/

}
