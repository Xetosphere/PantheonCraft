package com.xetosphere.pantheon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import com.xetosphere.pantheon.block.ModBlocks;
import com.xetosphere.pantheon.core.proxy.CommonProxy;
import com.xetosphere.pantheon.creativetab.TabPantheon;
import com.xetosphere.pantheon.event.CultureEventHandler;
import com.xetosphere.pantheon.item.ModItems;
import com.xetosphere.pantheon.lib.Reference;
import com.xetosphere.pantheon.network.PacketPipeline;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class PantheonCraft {

	public static final CreativeTabs tabPC = new TabPantheon(CreativeTabs.getNextID(), Reference.MOD_ID);

	public static final PacketPipeline packetPipeline = new PacketPipeline();

	ModBlocks blocks = new ModBlocks();
	ModItems items = new ModItems();

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

		packetPipeline.initialise();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

		packetPipeline.postInitialise();

		MinecraftForge.EVENT_BUS.register(new CultureEventHandler());

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
	}
}
