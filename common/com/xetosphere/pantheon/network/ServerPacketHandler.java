package com.xetosphere.pantheon.network;

import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class ServerPacketHandler {

	@SuppressWarnings({ "unused", "resource" })
	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {

		EntityPlayerMP player = ((NetHandlerPlayServer) event.handler).playerEntity;
		ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
	}
}
