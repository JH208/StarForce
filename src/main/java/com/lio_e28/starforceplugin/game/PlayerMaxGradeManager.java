package com.lio_e28.starforceplugin.game;

import com.lio_e28.starforceplugin.StarForcePlugin;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PlayerMaxGradeManager {
    private static Map<String, Integer> scores = new HashMap<>();

    public static void setPlayerMaxGrade(Player player, int grade) {
        String playerName = player.getName();
        scores.put(playerName, grade); // 최대 강화 등급을 맵에 저장
        GlobalScoreboard.updatePlayerGrade(player, grade); // 스코어보드 업데이트

        System.out.println("Updated " + playerName + "'s grade to " + grade);
    }
    public static int getPlayerMaxGrade(Player player) {
        return scores.getOrDefault(player.getName(), 0); // 기본값으로 0을 반환
    }

    public static void loadScores() {
        System.out.println("Loading scores...");
        File file = new File(StarForcePlugin.getInstance().getDataFolder(), "scores.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(":");
                if (parts.length == 2) {
                    scores.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Scores file not found, creating a new one.");
        }
    }

    public static void saveScores() {
        System.out.println("Saving scores...");
        File dataFolder = StarForcePlugin.getInstance().getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs(); // 데이터 폴더가 없으면 생성
        }
        File file = new File(StarForcePlugin.getInstance().getDataFolder(), "scores.txt");
        try (PrintWriter out = new PrintWriter(file)) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                out.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
