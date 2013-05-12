package sharConsole.common;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import sharConsole.common.handlers.ConsoleKeyHandler;
import sharConsole.shar.util.Debug;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;


/**
 * This mod adds a console into the minecraft user interface.
 * 
 * @author Sharingan616
 * @version 1.4.0
 */
@Mod(modid = "mod_sharConsole", name="Minecraft Console", version = "1.4.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SharConsole {
	private Debug debug;
	@Instance("Minecraft Console")
	public static SharConsole instance;
	
	@SidedProxy(clientSide="sharConsole.common.client.ClientProxySharConsole", serverSide="sharConsole.common.CommonProxySharConsole")
	public static CommonProxySharConsole proxy;
	
	@PreInit
	public void preLoad(FMLPreInitializationEvent event)
	{
		this.debug.debugger = true;
		this.debug.modName = "Minecraft Console";
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		//Console button control
		KeyBinding[] consoleButton = {new KeyBinding("Console", Keyboard.KEY_GRAVE)};
		boolean[] repeats = {false};
		KeyBindingRegistry.registerKeyBinding(new ConsoleKeyHandler(consoleButton, repeats));
		
		
		proxy.registerRenderers();
	}
	
	@PostInit
	public void postLoad(FMLPostInitializationEvent event) {}
	
	public static String getVersion()
	{
		return "1.4.0";
	}

}
