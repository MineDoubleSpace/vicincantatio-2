package me.lubinn.Vicincantatio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class VicincantatioPlayerListener implements Listener {
	public static Vicincantatio plugin;
	PlayerChatEvent ev;

	public VicincantatioPlayerListener(Vicincantatio instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent playermoveevent) {
	}

	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		VicincantatioSpell spellThread = new VicincantatioSpell(plugin);
		String message = event.getMessage();
		spellThread.ev = event;
		if ((message.trim().equalsIgnoreCase("tueri")) && (!spellThread.CheckPermissions("tueri", null))) {
			message = "tuerifail";
		}
		if ((message.trim().equalsIgnoreCase("itero")) && (spellThread.CheckPermissions("itero", null))) {
			try {
				FileInputStream in = new FileInputStream(Vicincantatio.VicincantatioLog);
				Vicincantatio.prop.load(in);
				String LastSpell = Vicincantatio.prop.getProperty(event.getPlayer().getName());
				message = LastSpell;

				in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				FileOutputStream out = new FileOutputStream(Vicincantatio.VicincantatioLog);
				Vicincantatio.prop.put(event.getPlayer().getName(), message);
				Vicincantatio.prop.store(out, null);
				out.flush();
				out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String[] components = message.trim().split(" ");
		this.ev = event;
		int m = 0;
		if ((components[m].equalsIgnoreCase("magna")) || (components[m].equalsIgnoreCase("parva"))) {
			m++;
		}
		if ((components.length > m + 1) && (components[(m + 1)].equalsIgnoreCase("augere")) && (components[(m + 2)].equalsIgnoreCase("silentium"))
				&& (Vicincantatio.config.getBoolean(components[m].toLowerCase() + "_enabled", false))) {
			this.ev.setCancelled(true);
		}
		if ((components.length > m + 2) && (components[(m + 2)].equalsIgnoreCase("augere")) && (components[(m + 3)].equalsIgnoreCase("silentium"))) {
			if (Vicincantatio.config.getBoolean(components[m].toLowerCase() + "_" + components[(m + 1)].toLowerCase() + "_enabled", false)) {
				this.ev.setCancelled(true);
			} else if (Vicincantatio.config.getBoolean(components[m].toLowerCase() + "_" + components[(m + 1)].toLowerCase(), false)) {
				this.ev.setCancelled(true);
			}
		}
		spellThread.CastSpell(components, this.ev);
	}
}