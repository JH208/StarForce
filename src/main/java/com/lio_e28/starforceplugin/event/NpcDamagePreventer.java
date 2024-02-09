package com.lio_e28.starforceplugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Villager;

public class NpcDamagePreventer implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // 이벤트에서 피해를 받는 엔티티가 우리의 NPC인지 확인
        if (event.getEntity() instanceof Villager) {
            Villager villager = (Villager) event.getEntity();
            // NPC의 이름이나 다른 속성을 사용하여 NPC를 식별할 수 있습니다.
            // 여기서는 커스텀 이름을 확인하겠습니다.
            if (villager.getCustomName() != null && villager.getCustomName().equals("강화 재료 상인")) {
                // 이벤트 취소로 피해를 방지
                event.setCancelled(true);
            }
        }
    }
}
