package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class Tempestas extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 2;
      UseFood(event, this.foodCost);
    }

    event.getPlayer().getWorld().setStorm(true);
    event.getPlayer().getWorld().setThundering(true);
    return this;
  }
}