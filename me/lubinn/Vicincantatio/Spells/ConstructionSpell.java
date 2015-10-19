package me.lubinn.Vicincantatio.Spells;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.lubinn.Vicincantatio.Vicincantatio;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.util.Vector;

abstract class ConstructionSpell extends Spell
{
  protected Area area = null;
  protected Material forceMaterial = null;
  protected boolean alwaysBubble = false;
  protected boolean alwaysForce = false;
  protected boolean alwaysFlight = false;
  protected boolean beforeTarget = false;

  static final Map<String, ConstructionSpell> spells = new HashMap();

  static {
    spells.put("cubus", new Cubus());
    spells.put("demis", new Demis());
    spells.put("libyes", new Libyes());
    spells.put("sphaera", new Sphaera());
    spells.put("tholus", new Tholus());
    spells.put("turris", new Turris());
    spells.put("discus", new Discus());
    spells.put("annulus", new Annulus());
    spells.put("bulla", new Bulla());
    spells.put("gradi", new Gradi());
    spells.put("restituo", new Restituo());
    spells.put("lux", new Lux());
    spells.put("accendit", new Accendit());
    spells.put("respiro", new Respiro());
    spells.put("frigidus", new Frigidus());
    spells.put("concalesco", new Concalesco());
    spells.put("uanescere", new Uanescere());
    spells.put("evanescere", new Evanescere());
  }

  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 2;
      UseFood(event, this.foodCost, size);
    }

    if (this.forceMaterial != null)
      mat = this.forceMaterial;
    if (mat == null) {
      return null;
    }
    Vicincantatio.log.info("In CastSpell");
    boolean force = (augments.containsKey("force")) || (this.alwaysForce);
    boolean bubble = (augments.containsKey("bubble")) || (this.alwaysBubble);
    boolean flight = (augments.containsKey("flight")) || (this.alwaysFlight);

    Player player = event.getPlayer();
    World world = player.getWorld();
    Location location = player.getLocation();

    if (this.targeted)
    {
      int range = 500;
      if (flight)
        range = 10 + 5 * size;
      if (this.beforeTarget)
      {
        List blocks = player.getLastTwoTargetBlocks(null, range);
        location = ((Block)blocks.get(0)).getLocation();
      }
      else
      {
        location = player.getTargetBlock(null, range).getLocation();
      }

    }

    int xorg = location.getBlockX();
    int yorg = location.getBlockY();
    int zorg = location.getBlockZ();

    int xlo = this.area.boundXLow(size);
    int xhi = this.area.boundXHi(size);
    int ylo = this.area.boundYLow(size);
    int yhi = this.area.boundYHi(size);
    int zlo = this.area.boundZLow(size);
    int zhi = this.area.boundZHi(size);

    for (int x = xorg + xlo; x <= xorg + xhi; x++)
    {
      int dx = x - xorg;
      for (int z = zorg + zlo; z <= zorg + zhi; z++)
      {
        int dz = z - zorg;
        if (!this.area.InY(size, dx, dz))
          continue;
        for (int y = Math.max(yorg + ylo, 1); y <= Math.min(yorg + yhi, 127); y++)
        {
          int dy = y - yorg;
          boolean inArea;
          if (bubble)
            inArea = this.area.OnBorder(size, dx, dy, dz);
          else
            inArea = this.area.In(size, dx, dy, dz);
          if (!inArea)
            continue;
          Block block = world.getBlockAt(x, y, z);
          ReplaceBlock(block, mat, force);
        }

      }

    }

    Vector V = MoveVector(size);
    if ((augments.containsKey("motion")) && (V != null))
      event.getPlayer().teleport(location.add(V.toLocation(world)));
    return this;
  }

  protected void ReplaceBlock(Block block, Material mat, boolean force)
  {
    Material type = block.getType();
    if ((type == Material.AIR) || ((force) && (type != Material.BEDROCK)))
      block.setType(mat);
  }
}