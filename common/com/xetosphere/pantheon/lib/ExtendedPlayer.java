package com.xetosphere.pantheon.lib;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.xetosphere.pantheon.PantheonCraft;
import com.xetosphere.pantheon.network.packet.SyncPlayerPropsPacket;
import com.xetosphere.pantheon.proxy.CommonProxy;

public class ExtendedPlayer implements IExtendedEntityProperties {

	public static final String IDENTIFIER = Reference.MOD_ID + "_properties";

	private final EntityPlayer player;

	private int culture, maxCulture, pantheonId, greekOp, norseOp, romanOp, egyptianOp, mayanOp, chineseOp, hinduOp, aztecOp, maxOpinion;

	private String pantheon;

	public static final int CULTURE_WATCHER = 21;
	public static final int PANTHEON_WATCHER = 22;
	public static final int ID_WATCHER = 23;
	public static final int GREEKOP_WATCHER = 24;
	public static final int NORSEOP_WATCHER = 25;
	public static final int ROMANOP_WATCHER = 26;
	public static final int EGYPTIANOP_WATCHER = 27;
	public static final int MAYANOP_WATCHER = 28;
	public static final int CHINESEOP_WATCHER = 29;
	public static final int HINDUOP_WATCHER = 30;
	public static final int AZTECOP_WATCHER = 31;

