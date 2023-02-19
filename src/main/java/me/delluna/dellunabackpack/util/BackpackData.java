package me.delluna.dellunabackpack.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.delluna.dellunabackpack.Reference;

public class BackpackData {
    public static void create(String uuid) {
        File file = getFile(uuid);
        YamlConfiguration config = getConfig(file);
        
        config.set("0", null);
        config.set("1", null);
        config.set("2", null);
        config.set("3", null);
        config.set("4", null);
        config.set("5", null);
        config.set("6", null);
        config.set("7", null);
        config.set("8", null);
        
        saveConfig(file, config);
    }
    public static Inventory getInventory(Player player) {
        Inventory inv = Bukkit.getServer().createInventory(null, 9, Reference.BACKPACK_INVENTORY_TITLE_COLOR + player.getName() + Reference.BACKPACK_INVENTORY_TITLE);
        
        String uuid = player.getUniqueId().toString();
        File file = getFile(uuid);
        YamlConfiguration config = getConfig(file);
        
        for (int i = 0; i < 9; i++) {
            String num = Integer.toString(i);
            
            if (config.get(num) == null) continue;
            
            ItemStack item = (ItemStack) config.get(num);
            inv.setItem(i, item);
        }
        
        return inv;
    }
    public static boolean isBackpackItem(ItemStack item) {
        if (!item.hasItemMeta()) return false;
        
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasCustomModelData()) return false;
        if (!meta.hasLore()) return false;
        
        List<String> lore = meta.getLore();
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).contains(Reference.BACKPACK_ITEM_LORE)) {
                return true;
            }
        }
        return false;
    }
    
    private static File getFile(String uuid) {
        return new File(Reference.PLUGIN_DATA_PATH, uuid + ".yml");
    }
    private static YamlConfiguration getConfig(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }
    private static void saveConfig(File file, YamlConfiguration config) {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
