package net.salju.curse.data;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;

import java.util.Collection;
import java.util.List;

record ExtraDropDefinition(
        EntityType<?> entityType,
        Collection<ItemLike> extraItems,
        float chance,
        float lootingMultiplier
) {
    protected ExtraDropDefinition(
            EntityType<?> entityType,
            Collection<ItemLike> extraItems,
            float chance,
            float lootingMultiplier
    ) {
        this.entityType = entityType;
        this.extraItems = List.copyOf(extraItems);
        this.chance = chance;
        this.lootingMultiplier = lootingMultiplier;
    }

    @Override
    public Collection<ItemLike> extraItems() {
        return List.copyOf(extraItems);
    }
}
