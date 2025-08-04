//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.salju.curse.init;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.salju.curse.gui.CurseGuiScreen;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT)
public final class CursedScreens {

    private CursedScreens() {
    }

    @SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(CursedMenus.CURSE_GUI.get(), CurseGuiScreen::new));
    }
}
