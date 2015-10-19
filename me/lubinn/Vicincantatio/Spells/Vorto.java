package me.lubinn.Vicincantatio.Spells;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerChatEvent;

class Vorto extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost, size);
    }

    Material[][][] temp = new Material[size + 2][size + 2][size + 2];
    Location location = event.getPlayer().getTargetBlock(null, 500).getLocation();
    Location location2 = location;

    World world = event.getPlayer().getWorld();
    Block[][][] block = new Block[size + 2][size + 2][size + 2];

    for (int i = 0; i < size + 2; i++)
    {
      for (int j = 0; j < size + 2; j++)
      {
        for (int k = 0; k < size + 2; k++)
        {
          temp[i][j][k] = location2.add(i, j, k).getBlock().getType();
          location2 = location.clone();
          block[i][j][k] = location2.add(i, j, k).getBlock();
          location2 = location.clone();
        }
      }
    }

    for (int i = 0; i < size + 2; i++)
    {
      for (int j = 0; j < size + 2; j++)
      {
        for (int k = 0; k < size + 2; k++)
        {
          block[j][i][k].setType(temp[i][j][k]);
        }
      }
    }
    return this;
  }
}