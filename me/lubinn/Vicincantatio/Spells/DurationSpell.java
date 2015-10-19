package me.lubinn.Vicincantatio.Spells;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerChatEvent;

public class DurationSpell extends Spell
{
  protected Timer timer = new Timer();

  protected int delay = 0;
  protected int period = 100;

  TimerTask task = new TimerTask() {
    public void run() {  } } ;

  public Spell CastSpell(PlayerChatEvent event, Material mat, int size, Map<String, Boolean> augments)
  {
    this.timer.scheduleAtFixedRate(this.task, this.delay, this.period);
    return this;
  }
}