package me.lubinn.Vicincantatio.Spells;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class Starve extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 2;
      UseFood(event, this.foodCost);
    }

    Location location = event.getPlayer().getTargetBlock(null, 500).getLocation();
    World world = event.getPlayer().getWorld();
    List entities = world.getEntities();
    for (Iterator iterator = entities.iterator(); iterator.hasNext(); )
    {
      Entity entity = (Entity)iterator.next();
      Location oloc = entity.getLocation();

      if ((location.distance(oloc) <= 5.0D) && ((entity instanceof Player)) && (entity != event.getPlayer())) {
        ((Player) entity).setFoodLevel((int)(0.4D * ((Player) entity).getFoodLevel()));
      }
    }

    return this;
  }
}