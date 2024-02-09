package com.lio_e28.starforceplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.lio_e28.starforceplugin.item.StarForceItem.Smithing;
import static com.lio_e28.starforceplugin.item.StarForceItem.blink;

public class StarForceGUI{
    private static Set<Player> openGUIPlayers = new HashSet<>();
    private static ItemStack selectedEnhancementItem = null;
    public static void openStarForceGUI(Player player) {
        Inventory starForceGUI = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "강화 창");

        // GUI의 특정 슬롯에 아이템 배치
        starForceGUI.setItem(0, blink);
        starForceGUI.setItem(1, blink);
        starForceGUI.setItem(3, blink);
        starForceGUI.setItem(4, blink);
        starForceGUI.setItem(5, Smithing);
        starForceGUI.setItem(6, blink);
        starForceGUI.setItem(8, blink);


        // 강화 창 열기
        player.openInventory(starForceGUI);
        openGUIPlayers.add(player);
    }


    public static ItemStack getSelectedEnhancementItem() {
        return selectedEnhancementItem;
    }

    public static boolean isStarForceGUIOpen(Player player) {
        return openGUIPlayers.contains(player);
    }
}
