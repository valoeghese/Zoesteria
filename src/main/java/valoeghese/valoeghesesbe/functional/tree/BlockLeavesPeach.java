package valoeghese.valoeghesesbe.functional.tree;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.init.ModBlocks;
import valoeghese.valoeghesesbe.init.ModItems;
import valoeghese.valoeghesesbe.util.Console;
import valoeghese.valoeghesesbe.util.IHasModel;

public class BlockLeavesPeach extends BlockLeaves implements IHasModel
{
	
	public final EnumGenotype VARIANT;
	
	@Override
	public int quantityDropped(Random rand)
	{
		return rand.nextInt(15) == 0 ? 1 : 0;
	}
	
	public BlockLeavesPeach(String name, EnumGenotype variant)
	{
		super();
		
		this.VARIANT = variant;
		
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(Main.tabWorld);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(false)));
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return true;
	}
	
	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return 200;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add("Genotype unknown");
    }
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		return true;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		int mut = rand.nextInt(MUTATION_CHANCE);
		
		if (mut == 0)
			return getSapling(VARIANT.mutate(rand));
		else
			return getSapling(VARIANT);
	}
	
	public static Item getSapling(EnumGenotype variant)
	{
		Block block;
		if (variant == EnumGenotype.PP)
		{
			block = ModBlocks.SAPLING_PEACH_PP;
		}
		else if (variant == EnumGenotype.Pp)
		{
			block = ModBlocks.SAPLING_PEACH_Pp;
		}
		else if (variant == EnumGenotype.pP)
		{
			block = ModBlocks.SAPLING_PEACH_pP;
		}
		else
		{
			block = ModBlocks.SAPLING_PEACH_pp;
		}
		
		return Item.getItemFromBlock(block);
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return super.damageDropped(state);
	}
	
	public static final int MUTATION_CHANCE = 10;
	
	/**
     * This gets a complete list of items dropped from this block.
     *
     * @param drops add all items this block drops to this drops list
     * @param world The current world
     * @param pos Block position in world
     * @param state Current state
     * @param fortune Breakers fortune level
     */
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
                drops.add(new ItemStack(item, 1, this.damageDropped(state)));
            }
        }
        
        if (world instanceof World)
        {
        	if (rand.nextInt(5) == 0) this.dropApple((World)world, pos, state, 5);
        }
    }
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int i = 0;
		
		if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i |= 1;
        }

        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i |= 2;
        }
        
		return i;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) 
	{
		return new ItemStack(this);
	}
	
	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
	{
		if (worldIn.rand.nextInt(chance) == 0)
		{
			if (VARIANT == BlockLeavesPeach.EnumGenotype.pp)
			{
				spawnAsEntity(worldIn, pos, new ItemStack(ModItems.NECTARINE));
			}
			else
			{
				spawnAsEntity(worldIn, pos, new ItemStack(ModItems.PEACH));
			}
		}
	}
	
	@Override
	protected int getSaplingDropChance(IBlockState state) {return 20;}
	
	@Override
	public EnumType getWoodType(int meta) {return null;}
	
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) 
	{
		return NonNullList.withSize(1, new ItemStack(this));
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
		return Blocks.LEAVES.getBlockLayer();
	}
	
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState();
        
        state = state.withProperty(DECAYABLE, Boolean.valueOf((meta & 1) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 2) > 0));
        return state;
    }
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
	/**
	 * 
	 * Flattened before release, so this is different by block, rather than blockstate.
	 *
	 */
	public static enum EnumGenotype implements IStringSerializable
	{
		PP(0, "homozygous_dominant", Allele.DOM, Allele.DOM),
		Pp(1, "heterozygous_0", Allele.DOM, Allele.REC),
		pP(2, "heterozygous_1", Allele.REC, Allele.DOM),
		pp(3, "homozygous_recessive", Allele.REC, Allele.REC);
		

		private static final BlockLeavesPeach.EnumGenotype[] META_LOOKUP = new BlockLeavesPeach.EnumGenotype[values().length];
		private final int meta;
		private final String name;
		private final String unlocalizedName;
		private final Allele allele0;
		private final Allele allele1;
		
		private static enum Allele
		{
			DOM, REC;
			
			public Allele getOpposite()
			{
				if (this == DOM) return REC;
				else return DOM;
			}
		}
		
		private EnumGenotype(int metaIn, String nameIn, Allele...alleles)
		{
			this(metaIn, nameIn, nameIn, alleles);
		}
		
		private EnumGenotype(int metaIn, String nameIn, String unlocalizedNameIn, Allele[] alleles)
		{
			this.meta = metaIn;
			this.name = nameIn;
			this.unlocalizedName = unlocalizedNameIn;
			
			this.allele0 = alleles[0];
			this.allele1 = alleles[1];
		}
		
		public static EnumGenotype getGenotype(Allele allele0, Allele allele1)
		{
			if (allele0 == Allele.DOM)
			{
				if (allele1 == Allele.DOM)
				{
					return PP;
				}
				else
				{
					return Pp;
				}
			}
			else
			{
				if (allele1 == Allele.DOM)
				{
					return pP;
				}
				else
				{
					return pp;
				}
			}	
		}
		
		public EnumGenotype mutate(Random rand)
		{
			Allele a0 = this.allele0;
			Allele a1 = this.allele1;
			
			if (rand.nextBoolean()) a0 = a0.getOpposite();
			else a1 = a1.getOpposite();
			
			return getGenotype(a0, a1);
		}
		
		public int getMetadata()
		{
			return this.meta;
		}

		public String toString()
		{
			return this.name;
		}

		public static BlockLeavesPeach.EnumGenotype byMetadata(int meta)
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
			for (BlockLeavesPeach.EnumGenotype BlockLeavesPeach$EnumGenotype : values())
			{
				META_LOOKUP[BlockLeavesPeach$EnumGenotype.getMetadata()] = BlockLeavesPeach$EnumGenotype;
			}
		}
	}
	
	public static Block getBlockByGenotype(EnumGenotype genotype)
	{
		switch(genotype)
		{
		case PP:
			return ModBlocks.LEAVES_PEACH_PP;
		case Pp:
			return ModBlocks.LEAVES_PEACH_Pp;
		case pP:
			return ModBlocks.LEAVES_PEACH_pP;
		case pp:
			return ModBlocks.LEAVES_PEACH_pp;
		default:
			//This should not occur. Leaving this here due to eclipse nagging me about it.
			return ModBlocks.LEAVES_PEACH_PP;
		}
	}
}