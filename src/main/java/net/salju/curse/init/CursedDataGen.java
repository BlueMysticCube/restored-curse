package net.salju.curse.init;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider.Factory;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.salju.curse.data.CursedLootModifierProvider;
import net.salju.curse.data.CursedLootTableProvider;

@EventBusSubscriber
public final class CursedDataGen {

    private CursedDataGen() {
    }

    public static void register(GatherDataEvent event) {
        // register loot table datagen
        DataGenerator generator = event.getGenerator();
        generator.addProvider(true, new CursedLootTableProvider(generator.getPackOutput()));

        // register global loot modifiers datagen
        generator.addProvider(
                // Tell generator to run only when server data are generating
                event.includeServer(),
                (Factory<CursedLootModifierProvider>) CursedLootModifierProvider::new
        );
    }
}
