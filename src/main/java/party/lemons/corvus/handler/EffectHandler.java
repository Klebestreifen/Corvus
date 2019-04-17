package party.lemons.corvus.handler;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EffectHandler
{
	public static final int BLOCK_HIGHLIGHT = 0;

	public static void doEffect(int effect, BlockPos pos, World world)
	{
		switch(effect)
		{
			case BLOCK_HIGHLIGHT:
				blockHighlight(world, pos);
				break;
		}
	}

	public static void blockHighlight(World world, BlockPos pos)
	{
		if(!world.isRemote)
			return;

		int amt = 10;
		float step = 1F / (float)amt;

		EnumParticleTypes type = EnumParticleTypes.END_ROD;
		float xSpeed = 0;
		float ySpeed = 0.008F;
		float zSpeed = 0;

		for(int x = 0; x < amt; x++)
		{
			float stepPos = x * step;
			world.spawnParticle(type, pos.getX() + stepPos, pos.getY(), pos.getZ(), xSpeed, ySpeed, zSpeed);
			world.spawnParticle(type, pos.getX() + stepPos, pos.getY() + 1, pos.getZ(), xSpeed, ySpeed, zSpeed);
			world.spawnParticle(type, (pos.getX() + 1), pos.getY() + stepPos, pos.getZ(), xSpeed, ySpeed, zSpeed);
			world.spawnParticle(type, pos.getX(), pos.getY() + stepPos, pos.getZ(), xSpeed, ySpeed, zSpeed);

			world.spawnParticle(type, pos.getX(), pos.getY(), pos.getZ() + stepPos, xSpeed, ySpeed, zSpeed);
			world.spawnParticle(type, pos.getX(), pos.getY() + 1, pos.getZ() + stepPos, xSpeed, ySpeed, zSpeed);
			world.spawnParticle(type, pos.getX(), pos.getY() + stepPos, pos.getZ() + 1, xSpeed, ySpeed, zSpeed);

			world.spawnParticle(type, pos.getX() + stepPos, pos.getY(), pos.getZ() + 1, xSpeed, ySpeed, zSpeed);
			world.spawnParticle(type, pos.getX() + stepPos, pos.getY() + 1, pos.getZ() + 1, xSpeed, ySpeed, zSpeed);
			world.spawnParticle(type, pos.getX() + 1, pos.getY() + stepPos, pos.getZ() + 1, xSpeed, ySpeed, zSpeed);

			world.spawnParticle(type, pos.getX() + 1, pos.getY(), pos.getZ() + stepPos, xSpeed, ySpeed, zSpeed);
			world.spawnParticle(type, pos.getX() + 1, pos.getY() + 1, pos.getZ() + stepPos, xSpeed, ySpeed, zSpeed);
		}
	}
}
