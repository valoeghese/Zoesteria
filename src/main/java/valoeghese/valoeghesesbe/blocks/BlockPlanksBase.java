package valoeghese.valoeghesesbe.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import valoeghese.valoeghesesbe.Main;

public class BlockPlanksBase extends BlockBase
{

	public BlockPlanksBase(String name)
	{
		super(name, Material.WOOD);
		
		this.setSoundType(SoundType.WOOD);
		this.setCreativeTab(Main.tabMisc);
		this.setHarvestLevel("axe", 0);
		this.setResistance(0.2F);
		this.setHardness(2.0F);
	}
}


