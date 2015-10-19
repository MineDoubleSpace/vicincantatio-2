package me.lubinn.Vicincantatio.Spells;

import org.bukkit.util.Vector;

class Cubus extends ConstructionSpell
{
  public Cubus()
  {
    this.area = new Cube();
  }

  protected Vector MoveVector(int size) {
    return new Vector(0, this.area.Size(size), 0);
  }
}