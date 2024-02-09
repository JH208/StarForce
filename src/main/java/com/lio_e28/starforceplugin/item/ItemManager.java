package com.lio_e28.starforceplugin.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemManager {


    static ItemStack buildItem(Material type, int amount, String displayName, String... lore) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        if (meta != null){
            meta.setDisplayName(displayName);
            meta.setLore(Arrays.asList(lore));
            stack.setItemMeta(meta);
        } else {
            System.err.println("ItemMeta is null for item: " + type.toString());
        }
        return stack;
    }

}
