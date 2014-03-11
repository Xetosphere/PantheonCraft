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

	public static int pantheon;

	public ItemTalisman() {

	}

	public ItemTalisman(EntityPlayer player) {

		pantheon = ExtendedPlayer.get(player).getPantheonId();
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

		final double EYE_HEIGHT = 1.62;
		final double reachDistance = 300;

		Vec3 startPos = player.getPosition(1.0F);

		if (!world.isRemote) startPos = startPos.addVector(0, EYE_HEIGHT, 0);

		Vec3 look = player.getLook(1.0F);
		Vec3 endPos = startPos.addVector(look.xCoord * reachDistance, look.yCoord * reachDistance, look.zCoord * reachDistance);

		MovingObjectPosition objectMouseOver = world.rayTraceBlocks(startPos, endPos);

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			if (world.isRemote) {

				player.openGui(PantheonCraft.instance, GuiIds.RELIGION_GUI, world, (int) player.posX, (int) player.posY, (int) player.posZ);
			}

			if (!world.isRemote) {

				switch (pantheon) {

					case 1:
						ExtendedPlayer.get(player).setPantheon("None");
						ExtendedPlayer.get(player).setCulture(0);
						ExtendedPlayer.get(player).setPantheonId(pantheon);
						System.out.println("Pantheon is set to " + ExtendedPlayer.get(player).getPantheon());
						System.out.println("Pantheon ID = " + ExtendedPlayer.get(player).getPantheonId());
						break;
					case 2:
						ExtendedPlayer.get(player).setPantheon("Greek");
						ExtendedPlayer.get(player).setCulture(0);
						ExtendedPlayer.get(player).setPantheonId(pantheon);
						System.out.println("Pantheon is set to " + ExtendedPlayer.get(player).getPantheon());
						System.out.println("Pantheon ID = " + ExtendedPlayer.get(player).getPantheonId());
						break;
					case 3:
						ExtendedPlayer.get(player).setPantheon("Norse");
						ExtendedPlayer.get(player).setCulture(0);
						ExtendedPlayer.get(player).setPantheonId(pantheon);
						System.out.println("Pantheon is set to " + ExtendedPlayer.get(player).getPantheon());
						System.out.println("Pantheon ID = " + ExtendedPlayer.get(player).getPantheonId());
						break;
					case 4:
						ExtendedPlayer.get(player).setPantheon("Roman");
						ExtendedPlayer.get(player).setCulture(0);
						ExtendedPlayer.get(player).setPantheonId(pantheon);
						System.out.println("Pantheon is set to " + ExtendedPlayer.get(player).getPantheon());
						System.out.println("Pantheon ID = " + ExtendedPlayer.get(player).getPantheonId());
						break;
					case 5:
						ExtendedPlayer.get(player).setPantheon("Egyptian");
						ExtendedPlayer.get(player).setCulture(0);
						ExtendedPlayer.get(player).setPantheonId(pantheon);
						System.out.println("Pantheon is set to " + ExtendedPlayer.get(player).getPantheon());
						System.out.println("Pantheon ID = " + ExtendedPlayer.get(player).getPantheonId());
						break;
				}
			}

		}

		// TODO Greek
		if (ExtendedPlayer.get(player).getPantheon().equals("Greek") && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			if (ExtendedPlayer.get(player).getCulture() >= 100 && objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {

				ExtendedPlayer.get(player).consumeCulture(100);

				int i = objectMouseOver.blockX;
				int j = objectMouseOver.blockY;
				int k = objectMouseOver.blockZ;

				world.spawnEntityInWorld(new EntityLightningBolt(world, i, j, k));

			} else {

				if (!world.isRemote) {

					player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
				}
			}
		}

		// TODO Norse
		if (ExtendedPlayer.get(player).getPantheon().equals("Norse") && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			if (ExtendedPlayer.get(player).getCulture() >= 100) {

				ExtendedPlayer.get(player).consumeCulture(100);
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 0, false));

			} else {

				if (!world.isRemote) {

					player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
				}
			}
		}

		// TODO Roman
		if (ExtendedPlayer.get(player).getPantheon().equals("Roman") && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			if (ExtendedPlayer.get(player).getCulture() >= 100 && objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
				ExtendedPlayer.get(player).consumeCulture(100);

				int i = objectMouseOver.blockX;
				int j = objectMouseOver.blockY;
				int k = objectMouseOver.blockZ;

				if (!world.isRemote) world.createExplosion(player, i, j, k, 5, true);

			} else {

				if (!world.isRemote) {

					player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
				}
			}
		}

		// TODO Egyptian
		if (ExtendedPlayer.get(player).getPantheon().equals("Egyptian") && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			if (ExtendedPlayer.get(player).getCulture() >= 100) {

				ExtendedPlayer.get(player).consumeCulture(100);
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2400, 0, false));

			} else {

				if (!world.isRemote) {

					player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
				}
			}
		}

		ExtendedPlayer.get(player).setCulture(100);

		return itemStack;
	}
}
