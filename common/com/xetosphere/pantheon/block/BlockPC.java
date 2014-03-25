package com.xetosphere.pantheon.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.xetosphere.pantheon.PantheonCraft;

public class BlockPC extends Block {

	public BlockPC() {
		super(Material.rock);

		setCreativeTab(PantheonCraft.tabPC);
	}

	public void registerBlockIcons(IIconRegister iconRegister) {

		blockIcon = iconRegister.registerIcon(getUnlocalizedName().substring(getUnlocalizedName().indexOf('.') + 1));
	}
}
