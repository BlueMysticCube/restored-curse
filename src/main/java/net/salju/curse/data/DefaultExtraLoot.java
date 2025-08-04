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
            extra(EntityType.COW, Items.LEATHER),
            extra(EntityType.CHICKEN, Items.FEATHER),
            extra(EntityType.ZOMBIE, Items.SLIME_BALL),
            extra(EntityType.DROWNED, Items.CLAY_BALL),
            extra(EntityType.HUSK, Items.SAND),
            extra(EntityType.SKELETON, Items.ARROW),
            extra(EntityType.STRAY, Items.ARROW),
            extra(EntityType.CREEPER, Items.GUNPOWDER),
            extra(EntityType.SPIDER, Items.FERMENTED_SPIDER_EYE),
            extra(EntityType.CAVE_SPIDER, Items.FERMENTED_SPIDER_EYE),
            rare(EntityType.ENDERMAN, Items.ECHO_SHARD),
            extra(EntityType.WITCH, Items.PHANTOM_MEMBRANE),
            extra(EntityType.EVOKER, Items.DIAMOND),
            extra(EntityType.PILLAGER, Items.EMERALD),
            extra(EntityType.VINDICATOR, Items.LAPIS_LAZULI),
            extra(EntityType.MAGMA_CUBE, Items.GLOWSTONE_DUST),
            extra(EntityType.BLAZE, Items.BLAZE_ROD),
            rare(EntityType.WITHER_SKELETON, Items.NETHERITE_SCRAP),
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
