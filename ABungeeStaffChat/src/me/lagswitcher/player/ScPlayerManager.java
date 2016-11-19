package me.lagswitcher.player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ScPlayerManager
{
  private Set<ScPlayer> players;
  
  public ScPlayerManager()
  {
    this.players = new HashSet<ScPlayer>();
  }
  
  public ScPlayer getPlayer(UUID uuid)
  {
    for (ScPlayer player : this.players) {
      if (player.getUniqueId().equals(uuid)) {
        return player;
      }
    }
    return null;
  }
  
  public void addPlayer(UUID uuid)
  {
    this.players.add(new ScPlayer(uuid));
  }
  
  public void removePlayer(UUID uuid)
  {
    this.players.remove(getPlayer(uuid));
  }
  
  public Set<ScPlayer> getPlayers()
  {
    return this.players;
  }
}
