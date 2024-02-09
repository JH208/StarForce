package com.lio_e28.starforceplugin.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class GlobalScoreboard {
    private static Scoreboard scoreboard;

    // 모든 플레이어를 위한 공통 스코어보드 초기화
    public static void initializeScoreboard() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) return;

        scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("MaxEnhanceGrade", "dummy",
                ChatColor.AQUA + "" + ChatColor.BOLD + "강화 최고 등급");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    // 플레이어의 최대 강화 등급 업데이트
    public static void updatePlayerGrade(Player player, int maxGrade) {
        if (scoreboard == null) initializeScoreboard();

        Objective objective = scoreboard.getObjective("MaxEnhanceGrade");
        if (objective == null) return;

        // 플레이어의 최대 강화 등급을 업데이트
        Score score = objective.getScore(player.getName());
        score.setScore(maxGrade);

        // 모든 플레이어에게 업데이트된 스코어보드 적용
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setScoreboard(scoreboard);
        }
    }


}
