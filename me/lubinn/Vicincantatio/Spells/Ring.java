package me.lubinn.Vicincantatio.Spells;

class Ring extends Sphere
{
  public Ring()
  {
  }

  public Ring(double base, double growth)
  {
    super(base, growth);
  }

  public int boundYLow(int size)
  {
    return -Size(size) / 2;
  }

  public int boundYHi(int size) {
    return Size(size) / 2;
  }

  public boolean In(int size, int dx, int dy, int dz) {
    int r = Size(size);
    return (int)Math.sqrt(dx * dx + dz * dz) <= r;
  }

  public boolean OnBorder(int size, int dx, int dy, int dz) {
    int r = Size(size);
    return (int)Math.sqrt(dx * dx + dz * dz) == r;
  }
}