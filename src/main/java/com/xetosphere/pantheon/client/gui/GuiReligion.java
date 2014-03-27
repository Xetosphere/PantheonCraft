package com.xetosphere.pantheon.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import com.xetosphere.pantheon.PantheonCraft;
import com.xetosphere.pantheon.lib.ExtendedPlayer;
import com.xetosphere.pantheon.lib.PantheonReference;
import com.xetosphere.pantheon.lib.Textures;
import com.xetosphere.pantheon.network.packet.UpdateOpinionPacket;
import com.xetosphere.pantheon.network.packet.UpdateReligionPacket;

public class GuiReligion extends GuiScreen {

	private int xSize = 176;

	private int ySize = 206;

	EntityPlayer player;

	public GuiReligion(EntityPlayer player) {

		this.player = player;
	}

	public void updateScreen() {
		super.updateScreen();
	}

	public void drawScreen(int x, int y, float f) {

		drawDefaultBackground();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		mc.getTextureManager().bindTexture(Textures.GUI_RELIGION);

		int posX = (width - xSize) / 2;
		int posY = (height - ySize) / 2;
		
		int opinionId = ExtendedPlayer.get(player).getPantheonId();

		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawString(fontRendererObj, "Pantheon:", posX + (xSize / 12 * 2), posY + 16, 0xffffffff);
		drawString(fontRendererObj, "Culture:", posX + (xSize / 12 * 2), posY + 36, 0xffffffff);
		drawString(fontRendererObj, "Opinion:", posX + (xSize / 12 * 2), posY + 56, 0xffffffff);
		drawCenteredString(fontRendererObj, ExtendedPlayer.get(player).getPantheon(), posX + (xSize / 6 * 4), posY + 16, 0xffffffff);
		drawCenteredString(fontRendererObj, ExtendedPlayer.get(player).getCulture() + "/" + ExtendedPlayer.get(player).getMaxCulture(), posX + (xSize / 6 * 4), posY + 36, 0xffffffff);
		drawCenteredString(fontRendererObj, "" + ExtendedPlayer.get(player).getOpinion(opinionId), posX + (xSize / 6 * 4), posY + 56, 0xffffffff);
		drawCenteredString(fontRendererObj, "Change pantheon to:", posX + (xSize / 2), posY + (ySize / 2), 0xffffffff);

		super.drawScreen(x, y, f);
	}

