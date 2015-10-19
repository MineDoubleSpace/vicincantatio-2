package me.lubinn.Vicincantatio.Spells;

import org.bukkit.Material;
import org.bukkit.block.Block;

class Uanescere extends ConstructionSpell
{
  public Uanescere()
  {
    this.area = new Sphere();
  }

  public void ReplaceBlock(Block block, Material mat, boolean force) {
    if (block.getType() == mat)
      block.setType(Material.AIR);
    if (((mat == Material.STATIONARY_WATER) || (mat == Material.WATER)) && (
      (block.getType() == Material.WATER) || (block.getType() == Material.STATIONARY_WATER)))
      block.setType(Material.AIR);
    if (((mat == Material.LAVA) || (mat == Material.STATIONARY_LAVA)) && (
      (block.getType() == Material.LAVA) || (block.getType() == Material.STATIONARY_LAVA)))
      block.setType(Material.AIR);
  }
}