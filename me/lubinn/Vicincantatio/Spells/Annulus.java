package me.lubinn.Vicincantatio.Spells;

import org.bukkit.util.Vector;

class Annulus extends ConstructionSpell
{
  public Annulus()
  {
    this.area = new Ring();
    this.alwaysBubble = true;
  }

  protected Vector MoveVector(int size) {
    return null;
  }
}