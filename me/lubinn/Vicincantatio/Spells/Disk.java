package me.lubinn.Vicincantatio.Spells;

class Disk extends Ring
{
  public Disk()
  {
  }

  public Disk(double base, double growth)
  {
    super(base, growth);
  }

  public int boundYLow(int size)
  {
    return 0;
  }

  public int boundYHi(int size) {
    return 0;
  }
}