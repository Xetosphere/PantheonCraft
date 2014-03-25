package com.xetosphere.pantheon.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import com.xetosphere.pantheon.lib.ExtendedPlayer;
import com.xetosphere.pantheon.lib.PantheonReference;
import com.xetosphere.pantheon.network.AbstractPacket;

import cpw.mods.fml.common.network.ByteBufUtils;

public class UpdateOpinionPacket extends AbstractPacket {

	private NBTTagCompound data;

	public UpdateOpinionPacket() {

	}

	public UpdateOpinionPacket(int op1, int op2, int op3, int op4, int op5, int op6, int op7, int op8) {

		data = new NBTTagCompound();
		data.setInteger("OpinionGreek", op1);
		data.setInteger("OpinionNorse", op2);
		data.setInteger("OpinionRoman", op3);
		data.setInteger("OpinionEgyptian", op4);
		data.setInteger("OpinionMayan", op5);
		data.setInteger("OpinionChinese", op6);
		data.setInteger("OpinionHindu", op7);
		data.setInteger("OpinionAztec", op8);
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

		ExtendedPlayer.get(player).setOpinion(data.getInteger("OpinionGreek"), PantheonReference.GREEK);
		ExtendedPlayer.get(player).setOpinion(data.getInteger("OpinionNorse"), PantheonReference.NORSE);
		ExtendedPlayer.get(player).setOpinion(data.getInteger("OpinionRoman"), PantheonReference.ROMAN);
		ExtendedPlayer.get(player).setOpinion(data.getInteger("OpinionEgyptian"), PantheonReference.EGYPTIAN);
		ExtendedPlayer.get(player).setOpinion(data.getInteger("OpinionMayan"), PantheonReference.MAYAN);
		ExtendedPlayer.get(player).setOpinion(data.getInteger("OpinionChinese"), PantheonReference.CHINESE);
		ExtendedPlayer.get(player).setOpinion(data.getInteger("OpinionHindu"), PantheonReference.HINDU);
		ExtendedPlayer.get(player).setOpinion(data.getInteger("OpinionAztec"), PantheonReference.AZTECIAN);
	}

}
