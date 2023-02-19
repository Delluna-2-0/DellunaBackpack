package me.delluna.dellunabackpack.event;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.delluna.dellunabackpack.Reference;
import me.delluna.dellunabackpack.util.BackpackData;

public class onPlayerJoinEvent implements Listener {
    @EventHandler
    private void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        
        if (!isBackpackDataFileExists(uuid)) {
            BackpackData.create(uuid);
        }
    }
    
    private boolean isBackpackDataFileExists(String uuid) {
        return new File(Reference.PLUGIN_DATA_PATH, uuid + "yml").exists();
    }
}
