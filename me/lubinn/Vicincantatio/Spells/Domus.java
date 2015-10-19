package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class Domus extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost);
    }

    Player player = event.getPlayer();
    player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 1);
    player.teleport(player.getWorld().getSpawnLocation());
    return this;
  }
}