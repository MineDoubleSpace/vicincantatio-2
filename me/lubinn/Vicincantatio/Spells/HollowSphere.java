package me.lubinn.Vicincantatio.Spells;

class HollowSphere extends Sphere
{
  public HollowSphere()
  {
  }

  public HollowSphere(double base, double growth)
  {
    super(base, growth);
  }

  public boolean In(int size, int dx, int dy, int dz)
  {
    int r = Size(size);
    int d = (int)Math.sqrt(dx * dx + dy * dy + dz * dz);
    return (d <= r) && (d >= 3);
  }
}