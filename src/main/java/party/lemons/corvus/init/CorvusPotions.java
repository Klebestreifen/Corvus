package party.lemons.corvus.init;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import party.lemons.corvus.Corvus;
import party.lemons.corvus.potion.PotionConceal;
import party.lemons.corvus.potion.PotionCorvus;
import party.lemons.corvus.potion.PotionProjection;

@Mod.EventBusSubscriber(modid = Corvus.MODID)
@GameRegistry.ObjectHolder(Corvus.MODID)
public class CorvusPotions
{
	public static final Potion SLOW_FALL = MobEffects.RESISTANCE;
	public static final Potion BURNING_RAGE = MobEffects.RESISTANCE;
	public static final Potion CONCEALMENT = MobEffects.INVISIBILITY;
	public static final Potion SOUL_FORGE = MobEffects.ABSORPTION;
	public static final Potion PROJECTION = MobEffects.INVISIBILITY;

	@SubscribeEvent
	public static void onRegisterPotion(RegistryEvent.Register<Potion> event)
	{
		event.getRegistry().registerAll(
				createPotion(new PotionCorvus(false, 0xd3ffd6, 0, 0), "slow_fall"),
				createPotion(new PotionCorvus(false, 0xe07f00, 1, 0), "burning_rage"),
				createPotion(new PotionConceal(false, 0x232323, 2, 0), "concealment"),
				createPotion(new PotionCorvus(false, 0xfff875, 3, 0), "soul_forge"),
				createPotion(new PotionProjection(false, 0xfff875, 0, 1), "projection")
		);
	}

	private static Potion createPotion(Potion potion, String name)
	{
		potion.setRegistryName(Corvus.MODID, name);
		potion.setPotionName("effects." + Corvus.MODID + name + ".name");
		return potion;
	}
}