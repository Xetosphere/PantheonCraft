package com.xetosphere.pantheon;

import net.minecraft.creativetab.CreativeTabs;

import com.xetosphere.pantheon.block.ModBlocks;
import com.xetosphere.pantheon.core.proxy.CommonProxy;
import com.xetosphere.pantheon.creativetab.TabPantheon;
import com.xetosphere.pantheon.item.ModItems;
import com.xetosphere.pantheon.lib.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class PantheonCraft {

	public static final CreativeTabs tabPC = new TabPantheon(CreativeTabs.getNextID(), Reference.MOD_ID);

	ModBlocks blocks = new ModBlocks();
	ModItems items = new ModItems();
	public static FMLEventChannel channel;

	@Instance(Reference.MOD_ID)
	public static PantheonCraft instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {

		blocks.init();
		items.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("ExampleMod");
    	proxy.load();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}
}