	public boolean doesGuiPauseGame() {

		return false;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {

		this.buttonList.clear();

		int posX = (width - xSize) / 2;
		int posY = (height - ySize) / 2;

		this.buttonList.add(new GuiButton(0, posX + (xSize / 9 * 0) + 6, posY + 120 + (25 * 0), 50, 20, PantheonReference.pantheons[PantheonReference.NO_RELIGION]));
		this.buttonList.add(new GuiButton(1, posX + (xSize / 9 * 0) + 6, posY + 120 + (25 * 1), 50, 20, PantheonReference.pantheons[PantheonReference.GREEK]));
		this.buttonList.add(new GuiButton(2, posX + (xSize / 9 * 0) + 6, posY + 120 + (25 * 2), 50, 20, PantheonReference.pantheons[PantheonReference.NORSE]));
		this.buttonList.add(new GuiButton(3, posX + (xSize / 9 * 3) + 6, posY + 120 + (25 * 0), 50, 20, PantheonReference.pantheons[PantheonReference.ROMAN]));
		this.buttonList.add(new GuiButton(4, posX + (xSize / 9 * 3) + 6, posY + 120 + (25 * 1), 50, 20, PantheonReference.pantheons[PantheonReference.EGYPTIAN]));
		this.buttonList.add(new GuiButton(5, posX + (xSize / 9 * 3) + 6, posY + 120 + (25 * 2), 50, 20, PantheonReference.pantheons[PantheonReference.MAYAN]));
		this.buttonList.add(new GuiButton(6, posX + (xSize / 9 * 6) + 6, posY + 120 + (25 * 0), 50, 20, PantheonReference.pantheons[PantheonReference.CHINESE]));
		this.buttonList.add(new GuiButton(7, posX + (xSize / 9 * 6) + 6, posY + 120 + (25 * 1), 50, 20, PantheonReference.pantheons[PantheonReference.HINDU]));
		this.buttonList.add(new GuiButton(8, posX + (xSize / 9 * 6) + 6, posY + 120 + (25 * 2), 50, 20, PantheonReference.pantheons[PantheonReference.AZTECIAN]));
	}

	public void actionPerformed(GuiButton button) {

		String currentReligion = ExtendedPlayer.get(player).getPantheon();
		String religion = PantheonReference.pantheons[PantheonReference.NO_RELIGION];
		int religionId = PantheonReference.NO_RELIGION;
		int culturePoints = ExtendedPlayer.get(player).getCulture();
		int opinionGreek = ExtendedPlayer.get(player).getOpinion(PantheonReference.GREEK);
		int opinionNorse = ExtendedPlayer.get(player).getOpinion(PantheonReference.NORSE);
		int opinionRoman = ExtendedPlayer.get(player).getOpinion(PantheonReference.ROMAN);
		int opinionEgyptian = ExtendedPlayer.get(player).getOpinion(PantheonReference.EGYPTIAN);
		int opinionMayan = ExtendedPlayer.get(player).getOpinion(PantheonReference.MAYAN);
		int opinionChinese = ExtendedPlayer.get(player).getOpinion(PantheonReference.CHINESE);
		int opinionHindu = ExtendedPlayer.get(player).getOpinion(PantheonReference.HINDU);
		int opinionAztec = ExtendedPlayer.get(player).getOpinion(PantheonReference.AZTECIAN);
		int amount = 20;

		switch (button.id) {
			case 0:
				if (!currentReligion.equals(PantheonReference.pantheons[PantheonReference.NO_RELIGION])) {

					culturePoints = 0;
				}
				religion = PantheonReference.pantheons[PantheonReference.NO_RELIGION];
				religionId = PantheonReference.NO_RELIGION;
				break;
			case 1:
				if (!currentReligion.equals(PantheonReference.pantheons[PantheonReference.GREEK])) {

					culturePoints = 0;
					if (opinionGreek - amount >= -200) {
						opinionGreek -= amount;
					} else {
						opinionGreek = -200;
					}
				}
				religion = PantheonReference.pantheons[PantheonReference.GREEK];
				religionId = PantheonReference.GREEK;
				break;
			case 2:
				if (!currentReligion.equals(PantheonReference.pantheons[PantheonReference.NORSE])) {

					culturePoints = 0;
					if (opinionNorse - amount >= -200) {
						opinionNorse -= amount;
					} else {
						opinionNorse = -200;
					}
				}
				religion = PantheonReference.pantheons[PantheonReference.NORSE];
				religionId = PantheonReference.NORSE;
				break;
			case 3:
				if (!currentReligion.equals(PantheonReference.pantheons[PantheonReference.ROMAN])) {

					culturePoints = 0;
					if (opinionRoman - amount >= -200) {
						opinionRoman -= amount;
					} else {
						opinionRoman = -200;
					}
				}
				religion = PantheonReference.pantheons[PantheonReference.ROMAN];
				religionId = PantheonReference.ROMAN;
				break;
			case 4:
				if (!currentReligion.equals(PantheonReference.pantheons[PantheonReference.EGYPTIAN])) {

					culturePoints = 0;
					if (opinionEgyptian - amount >= -200) {
						opinionEgyptian -= amount;
					} else {
						opinionEgyptian = -200;
					}
				}
				religion = PantheonReference.pantheons[PantheonReference.EGYPTIAN];
				religionId = PantheonReference.EGYPTIAN;
				break;
			case 5:
				if (!currentReligion.equals(PantheonReference.pantheons[PantheonReference.MAYAN])) {

					culturePoints = 0;
					if (opinionMayan - amount >= -200) {
						opinionMayan -= amount;
					} else {
						opinionMayan = -200;
					}
				}
				religion = PantheonReference.pantheons[PantheonReference.MAYAN];
				religionId = PantheonReference.MAYAN;
				break;
			case 6:
				if (!currentReligion.equals(PantheonReference.pantheons[PantheonReference.CHINESE])) {

					culturePoints = 0;
					if (opinionChinese - amount >= -200) {
						opinionChinese -= amount;
					} else {
						opinionChinese = -200;
					}
				}
				religion = PantheonReference.pantheons[PantheonReference.CHINESE];
				religionId = PantheonReference.CHINESE;
				break;
			case 7:
				if (!currentReligion.equals(PantheonReference.pantheons[PantheonReference.HINDU])) {

					culturePoints = 0;
					if (opinionHindu - amount >= -200) {
						opinionHindu -= amount;
					} else {
						opinionHindu = -200;
					}
				}
				religion = PantheonReference.pantheons[PantheonReference.HINDU];
				religionId = PantheonReference.HINDU;
				break;
			case 8:
				if (!currentReligion.equals(PantheonReference.pantheons[PantheonReference.AZTECIAN])) {

					culturePoints = 0;
					if (opinionAztec - amount >= -200) {
						opinionAztec -= amount;
					} else {
						opinionAztec = -200;
					}
				}
				religion = PantheonReference.pantheons[PantheonReference.AZTECIAN];
				religionId = PantheonReference.AZTECIAN;
				break;
		}

		PantheonCraft.packetPipeline.sendToServer(new UpdateReligionPacket(religion, religionId, culturePoints));
		PantheonCraft.packetPipeline.sendToServer(new UpdateOpinionPacket(opinionGreek, opinionNorse, opinionRoman, opinionEgyptian, opinionMayan, opinionChinese, opinionHindu, opinionAztec));
	}
}
