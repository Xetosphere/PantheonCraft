package com.xetosphere.pantheon.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import com.xetosphere.pantheon.entity.ExtendedPlayer;
import com.xetosphere.pantheon.item.ItemTalisman;
import com.xetosphere.pantheon.lib.Textures;

public class GuiReligion extends GuiScreen {

	private int xSize = 176;

	private int ySize = 166;

	private String pantheon;

	private String pantheonId;

	EntityPlayer player;

	public GuiReligion(EntityPlayer player) {

		this.player = player;
	}

	public void updateScreen() {
		super.updateScreen();

		pantheon = "Pantheon is set to " + ExtendedPlayer.get(player).getPantheon();
		pantheonId = "Pantheon ID = " + ExtendedPlayer.get(player).getPantheonId();
	}

	public void drawScreen(int x, int y, float f) {

		drawDefaultBackground();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		mc.getTextureManager().bindTexture(Textures.GUI_RELIGION);

		int posX = (width - xSize) / 2;
		int posY = (height - ySize) / 2;

		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawCenteredString(fontRendererObj, ExtendedPlayer.get(player).getPantheon(), posX + (xSize / 4 * 3), posY + 15, 0xffffffff);

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

		this.buttonList.add(new GuiButton(0, posX + 10, posY + 10, 50, 20, "None"));
		this.buttonList.add(new GuiButton(1, posX + 10, posY + 40, 50, 20, "Greek"));
		this.buttonList.add(new GuiButton(2, posX + 10, posY + 70, 50, 20, "Norse"));
		this.buttonList.add(new GuiButton(3, posX + 10, posY + 100, 50, 20, "Roman"));
		this.buttonList.add(new GuiButton(4, posX + 10, posY + 130, 50, 20, "Egyptian"));
	}

	public void actionPerformed(GuiButton button) {

		switch (button.id) {
			case 0:
				ItemTalisman.pantheon = 1;
				pantheon = "None";
				pantheonId = "" + 1;
				System.out.println("[XETOPC] Pantheon = " + pantheon);
				System.out.println("[XETOPC] PantheonId = " + pantheonId);
				break;
			case 1:
				ItemTalisman.pantheon = 2;
				pantheon = "Greek";
				pantheonId = "" + 2;
				System.out.println("[XETOPC] Pantheon = " + pantheon);
				System.out.println("[XETOPC] PantheonId = " + pantheonId);
				break;
			case 2:
				ItemTalisman.pantheon = 3;
				pantheon = "Norse";
				pantheonId = "" + 3;
				System.out.println("[XETOPC] Pantheon = " + pantheon);
				System.out.println("[XETOPC] PantheonId = " + pantheonId);
				break;
			case 3:
				ItemTalisman.pantheon = 4;
				pantheon = "Roman";
				pantheonId = "" + 4;
				System.out.println("[XETOPC] Pantheon = " + pantheon);
				System.out.println("[XETOPC] PantheonId = " + pantheonId);
				break;
			case 4:
				ItemTalisman.pantheon = 5;
				pantheon = "Egyptian";
				pantheonId = "" + 5;
				System.out.println("[XETOPC] Pantheon = " + pantheon);
				System.out.println("[XETOPC] PantheonId = " + pantheonId);
				break;
		}
	}
}
