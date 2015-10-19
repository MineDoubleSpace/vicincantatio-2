package me.lubinn.Vicincantatio.Spells;

import org.bukkit.util.Vector;

class Turris extends ConstructionSpell
{
  public Turris()
  {
    this.area = new Cylinder(2.5D, 3.75D, 5.0D, 7.5D);
  }

  protected Vector MoveVector(int size) {
    return new Vector(0, this.area.Size(size) + 1, 0);
  }
}