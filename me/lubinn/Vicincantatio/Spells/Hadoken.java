package me.lubinn.Vicincantatio.Spells;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.util.Vector;

class Hadoken extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 3;
      UseFood(event, this.foodCost);
    }

    World world = event.getPlayer().getWorld();
    Location location = event.getPlayer().getLocation();
    Vector aim = event.getPlayer().getEyeLocation().getDirection().normalize();

    location.setX(location.getX() + aim.getX() * 4.0D);
    location.setY(location.getY() + 0.5D);
    location.setZ(location.getZ() + aim.getZ() * 4.0D);

    for (int i = 0; i < 100; i++)
    {
      Arrow a = event.getPlayer().getWorld().spawnArrow(location, aim, 600.0F, 10.0F + 5.0F * size);
      a.setVelocity(aim.normalize().multiply(10.0F));
    }

    return this;
  }
}