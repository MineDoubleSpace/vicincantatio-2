package me.lubinn.Vicincantatio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Vicincantatio extends JavaPlugin {
	public static boolean debug = false;
	public static File VicincantatioLog;
	public static File configFile;
	public static Properties prop = new Properties();
	public static FileConfiguration config = null;
	public static FileConfiguration oldconfig;
	public static Logger log = Logger.getLogger("Minecraft");
	private final VicincantatioPlayerListener playerListener = new VicincantatioPlayerListener(this);
	private final VicincantatioBlockListener blockListener = new VicincantatioBlockListener(this);
	private final VicincantatioEntityListener entityListener = new VicincantatioEntityListener(this);

	public final Vicincantatio plugin = this;

	public void onEnable() {
		VicincantatioLog = new File(getDataFolder(), "Vicincantatio.log");
		if (debug) {
			log.info("[Vicincantatio-Debug] Loading FileConfigurations...");
		}
		ConfigLoad();
		log.info("Vicincantatio " + getDescription().getVersion() + " successfully loaded.");
		if (debug) {
			log.info("[Vicincantatio-Debug] Getting PluginManager");
		}
		PluginManager pm = getServer().getPluginManager();
		if (debug) {
			log.info("[Vicincantatio-Debug] Registering PLAYER_CHAT");
		}
		pm.registerEvents(this.playerListener, this);
		if (debug) {
			log.info("[Vicincantatio-Debug] Registering PLAYER_MOVE");
		}
		pm.registerEvents(this.blockListener, this);
		if (debug) {
			log.info("[Vicincantatio-Debug] Registering ENTITY_DAMAGE");
		}
		pm.registerEvents(this.entityListener, this);
		if (debug) {
			log.info("[Vicincantatio-Debug] Registering PLAYER_KICK");
		}
		if (!VicincantatioLog.exists()) {
			try {
				if (debug) {
					log.info("[Vicincantatio-Debug] Creating a new log");
				}
				VicincantatioLog.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if (debug) {
			log.info("[Vicincantatio-Debug] Attempting to get the Permissions plugin");
		}
		Plugin test = getServer().getPluginManager().getPlugin("Permissions");
		if (debug) {
			log.info("[Vicincantatio-Debug] Checking the existence of the Permissions plugin");
		}

		if (getServer().getPluginManager().getPlugin("PermissionsEx") != null) {
			log.info("[Vicincantatio] Loading up PermissionsEx");
		} else {
			log.info("[Vicincantatio] Permissions plugin not found, everyone can cast spells.");
		}
	}

	private void ConfigLoad() {
		configFile = new File(getDataFolder(), "config.yml");
		oldconfig = this.getConfig();

		if (debug) {
			log.info("[Vicincantatio-Debug] Checking if Vicincantatio.config exists");
		}

		if (!configFile.exists()) {
			log.info("[Vicincantatio] FileConfiguration file does not exist! Creating one now...");
			ConfigCreate(false);
		} else {
			if (debug) {
				log.info("[Vicincantatio-Debug] Loading the old FileConfiguration file");
			}
			try {
				oldconfig.load(configFile);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			if (debug) {
				log.info("[Vicincantatio-Debug] Checking the old FileConfiguration file's version");
			}
			if (true) {
				log.info("[Vicincantatio] FileConfiguration file out of date! Updating...");
				ConfigCreate(true);
			}
		}
	}

	private void ConfigCreate(boolean update) {
		if (!update) {
			try {
				if (debug) {
					log.info("[Vicincantatio-Debug] Creating a new Vicincantatio.config file");
				}
				File folder = getDataFolder();
				folder.mkdir();
				VicincantatioLog = new File(getDataFolder(), "Vicincantatio.log");
				configFile = new File(getDataFolder(), "config.yml");
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(configFile);
		if (debug) {
			log.info("[Vicincantatio-Debug] Storing all the properties...");
		}
		config.set("version", getDescription().getVersion());
		ConfigPropertyStore("instant", "spells", true, update);
		ConfigPropertyStore("all", "spells_require_book", false, update);
		String spell = "salio";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		spell = "celeritas";
		ConfigPropertyStore(spell, "enabled", true, update);
		spell = "respiro";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		spell = "mutatio";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		spell = "vello";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "affects_players", true, update);
		ConfigPropertyStore(spell, "affects_self", true, update);
		spell = "urgeo";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "affects_players", true, update);
		ConfigPropertyStore(spell, "affects_self", true, update);
		spell = "lux";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "replaces_block", false, update);
		spell = "ignis_perpetua";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "replaces_block", false, update);
		spell = "extinguere";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		spell = "remedium";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "book_augment", true, update);
		spell = "accendit";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		spell = "praemium";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "book_augment", true, update);
		ConfigPropertyStore(spell, "replaces_block", false, update);
		spell = "ignis_spiritus";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		spell = "fulmen";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "book_augment", true, update);
		spell = "frigidus";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "cools_lava", true, update);
		ConfigPropertyStore(spell, "creates_snow", true, update);
		ConfigPropertyStore(spell, "freezes_water", true, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);
		spell = "concalesco";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "thaws_ice", true, update);
		ConfigPropertyStore(spell, "removes_snow", true, update);
		ConfigPropertyStore(spell, "melts_obsidian", false, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);
		spell = "sereno";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", true, update);
		spell = "pluvia";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", true, update);
		spell = "tempestas";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", true, update);
		spell = "noctem";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", true, update);
		spell = "lucem";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", true, update);
		spell = "implete_aquas";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);
		spell = "itero";
		ConfigPropertyStore(spell, "enabled", true, update);
		spell = "tueri";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		spell = "domus";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", true, update);
		spell = "evanescere";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "vitrea", true, update);
		ConfigPropertyStore(spell, "glacies", true, update);
		ConfigPropertyStore(spell, "silice", true, update);
		ConfigPropertyStore(spell, "aquae", true, update);
		ConfigPropertyStore(spell, "folia", true, update);
		ConfigPropertyStore(spell, "ignis", true, update);
		ConfigPropertyStore(spell, "aranea", true, update);
		ConfigPropertyStore(spell, "aeris", false, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);
		spell = "uanescere";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "vitrea", true, update);
		ConfigPropertyStore(spell, "glacies", true, update);
		ConfigPropertyStore(spell, "silice", true, update);
		ConfigPropertyStore(spell, "aquae", true, update);
		ConfigPropertyStore(spell, "folia", true, update);
		ConfigPropertyStore(spell, "ignis", true, update);
		ConfigPropertyStore(spell, "aranea", true, update);
		ConfigPropertyStore(spell, "aeris", false, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);
		spell = "bulla";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "vitrea", true, update);
		ConfigPropertyStore(spell, "glacies", true, update);
		ConfigPropertyStore(spell, "silice", true, update);
		ConfigPropertyStore(spell, "aquae", true, update);
		ConfigPropertyStore(spell, "folia", true, update);
		ConfigPropertyStore(spell, "ignis", true, update);
		ConfigPropertyStore(spell, "aranea", true, update);
		ConfigPropertyStore(spell, "aeris", false, update);
		ConfigPropertyStore(spell, "can_augere_vi", true, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);
		spell = "sphaera";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "vitrea", true, update);
		ConfigPropertyStore(spell, "glacies", true, update);
		ConfigPropertyStore(spell, "silice", true, update);
		ConfigPropertyStore(spell, "aquae", true, update);
		ConfigPropertyStore(spell, "folia", true, update);
		ConfigPropertyStore(spell, "ignis", true, update);
		ConfigPropertyStore(spell, "aranea", true, update);
		ConfigPropertyStore(spell, "aeris", true, update);
		ConfigPropertyStore(spell, "can_augere_vi", true, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);
		spell = "tholus";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "vitrea", true, update);
		ConfigPropertyStore(spell, "glacies", true, update);
		ConfigPropertyStore(spell, "silice", true, update);
		ConfigPropertyStore(spell, "aquae", true, update);
		ConfigPropertyStore(spell, "folia", true, update);
		ConfigPropertyStore(spell, "ignis", true, update);
		ConfigPropertyStore(spell, "aranea", true, update);
		ConfigPropertyStore(spell, "aeris", true, update);
		ConfigPropertyStore(spell, "can_augere_vi", true, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);
		spell = "libyes";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "vitrea", true, update);
		ConfigPropertyStore(spell, "glacies", true, update);
		ConfigPropertyStore(spell, "silice", true, update);
		ConfigPropertyStore(spell, "aquae", true, update);
		ConfigPropertyStore(spell, "folia", true, update);
		ConfigPropertyStore(spell, "ignis", true, update);
		ConfigPropertyStore(spell, "aranea", true, update);
		ConfigPropertyStore(spell, "aeris", false, update);
		spell = "annulus";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "vitrea", true, update);
		ConfigPropertyStore(spell, "glacies", true, update);
		ConfigPropertyStore(spell, "silice", true, update);
		ConfigPropertyStore(spell, "aquae", true, update);
		ConfigPropertyStore(spell, "folia", true, update);
		ConfigPropertyStore(spell, "ignis", true, update);
		ConfigPropertyStore(spell, "aranea", true, update);
		ConfigPropertyStore(spell, "aeris", false, update);
		ConfigPropertyStore(spell, "can_augere_vi", true, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);
		spell = "murus";
		ConfigPropertyStore(spell, "enabled", true, update);
		ConfigPropertyStore(spell, "consumes_book", false, update);
		ConfigPropertyStore(spell, "vitrea", true, update);
		ConfigPropertyStore(spell, "glacies", true, update);
		ConfigPropertyStore(spell, "silice", true, update);
		ConfigPropertyStore(spell, "aquae", true, update);
		ConfigPropertyStore(spell, "folia", true, update);
		ConfigPropertyStore(spell, "ignis", true, update);
		ConfigPropertyStore(spell, "aranea", true, update);
		ConfigPropertyStore(spell, "aeris", false, update);
		ConfigPropertyStore(spell, "can_augere_vi", true, update);
		ConfigPropertyStore(spell, "magna_parva", true, update);

		spell = "chasma tempestas";
		ConfigPropertyStore(spell, "book_augment", true, update);

		spell = "world_maiming";
		ConfigPropertyStore(spell, "enabled", true, update);

		spell = "food";
		ConfigPropertyStore(spell, "enabled", false, update);

		if (debug) {
			log.info("[Vicincantatio-Debug] Saving the FileConfiguration file");
		}
		saveConfig();
	}

	private void ConfigPropertyStore(String spell, String property, boolean defaultvalue, boolean update) {
		String value = spell + "_" + property;
		if (update) {
			config.set(value, oldconfig.getString(value));
		} else
			config.set(value, Boolean.valueOf(defaultvalue));
	}

	public void onDisable() {
		log.info("Vicincantatio v" + getDescription().getVersion() + " successfully unloaded.");

		configFile = new File(getDataFolder(), "config.yml");
		oldconfig = YamlConfiguration.loadConfiguration(configFile);
	}
}