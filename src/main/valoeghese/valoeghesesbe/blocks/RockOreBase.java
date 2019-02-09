package valoeghese.valoeghesesbe.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class RockOreBase extends BlockBase
{

	public RockOreBase(String name, Material material, int harvestlevel)
	{
		
		super(name, material);
		
		setSoundType(SoundType.STONE);
		setHardness(3.2F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", harvestlevel);
		
	}

}
