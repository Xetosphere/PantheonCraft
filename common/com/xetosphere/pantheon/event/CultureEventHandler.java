package com.xetosphere.pantheon.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.xetosphere.pantheon.entity.ExtendedPlayer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CultureEventHandler {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {

		if (event.entity instanceof EntityPlayer) {

			if (ExtendedPlayer.get((EntityPlayer) event.entity) == null)

			ExtendedPlayer.register((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {

		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {

			ExtendedPlayer.loadProxyData((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {

		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {

			ExtendedPlayer.saveProxyData((EntityPlayer) event.entity);
		}
	}
}
