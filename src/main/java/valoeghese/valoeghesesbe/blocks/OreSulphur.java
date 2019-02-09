package valoeghese.valoeghesesbe.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import valoeghese.valoeghesesbe.init.ModItems;

public class OreSulphur extends RockOreBase
{

	public OreSulphur(String name, Material material, int harvestlevel)
	{
		super(name, material, harvestlevel);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return ModItems.SULPHURROCK;
	}
	
	@Override
	public int quantityDropped(Random rand)
	{
		int baseRand = rand.nextInt(3);
		if (baseRand == 0)
		{
			baseRand = 1;
		}
		return baseRand;
		
	}

}
