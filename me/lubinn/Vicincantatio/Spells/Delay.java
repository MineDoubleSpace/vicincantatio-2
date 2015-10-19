package me.lubinn.Vicincantatio.Spells;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

public class Delay
{
  static final Map<String, Spell> delay = new HashMap();
  static Material material;
  static int size1;
  static Map<String, Boolean> aug;
  static String spellName;

  public static void delay(String name, Spell spell, PlayerChatEvent ev, Material mat, int size, Map<String, Boolean> augments)
  {
    delay.put(ev.getPlayer().getName(), spell);
    material = mat;
    size1 = size;
    aug = augments;
    spellName = name;
  }

  public static void release(PlayerChatEvent ev)
  {
    Spell toCast = (Spell)delay.get(ev.getPlayer().getName());
    if (toCast != null)
    {
      ((Spell)delay.get(ev.getPlayer().getName())).CastSpell(ev, material, size1, aug);

      delay.remove(ev.getPlayer().getName());
    }
  }
}