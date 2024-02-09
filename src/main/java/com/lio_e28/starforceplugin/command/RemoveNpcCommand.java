package com.lio_e28.starforceplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.ChatColor;

public class RemoveNpcCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("npc.remove")) {
                // NPC 제거 로직
                // 이 예제에서는 가장 가까운 NPC를 찾아 제거합니다.
                Villager nearestVillager = player.getWorld().getEntitiesByClass(Villager.class).stream()
                        .filter(v -> v.getCustomName() != null && v.getCustomName().equals("강화 재료 상인"))
                        .findFirst().orElse(null);

                if (nearestVillager != null) {
                    nearestVillager.remove();
                    player.sendMessage(ChatColor.GREEN + "NPC가 제거되었습니다.");
                } else {
                    player.sendMessage(ChatColor.RED + "제거할 NPC를 찾을 수 없습니다.");
                }
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "NPC를 제거할 권한이 없습니다.");
            }
        }
        return false;
    }
}
