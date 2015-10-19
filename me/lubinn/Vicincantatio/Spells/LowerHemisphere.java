package me.lubinn.Vicincantatio.Spells;

class LowerHemisphere extends Sphere
{
  private double height;

  public LowerHemisphere()
  {
  }

  public LowerHemisphere(double base, double growth)
  {
    this(base, growth, 1.0D);
  }

  public LowerHemisphere(double base, double growth, double height) {
    super(base, growth);
    this.height = height;
  }

  public int boundYLo(int size)
  {
    return (int)(-Size(size) * this.height) - 1;
  }

  public int boundYHi(int size) {
    return 0;
  }

  public boolean InY(int size, int dx, int dz) {
    int r = Size(size);
    return (int)Math.sqrt(dx * dx + dz * dz) <= r;
  }

  public boolean In(int size, int dx, int dy, int dz) {
    int r = Size(size);
    double b = 1.0D / this.height;
    return (int)Math.sqrt(dx * dx + b * b * dy * dy + dz * dz) <= r;
  }

  public boolean OnBorder(int size, int dx, int dy, int dz) {
    int r = Size(size);
    double b = 1.0D / this.height;
    return ((int)Math.sqrt(dx * dx + b * b * dy * dy + dz * dz) == r) || ((dy == 0) && ((int)Math.sqrt(dx * dx + dz * dz) <= r));
  }
}