package valoeghese.valoeghesesbe.util.handlers;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.PotionColorCalculationEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import valoeghese.valoeghesesbe.world.biomes.biomeutil.IBiomeFog;

@EventBusSubscriber
public class BiomeFogTickEvents
{
	
	@SubscribeEvent
	public static void onTick(TickEvent.PlayerTickEvent event)
	{
		if (event.getPhase() == EventPriority.NORMAL)
		{
			Biome biome = event.player.getEntityWorld().getBiome(event.player.getPosition());
			if (biome instanceof IBiomeFog)
			{
				if (((IBiomeFog) biome).getFogStrength() > 0) event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), ((IBiomeFog) biome).getFogStrength() * 4, -6, true, false));
			}
		}
	}
}
