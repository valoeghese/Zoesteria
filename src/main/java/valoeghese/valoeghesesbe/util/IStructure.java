package valoeghese.valoeghesesbe.util;

import java.util.ArrayList;

import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraftforge.fml.common.FMLCommonHandler;

public interface IStructure
{
	public static final WorldServer wServer = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0);
	public static final PlacementSettings settings = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock(false).setMirror(Mirror.NONE).setRotation(Rotation.NONE);
	public static final PlacementSettings settings90 = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock(false).setMirror(Mirror.NONE).setRotation(Rotation.CLOCKWISE_90);
	public static final PlacementSettings settings180 = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock(false).setMirror(Mirror.NONE).setRotation(Rotation.CLOCKWISE_180);
	public static final PlacementSettings settings270 = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock(false).setMirror(Mirror.NONE).setRotation(Rotation.COUNTERCLOCKWISE_90);
}
