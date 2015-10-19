package me.lubinn.Vicincantatio.Spells;

class Sphere extends Area
{
  public Sphere()
  {
  }

  public Sphere(double base, double growth)
  {
    super(base, growth);
  }

  public int boundXLow(int size)
  {
    return -Size(size) - 1;
  }

  public int boundXHi(int size) {
    return Size(size) + 1;
  }

  public int boundYLow(int size) {
    return -Size(size) - 1;
  }

  public int boundYHi(int size) {
    return Size(size) + 1;
  }

  public int boundZLow(int size) {
    return -Size(size) - 1;
  }

  public int boundZHi(int size) {
    return Size(size) + 1;
  }

  public boolean InY(int size, int dx, int dz) {
    int r = Size(size);
    return (int)Math.sqrt(dx * dx + dz * dz) <= r;
  }

  public boolean In(int size, int dx, int dy, int dz) {
    int r = Size(size);
    return (int)Math.sqrt(dx * dx + dy * dy + dz * dz) <= r;
  }

  public boolean OnBorder(int size, int dx, int dy, int dz) {
    int r = Size(size);
    return (int)Math.sqrt(dx * dx + dy * dy + dz * dz) == r;
  }
}