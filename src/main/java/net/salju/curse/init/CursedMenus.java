//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.salju.curse.init;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.salju.curse.CurseMod;
import net.salju.curse.gui.CurseGuiMenu;

public class CursedMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CurseMod.MODID);;

    public static final RegistryObject<MenuType<CurseGuiMenu>> CURSE_GUI = MENUS.register(
            "curse_gui", () -> new MenuType<>(CurseGuiMenu::new, FeatureFlags.DEFAULT_FLAGS)
    );
}
