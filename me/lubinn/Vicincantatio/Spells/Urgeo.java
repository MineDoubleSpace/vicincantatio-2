package me.lubinn.Vicincantatio.Spells;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import me.lubinn.Vicincantatio.Vicincantatio;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.util.Vector;

class Urgeo extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost, size);
    }

    String spell = "urgeo";
    Location location = ((Block)event.getPlayer().getLastTwoTargetBlocks(null, 500).get(0)).getLocation();
    Vector vector = event.getPlayer().getEyeLocation().getDirection();
    List entities = event.getPlayer().getWorld().getEntities();
    for (Iterator iterator = entities.iterator(); iterator.hasNext(); )
    {
      Entity entity = (Entity)iterator.next();
      if ((location.distance(entity.getLocation()) > 5.0D) || 
        ((!Vicincantatio.config.getBoolean(spell + "_affects_players", true)) && 
        ((entity instanceof Player))) || (
        (!Vicincantatio.config.getBoolean(String.valueOf(spell) + "_affects_self", true)) && 
        (event.getPlayer().getEntityId() == entity.getEntityId())))
        continue;
      entity.setVelocity(vector.multiply(1.0D + size));
    }

    return this;
  }
}