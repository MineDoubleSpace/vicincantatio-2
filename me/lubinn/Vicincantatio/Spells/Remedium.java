package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class Remedium extends Spell {
	public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments) {
		if (FoodEnabled()) {
			this.foodCost = 3;
			UseFood(event, this.foodCost);

			if (event.getPlayer().getFoodLevel() <= 0) {
				return this;
			}
		}
		Player player = event.getPlayer();
		double health = player.getHealthScale();
		if (augments.containsKey("book")) {
			player.setHealth(20.0);
		} else if (health + 6 < 20) {
			health += 6;
			player.setHealth(health);
		} else {
			health = 20;
			player.setHealth(health);
		}
		return this;
	}
}