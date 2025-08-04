//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.salju.curse;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

public class CurseManager {

    private static final String IS_CURSED_KEY = "isCursed";

    public static boolean isCursed(Player target) {
        return target.getPersistentData().getBoolean(IS_CURSED_KEY);
    }

    public static void setCursed(Player player, boolean check) {
        player.getPersistentData().putBoolean(IS_CURSED_KEY, check);
    }

    public static Item getItem(EntityType type) {
        return getMap().getOrDefault(type, Items.AIR);
    }

    public static Map<EntityType, Item> getMap() { // TODO use loot tables like 1.21 version
        Map<EntityType, Item> typeMap = new HashMap();
        typeMap.put(EntityType.COW, Items.LEATHER);
        typeMap.put(EntityType.CHICKEN, Items.FEATHER);
        typeMap.put(EntityType.ZOMBIE, Items.SLIME_BALL);
        typeMap.put(EntityType.DROWNED, Items.CLAY_BALL);
        typeMap.put(EntityType.HUSK, Items.SAND);
        typeMap.put(EntityType.SKELETON, Items.ARROW);
        typeMap.put(EntityType.STRAY, Items.ARROW);
        typeMap.put(EntityType.CREEPER, Items.GUNPOWDER);
        typeMap.put(EntityType.SPIDER, Items.FERMENTED_SPIDER_EYE);
        typeMap.put(EntityType.CAVE_SPIDER, Items.FERMENTED_SPIDER_EYE);
        typeMap.put(EntityType.ENDERMAN, Items.ECHO_SHARD);
        typeMap.put(EntityType.WITCH, Items.PHANTOM_MEMBRANE);
        typeMap.put(EntityType.EVOKER, Items.DIAMOND);
        typeMap.put(EntityType.PILLAGER, Items.EMERALD);
        typeMap.put(EntityType.VINDICATOR, Items.LAPIS_LAZULI);
        typeMap.put(EntityType.MAGMA_CUBE, Items.GLOWSTONE_DUST);
        typeMap.put(EntityType.BLAZE, Items.BLAZE_ROD);
        typeMap.put(EntityType.WITHER_SKELETON, Items.NETHERITE_SCRAP);
        typeMap.put(EntityType.PIGLIN_BRUTE, Items.NETHERITE_SCRAP);
        typeMap.put(EntityType.ZOMBIFIED_PIGLIN, Items.GOLD_INGOT);
        typeMap.put(EntityType.PIGLIN, Items.GOLD_INGOT);
        return typeMap;
    }
}
