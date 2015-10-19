package me.lubinn.Vicincantatio.Spells;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import me.lubinn.Vicincantatio.Vicincantatio;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.util.Vector;

public abstract class Spell
{
  protected boolean targeted = true;
  protected double rangeBase = 10.0D;
  protected double rangeGrowth = 5.0D;
  protected int foodCost;
  static final Map<String, Spell> spells = new HashMap();
  static Random rand;

  static
  {
    spells.put("salio", new Salio());
    spells.put("fulmen", new Fulmen());
    spells.put("domus", new Domus());
    spells.put("pluvia", new Pluvia());
    spells.put("tempestas", new Tempestas());
    spells.put("sereno", new Sereno());
    spells.put("lucem", new Lucem());
    spells.put("noctem", new Noctem());
    spells.put("arbore", new Arbore());
    spells.put("foobar", new RealityCheck());
    spells.put("vorto", new Vorto());
    spells.put("urgeo", new Urgeo());
    spells.put("vello", new Vello());
    spells.put("ostium tartarus", new OstiumTartarus());
    spells.put("chunkus erasus", new ChunkusErasus());
    spells.put("chunkus renero", new ChunkusRenero());
    spells.put("sagitto", new Sagitto());
    spells.put("sagitto pluvia", new SagittoPluvia());
    spells.put("remedium", new Remedium());
    spells.put("mutatio", new Mutatio());
    spells.put("hadoken", new Hadoken());
    spells.put("extinguere", new Extinguere());
    spells.put("langueo", new Starve());
    spells.put("rapio", new leap());
    spells.put("ignis perpetua", new IgnisPerpetua());
    spells.put("surge gigas", new SummonGiant());
    spells.put("exercitum limo", new SummonSlime());

    rand = new Random();
  }

  protected int Range(int size) {
    return (int)(this.rangeBase + size * this.rangeGrowth);
  }

  protected Vector MoveVector(int size) {
    return new Vector(0, 1, 0);
  }
  public abstract Spell CastSpell(PlayerChatEvent paramPlayerChatEvent, Material paramMaterial, int paramInt, Map<String, Boolean> paramMap);

  public static Spell GetSpell(String spellName) {
    if (spells.containsKey(spellName))
      return (Spell)spells.get(spellName);
    if (ConstructionSpell.spells.containsKey(spellName)) {
      return (Spell)ConstructionSpell.spells.get(spellName);
    }
    return null;
  }

  protected boolean FoodEnabled()
  {
    return Vicincantatio.config.getBoolean("food_enabled", true);
  }

  protected void UseFood(PlayerChatEvent event, int foodCost)
  {
    if (event.getPlayer().getFoodLevel() <= 0) {
      event.getPlayer().damage(foodCost);
    }
    else
      event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() - foodCost);
  }

  protected void UseFood(PlayerChatEvent event, int foodCost, int size)
  {
    switch (size)
    {
    case 9:
      foodCost += 2;
      break;
    case 6:
      foodCost++;
      break;
    case 4:
      break;
    case 2:
      break;
    case 1:
      break;
    case 0:
      foodCost--;
    case 3:
    case 5:
    case 7:
    case 8:
    }
    if (foodCost <= 0) {
      foodCost = 0;
    }
    if (event.getPlayer().getFoodLevel() <= 0) {
      event.getPlayer().damage(foodCost);
    }
    else
      event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() - foodCost);
  }
}