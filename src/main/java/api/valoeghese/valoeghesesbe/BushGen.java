package api.valoeghese.valoeghesesbe;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BushGen
{
	
	/*
	 *  The instances of BushGens are found in the Main method.
	 *  
	 *  PLEASE NOTE in the Init stage biomes with type PLAINS are blacklisted and removed from the list
	 */
	
	public Set<Biome> allowedBiomes = new HashSet<>();
	
	protected Set<Biome> blacklist = new HashSet<>();
	
	private Type[] types;
	//protected final boolean restrictedTo; -- Was buggy so removed
	
	/**
	 * The IBlockState spawned by this BushGen <p/>
	 * Yes, you can mess with this if you wish.
	 */
	public IBlockState gen;
	
	public Set<Class> getAllowedBiomeClasses()
	{
		Set<Class> temp = new HashSet<Class>();
		
		for (Biome b : this.allowedBiomes) temp.add(b.getClass());
		
		return temp;
	}
	
	public BushGen(IBlockState bush, Type...types)
	{
		this.types = types;
		this.gen = bush;
	}
	
	public boolean canGenInBiome(Biome b)
	{
		return this.allowedBiomes.contains(b); 
	}
	
	/**
	 * 
	 * Loads or Reloads the set of allowed biomes
	 * 
	 * @return the object calling this method
	 */
	public BushGen load()
	{
		return loadUnrestricted();
	}
	
	/**
	 * 
	 * Seems buggy and I'm too tired at the moment to fix this
	 */
	@Deprecated
	private BushGen loadRestricted()
	{
		if (types.length == 1) return this.loadUnrestricted();
		else
		{
			for (Biome b : BiomeDictionary.getBiomes(this.types[0]))
			{
				
				if (this.blacklist.contains(b)) continue;
				
				boolean allow = true;
				for (Type t : BiomeDictionary.getTypes(b))
				{
					if (!BiomeDictionary.hasType(b, t))
					{
						allow = false;
						break;
					}
				}
				if (allow) this.allowedBiomes.add(b);
			}
			return this;
		}
	}
	private BushGen loadUnrestricted()
	{
		for (Type t : types)
		{
			for (Biome b : BiomeDictionary.getBiomes(t))
			{
				if (!this.blacklist.contains(b)) this.allowedBiomes.add(b);
			}
		}
		return this;
	}
	
	public BushGen removeBiomeFromSet(Biome b)
	{
		this.allowedBiomes.remove(b);
		this.blacklist.add(b);
		
		return this;
	}
}
