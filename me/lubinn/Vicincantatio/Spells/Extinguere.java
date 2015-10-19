package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class Extinguere extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost, size);
    }

    if (event.getPlayer().getFireTicks() > 0)
    {
      event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.SMOKE, 1);
    }
    event.getPlayer().setFireTicks(0);
    return this;
  }
}