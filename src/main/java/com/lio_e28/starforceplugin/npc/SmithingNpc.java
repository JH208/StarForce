package com.lio_e28.starforceplugin.npc;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

import static com.lio_e28.starforceplugin.item.StarForceItem.Scroll;

public class SmithingNpc {
    private Villager npc;

    // 생성자: 위치와 이름을 전달받아 NPC를 생성함
    public SmithingNpc(Location location, String name) {
        this.npc = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        npc.setCustomName(name);
        npc.setCustomNameVisible(true);
        npc.setProfession(Villager.Profession.ARMORER); // NPC 직업 설정
        npc.setAI(false); // NPC가 돌아다니지 않도록 AI 비활성화

        setupTrade();
    }

    private void setupTrade() {
        // 거래 목록 설정
        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack ingredient1 = new ItemStack(Material.IRON_INGOT, 6); // 철 6개
        MerchantRecipe recipe1 = new MerchantRecipe(Scroll, 0, 99999, false);
        recipe1.addIngredient(ingredient1);
        recipes.add(recipe1);

        ItemStack ingredient2 = new ItemStack(Material.GOLD_INGOT, 4);
        MerchantRecipe recipe2 = new MerchantRecipe(Scroll, 0, 99999, false);
        recipe2.addIngredient(ingredient2);
        recipes.add(recipe2);

        ItemStack ingredient3 = new ItemStack(Material.DIAMOND, 1);
        MerchantRecipe recipe3 = new MerchantRecipe(Scroll, 0, 99999, false);
        recipe3.addIngredient(ingredient3);
        recipes.add(recipe3);

        npc.setRecipes(recipes); // NPC에 거래 목록 설정
    }

    // NPC를 제거하는 메서드
    public void remove() {
        npc.remove();
    }
}
