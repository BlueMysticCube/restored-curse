package net.salju.curse.data;

import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.salju.curse.CurseMod;

import java.util.function.BiConsumer;

public class CursedLoot implements LootTableSubProvider {

    private static final String EXTRA_ITEM_LOCATION_PATH = "extra_items/";

    @Override
    public void generate(BiConsumer<ResourceLocation, Builder> consumer) {
        for (ExtraDropDefinition definition : DefaultExtraLoot.DEFAULT_EXTRA_DROPS) {
            curseExtraItem(consumer, definition);
        }
    }

    public static ResourceLocation getLootTableLocation(EntityType<?> entityType) {
        ResourceLocation entityRegistryId = ForgeRegistries.ENTITY_TYPES.getResourceKey(entityType).orElseThrow().location();
        return ResourceLocation.fromNamespaceAndPath(
                CurseMod.MODID,
                EXTRA_ITEM_LOCATION_PATH + entityRegistryId.getPath()
        );
    }

    private void curseExtraItem(BiConsumer<ResourceLocation, Builder> consumer, ExtraDropDefinition definition) {
        consumer.accept(getLootTableLocation(definition.entityType()), cursedExtraItemTable(definition));
    }

    private LootTable.Builder cursedExtraItemTable(ExtraDropDefinition definition) {
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1));
        for (ItemLike extraItem : definition.extraItems()) {
            poolBuilder.add(LootItem.lootTableItem(extraItem)
                    .apply(LootingEnchantFunction.lootingMultiplier(
                            UniformGenerator.between(0.0F, 1.0F))
                    )
                    .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(
                            definition.chance(), definition.lootingMultiplier()
                    ))
            );
        }

        return LootTable.lootTable().withPool(poolBuilder);
    }
}
