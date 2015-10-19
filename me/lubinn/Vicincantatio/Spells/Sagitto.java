package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.util.Vector;

class Sagitto extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost, size);
    }

    Player player = event.getPlayer();
    World world = player.getWorld();
    Vector dir = player.getEyeLocation().getDirection().normalize().multiply(3.0D);
    Vector pos = player.getEyeLocation().toVector().add(dir);
    for (int i = 0; i < size * size / 2 + 1; i++)
    {
      Arrow a = world.spawnArrow(pos.toLocation(world), dir.multiply(50.0F), 600.0F, 10.0F + 2.5F * size);
      a.teleport(pos.toLocation(world));
    }

    return this;
  }
}