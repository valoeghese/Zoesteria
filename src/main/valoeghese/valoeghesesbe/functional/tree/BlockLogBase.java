package valoeghese.valoeghesesbe.functional.tree;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.init.ModItems;
import valoeghese.valoeghesesbe.util.IHasModel;

public class BlockLogBase extends BlockLog implements IHasModel
{
	public final String typeVariant;

    public BlockLogBase(String name, String type)
    {
    	this.typeVariant = type;
    	
    	this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.WOOD);
		this.setCreativeTab(Main.tabWorld);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
				
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    /*
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
    	return BlockPlanks.EnumType.SPRUCE.getMapColor();
    }

    /*
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta & 6)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 2:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    /*
     * Convert the BlockState into the correct metadata value
     */
    @SuppressWarnings("incomplete-switch")
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
        {
            case X:
                i |= 2;
                break;
            case Z:
                i |= 4;
                break;
            case NONE:
                i |= 6;
        }

        return i;
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
    }
    
    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
    	return new ItemStack(this);
    }
    
    @Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
    
    public String getTypeVariant()
    {
    	return this.typeVariant;
    }
}
