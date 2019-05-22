package valoeghese.valoeghesesbe.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class VanadiumBlock extends BlockBase
{

	public VanadiumBlock(String name, Material material)
	{
		
		super(name, material);
		
		setSoundType(SoundType.METAL);
		setHardness(6.2F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 1);
	}
}
