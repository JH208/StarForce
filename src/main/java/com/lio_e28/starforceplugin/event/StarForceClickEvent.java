package com.lio_e28.starforceplugin.event;

import com.lio_e28.starforceplugin.game.EnhancementManager;
import com.lio_e28.starforceplugin.gui.StarForceGUI;
import com.lio_e28.starforceplugin.item.StarForceItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class StarForceClickEvent implements Listener {

    @EventHandler
    public void rightClick(PlayerInteractEvent event) {
        // 이벤트가 오른쪽 클릭에 의해 발생하는지 확인
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && event.getItem() != null && event.getItem().isSimilar(StarForceItem.StarForce)) {
            event.setCancelled(true);

            Player player = event.getPlayer();
            StarForceGUI.openStarForceGUI(player);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();

        if (inventory == null || inventory.getHolder() != null || !event.getView().getTitle().equals(ChatColor.YELLOW + "강화 창")) {
            return;
        }

        int clickedSlot = event.getRawSlot();

        if (clickedSlot == 5) { // 강화 버튼 슬롯 클릭 확인
            event.setCancelled(true);

            // 강화의 주문서 아이템 찾기
            ItemStack scrollItem = null;
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null && item.isSimilar(StarForceItem.Scroll)) {
                    scrollItem = item;
                    break;
                }
            }

            // 강화의 주문서가 있으면 소모, 없으면 메시지 출력 후 리턴
            if (scrollItem != null && scrollItem.getAmount() > 0) {
                scrollItem.setAmount(scrollItem.getAmount() - 1);

                ItemStack itemToEnhance = inventory.getItem(2); // 강화할 아이템은 2번 슬롯에 있어야 함
                if (itemToEnhance == null || !EnhancementManager.isEnhanceableItem(itemToEnhance)) {
                    player.sendMessage(ChatColor.RED + "강화할 수 없는 아이템입니다.");
                    event.setCancelled(true); // 강화할 수 없는 아이템 클릭 시 이벤트 취소
                    return;
                }

                // 강화 실행
                int newGrade = EnhancementManager.attemptEnhancement(player, itemToEnhance);
                inventory.setItem(2, null); // 강화할 아이템 슬롯 비우기

                if (newGrade > 0) { // 강화 성공 또는 실패, 파괴되지 않은 경우
                    ItemStack enhancedItem = EnhancementManager.getEnhancedItem(itemToEnhance, newGrade);
                    if (enhancedItem != null) {
                        inventory.setItem(7, enhancedItem); // 강화된 아이템을 7번 슬롯에 배치
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "강화의 주문서가 필요합니다.");
                return;
            }


        } else if (clickedSlot != 2 && clickedSlot != 7) {
            // 2번과 7번 슬롯 이외의 슬롯에서의 아이템 이동을 막음
            event.setCancelled(true);
        }
    }



    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        // 강화 창이 닫힐 때, 2번 자리에 있는 아이템이 있다면 플레이어 인벤토리로 이동
        if (inventory != null && inventory.getHolder() == null && event.getView().getTitle().equals(ChatColor.YELLOW + "강화 창")) {
            ItemStack itemToMove = inventory.getItem(2); // 2번 자리에 있는 아이템
            if (itemToMove != null) {
                player.getInventory().addItem(itemToMove);
            }
            // 강화 결과물이 있다면 플레이어 인벤토리로 이동
            ItemStack enhancedItem = inventory.getItem(7); // 7번 자리에 있는 강화 결과물
            if (enhancedItem != null) {
                player.getInventory().addItem(enhancedItem);
            }
        }
    }


}
