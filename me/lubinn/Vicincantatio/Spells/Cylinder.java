package me.lubinn.Vicincantatio.Spells;

class Cylinder extends Area
{
  private double radBase = 2.0D;
  private double radGrowth = 4.0D;

  public Cylinder() {
  }
  public Cylinder(double rBase, double rGrowth, double hBase, double hGrowth) {
    super(hBase, hGrowth);
    this.radBase = rBase;
    this.radGrowth = rGrowth;
  }

  public int Radius(int size)
  {
    return (int)(this.radBase + this.radGrowth * size);
  }

  public boolean In(int size, int dx, int dy, int dz) {
    int h = Size(size);
    int r = Radius(size);
    return ((int)Math.sqrt(dx * dx + dz * dz) <= r) && (Math.abs(dy) <= h);
  }

  public boolean OnBorder(int size, int dx, int dy, int dz) {
    int h = Size(size);
    int r = Radius(size);
    return ((int)Math.sqrt(dx * dx + dz * dz) == r) || ((Math.abs(dy) == h) && ((int)Math.sqrt(dx * dx + dz * dz) <= r));
  }
}