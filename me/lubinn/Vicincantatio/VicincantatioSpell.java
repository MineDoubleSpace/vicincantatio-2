package me.lubinn.Vicincantatio;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import me.lubinn.Vicincantatio.Spells.Delay;
import me.lubinn.Vicincantatio.Spells.Spell;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class VicincantatioSpell {
	private int size;
	public int n;
	public boolean book_augment;
	public FileConfiguration config;
	public String[] components;
	public PlayerChatEvent ev;
	public static Vicincantatio plugin;
	public static Random rand = new Random();


	private String[] listOfSpells = { "accendit", "annulus", "arbore", "bulla", "chasma tempestas", "concalesco", "cubus", "demis", "discus", "domus", "evanescere", "extinguere", "frigidus",
			"fulmen", "globus ignis", "gradi", "hadoken", "ignis perpetua", "implete aquas", "libyes", "lucem", "lux", "murus", "mutatio", "noctem", "pluvia", "praemium", "remedium", "respiro",
			"sagitto", "sagitto pluvia", "salio", "sereno", "sphaera", "tempestas", "tholus", "turris", "uanescere", "urgeo", "vello", "vorto", "chunkus erasus", "chunkus renero" };

	public static final Map<String, Material> listOfMaterials = new HashMap();
	Thread runner;

	static {
		listOfMaterials.put("fimus", Material.DIRT);
		listOfMaterials.put("aquae", Material.STATIONARY_WATER);

		listOfMaterials.put("aeris", Material.AIR);
		listOfMaterials.put("vitrea", Material.GLASS);
		listOfMaterials.put("glacies", Material.ICE);
		listOfMaterials.put("aranea", Material.WEB);
		listOfMaterials.put("folia", Material.LEAVES);
		listOfMaterials.put("ignis", Material.FIRE);
		listOfMaterials.put("silice", Material.LAVA);

		listOfMaterials.put("lapis", Material.STONE);
		listOfMaterials.put("obsidianus", Material.OBSIDIAN);
		listOfMaterials.put("ferrum", Material.IRON_BLOCK);
		listOfMaterials.put("aurum", Material.GOLD_BLOCK);
		listOfMaterials.put("adamas", Material.DIAMOND_BLOCK);
		listOfMaterials.put("arena", Material.SAND);
		listOfMaterials.put("herba", Material.GRASS);

		listOfMaterials.put("explodium", Material.TNT);
	}

	public VicincantatioSpell(Vicincantatio instance) {
		this.size = 1;
		this.n = 0;
		this.book_augment = false;
		this.config = null;
		plugin = instance;
	}

	private boolean isIn(String[] list, String str) {
		for (int i = 0; i < list.length; i++)
			if (list[i].equalsIgnoreCase(str))
				return true;
		return false;
	}

	public void CastSpell(String[] components, PlayerChatEvent ev) {
		this.config = Vicincantatio.config;
		this.components = components;
		this.ev = ev;
		this.n = 0;
		this.size = 2;
		this.book_augment = false;

		String spellName = "";
		Spell toCast = null;
		Material material = null;
		Map augments = new HashMap();

		for (int i = 0; i < components.length; i++) {
			components[i] = components[i].toLowerCase();
		}
		for (int i = 0; i < components.length; i++) {
			if ((spellName.equals("")) || (spellName.equals("bulla"))) {
				String foundSpell = "";

				if (Spell.GetSpell(components[i]) != null)
					foundSpell = components[i];
				if (i < components.length - 1) {
					String twoWords = components[i] + " " + components[(i + 1)];
					if (Spell.GetSpell(twoWords) != null) {
						foundSpell = twoWords;
						i++;
					}
				}

				if (!foundSpell.equals("")) {
					if (spellName.equals("bulla"))
						augments.put("bubble", Boolean.valueOf(true));
					spellName = foundSpell;
					toCast = Spell.GetSpell(spellName);
					Vicincantatio.log.info("Spell name: " + spellName);
					continue;
				}

			}

			if (this.size == 2) {
				if (Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
					PermissionManager permissions = PermissionsEx.getPermissionManager();

					if ((components[i].equalsIgnoreCase("ultima")) && (Vicincantatio.config.getBoolean("world_maiming" + "_enabled", true))) {
						if (permissions.has(ev.getPlayer(), "ultima.allow")) {
							this.size = 9;
						} else {
							Vicincantatio.log.info("Ultima size not allowed!");
							toCast = null;
						}
					} else if ((components[i].equalsIgnoreCase("supera")) && (Vicincantatio.config.getBoolean("world_maiming" + "_enabled", true))) {
						if (permissions.has(ev.getPlayer(), "supera.allow")) {
							this.size = 6;
						} else {
							Vicincantatio.log.info("Supera size not allowed!");
							toCast = null;
						}
					} else if (components[i].equalsIgnoreCase("magna"))
						this.size = 4;
					else if (components[i].equalsIgnoreCase("parva"))
						this.size = 1;
					else if (components[i].equalsIgnoreCase("infera")) {
						this.size = 0;
					}
					if (this.size != 2) {
						continue;
					}

				} else {
					if ((components[i].equalsIgnoreCase("ultima")) && (Vicincantatio.config.getBoolean("world_maiming" + "_enabled", true)))
						this.size = 9;
					else if ((components[i].equalsIgnoreCase("supera")) && (Vicincantatio.config.getBoolean("world_maiming" + "_enabled", true)))
						this.size = 6;
					else if (components[i].equalsIgnoreCase("magna"))
						this.size = 4;
					else if (components[i].equalsIgnoreCase("parva"))
						this.size = 1;
					else if (components[i].equalsIgnoreCase("infera")) {
						this.size = 0;
					}
					if (this.size != 2) {
						continue;
					}

				}

			}

			if (material == null) {
				if (Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
					PermissionManager permissions = PermissionsEx.getPermissionManager();
					if (listOfMaterials.containsKey(components[i])) {
						if (permissions.has(ev.getPlayer(), components[i] + ".allow")) {
							material = (Material) listOfMaterials.get(components[i]);
							Vicincantatio.log.info("Material: " + components[i]);
							continue;
						}

						Vicincantatio.log.info("Material not allowed.");
						ev.getPlayer().sendMessage("Sorry, you don't have the permissions for that material");
					}

				} else if (listOfMaterials.containsKey(components[i])) {
					material = (Material) listOfMaterials.get(components[i]);
					Vicincantatio.log.info("Material: " + components[i]);
					continue;
				}

			}

			if ((components[i].equalsIgnoreCase("augere")) && (i + 1 < components.length)) {
				if (components[(i + 1)].equalsIgnoreCase("vi")) {
					augments.put("force", Boolean.valueOf(true));
				} else if (components[(i + 1)].equalsIgnoreCase("volo")) {
					augments.put("flight", Boolean.valueOf(true));
				} else if (components[(i + 1)].equalsIgnoreCase("motor")) {
					augments.put("motion", Boolean.valueOf(true));
				} else if (components[(i + 1)].equalsIgnoreCase("silentium")) {
					augments.put("silence", Boolean.valueOf(true));
				} else if (components[(i + 1)].equalsIgnoreCase("mora")) {
					Delay.delay(spellName, toCast, ev, material, this.size, augments);

					toCast = null;
				}

			}

			if (components[i].equalsIgnoreCase("bulla")) {
				augments.put("bubble", Boolean.valueOf(true));
			}

			if (components[i].equalsIgnoreCase("libero")) {
				Delay.release(ev);
			}


			components[i].equalsIgnoreCase("praevenio");
		}

		if (!spellName.equals("")) {
			boolean canCast = true;

			if (Vicincantatio.config.getBoolean(spellName + "_consumes_book", false)) {
				if (!PlayerSpendReagent(ev.getPlayer(), Material.BOOK, 1))
					canCast = false;
			}
			if (Vicincantatio.config.getBoolean(spellName + "_book_augment", false)) {
				if (PlayerSpendReagent(ev.getPlayer(), Material.BOOK, 1)) {
					augments.put("book", Boolean.valueOf(true));
				}
			}
			if ((toCast != null) && (canCast)) {
				if (Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
					PermissionManager permissions = PermissionsEx.getPermissionManager();
					if (permissions.has(ev.getPlayer(), spellName + ".allow")) {
						toCast.CastSpell(ev, material, this.size, augments);
					} else {
						Vicincantatio.log.info("Spell not allowed by user.");
						ev.getPlayer().sendMessage("Sorry, you do not have permission to do that.");
					}

				} else {
					toCast.CastSpell(ev, material, this.size, augments);
				}
			}

			if (augments.containsKey("silence"))
				ev.setCancelled(true);
		}
	}

	boolean CheckPermissions(String spell, String[] components) {
		if (Vicincantatio.config.getBoolean(spell + "_consumes_book", false)) {
			return PlayerSpendReagent(this.ev.getPlayer(), Material.BOOK, 1);
		}
		if (Vicincantatio.config.getBoolean(spell + "_book_augment", false)) {
			this.book_augment = PlayerSpendReagent(this.ev.getPlayer(), Material.BOOK, 1);
		}
		return true;
	}

	boolean Hold_place(String spell, String[] components) {
		if (!Vicincantatio.config.getBoolean(spell + "_enabled", true)) {
			return false;
		}
		if ((Vicincantatio.config.getBoolean(spell + "_enabled", true)) && (spell == "celeritas")) {
			return true;
		}
		if ((Vicincantatio.config.getBoolean("all_spells_require_book", false)) && (this.ev.getPlayer().getItemInHand().getType() != Material.BOOK)) {
			return false;
		}
		if (Vicincantatio.config.getBoolean(spell + "_consumes_book", false)) {
			return PlayerSpendReagent(this.ev.getPlayer(), Material.BOOK, 1);
		}

		if (!Vicincantatio.config.getBoolean(spell + "_magna_parva", false)) {
			this.size = 2;
		}
		if (Vicincantatio.config.getBoolean(spell + "_book_augment", false)) {
			this.book_augment = PlayerSpendReagent(this.ev.getPlayer(), Material.BOOK, 1);
		}
		return true;
	}

	private boolean IsForceful(String[] components, PlayerChatEvent ev2) {
		int l = components.length;
		if (l < 2)
			return false;
		return (components[(l - 2)].equalsIgnoreCase("augere")) && (components[(l - 1)].equalsIgnoreCase("vi"));
	}

	private void IsSilent(String[] components, PlayerChatEvent ev2, int i) {
		int l = components.length;
		if (l < 2)
			return;
		if ((components[(l - 2)].equalsIgnoreCase("augere")) && (components[(l - 1)].equalsIgnoreCase("silentium")))
			ev2.setCancelled(true);
	}

	private boolean IsMotion(String[] components, PlayerChatEvent ev) {
		int l = components.length;
		if (l < 2)
			return false;
		return (components[(l - 2)].equalsIgnoreCase("augere")) && (components[(l - 1)].equalsIgnoreCase("motor"));
	}

	private boolean IsFlying(String[] components, PlayerChatEvent ev) {
		int l = components.length;
		if (l < 2)
			return false;
		return (components[(l - 2)].equalsIgnoreCase("augere")) && (components[(l - 1)].equalsIgnoreCase("volo"));
	}

	public boolean PlayerSpendReagent(Player player, Material reagent, int count) {
		PlayerInventory inventory = player.getInventory();
		int slot = inventory.getHeldItemSlot();
		ItemStack item = player.getItemInHand();
		int amount = item.getAmount();
		Material typeid = item.getType();
		if (typeid != reagent) {
			return false;
		}
		if (amount > count) {
			item.setAmount(amount - count);
			return true;
		}
		if (amount < count) {
			return false;
		}
		if (amount == count) {
			inventory.clear(slot);
			return true;
		}

		return false;
	}

	private void ImpleteAquas(String[] components, PlayerChatEvent ev) {
		int radius = 6 + 4 * this.size;
		Block block = ev.getPlayer().getTargetBlock(null, 500);
		Location origin = block.getLocation();
		int xorg = (int) origin.getX();
		int yorg = (int) origin.getY();
		int zorg = (int) origin.getZ();
		if (Math.abs(yorg - 63) < radius) {
			int y = 63;
			for (int x = xorg - radius - 1; x <= xorg + radius + 1; x++) {
				for (int z = zorg - radius - 1; z <= zorg + radius + 1; z++) {
					int dx = x - xorg;
					int dz = z - zorg;
					if ((int) Math.sqrt(dx * dx + dz * dz) > radius)
						continue;
					Block targetblock = ev.getPlayer().getWorld().getBlockAt(x, y, z);
					Material type = targetblock.getType();
					if ((type != Material.AIR) && (type != Material.WATER) && (type != Material.STATIONARY_WATER))
						continue;
					targetblock.setType(Material.STATIONARY_WATER);
				}
			}
		}
	}

	private Material GetMaterial(String latinname) {
		if (latinname.equalsIgnoreCase("aquae"))
			return Material.STATIONARY_WATER;
		if (latinname.equalsIgnoreCase("aeris"))
			return Material.AIR;
		if (latinname.equalsIgnoreCase("vitrea"))
			return Material.GLASS;
		if (latinname.equalsIgnoreCase("glacies"))
			return Material.ICE;
		if (latinname.equalsIgnoreCase("aranea"))
			return Material.WEB;
		if (latinname.equalsIgnoreCase("folia"))
			return Material.LEAVES;
		if (latinname.equalsIgnoreCase("ignis"))
			return Material.FIRE;
		if (latinname.equalsIgnoreCase("silice"))
			return Material.LAVA;
		if (latinname.equalsIgnoreCase("cactus"))
			return Material.CACTUS;
		if (latinname.equalsIgnoreCase("lapis"))
			return Material.STONE;
		if (latinname.equalsIgnoreCase("obsidianus"))
			return Material.OBSIDIAN;
		if (latinname.equalsIgnoreCase("ferrum"))
			return Material.IRON_BLOCK;
		if (latinname.equalsIgnoreCase("aurum"))
			return Material.GOLD_BLOCK;
		if (latinname.equalsIgnoreCase("adamas"))
			return Material.DIAMOND_BLOCK;
		if (latinname.equalsIgnoreCase("arena"))
			return Material.SAND;
		if (latinname.equalsIgnoreCase("fimus"))
			return Material.DIRT;
		if (latinname.equalsIgnoreCase("herba"))
			return Material.GRASS;
		if (latinname.equalsIgnoreCase("nether"))
			return Material.NETHERRACK;
		return null;
	}

	private void Murus(String[] components, PlayerChatEvent ev) {
		boolean overwrite = IsForceful(components, ev);
		Material component = GetMaterial(components[(this.n + 1)]);
		int length = 5 + 15 * this.size / 2;
		Vector vector = ev.getPlayer().getEyeLocation().getDirection().normalize();
		Location location;
		if (IsFlying(components, ev))
			location = ev.getPlayer().getTargetBlock(null, 10 + 5 * this.size).getLocation();
		else
			location = ev.getPlayer().getTargetBlock(null, 500).getLocation();
		int xorg = location.getBlockX();
		int yorg = location.getBlockY();
		int zorg = location.getBlockZ();
		double vx = vector.getX();
		double vz = vector.getZ();
		double ox = -vz;
		double oz = vx;
		for (int i = -length / 2; i <= length / 2 + 1; i++) {
			location.setX(xorg + i * ox);
			location.setZ(zorg + i * oz);
			for (int y = Math.max(yorg, 1); y <= Math.min(yorg + length / 2, 120); y++) {
				location.setY(y);
				Block block = location.getBlock();
				Material type = block.getType();
				if ((overwrite) && (type != Material.BEDROCK)) {
					block.setType(component);
				} else {
					if (type != Material.AIR)
						continue;
					block.setType(component);
				}
			}

		}

		if (IsMotion(components, ev))
			ev.getPlayer().teleport(new Vector(xorg, yorg + length / 2 + 1, zorg).toLocation(ev.getPlayer().getWorld()));
	}

	private void Scalae(String[] components, PlayerChatEvent ev) {
		boolean ow = IsForceful(components, ev);
		Material _cp = null;

		Location location = ev.getPlayer().getTargetBlock(null, 500).getLocation();
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		for (int scale = 0; scale <= this.size; scale++) {
			write_block(ow, x - 1, y, z - 1, _cp);
			write_block(ow, x - 1, y + 1, z, _cp);
			write_block(ow, x - 1, y + 2, z + 1, _cp);
			write_block(ow, x, y + 3, z + 1, _cp);
			write_block(ow, x + 1, y + 4, z + 1, _cp);
			write_block(ow, x + 1, y + 5, z, _cp);
			write_block(ow, x + 1, y + 6, z - 1, _cp);
			write_block(ow, x, y + 7, z - 1, _cp);
			for (int h = 0; h < 8; h++) {
				write_block(ow, x, y++, z, _cp);
			}
		}
		if (IsMotion(components, ev))
			ev.getPlayer().teleport(location.toVector().add(new Vector(0, (this.size + 1) * 8, 0)).toLocation(ev.getPlayer().getWorld()));
	}

	private void write_block(boolean overwrite, int x, int y, int z, Material _cmpnt) {
		Block _bk = this.ev.getPlayer().getWorld().getBlockAt(x, y, z);
		Material type = _bk.getType();
		if ((type == Material.AIR) || ((overwrite) && (type != Material.BEDROCK))) {
			_bk.setType(_cmpnt);
			_bk.getWorld().playEffect(_bk.getLocation(), Effect.SMOKE, 1);
		}
	}
}