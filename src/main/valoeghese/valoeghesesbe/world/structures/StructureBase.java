package valoeghese.valoeghesesbe.world.structures;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import valoeghese.valoeghesesbe.util.IStructure;
import valoeghese.valoeghesesbe.util.Reference;

public class StructureBase extends WorldGenerator implements IStructure
{
	public String structureName;
	
	public StructureBase(String name)
	{
		 this.structureName = name;
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		this.generateStructure(worldIn, pos, rand);
		return true;
	}
	
	public void generateStructure(World worldIn, BlockPos pos, Random rand)
	{
		MinecraftServer server = worldIn.getMinecraftServer();
		TemplateManager manager = wServer.getStructureTemplateManager();
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, structureName);
		Template template = manager.get(server, location);
		
		if (template != null)
		{
			IBlockState state = worldIn.getBlockState(pos);
			worldIn.notifyBlockUpdate(pos, state, state, 3);
			int setting = rand.nextInt(4);
			switch (setting)
			{
			case 0:
				template.addBlocksToWorld(worldIn, pos, settings90);;
				break;
			case 1:
				template.addBlocksToWorld(worldIn, pos, settings180);
				break;
			case 2:
				template.addBlocksToWorld(worldIn, pos, settings270);
				break;
			default:
				template.addBlocksToWorld(worldIn, pos, settings);
				break;
			}
		}
	}
}
