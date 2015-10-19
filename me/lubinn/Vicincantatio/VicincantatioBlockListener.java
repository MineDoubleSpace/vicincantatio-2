package me.lubinn.Vicincantatio;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class VicincantatioBlockListener implements Listener
{
  public static Vicincantatio plugin;

  public VicincantatioBlockListener(Vicincantatio instance)
  {
    plugin = instance;
  }
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event)
  {
    Block block = event.getBlock();
    if (block.getType() == Material.WEB)
    {
      block.setType(Material.AIR);
      event.setCancelled(true);
    }
    else if (block.getType() == Material.ICE)
    {
      block.setType(Material.DIRT);
      block.setType(Material.AIR);
    }
  }
}