package me.lubinn.Vicincantatio.Spells;

class Cube extends Area
{
  public Cube()
  {
  }

  public Cube(double base, double growth)
  {
    super(base, growth);
  }

  public boolean In(int size, int dx, int dy, int dz)
  {
    int s = Size(size);
    return (Math.abs(dx) <= s) && (Math.abs(dy) <= s) && (Math.abs(dz) <= s);
  }

  public boolean OnBorder(int size, int dx, int dy, int dz) {
    int s = Size(size);
    return (Math.abs(dx) == s) || (Math.abs(dy) == s) || (Math.abs(dz) == s);
  }
}