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
	public int quantityDroppedWithBonus(int fortune, Random rand)
	{
		int quantityOut = rand.nextInt(3);
		if (quantityOut == 0)
		{
			quantityOut = 1;
		}
		
		for (int i = 0; i < fortune; ++i)
		{
			if (rand.nextInt(3) == 0)
			{
				quantityOut += 1;
			}
		}
		
		if (quantityOut > 8) return 8;
		return quantityOut;
	}

}
