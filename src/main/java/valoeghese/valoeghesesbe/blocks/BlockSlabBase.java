package valoeghese.valoeghesesbe.blocks;

import java.util.Random;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.init.ModItems;
import valoeghese.valoeghesesbe.util.IHasModel;

public class BlockSlabBase extends BlockSlab implements IHasModel
{
	
	private IBlockState state;
	private boolean isDouble;
    public BlockSlabBase(String name, IBlockState state, boolean isDouble)
    {
        super(state.getMaterial());
        
        setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.tabMisc);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
        this.state = state;
        IBlockState iblockstate = this.blockState.getBaseState();
        this.isDouble = isDouble;
        this.setResistance(state.getMaterial() == Material.ROCK ? 10.0F : 5.0F);
        this.setHardness(2.0F);
        this.setSoundType(state.getMaterial() == Material.ROCK ? SoundType.STONE : SoundType.WOOD);

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }
        
    }
    
    @Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        
        if (this.isDouble() == false && !itemstack.isEmpty() && itemstack.getItem() == Item.getItemFromBlock(this) && (hitY - Math.floor(hitY)) >= 0.5)
        {
        	worldIn.setBlockState(pos, this.state);
        	worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1F, 0.75F);
        	return true;
        }
        else
        {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }
    
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return this.state.getMapColor(worldIn, pos);
    }
    
    @Override
    public String getUnlocalizedName(int meta)
    {
    	return this.getUnlocalizedName();
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
    	return 0;
    }

    @Override
    public IProperty<?> getVariantProperty()
    {
        return HALF;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return EnumBlockHalf.BOTTOM;
    }
    
	@Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		if(world.getBlockState(pos).getMaterial() == Material.WOOD)
			return Blocks.WOODEN_SLAB.getFlammability(world, pos, face);
		
		return 0;
    }
	
	@Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		if(world.getBlockState(pos).getMaterial() == Material.WOOD)
			return Blocks.WOODEN_SLAB.getFireSpreadSpeed(world, pos, face);
		
		return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        if (!this.isDouble())
        {
            return this.getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]);
        }

        return this.getDefaultState();
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
    	if(this.isDouble())
    		return 0;
    	
    	return ((EnumBlockHalf)state.getValue(HALF)).ordinal() + 1;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {}) : new BlockStateContainer(this, new IProperty[] {HALF});
    }

    @Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		if (this.isDouble())
		{
			return Item.getItemFromBlock(this.state.getBlock());
		} else {
			return Item.getItemFromBlock(this);
		}
    }
    
	@Override
	public boolean isDouble()
	{
		return this.isDouble;
	}
	
}

