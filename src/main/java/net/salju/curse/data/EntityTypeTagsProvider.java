package net.salju.curse.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.salju.curse.CurseMod;
import net.salju.curse.CursedTags;
import org.jetbrains.annotations.Nullable;


import java.util.concurrent.CompletableFuture;

public class EntityTypeTagsProvider extends net.minecraft.data.tags.EntityTypeTagsProvider {
    public EntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CurseMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //Examples:
        //Vanilla Tags:
        //this.tag(BlockTags.TAGNAME).add
        //      (REGISTRYCLASS.BLOCKNAME.get(),
        //      (REGISTRYCLASS.BLOCKNAME.get();
        //
        //Forge Tags:
        //this.tag(Tags.Blocks.TAGNAME).add
        //      (REGISTRYCLASS.BLOCKNAME.get(),
        //      (REGISTRYCLASS.BLOCKNAME.get();
        //
        //Custom Tags:
        //this.tag(EvenBetterEndTags.Blocks.TAGNAME).add
        //      (REGISTRYCLASS.BLOCKNAME.get(),
        //      (REGISTRYCLASS.BLOCKNAME.get());

        //this.tag(CursedTags.NOT_ANGRY).add(
        //        EntityType.IRON_GOLEM,
        //        EntityType.SNOW_GOLEM
        //        );

    }
}