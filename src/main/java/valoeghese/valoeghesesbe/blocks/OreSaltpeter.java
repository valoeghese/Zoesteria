package valoeghese.valoeghesesbe.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
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
	public int quantityDroppedWithBonus(int fortune, Random rand)
	{
		int quantityOut = rand.nextInt(4);
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
