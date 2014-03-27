package com.xetosphere.pantheon.block;

import net.minecraft.block.Block;

import com.xetosphere.pantheon.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static Block oreRuby;

	public void init() {

		oreRuby = new BlockPC().setBlockName(Strings.RESOURCE_PREFIX + Strings.RUBYORE_NAME);

		GameRegistry.registerBlock(oreRuby, Strings.RUBYORE_NAME);
	}
}
