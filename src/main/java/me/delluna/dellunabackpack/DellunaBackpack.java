package me.delluna.dellunabackpack;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.delluna.dellunabackpack.event.onInventoryCloseEvent;
import me.delluna.dellunabackpack.event.onPlayerInteractEvent;
import me.delluna.dellunabackpack.event.onPlayerJoinEvent;


public final class DellunaBackpack extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new onPlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerInteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onInventoryCloseEvent(), this);
        
        Plugin plugin = JavaPlugin.getPlugin(DellunaBackpack.class);
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
    }
}
