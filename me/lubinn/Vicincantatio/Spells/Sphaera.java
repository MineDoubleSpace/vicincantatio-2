package me.lubinn.Vicincantatio.Spells;

import org.bukkit.util.Vector;

class Sphaera extends ConstructionSpell
{
  public Sphaera()
  {
    this.area = new HollowSphere();
    this.targeted = false;
  }

  protected Vector MoveVector(int size) {
    return new Vector(0, this.area.Size(size), 0);
  }
}