package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class Lucem extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 2;
      UseFood(event, this.foodCost);
    }

    event.getPlayer().getWorld().setTime(23425L);
    return this;
  }
}