package com.lio_e28.starforceplugin.game;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static com.lio_e28.starforceplugin.item.StarForceItem.Scroll;

public class GameResult {
    // 등급별 실패 확률과 파괴 확률 저장
    private static final Map<Integer, Double> failureChances = new HashMap<>();
    private static final Map<Integer, Double> destructionChances = new HashMap<>();

    static {
        failureChances.put(0, 0.0); // 0성 실패 확률
        failureChances.put(1, 5.0); // 1성 실패 확률
        failureChances.put(2, 10.0); // 2성 실패 확률
        failureChances.put(3, 15.0); // 3성 실패 확률
        failureChances.put(4, 20.0); // 4성 실패 확률
        failureChances.put(5, 25.0); // 5성 실패 확률
        failureChances.put(6, 30.0); // 6성 실패 확률
        failureChances.put(7, 35.0); // 7성 실패 확률
        failureChances.put(8, 40.0); // 8성 실패 확률
        failureChances.put(9, 45.0); // 9성 실패 확률
        failureChances.put(10, 0.0); // 10성 실패 확률
        failureChances.put(11, 5.0); // 11성 실패 확률
        failureChances.put(12, 10.0); // 12성 실패 확률
        failureChances.put(13, 15.0); // 13성 실패 확률
        failureChances.put(14, 20.0); // 14성 실패 확률
        failureChances.put(15, 67.9); // 15성 실패 확률
        failureChances.put(16, 67.9); // 16성 실패 확률
        failureChances.put(17, 67.9); // 17성 실패 확률
        failureChances.put(18, 67.2); // 18성 실패 확률
        failureChances.put(19, 67.2); // 19성 실패 확률
        failureChances.put(20, 63.0); // 20성 실패 확률
        failureChances.put(21, 63.0); // 21성 실패 확률
        failureChances.put(22, 77.6); // 22성 실패 확률
        failureChances.put(23, 68.6); // 23성 실패 확률
        failureChances.put(24, 59.4); // 24성 실패 확률

        destructionChances.put(0, 0.0); // 0성 파괴 확률
        destructionChances.put(1, 0.0); // 1성 파괴 확률
        destructionChances.put(2, 0.0); // 2성 파괴 확률
        destructionChances.put(3, 0.0); // 3성 파괴 확률
        destructionChances.put(4, 0.0); // 4성 파괴 확률
        destructionChances.put(5, 0.0); // 5성 파괴 확률
        destructionChances.put(6, 0.0); // 6성 파괴 확률
        destructionChances.put(7, 0.0); // 7성 파괴 확률
        destructionChances.put(8, 0.0); // 8성 파괴 확률
        destructionChances.put(9, 0.0); // 9성 파괴 확률
        destructionChances.put(10, 0.0); // 10성 파괴 확률
        destructionChances.put(11, 0.0); // 11성 파괴 확률
        destructionChances.put(12, 0.0); // 12성 파괴 확률
        destructionChances.put(13, 0.0); // 13성 파괴 확률
        destructionChances.put(14, 0.0); // 14성 파괴 확률
        destructionChances.put(15, 2.1); // 15성 파괴 확률
        destructionChances.put(16, 2.1); // 16성 파괴 확률
        destructionChances.put(17, 2.1); // 17성 파괴 확률
        destructionChances.put(18, 2.8); // 18성 파괴 확률
        destructionChances.put(19, 2.8); // 19성 파괴 확률
        destructionChances.put(20, 7.0); // 20성 파괴 확률
        destructionChances.put(21, 7.0); // 21성 파괴 확률
        destructionChances.put(22, 19.4); // 22성 파괴 확률
        destructionChances.put(23, 29.4); // 23성 파괴 확률
        destructionChances.put(24, 39.6); // 24성 파괴 확률
    }

    // 등급에 따른 실패 확률 반환
    public static double getFailureChance(int grade) {
        return failureChances.getOrDefault(grade, 1.0);
    }

    // 등급에 따른 파괴 확률 반환
    public static double getDestructionChance(int grade) {
        return destructionChances.getOrDefault(grade, 0.0);
    }

    public static double getSuccessChance(int grade) {
        double failureChance = getFailureChance(grade);
        double destructionChance = getDestructionChance(grade);
        return 100.0 - (failureChance + destructionChance);
    }


}
