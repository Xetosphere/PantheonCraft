package com.xetosphere.pantheon.item;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.xetosphere.pantheon.PantheonCraft;
import com.xetosphere.pantheon.entity.ExtendedPlayer;
import com.xetosphere.pantheon.lib.GuiIds;

public class ItemTalisman extends ItemPC {

	public ItemTalisman() {

	}

	public ItemTalisman(EntityPlayer player) {

	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

		String pantheon = ExtendedPlayer.get(player).getPantheon();

		final double EYE_HEIGHT = 1.62;
		final double REACH_DISTANCE = 300;

		Vec3 startPos = player.getPosition(1.0F);

		if (!world.isRemote) startPos = startPos.addVector(0, EYE_HEIGHT, 0);

		Vec3 look = player.getLook(1.0F);
		Vec3 endPos = startPos.addVector(look.xCoord * REACH_DISTANCE, look.yCoord * REACH_DISTANCE, look.zCoord * REACH_DISTANCE);

		MovingObjectPosition objectMouseOver = world.rayTraceBlocks(startPos, endPos);

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			if (world.isRemote) {

				player.openGui(PantheonCraft.instance, GuiIds.RELIGION_GUI, world, (int) player.posX, (int) player.posY, (int) player.posZ);
			}

		}

		// TODO Greek
		if (pantheon.equals("Greek") && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			if (ExtendedPlayer.get(player).getCulture() >= 100 && objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {

				ExtendedPlayer.get(player).consumeCulture(100);

				int i = objectMouseOver.blockX;
				int j = objectMouseOver.blockY;
				int k = objectMouseOver.blockZ;
				
				player.addChatComponentMessage(new ChatComponentText("This is ran. Greek"));

				world.spawnEntityInWorld(new EntityLightningBolt(world, i, j, k));

			} else {

				if (!world.isRemote) {

					player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
					ExtendedPlayer.get(player).setCulture(100);
				}
			}
		}

		// TODO Norse
		if (pantheon.equals("Norse") && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			if (ExtendedPlayer.get(player).getCulture() >= 100) {

				ExtendedPlayer.get(player).consumeCulture(100);
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 0, false));
				player.addChatComponentMessage(new ChatComponentText("This is ran. Norse"));

			} else {

				if (!world.isRemote) {

					player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
					ExtendedPlayer.get(player).setCulture(100);
				}
			}
		}

		// TODO Roman
		if (pantheon.equals("Roman") && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			if (ExtendedPlayer.get(player).getCulture() >= 100 && objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
				ExtendedPlayer.get(player).consumeCulture(100);

				int i = objectMouseOver.blockX;
				int j = objectMouseOver.blockY;
				int k = objectMouseOver.blockZ;
				player.addChatComponentMessage(new ChatComponentText("This is ran. Roman"));

				if (!world.isRemote) world.createExplosion(player, i, j, k, 5, true);

			} else {

				if (!world.isRemote) {

					player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
					ExtendedPlayer.get(player).setCulture(100);
				}
			}
		}

		// TODO Egyptian
		if (pantheon.equals("Egyptian") && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			if (ExtendedPlayer.get(player).getCulture() >= 100) {

				ExtendedPlayer.get(player).consumeCulture(100);
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2400, 0, false));
				player.addChatComponentMessage(new ChatComponentText("This is ran. Egyptian"));

			} else {

				if (!world.isRemote) {

					player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
					ExtendedPlayer.get(player).setCulture(100);
				}
			}
		}

		return itemStack;
	}
}
