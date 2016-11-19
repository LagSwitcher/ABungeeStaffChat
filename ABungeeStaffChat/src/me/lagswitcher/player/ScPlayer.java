package me.lagswitcher.player;

import java.util.UUID;

public class ScPlayer
{
  private UUID uniqueId;
  private boolean scDisabled;
  private boolean scToggled;
  private UUID lastMsgSender;
  
  public ScPlayer(UUID uniqueId)
  {
    this.uniqueId = uniqueId;
    this.scDisabled = false;
    this.scToggled = false;
  }
  
  public UUID getUniqueId()
  {
    return this.uniqueId;
  }
  
  public boolean isScDisabled()
  {
    return this.scDisabled;
  }
  
  public void setScDisabled(boolean scDisabled)
  {
    this.scDisabled = scDisabled;
  }
  
  public boolean isScToggled()
  {
    return this.scToggled;
  }
  
  public void setScToggled(boolean scToggled)
  {
    this.scToggled = scToggled;
  }
  
  public UUID getLastMsgSender()
  {
    return this.lastMsgSender;
  }
  
  public void setLastMsgSender(UUID lastMsgSender)
  {
    this.lastMsgSender = lastMsgSender;
  }
}
