//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.salju.curse.gui;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.salju.curse.init.CursedMenus;

public class CurseGuiMenu extends AbstractContainerMenu {

    public static final Component TITLE_TEXT = Component.translatable("gui.curse.curse_gui.label_title");

    public CurseGuiMenu(int id, Inventory inv) {
        super(CursedMenus.CURSE_GUI.get(), id);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }
}
