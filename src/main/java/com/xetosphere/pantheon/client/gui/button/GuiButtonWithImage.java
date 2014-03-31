package com.xetosphere.pantheon.client.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiButtonWithImage extends GuiButton {

	private final int textureX;
	private final int textureY;
	private final ResourceLocation texture;

	public GuiButtonWithImage(int buttonId, int x, int y, int width, int height, int textureX, int textureY, String text, ResourceLocation texture) {

		super(buttonId, x, y, width, height, text);
		this.textureX = textureX;
		this.textureY = textureY;
		this.texture = texture;
	}

	public GuiButtonWithImage setDisplayString(String displayString) {

		this.displayString = displayString;
		return this;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {

		if (this.visible) {

			boolean isMouseOver = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(texture);
			drawTexturedModalRect(xPosition, yPosition, textureX, textureY, width, height);

			if (displayString != null) {

				drawCenteredString(mc.fontRenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, isMouseOver ? 0xffffa0 : 0xffffff);
			}
		}
	}
}
