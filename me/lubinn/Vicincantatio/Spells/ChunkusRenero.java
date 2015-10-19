package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class ChunkusRenero extends Spell
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
    world.regenerateChunk(chunk.getX(), chunk.getZ());
    return this;
  }
}