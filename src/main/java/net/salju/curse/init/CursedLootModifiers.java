package net.salju.curse.init;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegistryObject;
import net.salju.curse.CurseMod;
import net.salju.curse.loot.AddTableLootModifier;

public final class CursedLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(
            Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, CurseMod.MODID
    );

    public static final RegistryObject<? extends Codec<? extends IGlobalLootModifier>> ADD_TABLE = LOOT_MODIFIERS.register(
            "add_table",
            () -> AddTableLootModifier.CODEC
    );

    private CursedLootModifiers() {
    }
}
