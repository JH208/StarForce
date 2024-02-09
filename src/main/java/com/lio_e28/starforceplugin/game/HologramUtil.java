package com.lio_e28.starforceplugin.game;

import com.lio_e28.starforceplugin.StarForcePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class HologramUtil {

    public static void displayHologram(Player player, String message, int duration) {
        Location location = player.getLocation().add(0, 2.5, 0);
        ArmorStand hologram = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        hologram.setGravity(false);
        hologram.setVisible(false);
        hologram.setCustomName(message);
        hologram.setCustomNameVisible(true);

        // 설정한 시간(duration) 이후에 홀로그램(아머 스탠드) 제거
        Bukkit.getScheduler().runTaskLater(StarForcePlugin.getInstance(), hologram::remove, duration);
    }
}
