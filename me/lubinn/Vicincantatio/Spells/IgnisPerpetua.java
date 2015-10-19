package me.lubinn.Vicincantatio.Spells;

import java.util.Map;

import me.lubinn.Vicincantatio.Vicincantatio;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerChatEvent;

class IgnisPerpetua extends Spell {
	public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments) {
		if (FoodEnabled()) {
			this.foodCost = 1;
			UseFood(event, this.foodCost);
		}

		int w = 1;
		Block block = (Block) event.getPlayer().getLastTwoTargetBlocks(null, 500).get(w);
		Block aboveblock = block.getRelative(BlockFace.UP);
		Material type = block.getType();
		Material abovetype = aboveblock.getType();
		if ((type != Material.BEDROCK) && (abovetype != Material.BEDROCK)) {
			block.setType(Material.NETHERRACK);
			block.getWorld().playEffect(block.getLocation(), Effect.SMOKE, 1);
			aboveblock.setType(Material.FIRE);
			aboveblock.getWorld().playEffect(aboveblock.getLocation(), Effect.SMOKE, 1);
		}

		return this;
	}
}