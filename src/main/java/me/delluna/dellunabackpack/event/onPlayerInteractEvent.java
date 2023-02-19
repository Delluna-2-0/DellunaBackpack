package me.delluna.dellunabackpack.event;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.delluna.dellunabackpack.Reference;
import me.delluna.dellunabackpack.util.BackpackData;

public class onPlayerInteractEvent implements Listener {
    @EventHandler
    private void onPlayerInteractEvent(PlayerInteractEvent event) {
        
        if (!isBackpackUseAction(event)) {
            return;
        }
        
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (BackpackData.isBackpackItem(item)) {
            player.openInventory(new BackpackData().getInventory(player));
        }
    }

    private boolean isBackpackUseAction(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return false;
        if (event.getPlayer().isSneaking()) return false;
        return true;
    }
}
