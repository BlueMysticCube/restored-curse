package net.salju.curse.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.registries.ForgeRegistries;
import net.salju.curse.CurseMod;
import net.salju.curse.loot.AddTableLootModifier;
import net.salju.curse.loot.CurseCondition;

public class CursedLootModifierProvider extends GlobalLootModifierProvider {

    public CursedLootModifierProvider(PackOutput output) {
        super(output, CurseMod.MODID);
    }

    @Override
    protected void start() {
        for (ExtraDropDefinition definition : DefaultExtraLoot.DEFAULT_EXTRA_DROPS) {
            ResourceLocation entityRegistryId = ForgeRegistries.ENTITY_TYPES.getResourceKey(definition.entityType()).orElseThrow().location();

            this.add(
                    entityRegistryId.getPath(),
                    new AddTableLootModifier(
                            new LootItemCondition[]{
                                    new CurseCondition(),
                                    LootTableIdCondition.builder(definition.entityType().getDefaultLootTable()).build()
                            },
                            CursedLoot.getLootTableLocation(definition.entityType())
                    )
            );
        }
    }
}
