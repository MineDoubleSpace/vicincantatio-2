package me.lubinn.Vicincantatio.Spells;

class UpperHemisphere extends Sphere
{
  public UpperHemisphere()
  {
  }

  public UpperHemisphere(double base, double growth)
  {
    super(base, growth);
  }

  public int boundYLo(int size)
  {
    return 0;
  }

  public boolean OnBorder(int size, int dx, int dy, int dz) {
    int r = Size(size);
    return ((int)Math.sqrt(dx * dx + dy * dy + dz * dz) == r) || ((dy == 0) && ((int)Math.sqrt(dx * dx + dz * dz) <= r));
  }
}