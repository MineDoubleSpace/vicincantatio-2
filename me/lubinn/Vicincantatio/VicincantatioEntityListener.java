package me.lubinn.Vicincantatio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VicincantatioEntityListener implements Listener {
	public Vicincantatio plugin;
	private boolean flag = false;
	private boolean noTimer = true;
	protected Timer timer = new Timer();

	TimerTask task = new TimerTask() {
		public void run() {
			int iterations = 0;
			if (iterations >= 1) {
				VicincantatioEntityListener.this.flag = true;
				cancel();
			}
			iterations++;
		}
	};

	public VicincantatioEntityListener(Vicincantatio instance) {
		this.plugin = instance;
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if ((event.getEntity() instanceof Player)) {
			String playername = ((Player) event.getEntity()).getName();
			try {
				FileInputStream in = new FileInputStream(Vicincantatio.VicincantatioLog);
				Vicincantatio.prop.load(in);
				String LastSpell = Vicincantatio.prop.getProperty(playername);
				in.close();

				if ((LastSpell.trim().split(" ")[0].equalsIgnoreCase("celeritas")) && (event.getCause() == EntityDamageEvent.DamageCause.FALL)) {
					event.setCancelled(true);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}