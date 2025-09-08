//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.salju.curse;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public final class CursedTags {
    //List of entity tags. Example:
    //public static final TagKey<EntityType<?>> TAGNAME = tag("tagname");
    public static final TagKey<EntityType<?>> NOT_ANGRY = tag("not_angry");

    //Generates Entity Tags from above
    private static TagKey<EntityType<?>> tag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(CurseMod.MODID, name));
    }
}
