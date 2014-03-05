package com.xetosphere.pantheon.core.proxy;

import com.xetosphere.pantheon.PantheonCraft;
import com.xetosphere.pantheon.network.client.ClientPacketHandler;

public class ClientProxy extends CommonProxy {

	public void load() {
		PantheonCraft.channel.register(new ClientPacketHandler());
	}
}
