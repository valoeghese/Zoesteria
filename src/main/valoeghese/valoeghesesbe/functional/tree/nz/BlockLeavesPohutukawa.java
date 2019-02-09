package valoeghese.valoeghesesbe.functional.tree.nz;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.init.ModItems;
import valoeghese.valoeghesesbe.util.IHasModel;


public class BlockLeavesPohutukawa extends BlockLeaves implements IHasModel
{

	private final String name;
	public BlockLeavesPohutukawa(String name)
	{
		super();

		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(Main.tabWorld);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(false)).withProperty(DECAYABLE, Boolean.valueOf(false)));

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(ModBlocks.SAPLING_POHUTUKAWA);
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		return true;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int i = 0;
		if(!((Boolean)state.getValue(DECAYABLE)).booleanValue()) i |= 2;
		if(!((Boolean)state.getValue(CHECK_DECAY)).booleanValue()) i|= 4;
		return i;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (this.name == "leaves_pohutukawa_flower" && playerIn.getHeldItem(hand).getItem() != Items.FLINT_AND_STEEL)
		{
			this.spawnAsEntity(worldIn, pos, new ItemStack(ModItems.FLOWER_POHUTUKAWA, 1));
			worldIn.setBlockState(pos, ModBlocks.LEAVES_POHUTUKAWA.getDefaultState());
			return true;
		}
		else return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) 
	{
		return new ItemStack(this);
	}

	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {return;}

	@Override
	protected int getSaplingDropChance(IBlockState state) {return 20;}

	@Override
	public EnumType getWoodType(int meta) {return null;}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) 
	{
		return NonNullList.withSize(1, new ItemStack(ModBlocks.LEAVES_POHUTUKAWA));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
	}

	@Override
	public BlockRenderLayer getBlockLayer() 
	{
		return BlockRenderLayer.TRANSLUCENT;
	}	

	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
}

