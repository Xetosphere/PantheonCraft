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
import com.xetosphere.pantheon.lib.ExtendedPlayer;
import com.xetosphere.pantheon.lib.GuiIds;
import com.xetosphere.pantheon.lib.PantheonReference;
import com.xetosphere.pantheon.network.packet.UpdateOpinionPacket;

public class ItemTalisman extends ItemPC {

	public ItemTalisman() {
		
		maxStackSize = 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

		String pantheon = ExtendedPlayer.get(player).getPantheon();
		int opinionGreek = ExtendedPlayer.get(player).getOpinion(PantheonReference.GREEK);
		int opinionNorse = ExtendedPlayer.get(player).getOpinion(PantheonReference.NORSE);
		int opinionRoman = ExtendedPlayer.get(player).getOpinion(PantheonReference.ROMAN);
		int opinionEgyptian = ExtendedPlayer.get(player).getOpinion(PantheonReference.EGYPTIAN);
		int opinionMayan = ExtendedPlayer.get(player).getOpinion(PantheonReference.MAYAN);
		int opinionChinese = ExtendedPlayer.get(player).getOpinion(PantheonReference.CHINESE);
		int opinionHindu = ExtendedPlayer.get(player).getOpinion(PantheonReference.HINDU);
		int opinionAztec = ExtendedPlayer.get(player).getOpinion(PantheonReference.AZTECIAN);

		final int ONE_SECOND = 20;

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

		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {

			// TODO Greek
			if (pantheon.equals(PantheonReference.pantheons[PantheonReference.GREEK])) {

				if (ExtendedPlayer.get(player).getCulture() >= 100 && objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {

					ExtendedPlayer.get(player).consumeCulture(100);

					int i = objectMouseOver.blockX;
					int j = objectMouseOver.blockY;
					int k = objectMouseOver.blockZ;

					world.spawnEntityInWorld(new EntityLightningBolt(world, i, j, k));

					if (opinionGreek + 2 < 200) {
						opinionGreek += 2;
					}

				} else {

					if (!world.isRemote) {

						player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
						ExtendedPlayer.get(player).setCulture(1000);
					}
				}
			}

			// TODO Norse
			if (pantheon.equals(PantheonReference.pantheons[PantheonReference.NORSE])) {

				if (ExtendedPlayer.get(player).getCulture() >= 100) {

					ExtendedPlayer.get(player).consumeCulture(100);
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 120 * ONE_SECOND, 0, false));

					if (opinionNorse + 2 < 200) {
						opinionNorse += 2;
					}

				} else {

					if (!world.isRemote) {

						player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
						ExtendedPlayer.get(player).setCulture(1000);
					}
				}
			}

			// TODO Roman
			if (pantheon.equals(PantheonReference.pantheons[PantheonReference.ROMAN])) {

				if (ExtendedPlayer.get(player).getCulture() >= 100 && objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {

					ExtendedPlayer.get(player).consumeCulture(100);

					int i = objectMouseOver.blockX;
					int j = objectMouseOver.blockY;
					int k = objectMouseOver.blockZ;

					if (!world.isRemote) world.createExplosion(player, i, j, k, 3, true);

					if (opinionRoman + 2 < 200) {
						opinionRoman += 2;
					}

				} else {

					if (!world.isRemote) {

						player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
						ExtendedPlayer.get(player).setCulture(1000);
					}
				}
			}

			// TODO Egyptian
			if (pantheon.equals(PantheonReference.pantheons[PantheonReference.EGYPTIAN])) {

				if (ExtendedPlayer.get(player).getCulture() >= 100) {

					ExtendedPlayer.get(player).consumeCulture(100);
					player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 120 * ONE_SECOND, 0, false));

					if (opinionEgyptian + 2 < 200) {
						opinionEgyptian += 2;
					}

				} else {

					if (!world.isRemote) {

						player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
						ExtendedPlayer.get(player).setCulture(1000);
					}
				}
			}

			// TODO Mayan
			if (pantheon.equals(PantheonReference.pantheons[PantheonReference.MAYAN])) {

				if (ExtendedPlayer.get(player).getCulture() >= 100) {

					ExtendedPlayer.get(player).consumeCulture(100);
					if (!world.isRemote) player.addChatComponentMessage(new ChatComponentText("Shooting mayan powers."));

					if (opinionMayan + 2 < 200) {
						opinionMayan += 2;
					}

				} else {

					if (!world.isRemote) {

						player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
						ExtendedPlayer.get(player).setCulture(1000);
					}
				}
			}

			// TODO Chinese
			if (pantheon.equals(PantheonReference.pantheons[PantheonReference.CHINESE])) {

				if (ExtendedPlayer.get(player).getCulture() >= 100) {

					ExtendedPlayer.get(player).consumeCulture(100);
					if (!world.isRemote) player.addChatComponentMessage(new ChatComponentText("Shooting chinese powers."));

					if (opinionChinese + 2 < 200) {
						opinionChinese += 2;
					}

				} else {

					if (!world.isRemote) {

						player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
						ExtendedPlayer.get(player).setCulture(1000);
					}
				}
			}

			// TODO Hindi
			if (pantheon.equals(PantheonReference.pantheons[PantheonReference.HINDU])) {

				if (ExtendedPlayer.get(player).getCulture() >= 100) {

					ExtendedPlayer.get(player).consumeCulture(100);
					if (!world.isRemote) player.addChatComponentMessage(new ChatComponentText("Shooting hindu powers."));

					if (opinionHindu + 2 < 200) {
						opinionHindu += 2;
					}

				} else {

					if (!world.isRemote) {

						player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
						ExtendedPlayer.get(player).setCulture(1000);
					}
				}
			}

			// TODO Aztecian
			if (pantheon.equals(PantheonReference.pantheons[PantheonReference.AZTECIAN])) {

				if (ExtendedPlayer.get(player).getCulture() >= 100) {

					ExtendedPlayer.get(player).consumeCulture(100);
					if (!world.isRemote) player.addChatComponentMessage(new ChatComponentText("Shooting aztecian powers."));

					if (opinionAztec + 2 < 200) {
						opinionAztec += 2;
					}

				} else {

					if (!world.isRemote) {

						player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
						ExtendedPlayer.get(player).setCulture(1000);
					}
				}
			}

			if (!world.isRemote && pantheon.equals(PantheonReference.pantheons[PantheonReference.NO_RELIGION])) player.addChatComponentMessage(new ChatComponentText("No God, no special effects!"));
		}

		PantheonCraft.packetPipeline.sendToServer(new UpdateOpinionPacket(opinionGreek, opinionNorse, opinionRoman, opinionEgyptian, opinionMayan, opinionChinese, opinionHindu, opinionAztec));
		return itemStack;
	}
}
