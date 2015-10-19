package me.lubinn.Vicincantatio.Spells;

class Platform extends Point
{
  public int boundXLow(int size)
  {
    return -1;
  }

  public int boundXHi(int size) {
    return 1;
  }

  public int boundZLow(int size) {
    return -1;
  }

  public int boundZHi(int size) {
    return 1;
  }
}