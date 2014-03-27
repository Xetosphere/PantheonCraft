package com.xetosphere.pantheon.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

import com.xetosphere.pantheon.lib.ExtendedPlayer;
import com.xetosphere.pantheon.network.AbstractPacket;

import cpw.mods.fml.common.network.ByteBufUtils;

public class UpdateReligionPacket extends AbstractPacket {

	private NBTTagCompound data;

	public UpdateReligionPacket() {
	}

	public UpdateReligionPacket(String religion, int religionId, int culture) {

		data = new NBTTagCompound();
		data.setString("Pantheon", religion);
		data.setInteger("IdPantheon", religionId);
		data.setInteger("Culture", culture);
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {

		ByteBufUtils.writeTag(buffer, data);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {

		data = ByteBufUtils.readTag(buffer);
	}

	@Override
	public void handleClientSide(EntityPlayer player) {

	}

	@Override
	public void handleServerSide(EntityPlayer player) {

		ExtendedPlayer.get(player).setPantheon(data.getString("Pantheon"));
		ExtendedPlayer.get(player).setPantheonId(data.getInteger("IdPantheon"));
		ExtendedPlayer.get(player).setCulture(data.getInteger("Culture"));
		if (player.worldObj.isRemote) player.addChatComponentMessage(new ChatComponentText("You set your pantheon to: " + ExtendedPlayer.get(player).getPantheon()));
		System.out.println("[XETOPC] Pantheon = " + ExtendedPlayer.get(player).getPantheon());
		System.out.println("[XETOPC] PantheonId = " + ExtendedPlayer.get(player).getPantheonId());
	}

}
