package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.util.Vector;

class leap extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost);
    }

    Vector aim = event.getPlayer().getEyeLocation().getDirection().normalize();

    event.getPlayer().setVelocity(aim.multiply(10));

    return this;
  }
}