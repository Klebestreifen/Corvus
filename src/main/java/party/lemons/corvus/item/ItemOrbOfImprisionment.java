package party.lemons.corvus.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemOrbOfImprisionment extends ItemModel
{
	public ItemOrbOfImprisionment()
	{
		setMaxStackSize(1);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
	{
		//If the entity is a boss or the orb already has an entity or the target is a player, don't continue
		if(!target.isNonBoss() || target instanceof EntityPlayer || hasEntity(stack))
			return false;

		if(!playerIn.world.isRemote)
		{
			putEntity(stack, target);
			target.setDead();
		}
		return true;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);

		if(!worldIn.isRemote && hasEntity(stack))
		{
			Entity e = EntityList.createEntityFromNBT(stack.getTagCompound().getCompoundTag("entity"), worldIn);
			e.setPositionAndUpdate(pos.getX() + hitX, pos.getY() + hitY, pos.getZ() + hitZ);
			worldIn.spawnEntity(e);

			stack.setTagCompound(null);
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	private void putEntity(ItemStack stack, Entity entity)
	{
		if(entity.world.isRemote)
			return;

		NBTTagCompound tags = stack.getTagCompound();
		if(tags == null)
			tags = new NBTTagCompound();

		//Write entity to tags
		NBTTagCompound entityTags = new NBTTagCompound();
		entity.writeToNBTAtomically(entityTags);
		tags.setTag("entity", entityTags);

		stack.setTagCompound(tags);
	}

	private boolean hasEntity(ItemStack stack)
	{
		if(!stack.hasTagCompound())
			return false;

		return stack.getTagCompound().hasKey("entity");
	}
}
