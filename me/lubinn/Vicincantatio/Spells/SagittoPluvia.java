package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.util.Vector;

class SagittoPluvia extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost, size);
    }

    Location location = event.getPlayer().getTargetBlock(null, 500).getLocation();
    location.setY(120.0D);
    for (int i = 0; i < size * size * size * 0.15D + size * size * 5 + 8.0D; i++)
    {
      Arrow a = event.getPlayer().getWorld().spawnArrow(location, new Vector(0, -10, 0), 600.0F, 10.0F + 5.0F * size);
      double x = rand.nextGaussian() * (0.5D + 0.15D * size);
      double z = rand.nextGaussian() * (0.5D + 0.15D * size);
      a.setVelocity(new Vector(x, -10.0D, z));
      a.teleport(location);
    }
    return this;
  }
}