package me.lubinn.Vicincantatio.Spells;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class RealityCheck extends Spell {
	public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments) {
		if (FoodEnabled()) {
			this.foodCost = 1;
			UseFood(event, this.foodCost, size);
		}

		Material[][][] temp = new Material[size + 2][size + 2][size + 2];
		Location location = event.getPlayer().getTargetBlock(null, 500).getLocation();

		World world = event.getPlayer().getWorld();
		Block[][][] block = new Block[size + 2][size + 2][size + 2];

		for (int i = 0; i < size + 2; i++) {
			for (int j = 0; j < size + 2; j++) {
				for (int k = 0; k < size + 2; k++) {
					temp[i][j][k] = location.add(i, j, k).getBlock().getType();
					block[i][j][k] = location.add(i, j, k).getBlock();
				}
			}
		}

		for (int i = 0; i < size + 2; i++) {
			for (int j = 0; j < size + 2; j++) {
				for (int k = 0; k < size + 2; k++) {
					block[j][i][k].setType(Material.STONE);
				}
			}
		}
		return this;
	}
}