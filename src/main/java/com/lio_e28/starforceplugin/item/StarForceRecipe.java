package com.lio_e28.starforceplugin.item;

import com.lio_e28.starforceplugin.StarForcePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import static com.lio_e28.starforceplugin.item.StarForceItem.StarForce;

public class StarForceRecipe {

    // 스타포스 아이템 레시피를 등록하는 메서드
    public static void registerStarForceRecipe(StarForcePlugin plugin) {
        // 스타포스 아이템 레시피 생성
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "star_force_item"), StarForceItem.StarForce);

        // 레시피의 형태 설정 (다이아몬드 블록을 가로로 2개 놓기)
        recipe.shape("##", "##"); // 다이아몬드 블록 2x2 모양으로 배치합니다.

        // 레시피에 사용될 재료 설정
        recipe.setIngredient('#', Material.DIAMOND_BLOCK); // 다이아몬드 블록을 재료로 사용합니다.

        // 레시피 등록
        Bukkit.addRecipe(recipe);
    }
}

