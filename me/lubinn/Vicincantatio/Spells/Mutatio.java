package me.lubinn.Vicincantatio.Spells;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.player.PlayerChatEvent;

class Mutatio extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost);
    }

    Location location = event.getPlayer().getTargetBlock(null, 500).getLocation();
    World world = event.getPlayer().getWorld();
    List entities = world.getEntities();
    for (Iterator iterator = entities.iterator(); iterator.hasNext(); )
    {
      Entity entity = (Entity)iterator.next();
      Location oloc = entity.getLocation();
      if ((location.distance(oloc) > 5.0D) || ((!(entity instanceof Creeper)) && (!(entity instanceof Zombie)) && (!(entity instanceof Skeleton)) && (!(entity instanceof Spider))))
        continue;
      Location eloc = entity.getLocation();
      if ((eloc.getBlock().getType() == Material.WATER) || (eloc.getBlock().getType() == Material.STATIONARY_WATER))
      {
        CreatureType[] creatures = { 
          CreatureType.CHICKEN, CreatureType.COW, CreatureType.SHEEP, CreatureType.SQUID };

        entity.remove();
        world.playEffect(eloc, Effect.SMOKE, 1);
        int i = (int)(Math.random() * creatures.length);
        CreatureType creature = creatures[i];
        world.spawnCreature(eloc, creature);
      }
      else {
        CreatureType[] creatures = { 
          CreatureType.CHICKEN, CreatureType.COW, CreatureType.SHEEP };

        entity.remove();
        world.playEffect(eloc, Effect.SMOKE, 1);
        int i = (int)(Math.random() * creatures.length);
        CreatureType creature = creatures[i];
        world.spawnCreature(eloc, creature);
      }
    }

    return this;
  }
}