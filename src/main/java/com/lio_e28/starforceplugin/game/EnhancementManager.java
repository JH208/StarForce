package com.lio_e28.starforceplugin.game;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;
import java.util.UUID;

public class EnhancementManager{
    private static final Random random = new Random();

    public static int attemptEnhancement(Player player, ItemStack item) {
        int grade = getItemGrade(item);
//        verifyProbabilities();

        if (grade >= 25) {
            player.sendMessage(ChatColor.RED + "이미 최대 강화 등급에 도달했습니다!");
            return grade; // 현재 등급 반환
        }

        double successChance = GameResult.getSuccessChance(grade);
        double failureChance = GameResult.getFailureChance(grade);
        double destructionChance = GameResult.getDestructionChance(grade);
        double randomValue = Math.random() * 100;

        if (randomValue < successChance) {
            // 강화 성공
            grade++;
            player.sendMessage(ChatColor.GREEN + "강화에 성공하였습니다!");
            applyEnhancementSuccessEffects(player, item);
            HologramUtil.displayHologram(player, "강화에 성공하였습니다!", 60);
            applyEnhancedAttributes(item, grade);

            int currentGrade = grade;
            int maxGrade = PlayerMaxGradeManager.getPlayerMaxGrade(player);
            if (currentGrade > maxGrade) {
                PlayerMaxGradeManager.setPlayerMaxGrade(player, currentGrade);
                GlobalScoreboard.updatePlayerGrade(player, currentGrade);
            }

        } else {
            if (randomValue < successChance + failureChance) {
                // 강화 실패 (등급 유지)
                player.sendMessage(ChatColor.YELLOW + "강화에 실패하였습니다. 다행히 아이템은 안전합니다.");
                applyEnhancementFailureEffects(player);
                HologramUtil.displayHologram(player, "강화에 실패하였습니다.", 60);
            } else {
                // 아이템 파괴
                item.setAmount(0);
                player.sendMessage(ChatColor.RED + "강화에 실패하였고 아이템이 파괴되었습니다.");
                HologramUtil.displayHologram(player, "강화에 실패하였고 아이템이 파괴되었습니다.", 60);
            }
        }
        setItemGrade(item, grade);
        return grade;
    }

    // 강화 등급을 아이템에 설정합니다.
    public static void setItemGrade(ItemStack item, int grade) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setCustomModelData(grade);
            item.setItemMeta(meta);
        }
    }

    // 아이템에서 강화 등급을 조회합니다.
    public static int getItemGrade(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasCustomModelData()) {
            return meta.getCustomModelData();
        }
        return 0; // 기본값으로 0을 반환
    }
    public static boolean isEnhanceableItem(ItemStack item) {
        // 강화 가능한 아이템 유형을 여기에서 확장하거나 조정할 수 있습니다
        return item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.NETHERITE_SWORD;
    }

    public static void applyEnhancedAttributes(ItemStack item, int grade) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        double baseDamage = 7; // 다이아몬드 검의 기본 공격 피해
        double additionalDamage = calculateAdditionalDamage(grade); // 강화 등급에 따른 추가 공격 피해
        double attackSpeed = 2.4; // 고정 공격 속도

        // 기존의 속성 수정자를 제거
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);

        // 공격 피해 속성 수정자 추가
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", baseDamage + additionalDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

        // 공격 속도 속성 수정자 추가
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed",  4 -attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

        item.setItemMeta(meta);
    }

    private static double calculateAdditionalDamage(int grade) {
        double additionalDamage = 0.0;

        // 1성부터 15성까지는 각 등급마다 0.5씩 추가 피해
        if (grade >= 1 && grade <= 15) {
            additionalDamage = grade * 0.5;
        }
        // 16성부터 20성까지는 각 등급마다 1씩 추가 피해 (15성까지의 7.5 + 16~20성까지의 추가 피해)
        else if (grade >= 16 && grade <= 20) {
            additionalDamage = 7.5 + (grade - 15) * 1.0;
        }
        // 21성부터 25성까지는 각 등급마다 2씩 추가 피해 (20성까지의 12.5 + 21~25성까지의 추가 피해)
        else if (grade >= 21 && grade <= 25) {
            additionalDamage = 12.5 + (grade - 20) * 2.0;
        }

        return additionalDamage;
    }


    public static ItemStack getEnhancedItem(ItemStack item, int grade) {
        // 강화 성공 후의 아이템을 반환하는 메서드
        if (isEnhanceableItem(item)) {
            ItemStack enhancedItem = item.clone();
            ItemMeta meta = enhancedItem.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.GREEN + "강화된 검"+ChatColor.YELLOW + "✦"+ChatColor.GREEN +"[" + grade + "성]");
                enhancedItem.setItemMeta(meta);
            }
            return enhancedItem;
        }
        return null;
    }

    public static void verifyProbabilities() {
        for (int grade = 0; grade <= 24; grade++) {
            double successChance = GameResult.getSuccessChance(grade);
            double failureChance = GameResult.getFailureChance(grade);
            double destructionChance = GameResult.getDestructionChance(grade);
            double totalChance = successChance + failureChance + destructionChance;

            // 확률의 총합이 100%를 초과하는지 검증
            System.out.println("Grade: " + grade + ", 성공: " + successChance + ", 실패: " + failureChance + ", 파괴: " + destructionChance + ", 총합: " + totalChance);
            if (totalChance > 100) {
                System.out.println("Error: grade가 100%를 넘습니다." + grade);
            }
        }
    }



    private static void applyEnhancementSuccessEffects(Player player, ItemStack item) {
        Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.GREEN).flicker(true).build());
        firework.setFireworkMeta(fireworkMeta);
    }

    private static void applyEnhancementFailureEffects(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
    }
}
