package me.lubinn.Vicincantatio.Spells;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.player.PlayerChatEvent;

class OstiumTartarus extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost);
    }

    World world = event.getPlayer().getWorld();
    Location location = event.getPlayer().getTargetBlock(null, 500).getLocation();

    location.getBlock().setType(Material.OBSIDIAN);
    location.add(1.0D, 0.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(0.0D, 1.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(0.0D, 1.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(0.0D, 1.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(0.0D, 1.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(-1.0D, 0.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(-1.0D, 0.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(-1.0D, 0.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);
    location.add(1.0D, 0.0D, 0.0D).getBlock().setType(Material.OBSIDIAN);

    location.add(1.0D, 1.0D, 0.0D).getBlock().setType(Material.FIRE);

    return this;
  }
}