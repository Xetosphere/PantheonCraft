package com.xetosphere.pantheon.lib;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerInformation implements IExtendedEntityProperties {

	public static final String IDENTIFIER = Reference.MOD_ID + "_properties";

	public int culturePoints;

	public void saveNBTData(NBTTagCompound compound) {

		compound.setInteger("Culture", culturePoints);
	}

	public void loadNBTData(NBTTagCompound compound) {

		culturePoints = compound.getInteger("Culture");
	}

	public void init(Entity entity, World world) {
		culturePoints = 0;
	}

}
