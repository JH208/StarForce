package com.lio_e28.starforceplugin.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lio_e28.starforceplugin.item.ItemManager.buildItem;

public class StarForceItem {

    public static final ItemStack StarForce = buildItem(Material.NETHER_STAR, 1, ChatColor.YELLOW+"스타포스");
    public static final ItemStack DiamondSword = buildItem(Material.DIAMOND_SWORD, 1, "강화용 다이아몬드 검");
    public static final ItemStack Scroll = buildItem(Material.PAPER, 1, "강화의 주문서");
    public static final ItemStack Smithing = buildItem(Material.ANVIL, 1, "강화");

    public static final ItemStack blink = buildItem(Material.GRAY_STAINED_GLASS_PANE, 1, " ");

}
