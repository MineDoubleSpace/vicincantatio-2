package me.lubinn.Vicincantatio.Spells;

import org.bukkit.Material;
import org.bukkit.block.Block;

class Concalesco extends ConstructionSpell
{
  public Concalesco()
  {
    this.area = new Sphere();
    this.forceMaterial = Material.WATER;
  }

  public void ReplaceBlock(Block block, Material mat, boolean force) {
    Material type = block.getType();
    if ((type == Material.ICE) || (type == Material.SNOW_BLOCK))
      block.setType(Material.WATER);
    else if (type == Material.SNOW)
      block.setType(Material.AIR);
    else if (type == Material.OBSIDIAN)
      block.setType(Material.LAVA);
  }
}