package net.salju.curse.data;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.List;

final class DefaultExtraLoot {

    private static final float EXTRA_DROP_CHANCE = 0.15f;
    private static final float RARE_EXTRA_DROP_CHANCE = 0.15f * 0.15f;
    private static final float LOOTING_MULTIPLIER = 0.02f;
    private static final float RARE_LOOTING_MULTIPLIER = 0.01f;

    static final List<ExtraDropDefinition> DEFAULT_EXTRA_DROPS = List.of(
            //Vanilla Animals
            extra(EntityType.COW, Items.LEATHER),
            extra(EntityType.PIG, Items.PORKCHOP),
            extra(EntityType.CHICKEN, Items.FEATHER),
            extra(EntityType.SQUID, Items.BLACK_DYE),
            extra(EntityType.GLOW_SQUID, Items.INK_SAC),
            rare(EntityType.IRON_GOLEM, Items.IRON_BLOCK),
            extra(EntityType.HORSE, Items.LEATHER),
            extra(EntityType.MULE, Items.LEATHER),
            extra(EntityType.WOLF, Items.BONE),
            extra(EntityType.PANDA, Items.BAMBOO),
            extra(EntityType.CAT, Items.STRING),
            extra(EntityType.BEE, Items.HONEYCOMB),
            extra(EntityType.BAT, Items.LEATHER),
            extra(EntityType.VILLAGER, Items.EMERALD),
            extra(EntityType.POLAR_BEAR, Items.LEATHER),


            //Vanilla Monster
            rare(EntityType.ZOMBIE, Items.DIAMOND),
            rare(EntityType.DROWNED, Items.NAUTILUS_SHELL),
            extra(EntityType.HUSK, Items.SAND),
            extra(EntityType.SKELETON, Items.ARROW),
            extra(EntityType.STRAY, Items.BLUE_ICE),
            extra(EntityType.CREEPER, Items.TNT),
            extra(EntityType.SPIDER, Items.COBWEB),
            extra(EntityType.CAVE_SPIDER, Items.STRING),
            rare(EntityType.ENDERMAN, Items.ENDER_EYE),
            extra(EntityType.WITCH, Items.RABBIT_FOOT),
            extra(EntityType.EVOKER, Items.DIAMOND),
            extra(EntityType.PILLAGER, Items.EMERALD),
            extra(EntityType.VINDICATOR, Items.IRON_INGOT),

            //Nether Monster
            extra(EntityType.MAGMA_CUBE, Items.GLOWSTONE_DUST),
            extra(EntityType.BLAZE, Items.BLAZE_POWDER),
            extra(EntityType.WITHER_SKELETON, Items.COAL_BLOCK),
            rare(EntityType.PIGLIN_BRUTE, Items.NETHERITE_SCRAP),
            extra(EntityType.ZOMBIFIED_PIGLIN, Items.GOLD_INGOT),
            extra(EntityType.PIGLIN, Items.GOLD_INGOT)
    );

    private DefaultExtraLoot() {
    }

    private static ExtraDropDefinition extra(EntityType<?> entityType, ItemLike item) {
        return new ExtraDropDefinition(
                entityType,
                List.of(item),
                EXTRA_DROP_CHANCE,
                LOOTING_MULTIPLIER
        );
    }

    private static ExtraDropDefinition rare(EntityType<?> entityType, ItemLike item) {
        return new ExtraDropDefinition(
                entityType,
                List.of(item),
                RARE_EXTRA_DROP_CHANCE,
                RARE_LOOTING_MULTIPLIER
        );
    }

}
