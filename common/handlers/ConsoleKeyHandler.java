package sharConsole.common.handlers;

import java.util.EnumSet;

import sharConsole.common.gui.GuiConsole;

import net.minecraft.client.settings.KeyBinding;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

/**
 * Key handler for opening and closing the console.
 * @author Sharingan616/Benjamin Tovar-Prince (http://benjamintovar-prince.com)
 *
 */
public class ConsoleKeyHandler extends KeyHandler {

		private EnumSet tickTypes = EnumSet.of(TickType.CLIENT);
		
		public ConsoleKeyHandler(KeyBinding[] keyBindings, boolean[] repeatings) {
			super(keyBindings, repeatings);		
		}

		@Override
		public String getLabel() {
			return "Console Key Handler";
		}

		@Override
		public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
			if(tickEnd)
			{
				if (FMLClientHandler.instance().getClient().currentScreen == null)
				{
					//debug.println("Opening.");
					FMLClientHandler.instance().getClient().displayGuiScreen(GuiConsole.getInstance());
				}
				else if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiConsole)
				{
					//debug.println("Closing.");
					FMLClientHandler.instance().getClient().displayGuiScreen(null);
				}
			}
		}

		@Override
		public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		}

		@Override
		public EnumSet<TickType> ticks() {
			return tickTypes;
		}
	}