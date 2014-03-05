package com.xetosphere.pantheon.block;

import com.xetosphere.pantheon.PantheonCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockPC extends Block {

	public BlockPC() {
		super(Material.rock);

		setCreativeTab(PantheonCraft.tabPC);
	}

	public void registerBlockIcons(IIconRegister iconRegister) {

		blockIcon = iconRegister.registerIcon(getUnlocalizedName().substring(getUnlocalizedName().indexOf('.') + 1));
	}
}
