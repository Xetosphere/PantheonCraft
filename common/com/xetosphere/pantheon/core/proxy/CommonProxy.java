package com.xetosphere.pantheon.core.proxy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		return null;
	}

	public static void storeEntityData(String name, NBTTagCompound compound) {

		extendedEntityData.put(name, compound);
	}

	public static NBTTagCompound getEntityData(String name) {

		return extendedEntityData.remove(name);
	}
}
