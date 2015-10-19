package me.lubinn.Vicincantatio.Spells;

import org.bukkit.Material;
import org.bukkit.util.Vector;

class Respiro extends Restituo
{
  public Respiro()
  {
    this.rangeBase = 0.0D;
    this.rangeGrowth = 0.0D;
    this.alwaysFlight = true;
    this.forceMaterial = Material.AIR;
  }

  protected Vector MoveVector(int size) {
    return null;
  }
}