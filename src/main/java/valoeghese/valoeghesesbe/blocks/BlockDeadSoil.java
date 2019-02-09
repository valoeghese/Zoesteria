package valoeghese.valoeghesesbe.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.init.ModItems;

public class BlockDeadSoil extends BlockBase
{
	private final String name;
	//public BlockDeadSoil(String name, Material material) { this(name, material, null); }
	public BlockDeadSoil(String name, Material material/*, Item superForm*/)
	{
		
		super(name, material);
		
		this.name = name;
		//this.superForm = superForm;
		
		if (material == Material.GRASS) this.setSoundType(SoundType.PLANT);
		else this.setSoundType(SoundType.GROUND);
		setHardness(0.65F);
		setResistance(3.0F);
		setHarvestLevel("shovel", 0);
		
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (this.name == "brushland_grass") return Item.getItemFromBlock(ModBlocks.SOIL_DRY);
		else return super.getItemDropped(state, rand, fortune);
	}
	
	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
	{
		return true;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        
        if (!itemstack.isEmpty() && (itemstack.getItem() == ModItems.SALTPETER) /* && !worldIn.getBlockState(pos.up()).getBlock().canSustainPlant(state, worldIn, pos.up(), EnumFacing.UP, (IPlantable) Blocks.SAPLING) */ && this.name == "soil_dry")
        {
        	worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
        	itemstack.shrink(1);
        	return true;
        }
        else
        {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }
	
	
}

