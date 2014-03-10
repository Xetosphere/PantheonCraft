package com.xetosphere.pantheon.item;

import com.xetosphere.pantheon.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems {

	public static Item talisman;
	public static Item talismanRuby;
	public static Item ruby;

	public void init() {

		talisman = new ItemTalisman().setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.TALISMAN_NAME).setTextureName(Strings.RESOURCE_PREFIX + Strings.TALISMAN_NAME);
		talismanRuby = new ItemTalisman().setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.TALISMANRUBY_NAME).setTextureName(Strings.RESOURCE_PREFIX + Strings.TALISMANRUBY_NAME);
		ruby = new ItemPC().setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.RUBY_NAME).setTextureName(Strings.RESOURCE_PREFIX + Strings.RUBY_NAME);

		GameRegistry.registerItem(talisman, Strings.TALISMAN_NAME);
		GameRegistry.registerItem(talismanRuby, Strings.TALISMANRUBY_NAME);
		GameRegistry.registerItem(ruby, Strings.RUBY_NAME);
	}
}
