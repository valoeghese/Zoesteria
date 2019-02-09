package valoeghese.valoeghesesbe.world.worldtype;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import valoeghese.valoeghesesbe.init.ModBiomes;

public class WorldTypeTesting extends WorldType
{
	
	public final Biome BIOME;
	public WorldTypeTesting(String typeNameIn, Biome biome)
	{
		super(typeNameIn);
		
		this.BIOME = biome;
	}
	
	//TODO custom chunk generator
	@Override
	public BiomeProvider getBiomeProvider(World world)
	{
		return new BiomeProviderSingle(BIOME);
	}

}
