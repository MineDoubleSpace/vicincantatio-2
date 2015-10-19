package me.lubinn.Vicincantatio.Spells;

import java.util.List;
import java.util.Map;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

class Salio extends Spell
{
  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    if (FoodEnabled())
    {
      this.foodCost = 1;
      UseFood(event, this.foodCost);
    }

    List blocks = event.getPlayer().getLastTwoTargetBlocks(null, 500);
    World world = event.getPlayer().getWorld();
    boolean motion = augments.containsKey("motion");
    Location location = ((Block)blocks.get(motion ? 1 : 0)).getLocation();
    world.playEffect(location, Effect.SMOKE, 1);
    world.playEffect(event.getPlayer().getLocation(), Effect.SMOKE, 1);
    location.add(0.5D, 0.0D, 0.5D);
    if (motion)
      location.add(0.0D, 1.0D, 0.0D);
    event.getPlayer().teleport(location);

    return this;
  }
}