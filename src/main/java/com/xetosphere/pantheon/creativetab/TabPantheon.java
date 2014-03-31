package com.xetosphere.pantheon.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.xetosphere.pantheon.item.ModItems;

public class TabPantheon extends CreativeTabs {

	public TabPantheon(int id, String inCodeName) {
		super(id, inCodeName);
	}

	@Override
	public Item getTabIconItem() {

		return ModItems.talismanRuby;
	}

}
