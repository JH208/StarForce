package com.lio_e28.starforceplugin.command;

import com.lio_e28.starforceplugin.npc.SmithingNpc;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnSmithingNpcCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            SmithingNpc npc = new SmithingNpc(location, "강화 재료 상인");
            player.sendMessage("NPC가 생성되었습니다!");
            return false;
        }
        return true;
    }
}
