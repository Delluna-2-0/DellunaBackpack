package me.delluna.dellunabackpack;

import org.bukkit.plugin.java.JavaPlugin;

public class Reference {
    public static String PLUGIN_DATA_PATH = JavaPlugin.getPlugin(DellunaBackpack.class).getDataFolder() + "\\BackpackData";
    public static String BACKPACK_ITEM_LORE = "우클릭으로 가방을 열 수 있다.";
    public static String BACKPACK_INVENTORY_TITLE = "§x§0§0§b§3§b§6의 가방";
    public static String BACKPACK_INVENTORY_TITLE_COLOR = "§x§0§0§b§3§b§6";
    public static String BACKPACK_WARNING_MESSAGE = "가방에 가방을 넣을 수 없습니다.";
    public static String BACKPACK_DROP_WARNING_MESSAGE = "인벤토리에 자리가 없어 현재 위치에 가방을 떨어뜨렸습니다.";
}
