package com.xetosphere.pantheon.network.client;

import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class ClientPacketHandler {

	@SuppressWarnings({ "unused", "resource" })
	@SubscribeEvent
	public void onClientPacket(ClientCustomPacketEvent event) {

		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
	}
}
