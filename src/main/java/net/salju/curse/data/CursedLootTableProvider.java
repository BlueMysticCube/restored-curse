package net.salju.curse.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;

public final class CursedLootTableProvider extends LootTableProvider {

    public CursedLootTableProvider(PackOutput output) {
        super(output, null, null);
    }

    @Override
    public List<SubProviderEntry> getTables() {
        return List.of(
                new SubProviderEntry(CursedLoot::new, LootContextParamSets.ENTITY)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {
    }

}
