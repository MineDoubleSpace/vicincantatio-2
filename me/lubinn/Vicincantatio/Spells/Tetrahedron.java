package me.lubinn.Vicincantatio.Spells;

import org.bukkit.util.Vector;

class Tetrahedron extends Area
{
  static final double phi = 109.47122055D;
  static final double h = Math.cos(1.230959418815415D);
  static final double r = Math.sin(1.230959418815415D);
  protected Vector A;
  protected Vector B;
  protected Vector C;
  protected Vector D;
  protected Vector N1;
  protected Vector N2;
  protected Vector N3;
  protected Vector N4;

  public Tetrahedron()
  {
  }

  public Tetrahedron(double base, double growth)
  {
    super(base, growth);
  }

  public void Instantiate(Vector heading)
  {
    double xz = Math.sqrt(heading.getX() * heading.getX() + heading.getZ() * heading.getZ());

    this.A = new Vector(0, 1, 0);
    this.B = new Vector();
  }

  public boolean In(int size, int dx, int dy, int dz)
  {
    return false;
  }

  public boolean OnBorder(int size, int dx, int dy, int dz)
  {
    return false;
  }
}