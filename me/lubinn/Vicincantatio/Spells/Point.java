package me.lubinn.Vicincantatio.Spells;

class Point extends Area
{
  public int boundXLow(int size)
  {
    return 0;
  }

  public int boundXHi(int size) {
    return 0;
  }

  public int boundYLow(int size) {
    return 0;
  }

  public int boundYHi(int size) {
    return 0;
  }

  public int boundZLow(int size) {
    return 0;
  }

  public int boundZHi(int size) {
    return 0;
  }

  public boolean In(int size, int dx, int dy, int dz) {
    return true;
  }

  public boolean OnBorder(int size, int dx, int dy, int dz) {
    return true;
  }
}