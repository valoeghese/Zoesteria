package valoeghese.valoeghesesbe.world.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import akka.dispatch.ThreadPoolConfig;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import valoeghese.valoeghesesbe.util.Console;

/**
 * 
 * Uhhh doesn't seem to work. Keep getting false
 *
 */
@Deprecated
public class WorldGenMoorlandLake extends WorldGenerator
{
	
	protected IBlockState liquid = Blocks.WATER.getDefaultState();
	private Map<Integer, IBlockState> weights = new HashMap<Integer, IBlockState>();
	//private ArrayList<IBlockState> keys = new ArrayList<IBlockState>();
	private int weightSum = 0;
	
	protected ArrayList<Integer> bounds = new ArrayList<>();
	protected ArrayList<Integer> boundsNegative = new ArrayList<>();
	
	public WorldGenMoorlandLake()
	{
	}
	
	public WorldGenMoorlandLake addBounds(IBlockState boundary, int weight)
	{
		//keys.add(boundary);
		
		for (int i=0; i <= weight; ++i)
		{
			weights.put(weight, boundary);
		}
		weightSum += weight;
		
		return this;
	}
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		final int size = 3 + rand.nextInt(5);
		final int y = worldIn.getHeight(position).getY();
		
		int x = position.getX();
		int z = position.getZ();
		
		boolean flag = true;
		
		BlockPos pos = new BlockPos(x, y, z);
		
		for (int x1 = 0; (x1 <= size*2) || (x1 <= rand.nextInt(5)); ++x1)
		{
			int z1, z2;
			for (z1 = 0; (z1 <= size); ++z1)
			{
				IBlockState state1 = worldIn.getBlockState(pos.add(x1, -1, z1));
				if (state1.getMaterial() == Material.AIR || z1 == size || z1 < rand.nextInt(5))
				{
					if (z1 == 0) { flag = false; break; }
					else this.bounds.add(z1);
				}
			}
			for (z2 = 0; (MathHelper.abs(z2) <= size); --z2)
			{
				IBlockState state1 = worldIn.getBlockState(pos.add(x1, -1, z2));
				
				if (state1.getMaterial() == Material.AIR || z2 == size || z2 < rand.nextInt(5))
				{
					if (z2 == 0) { flag = false; break; }
					else this.boundsNegative.add(z2); break;
				}
			}
		}
		
		//Console.WriteLine(String.valueOf(flag));
		if (!flag)
		{
			return false;
		}
		else
		{
			for (int j1 = 0; j1 <= this.bounds.size(); ++j1)
			{
				int min = this.boundsNegative.get(j1);
				int max = this.bounds.get(j1);
				for (int j2 = min; j2 <= max; ++j2)
				{
					if (j1 == 0 || j1 == this.bounds.size())
					{
						this.setBound(worldIn, rand, pos.add(j1, 0, j2));
					} else if (j2 == this.bounds.get(j1) || j2 == this.boundsNegative.get(j1))
					{
						this.setBound(worldIn, rand, pos.add(j1, 0, j2));
					} else
					{
						this.setBlockAndNotifyAdequately(worldIn, pos.add(j1, 0, j2), liquid);
					}
				}
			}
			
			return true;
		}
	}

	private void setBound(World worldIn, Random rand, BlockPos pos)
	{
		int fdsdffs = rand.nextInt(this.weightSum + 1);
		
		IBlockState state = this.weights.get(Integer.valueOf(fdsdffs));
	}

}
