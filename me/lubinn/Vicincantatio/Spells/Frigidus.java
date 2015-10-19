package me.lubinn.Vicincantatio.Spells;

import org.bukkit.Material;
import org.bukkit.block.Block;

class Frigidus extends ConstructionSpell
{
  public Frigidus()
  {
    this.area = new Sphere();
    this.forceMaterial = Material.SNOW;
  }

  public void ReplaceBlock(Block block, Material mat, boolean force) {
    Material thisType = block.getType();
    Material botType = block.getRelative(0, -1, 0).getType();
    if ((thisType == Material.WATER) || (thisType == Material.STATIONARY_WATER))
      block.setType(Material.ICE);
    else if ((thisType == Material.AIR) && (botType != Material.AIR) && (botType != Material.SNOW))
      block.setType(Material.SNOW);
    else if ((thisType == Material.LAVA) || (thisType == Material.STATIONARY_LAVA))
      block.setType(Material.OBSIDIAN);
  }
}