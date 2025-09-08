package net.salju.curse.init;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider.Factory;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.salju.curse.CurseMod;
import net.salju.curse.data.CursedLootModifierProvider;
import net.salju.curse.data.CursedLootTableProvider;
import net.salju.curse.data.EntityTypeTagsProvider;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public final class CursedDataGen {

    private CursedDataGen() {
    }

    public static void register(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> holder = event.getLookupProvider();
        PackOutput packOutput = generator.getPackOutput();

        // register loot table datagen
        generator.addProvider(true, new CursedLootTableProvider(generator.getPackOutput()));


        //generator.addProvider(event.includeServer(), new EntityTypeTagsProvider(packOutput, holder, fileHelper));

        // register global loot modifiers datagen
        generator.addProvider(
                // Tell generator to run only when server data are generating
                event.includeServer(),
                (Factory<CursedLootModifierProvider>) CursedLootModifierProvider::new
        );
    }
}
