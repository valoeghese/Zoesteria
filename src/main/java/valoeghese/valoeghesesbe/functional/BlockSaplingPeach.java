package valoeghese.valoeghesesbe.functional;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.Main;
import valoeghese.valoeghesesbe.blocks.BlockSmallBush;
import valoeghese.valoeghesesbe.blocks.BlockSmallBush.EnumBushType;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesPeach;
import valoeghese.valoeghesesbe.functional.tree.BlockLeavesPeach.EnumGenotype;
import valoeghese.valoeghesesbe.functional.tree.BlockSaplingBase;
import valoeghese.valoeghesesbe.world.trees.WorldGenBluffPine;
import valoeghese.valoeghesesbe.world.trees.WorldGenIslandPalm;
import valoeghese.valoeghesesbe.world.trees.WorldGenManukaTree;
import valoeghese.valoeghesesbe.world.trees.WorldGenOceanPalm;
import valoeghese.valoeghesesbe.world.trees.evil.WorldGenEvilTreeSapling;
import valoeghese.valoeghesesbe.world.trees.fruittree.WorldGenPeach;
import valoeghese.valoeghesesbe.world.trees.newzealand.WorldGenPohutukawa1;
import valoeghese.valoeghesesbe.world.trees.oasispalm.WorldGenOasisPalm2;

public class BlockSaplingPeach extends BlockSaplingBase
{
	
	public final EnumGenotype VARIANT;
	
	public BlockSaplingPeach(String name, EnumGenotype variant)
	{
		super(name, "peach");
		
		this.VARIANT = variant;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		return this.getDefaultState().withProperty(STAGE, Integer.valueOf(meta & 1));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {STAGE});
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add("Genotype unknown");
    }
	
	@Override
	public void generateTree(World world, Random rand, BlockPos pos, IBlockState state)
	{
		if(!TerrainGen.saplingGrowTree(world, rand, pos)) return;
		WorldGenerator gen = (WorldGenerator)new WorldGenPeach(VARIANT, true);
		boolean flag = false;
		int i = 0;
		int j = 0;
		
		IBlockState iblockstate = Blocks.AIR.getDefaultState();
		
		if(flag)
		{
			world.setBlockState(pos.add(i, 0, j), iblockstate, 4);
			world.setBlockState(pos.add(i + 1, 0, j), iblockstate, 4);
			world.setBlockState(pos.add(i, 0, j + 1), iblockstate, 4);
			world.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate, 4);
		}
		else
		{
			world.setBlockState(pos, iblockstate, 4);
		}
		
		if(!gen.generate(world, rand, pos.add(i, 0, j)))
		{
			if(flag)
			{
				world.setBlockState(pos.add(i, 0, j), this.getDefaultState(), 4);
				world.setBlockState(pos.add(i + 1, 0, j), iblockstate, 4);
				world.setBlockState(pos.add(i, 0, j + 1), iblockstate, 4);
				world.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate, 4);
			}
			else
			{
				world.setBlockState(pos, this.getDefaultState(), 4);
			}
		}
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		if (((Integer)state.getValue(STAGE)).intValue() > 0)
			i |= 1;
		
		return i;
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
}
