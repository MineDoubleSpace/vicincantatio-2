package me.lubinn.Vicincantatio.Spells;

import org.bukkit.util.Vector;

abstract class Area
{
  protected double sizeBase = 4.0D;
  protected double sizeGrowth = 3.0D;

  public Area() {
  }
  public Area(double base, double growth) {
    this.sizeBase = base;
    this.sizeGrowth = growth;
  }

  public int Size(int size)
  {
    return (int)(this.sizeBase + size * this.sizeGrowth);
  }

  public int boundXLow(int size) {
    return -Size(size);
  }

  public int boundXHi(int size) {
    return Size(size);
  }

  public int boundYLow(int size) {
    return -Size(size);
  }

  public int boundYHi(int size) {
    return Size(size);
  }

  public int boundZLow(int size) {
    return -Size(size);
  }

  public int boundZHi(int size) {
    return Size(size);
  }

  public boolean InY(int size, int dx, int dz) {
    return true;
  }

  public abstract boolean In(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public abstract boolean OnBorder(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public void Instantiate(Vector heading)
  {
  }
}