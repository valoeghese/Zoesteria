package valoeghese.valoeghesesbe.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import valoeghese.valoeghesesbe.init.ModItems;

public class OreSaltpeter extends BlockBase
{
	
	public OreSaltpeter(String name, Material material)
	{
		super(name, material);
		
		setSoundType(SoundType.GROUND);
		setHardness(0.95F);
		setResistance(2.0F);
		setHarvestLevel("shovel", 3);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return ModItems.SALTPETER;
	}
	
	@Override
	public int quantityDropped(Random rand)
	{
		int baseRand = rand.nextInt(4);
		if (baseRand == 0)
		{
			baseRand = 1;
		}
		
		return baseRand;
	}

}

