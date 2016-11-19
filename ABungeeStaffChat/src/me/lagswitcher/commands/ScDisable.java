package me.lagswitcher.commands;

import me.lagswitcher.BungeeStaffChat;
import me.lagswitcher.player.ScPlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ScDisable
  extends Command
{
  public ScDisable(String name)
  {
    super(name);
  }
  
  public void execute(CommandSender sender, String[] args)
  {
    if (!(sender instanceof ProxiedPlayer))
    {
      sender.sendMessage(new ComponentBuilder("This command is player only.").create());
      return;
    }
    ProxiedPlayer pp = (ProxiedPlayer)sender;
    ScPlayer player = BungeeStaffChat.getInstance().getPlayerManager().getPlayer(pp.getUniqueId());
    if ((pp.hasPermission("sc.disable")) || (pp.hasPermission("sc.*")))
    {
      if (player.isScDisabled()) {
        pp.sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', BungeeStaffChat.getInstance().getLang().getString("sc-enable"))).create());
      } else {
        pp.sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', BungeeStaffChat.getInstance().getLang().getString("sc-disable"))).create());
      }
      player.setScDisabled(!player.isScDisabled());
    }
    else
    {
      sender.sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', BungeeStaffChat.getInstance().getLang().getString("no-permission"))).create());
    }
  }
}
