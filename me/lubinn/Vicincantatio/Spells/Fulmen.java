package me.lubinn.Vicincantatio.Spells;

import java.util.ArrayList;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class Fulmen extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 2;
      UseFood(event, this.foodCost, size);
    }

    boolean force = augments.containsKey("force");
    boolean book = augments.containsKey("book");
    Location location = event.getPlayer().getTargetBlock(null, 500).getLocation();
    int xorg = location.getBlockX();
    int yorg = location.getBlockY();
    int zorg = location.getBlockZ();
    World world = event.getPlayer().getWorld();
    ArrayList strikelocs = new ArrayList(60);
    strikelocs.add(location);
    int radius = 1 + (int)(0.45D * size);
    int nBolts = 1 + (int)(0.2D * size * size + 0.015D * size * size * size);
    if (book)
    {
      radius *= 5;
      nBolts = 5 + 5 * nBolts + (int)(0.12D * nBolts * nBolts);
    }
    for (int x = -radius; x <= radius; x++)
    {
      for (int y = -radius; y <= radius; y++)
      {
        for (int z = -radius; z <= radius; z++)
        {
          if (Math.sqrt(x * x + y * y + z * z) > radius)
            continue;
          Block block = world.getBlockAt(x + xorg, y + yorg, z + zorg);
          Location tloc = block.getLocation();
          if ((block.getType() == Material.AIR) || ((block.getRelative(BlockFace.NORTH).getType() != Material.AIR) && 
            (block.getRelative(BlockFace.SOUTH).getType() != Material.AIR) && 
            (block.getRelative(BlockFace.EAST).getType() != Material.AIR) && 
            (block.getRelative(BlockFace.WEST).getType() != Material.AIR) && 
            (block.getRelative(BlockFace.UP).getType() != Material.AIR) && 
            (block.getRelative(BlockFace.DOWN).getType() != Material.AIR))) continue;
          strikelocs.add(tloc);
        }
      }
    }

    for (int m = 0; m < nBolts; m++)
    {
      int choice = (int)(Math.random() * strikelocs.size());
      Location loc = (Location)strikelocs.get(choice);
      world.strikeLightning(loc);
      if (force)
      {
        world.getBlockAt(loc).getRelative(0, 1, 0).setType(Material.FIRE);
        if (book)
          world.createExplosion(loc, 3.0F);
      }
      if (nBolts - m > strikelocs.size())
        strikelocs.remove(choice);
    }
    return this;
  }
}