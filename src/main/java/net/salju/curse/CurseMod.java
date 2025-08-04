//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.salju.curse;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.salju.curse.init.CursedConfig;
import net.salju.curse.init.CursedDataGen;
import net.salju.curse.init.CursedLootConditions;
import net.salju.curse.init.CursedLootModifiers;
import net.salju.curse.init.CursedMenus;
import net.salju.curse.network.CursedPacketHandler;

@Mod(CurseMod.MODID)
public class CurseMod {
    public static final String MODID = "curse";
    private static final String CONFIG_FILE_NAME = "cursed-common.toml";

    public CurseMod() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        CursedMenus.MENUS.register(bus);
        CursedLootConditions.LOOT_CONDITIONS.register(bus);
        ModLoadingContext.get().registerConfig(Type.COMMON, CursedConfig.CONFIG, CONFIG_FILE_NAME);
        bus.addListener(CursedPacketHandler::registerMessages);

        // datagen
        CursedLootModifiers.LOOT_MODIFIERS.register(bus);
        bus.addListener(CursedDataGen::register);
    }
}

