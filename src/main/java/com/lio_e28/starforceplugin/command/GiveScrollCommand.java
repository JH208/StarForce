package com.lio_e28.starforceplugin.command;

import com.lio_e28.starforceplugin.item.StarForceItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveScrollCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            switch (args.length){
                case 0:
                    player.getInventory().addItem(StarForceItem.Scroll);
                    break;
                case 1:
                    try{
                        int amount = Integer.parseInt(args[0]);
                        ItemStack starforce = new ItemStack(StarForceItem.Scroll);
                        starforce.setAmount(amount);
                        player.getInventory().addItem(starforce);
                        break;
                    }catch (NumberFormatException e){
                        player.sendMessage("명령어를 재대로 입력해주세요.");
                        return false;
                    }catch (Exception e){
                        player.sendMessage("명령어를 재대로 입력해주세요.");
                        return false;
                    }
            }
        }else{
            sender.sendMessage("콘솔 불가능");
        }
        return true;

    }
}
