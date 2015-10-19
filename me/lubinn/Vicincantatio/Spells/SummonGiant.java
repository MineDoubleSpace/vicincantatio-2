package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class SummonGiant extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 3;
      UseFood(event, this.foodCost);
    }

    Location location = event.getPlayer().getTargetBlock(null, 500).getLocation();
    World world = event.getPlayer().getWorld();

    world.spawnCreature(location, CreatureType.GIANT);

    return this;
  }
}