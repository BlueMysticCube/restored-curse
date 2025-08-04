//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.salju.curse.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.salju.curse.CurseMod;

public final class CursedTags {
    public static final TagKey<EntityType<?>> NOT_ANGRY = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(CurseMod.MODID, "no_angry"));

    private CursedTags() {
    }
}
