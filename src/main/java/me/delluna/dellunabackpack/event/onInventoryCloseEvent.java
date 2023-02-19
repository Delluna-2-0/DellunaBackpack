package me.delluna.dellunabackpack.event;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.delluna.dellunabackpack.Reference;
import me.delluna.dellunabackpack.util.BackpackData;

public class onInventoryCloseEvent implements Listener {
    @EventHandler
    private void onInventoryCloseEvent(InventoryCloseEvent event) {
        if (!event.getView().getTitle().contains(Reference.BACKPACK_INVENTORY_TITLE)) return;
        
        ItemStack[] inv = event.getInventory().getContents();
        Player player = (Player) event.getPlayer();
        String uuid = player.getUniqueId().toString();
        
        File file = getFile(uuid);
        YamlConfiguration config = getConfig(file);
        
        for (int i = 0; i < 9; i++) {
            String num = Integer.toString(i);
            ItemStack item = inv[i];
            if (BackpackData.isBackpackItem(item)) {
                returnBackpack(player, item);
                config.set(num, null);
            }
            else {
                config.set(num, item);
            }
        }
        
        saveConfig(file, config);
    }
    
    private void returnBackpack(Player player, ItemStack item) {
        if (player.getInventory().firstEmpty() == -1){
            player.sendMessage(Reference.BACKPACK_WARNING_MESSAGE);
            player.sendMessage(Reference.BACKPACK_DROP_WARNING_MESSAGE);
            player.getWorld().dropItem(player.getLocation(), item);
        }
        else {
            player.sendMessage(Reference.BACKPACK_WARNING_MESSAGE);
            player.getInventory().addItem(item);
        }
    }
    private File getFile(String uuid) {
        return new File(Reference.PLUGIN_DATA_PATH, uuid + ".yml");
    }
    private YamlConfiguration getConfig(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }
    private void saveConfig(File file, YamlConfiguration config) {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
