package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class Arbore extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost);
    }
    World world = event.getPlayer().getWorld();
    Block block = event.getPlayer().getTargetBlock(null, 500);
    block.setType(Material.DIRT);
    world.generateTree(block.getLocation().add(0.0D, 1.0D, 0.0D), TreeType.TREE);
    return this;
  }
}