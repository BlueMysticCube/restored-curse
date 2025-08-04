package net.salju.curse.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.salju.curse.CurseMod;
import net.salju.curse.loot.CurseCondition;

import java.util.function.Supplier;

public class CursedLootConditions {
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, CurseMod.MODID);

    public static final Supplier<LootItemConditionType> CURSE_CONDITION = LOOT_CONDITIONS.register(
            "is_cursed", () -> new LootItemConditionType(new CurseCondition.Serializer())
    );
}
