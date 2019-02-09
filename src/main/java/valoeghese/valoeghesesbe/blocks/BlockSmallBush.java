package valoeghese.valoeghesesbe.blocks;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.init.ModItems;
import valoeghese.valoeghesesbe.util.IHasModel;

public class BlockSmallBush extends BlockBush implements IShearable, IHasModel
{
	protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	public static final PropertyEnum<BlockSmallBush.EnumBushType> VARIANT = PropertyEnum.<BlockSmallBush.EnumBushType>create("variant", BlockSmallBush.EnumBushType.class);
	
	public BlockSmallBush(String name)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockSmallBush.EnumBushType.STANDARD));
		
		this.setHardness(0.2F);
		//this.setCreativeTab(Main.tabWorld);
		this.setSoundType(SoundType.PLANT);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}
	
	@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
    	
        return super.canPlaceBlockAt(worldIn, pos) && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), EnumFacing.UP, this);
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) 
    {
    	return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state) 
    {
    	return false;
    }
    
    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        return super.canBlockStay(worldIn, pos, state);
    }
    
    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int f)
    {
    	if (state == this.getDefaultState().withProperty(VARIANT, EnumBushType.BERRY))
    	{
    		return rand.nextInt(3) == 0 ? ModItems.BERRY : Items.STICK;
    	}
    	else
    	{
    		return Items.STICK;
    	}
    }
    
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        int count = quantityDropped(state, fortune, rand);
        for (int i = 0; i < count; i++)
        {
            Item item = this.getItemDropped(state, rand, fortune);
            if (item != Items.AIR)
            {
                drops.add(new ItemStack(item, 1, 0));
            }
        }
    } 
    
    @Override
    public int quantityDropped(Random rand)
    {
		return rand.nextInt(4) + 1;
    }
    
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
    {
    	return SAPLING_AABB;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) 
    {
    	return NULL_AABB;
    }
	
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((BlockSmallBush.EnumBushType)state.getValue(VARIANT)).getMetadata();
    }
    
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (BlockSmallBush.EnumBushType variant : BlockSmallBush.EnumBushType.values())
        {
            items.add(new ItemStack(this, 1, variant.getMetadata()));
        }
    }
	
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockSmallBush.EnumBushType.byMetadata(meta));
    }
    
	@Override
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockSmallBush.EnumBushType)state.getValue(VARIANT)).getMetadata();
    }
	
	public static enum EnumBushType implements IStringSerializable
	{
		STANDARD(0, "standard"),
		BERRY(1, "berry"),
		CONIFEROUS(2, "coniferous");

		private static final BlockSmallBush.EnumBushType[] META_LOOKUP = new BlockSmallBush.EnumBushType[values().length];
		private final int meta;
		private final String name;
		private final String unlocalizedName;

		private EnumBushType(int metaIn, String nameIn)
		{
			this(metaIn, nameIn, nameIn);
		}

		private EnumBushType(int metaIn, String nameIn, String unlocalizedNameIn)
		{
			this.meta = metaIn;
			this.name = nameIn;
			this.unlocalizedName = unlocalizedNameIn;
		}

		public int getMetadata()
		{
			return this.meta;
		}

		public String toString()
		{
			return this.name;
		}

		public static BlockSmallBush.EnumBushType byMetadata(int meta)
		{
			if (meta < 0 || meta >= META_LOOKUP.length)
			{
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public String getName()
		{
			return this.name;
		}

		public String getUnlocalizedName()
		{
			return this.unlocalizedName;
		}

		static
		{
			for (BlockSmallBush.EnumBushType blocksmallbush$enumbushtype : values())
			{
				META_LOOKUP[blocksmallbush$enumbushtype.getMetadata()] = blocksmallbush$enumbushtype;
			}
		}
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
	{
		return true;
	}
	
	@Override
    public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).getMetadata()));
    }

	@Override
	public void registerModels()
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

}

