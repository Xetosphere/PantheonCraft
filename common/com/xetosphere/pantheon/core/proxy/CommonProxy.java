package com.xetosphere.pantheon.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.xetosphere.pantheon.PantheonCraft;
import com.xetosphere.pantheon.network.ServerPacketHandler;

import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		return null;
	}
	
	public void load(){
		
		PantheonCraft.channel.register(new ServerPacketHandler());
	}

}
