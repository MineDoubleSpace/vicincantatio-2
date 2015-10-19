package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class ChunkusErasus extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 4;
      UseFood(event, this.foodCost);
    }

    Player player = event.getPlayer();
    World world = player.getWorld();
    Location location = player.getTargetBlock(null, 500).getLocation();
    Chunk chunk = world.getChunkAt(location);
    for (int y = 0; y < 128; y++)
      for (int x = 0; x < 16; x++)
        for (int z = 0; z < 16; z++)
          chunk.getBlock(x, y, z).setType(Material.AIR);
    return this;
  }
}