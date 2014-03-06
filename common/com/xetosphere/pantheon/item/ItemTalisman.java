package com.xetosphere.pantheon.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

import com.xetosphere.pantheon.entity.ExtendedPlayer;

public class ItemTalisman extends ItemPC {

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

		if (world.isRemote) {

			return itemStack;
		}

		if (ExtendedPlayer.get(player).getCulture() >= 100) {

			ExtendedPlayer.get(player).consumeCulture(100);

			// player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 0, false));

			MovingObjectPosition objectMouseOver;
			Minecraft mc = Minecraft.getMinecraft();

			objectMouseOver = mc.thePlayer.rayTrace(300, 1);

			if (objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
				
				int i = objectMouseOver.blockX;
				int j = objectMouseOver.blockY;
				int k = objectMouseOver.blockZ;
				world.spawnEntityInWorld(new EntityLightningBolt(world, i, j, k));
			}

		} else {

			player.addChatComponentMessage(new ChatComponentText("You don't have enough culture."));
			ExtendedPlayer.get(player).setCulture(100);
		}

		return itemStack;
	}
}
