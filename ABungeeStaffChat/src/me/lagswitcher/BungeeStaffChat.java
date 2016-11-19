package me.lagswitcher;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

import me.lagswitcher.commands.Sc;
import me.lagswitcher.commands.ScDisable;
import me.lagswitcher.commands.ScToggle;
import me.lagswitcher.listeners.PlayerListener;
import me.lagswitcher.player.ScPlayerManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeeStaffChat
  extends Plugin
{
  private static BungeeStaffChat instance;
  private ScPlayerManager playerManager;
  private Configuration config;
  private Configuration lang;
  private boolean shortcutEnabled;
  private char shortcut;
  private String scLayout;
  private boolean scPriorityEnabled;
  private boolean bungeePerms;
  
  public static BungeeStaffChat getInstance()
  {
    return instance;
  }
  
  public static String getVersion()
  {
    return "1.0";
  }
  
  public void onLoad()
  {
    setupConfig();
  }
  
  public void onEnable()
  {
    instance = this;
    this.playerManager = new ScPlayerManager();
    
    getProxy().getPluginManager().registerCommand(this, new Sc(getConfig().getString("sc-command").replaceAll("/", "")));
    
    getProxy().getPluginManager().registerCommand(this, new ScDisable(getConfig().getString("scdisable-command").replaceAll("/", "")));
    
    
    getProxy().getPluginManager().registerCommand(this, new ScToggle(getConfig().getString("sctoggle-command").replaceAll("/", "")));
    
    
    getProxy().getPluginManager().registerListener(this, new PlayerListener());
    
    this.scPriorityEnabled = false;
    if (getProxy().getPluginManager().getPlugin("BungeePerms") != null) {
      this.bungeePerms = true;
    }
  }
  
  private void setupConfig()
  {
    Path dirPath = getDataFolder().toPath();
    Path configPath = Paths.get(dirPath.toAbsolutePath().toString(), new String[] { "config.yml" });
    Path langPath = Paths.get(dirPath.toAbsolutePath().toString(), new String[] { "lang.yml" });
    if (!Files.exists(dirPath, new LinkOption[0])) {
      try
      {
        Files.createDirectory(dirPath, new FileAttribute[0]);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    if (!Files.exists(configPath, new LinkOption[0])) {
      try
      {
        Files.copy(getResourceAsStream("config.yml"), configPath, new CopyOption[0]);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    if (!Files.exists(langPath, new LinkOption[0])) {
      try
      {
        Files.copy(getResourceAsStream("lang.yml"), langPath, new CopyOption[0]);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    try
    {
      this.config = YamlConfiguration.getProvider(YamlConfiguration.class).load(configPath.toFile());
      this.lang = YamlConfiguration.getProvider(YamlConfiguration.class).load(langPath.toFile());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    this.scLayout = getConfig().getString("sc-layout");
    this.shortcutEnabled = getConfig().getBoolean("shortcut-enabled");
    if (this.shortcutEnabled) {
      this.shortcut = getConfig().getString("shortcut").toCharArray()[0];
    }
  }
  
  public Configuration getConfig()
  {
    return this.config;
  }
  
  public Configuration getLang()
  {
    return this.lang;
  }
  
  public String getScLayout()
  {
    return ChatColor.translateAlternateColorCodes('&', this.scLayout);
  }
  
  public char getShortcut()
  {
    return this.shortcut;
  }
  
  public boolean isShortcutEnabled()
  {
    return this.shortcutEnabled;
  }
  
  public ScPlayerManager getPlayerManager()
  {
    return this.playerManager;
  }
  
  public boolean isScPriorityEnabled()
  {
    return this.scPriorityEnabled;
  }
  
  public void setScPriorityEnabled(boolean scPriorityEnabled)
  {
    this.scPriorityEnabled = scPriorityEnabled;
  }
  
  public boolean isBungeePerms()
  {
    return this.bungeePerms;
  }
  
  public void reload()
  {
    try
    {
      this.config = YamlConfiguration.getProvider(YamlConfiguration.class).load(Paths.get(getDataFolder().getAbsolutePath(), new String[] { "config.yml" }).toFile());
      
      this.lang = YamlConfiguration.getProvider(YamlConfiguration.class).load(Paths.get(getDataFolder().getAbsolutePath(), new String[] { "lang.yml" }).toFile());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    this.scLayout = getConfig().getString("sc-layout");
    this.shortcutEnabled = getConfig().getBoolean("shortcut-enabled");
    if (this.shortcutEnabled) {
      this.shortcut = getConfig().getString("shortcut").toCharArray()[0];
    }
  }
}
