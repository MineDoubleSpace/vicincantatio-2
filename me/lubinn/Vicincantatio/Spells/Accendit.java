package me.lubinn.Vicincantatio.Spells;

import org.bukkit.Material;

class Accendit extends ConstructionSpell
{
  public Accendit()
  {
    this.area = new Sphere(1.0D, 2.5D);
    this.forceMaterial = Material.FIRE;
  }
}