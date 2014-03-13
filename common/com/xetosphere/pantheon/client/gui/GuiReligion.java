package com.xetosphere.pantheon.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import org.lwjgl.opengl.GL11;

import com.xetosphere.pantheon.entity.ExtendedPlayer;
import com.xetosphere.pantheon.lib.Textures;

public class GuiReligion extends GuiScreen {

	private int xSize = 176;

	private int ySize = 166;

	EntityPlayer player;

	private String[] pantheons = { "None", "Greek", "Norse", "Roman", "Egyptian" };

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

		this.buttonList.add(new GuiButton(0, posX + 10, posY + 10, 50, 20, pantheons[0]));
		this.buttonList.add(new GuiButton(1, posX + 10, posY + 40, 50, 20, pantheons[1]));
		this.buttonList.add(new GuiButton(2, posX + 10, posY + 70, 50, 20, pantheons[2]));
		this.buttonList.add(new GuiButton(3, posX + 10, posY + 100, 50, 20, pantheons[3]));
		this.buttonList.add(new GuiButton(4, posX + 10, posY + 130, 50, 20, pantheons[4]));
	}

	public void actionPerformed(GuiButton button) {

		switch (button.id) {
			case 0:
				ExtendedPlayer.get(player).setPantheon(pantheons[0]);
				ExtendedPlayer.get(player).setPantheonId(0);
				System.out.println("[XETOPC] Pantheon = " + ExtendedPlayer.get(player).getPantheon());
				System.out.println("[XETOPC] PantheonId = " + ExtendedPlayer.get(player).getPantheonId());
				player.addChatComponentMessage(new ChatComponentText("You set your pantheon to: " + ExtendedPlayer.get(player).getPantheon()));
				break;
			case 1:
				ExtendedPlayer.get(player).setPantheon(pantheons[1]);
				ExtendedPlayer.get(player).setPantheonId(1);
				System.out.println("[XETOPC] Pantheon = " + ExtendedPlayer.get(player).getPantheon());
				System.out.println("[XETOPC] PantheonId = " + ExtendedPlayer.get(player).getPantheonId());
				player.addChatComponentMessage(new ChatComponentText("You set your pantheon to: " + ExtendedPlayer.get(player).getPantheon()));
				break;
			case 2:
				ExtendedPlayer.get(player).setPantheon(pantheons[2]);
				ExtendedPlayer.get(player).setPantheonId(2);
				System.out.println("[XETOPC] Pantheon = " + ExtendedPlayer.get(player).getPantheon());
				System.out.println("[XETOPC] PantheonId = " + ExtendedPlayer.get(player).getPantheonId());
				player.addChatComponentMessage(new ChatComponentText("You set your pantheon to: " + ExtendedPlayer.get(player).getPantheon()));
				break;
			case 3:
				ExtendedPlayer.get(player).setPantheon(pantheons[3]);
				ExtendedPlayer.get(player).setPantheonId(3);
				System.out.println("[XETOPC] Pantheon = " + ExtendedPlayer.get(player).getPantheon());
				System.out.println("[XETOPC] PantheonId = " + ExtendedPlayer.get(player).getPantheonId());
				player.addChatComponentMessage(new ChatComponentText("You set your pantheon to: " + ExtendedPlayer.get(player).getPantheon()));
				break;
			case 4:
				ExtendedPlayer.get(player).setPantheon(pantheons[4]);
				ExtendedPlayer.get(player).setPantheonId(4);
				System.out.println("[XETOPC] Pantheon = " + ExtendedPlayer.get(player).getPantheon());
				System.out.println("[XETOPC] PantheonId = " + ExtendedPlayer.get(player).getPantheonId());
				player.addChatComponentMessage(new ChatComponentText("You set your pantheon to: " + ExtendedPlayer.get(player).getPantheon()));
				break;
		}
	}
}
