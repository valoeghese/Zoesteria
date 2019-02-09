package valoeghese.valoeghesesbe.util.handlers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.entity.EntityTrueTNT;
import valoeghese.valoeghesesbe.entity.rendermodel.RenderTrueTNT;

public class ModelRenderHandler
{
	
	@SideOnly(Side.CLIENT)
	public static void preInit(FMLPreInitializationEvent e)
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityTrueTNT.class, new IRenderFactory<EntityTrueTNT>()
		{
			@Override
			public Render<? super EntityTrueTNT> createRenderFor(RenderManager manager)
			{
				return new RenderTrueTNT(manager);
			}
		});
	}
}
