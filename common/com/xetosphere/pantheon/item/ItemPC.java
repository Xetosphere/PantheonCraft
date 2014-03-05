package com.xetosphere.pantheon.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.xetosphere.pantheon.PantheonCraft;

public class ItemPC extends Item {

	public ItemPC() {

		setCreativeTab(PantheonCraft.tabPC);
	}

	public void registerIcons(IIconRegister iconRegister) {

		itemIcon = iconRegister.registerIcon(getUnlocalizedName().substring(getUnlocalizedName().indexOf('.') + 1));
	}
}
