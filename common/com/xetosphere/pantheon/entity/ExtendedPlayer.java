package com.xetosphere.pantheon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.xetosphere.pantheon.PantheonCraft;
import com.xetosphere.pantheon.lib.Reference;
import com.xetosphere.pantheon.network.packet.SyncPlayerPropsPacket;
import com.xetosphere.pantheon.proxy.CommonProxy;

public class ExtendedPlayer implements IExtendedEntityProperties {

	public static final String IDENTIFIER = Reference.MOD_ID + "_properties";

	private final EntityPlayer player;

	private int culture, maxCulture, pantheonId;

	private String pantheon;

	public static final int CULTURE_WATCHER = 25;
	public static final int PANTHEON_WATCHER = 26;
	public static final int ID_WATCHER = 27;

	public ExtendedPlayer(EntityPlayer player) {

		this.player = player;
		culture = 0;
		maxCulture = 1000;
		pantheon = "None";
		pantheonId = 1;
		player.getDataWatcher().addObject(CULTURE_WATCHER, culture);
		player.getDataWatcher().addObject(PANTHEON_WATCHER, pantheon);
		player.getDataWatcher().addObject(ID_WATCHER, pantheonId);
	}

	public static final void register(EntityPlayer player) {

		player.registerExtendedProperties(IDENTIFIER, new ExtendedPlayer(player));
	}

	public static final ExtendedPlayer get(EntityPlayer player) {

		return (ExtendedPlayer) player.getExtendedProperties(IDENTIFIER);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("Culture", player.getDataWatcher().getWatchableObjectInt(CULTURE_WATCHER));
		properties.setInteger("MaxCulture", maxCulture);
		properties.setString("Pantheon", player.getDataWatcher().getWatchableObjectString(PANTHEON_WATCHER));
		properties.setInteger("IdPantheon", player.getDataWatcher().getWatchableObjectInt(ID_WATCHER));
		compound.setTag(IDENTIFIER, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = (NBTTagCompound) compound.getTag(IDENTIFIER);
		player.getDataWatcher().updateObject(CULTURE_WATCHER, properties.getInteger("Culture"));
		maxCulture = properties.getInteger("MaxCulture");
		player.getDataWatcher().updateObject(PANTHEON_WATCHER, properties.getString("Pantheon"));
		player.getDataWatcher().updateObject(ID_WATCHER, properties.getInteger("IdPantheon"));
		System.out.println("[XETOPC PROPS] Culture from NBT: " + player.getDataWatcher().getWatchableObjectInt(CULTURE_WATCHER) + "/" + maxCulture);
		System.out.println("[XETOPC PROPS] Pantheon from NBT: [ID = " + player.getDataWatcher().getWatchableObjectInt(ID_WATCHER) + "] " + player.getDataWatcher().getWatchableObjectString(PANTHEON_WATCHER));
	}

	@Override
	public void init(Entity entity, World world) {
	}

	public final boolean consumeCulture(int amount) {
		boolean sufficient = amount <= getCulture();
		setCulture(getCulture() - amount);
		return sufficient;
	}

	public final int getCulture() {

		return player.getDataWatcher().getWatchableObjectInt(CULTURE_WATCHER);
	}

	public final String getPantheon() {

		return player.getDataWatcher().getWatchableObjectString(PANTHEON_WATCHER);
	}

	public final int getPantheonId() {

		return player.getDataWatcher().getWatchableObjectInt(ID_WATCHER);
	}

	public final void setCulture(int amount) {

		player.getDataWatcher().updateObject(CULTURE_WATCHER, amount > 0 ? (amount < maxCulture ? amount : maxCulture) : 0);
	}

	public final void setPantheon(String pantheon) {

		player.getDataWatcher().updateObject(PANTHEON_WATCHER, pantheon);
	}

	public final void setPantheonId(int id) {

		player.getDataWatcher().updateObject(ID_WATCHER, id);
	}

	public final int getMaxCulture() {

		return maxCulture;
	}

	public final void setMaxCulture(int amount) {

		maxCulture = (amount > 0 ? amount : 0);
		PantheonCraft.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
	}

	private static final String getSaveKey(EntityPlayer player) {

		return player.getCommandSenderName() + ":" + IDENTIFIER;
	}

	public static final void saveProxyData(EntityPlayer player) {

		NBTTagCompound savedData = new NBTTagCompound();
		ExtendedPlayer.get(player).saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}

	public static final void loadProxyData(EntityPlayer player) {

		ExtendedPlayer playerData = ExtendedPlayer.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
		if (savedData != null) {
			playerData.loadNBTData(savedData);
		}

		PantheonCraft.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
	}

}
