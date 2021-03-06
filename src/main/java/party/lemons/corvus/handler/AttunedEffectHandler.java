package party.lemons.corvus.handler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import party.lemons.corvus.Corvus;
import party.lemons.corvus.init.CorvusBlocks;
import party.lemons.corvus.init.CorvusNetwork;
import party.lemons.corvus.init.CorvusPotions;
import party.lemons.corvus.init.CorvusSounds;
import party.lemons.corvus.network.MessageDoEffect;

@Mod.EventBusSubscriber(modid = Corvus.MODID)
public class AttunedEffectHandler
{
	@SubscribeEvent
	public static void onPlayerInteractBlock(PlayerInteractEvent.RightClickBlock event)
	{
		EntityPlayer player = event.getEntityPlayer();
		if(isAttuned(player))
		{
			World world = player.getEntityWorld();
			BlockPos pos = event.getPos();
			IBlockState state = world.getBlockState(pos);

			if(state.getBlock() == CorvusBlocks.CRYSTAL_QUARTZ_ORE)
			{
				transformBlock(world, pos, player, CorvusBlocks.WISER_GEM_ORE.getDefaultState());
			}
			else if(state.getBlock() == Blocks.IRON_ORE)
			{
				transformBlock(world, pos, player, Blocks.GOLD_ORE.getDefaultState());
			}
		}
	}

	private static void transformBlock(World world, BlockPos pos, EntityPlayer player, IBlockState to)
	{
		player.swingArm(EnumHand.MAIN_HAND);

		if(world.isRemote)
			return;

		world.playSound(null, pos, CorvusSounds.SPELL_GENERIC, SoundCategory.PLAYERS, 1F, 1F);
		doEffect(EffectHandler.BLOCK_HIGHLIGHT, pos, world);
		world.setBlockState(pos, to);
	}

	private static void doEffect(int effect, BlockPos pos, World world)
	{
		if(world.isRemote)
			return;

		NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 40);
		CorvusNetwork.INSTANCE.sendToAllAround(new MessageDoEffect(effect, pos), point);
	}

	public static boolean isAttuned(EntityPlayer player)
	{
		PotionEffect effect = player.getActivePotionEffect(CorvusPotions.ATTUNED);
		return effect != null && effect.getDuration() > 0;
	}
}