	public ExtendedPlayer(EntityPlayer player) {

		this.player = player;
		culture = 0;
		greekOp = 0;
		norseOp = 0;
		romanOp = 0;
		egyptianOp = 0;
		mayanOp = 0;
		chineseOp = 0;
		hinduOp = 0;
		aztecOp = 0;
		maxOpinion = PantheonReference.MAX_OPINION;
		maxCulture = PantheonReference.MAX_CULTURE;
		pantheon = PantheonReference.pantheons[PantheonReference.NO_RELIGION];
		pantheonId = PantheonReference.NO_RELIGION;
		player.getDataWatcher().addObject(GREEKOP_WATCHER, greekOp);
		player.getDataWatcher().addObject(NORSEOP_WATCHER, norseOp);
		player.getDataWatcher().addObject(ROMANOP_WATCHER, romanOp);
		player.getDataWatcher().addObject(EGYPTIANOP_WATCHER, egyptianOp);
		player.getDataWatcher().addObject(MAYANOP_WATCHER, mayanOp);
		player.getDataWatcher().addObject(CHINESEOP_WATCHER, chineseOp);
		player.getDataWatcher().addObject(HINDUOP_WATCHER, hinduOp);
		player.getDataWatcher().addObject(AZTECOP_WATCHER, aztecOp);
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
		properties.setInteger("MaxOpinion", maxOpinion);
		properties.setInteger("OpinionGreek", player.getDataWatcher().getWatchableObjectInt(GREEKOP_WATCHER));
		properties.setInteger("OpinionNorse", player.getDataWatcher().getWatchableObjectInt(NORSEOP_WATCHER));
		properties.setInteger("OpinionRoman", player.getDataWatcher().getWatchableObjectInt(ROMANOP_WATCHER));
		properties.setInteger("OpinionEgyptian", player.getDataWatcher().getWatchableObjectInt(EGYPTIANOP_WATCHER));
		properties.setInteger("OpinionMayan", player.getDataWatcher().getWatchableObjectInt(MAYANOP_WATCHER));
		properties.setInteger("OpinionChinese", player.getDataWatcher().getWatchableObjectInt(CHINESEOP_WATCHER));
		properties.setInteger("OpinionHindu", player.getDataWatcher().getWatchableObjectInt(HINDUOP_WATCHER));
		properties.setInteger("OpinionAztec", player.getDataWatcher().getWatchableObjectInt(AZTECOP_WATCHER));
		properties.setString("Pantheon", player.getDataWatcher().getWatchableObjectString(PANTHEON_WATCHER));
		properties.setInteger("IdPantheon", player.getDataWatcher().getWatchableObjectInt(ID_WATCHER));
		compound.setTag(IDENTIFIER, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {

		NBTTagCompound properties = (NBTTagCompound) compound.getTag(IDENTIFIER);
		player.getDataWatcher().updateObject(CULTURE_WATCHER, properties.getInteger("Culture"));
		maxCulture = properties.getInteger("MaxCulture");
		maxOpinion = properties.getInteger("MaxOpinion");
		player.getDataWatcher().updateObject(GREEKOP_WATCHER, properties.getInteger("OpinionGreek"));
		player.getDataWatcher().updateObject(NORSEOP_WATCHER, properties.getInteger("OpinionNorse"));
		player.getDataWatcher().updateObject(ROMANOP_WATCHER, properties.getInteger("OpinionRoman"));
		player.getDataWatcher().updateObject(EGYPTIANOP_WATCHER, properties.getInteger("OpinionEgyptian"));
		player.getDataWatcher().updateObject(MAYANOP_WATCHER, properties.getInteger("OpinionMayan"));
		player.getDataWatcher().updateObject(CHINESEOP_WATCHER, properties.getInteger("OpinionChinese"));
		player.getDataWatcher().updateObject(HINDUOP_WATCHER, properties.getInteger("OpinionHindu"));
		player.getDataWatcher().updateObject(AZTECOP_WATCHER, properties.getInteger("OpinionAztec"));
		player.getDataWatcher().updateObject(PANTHEON_WATCHER, properties.getString("Pantheon"));
		player.getDataWatcher().updateObject(ID_WATCHER, properties.getInteger("IdPantheon"));
		System.out.println("[XETOPC PROPS] Culture from NBT: " + player.getDataWatcher().getWatchableObjectInt(CULTURE_WATCHER) + "/" + maxCulture);
		System.out.println("[XETOPC PROPS] Pantheon from NBT: [ID = " + player.getDataWatcher().getWatchableObjectInt(ID_WATCHER) + "] " + player.getDataWatcher().getWatchableObjectString(PANTHEON_WATCHER));
	}

	@Override
	public void init(Entity entity, World world) {
	}

	public final boolean addOpinion(int amount, int pantheon) {

		boolean sufficient = amount + getOpinion(pantheon) <= getMaxOpinion();
		if (sufficient) {
			setOpinion(getOpinion(pantheon) + amount, pantheon);
		} else {
			setOpinion(getMaxOpinion(), pantheon);
		}
		return sufficient;
	}

	public final boolean consumeCulture(int amount) {

		boolean sufficient = amount <= getCulture();
		setCulture(getCulture() - amount);
		return sufficient;
	}

	public final boolean consumeOpinion(int amount, int pantheon) {

		boolean sufficient = amount + getOpinion(pantheon) < -150;
		if (sufficient) {
			setOpinion(getOpinion(pantheon) - amount, pantheon);
		} else {
			setOpinion(-200, pantheon);
		}
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

	public final int getOpinion(int pantheon) {

		int opinion = 0;

		switch (pantheon) {
			case PantheonReference.GREEK:
				opinion = player.getDataWatcher().getWatchableObjectInt(GREEKOP_WATCHER);
				break;
			case PantheonReference.NORSE:
				opinion = player.getDataWatcher().getWatchableObjectInt(NORSEOP_WATCHER);
				break;
			case PantheonReference.ROMAN:
				opinion = player.getDataWatcher().getWatchableObjectInt(ROMANOP_WATCHER);
				break;
			case PantheonReference.EGYPTIAN:
				opinion = player.getDataWatcher().getWatchableObjectInt(EGYPTIANOP_WATCHER);
				break;
			case PantheonReference.MAYAN:
				opinion = player.getDataWatcher().getWatchableObjectInt(MAYANOP_WATCHER);
				break;
			case PantheonReference.CHINESE:
				opinion = player.getDataWatcher().getWatchableObjectInt(CHINESEOP_WATCHER);
				break;
			case PantheonReference.HINDU:
				opinion = player.getDataWatcher().getWatchableObjectInt(HINDUOP_WATCHER);
				break;
			case PantheonReference.AZTECIAN:
				opinion = player.getDataWatcher().getWatchableObjectInt(AZTECOP_WATCHER);
				break;
		}

		return opinion;
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

	public final void setOpinion(int opinion, int pantheon) {

		switch (pantheon) {
			case PantheonReference.GREEK:
				player.getDataWatcher().updateObject(GREEKOP_WATCHER, opinion);
				break;
			case PantheonReference.NORSE:
				player.getDataWatcher().updateObject(NORSEOP_WATCHER, opinion);
				break;
			case PantheonReference.ROMAN:
				player.getDataWatcher().updateObject(ROMANOP_WATCHER, opinion);
				break;
			case PantheonReference.EGYPTIAN:
				player.getDataWatcher().updateObject(EGYPTIANOP_WATCHER, opinion);
				break;
			case PantheonReference.MAYAN:
				player.getDataWatcher().updateObject(MAYANOP_WATCHER, opinion);
				break;
			case PantheonReference.CHINESE:
				player.getDataWatcher().updateObject(CHINESEOP_WATCHER, opinion);
				break;
			case PantheonReference.HINDU:
				player.getDataWatcher().updateObject(HINDUOP_WATCHER, opinion);
				break;
			case PantheonReference.AZTECIAN:
				player.getDataWatcher().updateObject(AZTECOP_WATCHER, opinion);
				break;
		}
	}

	public final int getMaxCulture() {

		return maxCulture;
	}

	public final int getMaxOpinion() {

		return maxOpinion;
	}

	public final void setMaxCulture(int amount) {

		maxCulture = (amount > 0 ? amount : 0);
		PantheonCraft.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
		PantheonCraft.packetPipeline.sendToServer(new SyncPlayerPropsPacket(player));
	}

	public final void setMaxOpinion(int amount) {

		maxOpinion = (amount > 0 ? amount : 0);
		PantheonCraft.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
		PantheonCraft.packetPipeline.sendToServer(new SyncPlayerPropsPacket(player));
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
		PantheonCraft.packetPipeline.sendToServer(new SyncPlayerPropsPacket(player));
	}

}
