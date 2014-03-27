package com.xetosphere.pantheon.proxy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.xetosphere.pantheon.client.gui.GuiReligion;
import com.xetosphere.pantheon.lib.GuiIds;

import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

		if (id == GuiIds.RELIGION_GUI) {

			return null;
		}

		return null;
	}

	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

		if (id == GuiIds.RELIGION_GUI) {

			return new GuiReligion(player);
		}

		return null;
	}

	public static void storeEntityData(String name, NBTTagCompound compound) {

		extendedEntityData.put(name, compound);
	}

	public static NBTTagCompound getEntityData(String name) {

		return extendedEntityData.remove(name);
	}
}
