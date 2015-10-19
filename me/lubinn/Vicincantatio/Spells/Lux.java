package me.lubinn.Vicincantatio.Spells;

import org.bukkit.Material;

class Lux extends ConstructionSpell
{
  public Lux()
  {
    this.area = new Point();
    this.beforeTarget = true;
    this.forceMaterial = Material.GLOWSTONE;
  }
}