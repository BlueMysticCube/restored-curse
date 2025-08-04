//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.salju.curse.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.salju.curse.CurseMod;
import net.salju.curse.init.CursedConfig;
import net.salju.curse.network.CursedMessage;
import net.salju.curse.network.CursedPacketHandler;

public class CurseGuiScreen extends AbstractContainerScreen<CurseGuiMenu> {

    public static final Component TITLE_TEXT = Component.translatable("gui.curse.curse_gui.label_title");

    private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(CurseMod.MODID, "textures/screens/curse_gui.png");
    private static final Style RUNES = Style.EMPTY.withFont(ResourceLocation.withDefaultNamespace("alt"));

    private static final Component[] RUNES_TEXT_ELEMENTS = new Component[] {
            Component.literal("The Cursed Urge of Determination").withStyle(RUNES),
            Component.literal("Everlasting With Sacred Hatred").withStyle(RUNES),
            Component.literal("You Can Do Anything No Matter The").withStyle(RUNES),
            Component.literal("Difficulty With The Cursed Urge").withStyle(RUNES)
    };

    private static final String DAMAGE_INFO_KEY = "gui.curse.curse_gui.label_player_damage";
    private static final Component FIRE_INFO = Component.translatable("gui.curse.curse_gui.label_fire");
    private static final Component ANGRY_INFO = Component.translatable("gui.curse.curse_gui.label_neutrals");
    private static final Component SLEEP_INFO = Component.translatable("gui.curse.curse_gui.label_sleep");
    private static final String EXP_INFO_KEY = "gui.curse.curse_gui.label_xp";
    private static final Component DROPS_INFO = Component.translatable("gui.curse.curse_gui.label_drops");

    private static final int TEXT_COLOR = 0x404040;
    private static final int MAX_SHOWABLE_PERCENTAGE = 999;

    private static final String PERCENTAGE_LITERAL = "%";

    public CurseGuiScreen(CurseGuiMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageWidth = 195;
        this.imageHeight = 165;
    }

    @Override
    public void init() {
        super.init();
        Button yes = Button.builder(
                CommonComponents.GUI_YES,
                (button) -> {
                    CursedPacketHandler.CHANNEL.sendToServer(new CursedMessage(true));
                    this.onClose();
                }).bounds(this.leftPos + 90, this.topPos + 166, 50, 20).build();
        this.addRenderableWidget(yes);

        Button no = Button.builder(
                CommonComponents.GUI_NO,
                (button) -> {
                    CursedPacketHandler.CHANNEL.sendToServer(new CursedMessage(false));
                    this.onClose();
                }).bounds(this.leftPos + 140, this.topPos + 166, 50, 20).build();
        this.addRenderableWidget(no);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(
                texture,
                this.leftPos,
                this.topPos,
                0.0F,
                0.0F,
                this.imageWidth,
                this.imageHeight,
                this.imageWidth,
                this.imageHeight
        );
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(this.font, RUNES_TEXT_ELEMENTS[0], 15, 6, TEXT_COLOR, false);
        graphics.drawString(this.font, RUNES_TEXT_ELEMENTS[1], 24, 34, TEXT_COLOR, false);
        graphics.drawString(this.font, RUNES_TEXT_ELEMENTS[2], 15, 147, TEXT_COLOR, false);
        graphics.drawString(this.font, RUNES_TEXT_ELEMENTS[3], 24, 156, TEXT_COLOR, false);

        graphics.drawString(this.font, TITLE_TEXT, 31, 20, TEXT_COLOR, false);

        if (CursedConfig.DEATH.get() > 100) {
            // make sure that the number does not overflow on the screen
            int moreDamagePercentage = Math.min(MAX_SHOWABLE_PERCENTAGE, CursedConfig.DEATH.get() - 100);
            graphics.drawString(
                    this.font,
                    //CurseManager.createComp(DAMAGE_INFO_KEY, moreDamagePercentage, "%"),
                    Component.translatable(DAMAGE_INFO_KEY, moreDamagePercentage, PERCENTAGE_LITERAL),
                    22,
                    48,
                    TEXT_COLOR,
                    false
            );
        }

        if (CursedConfig.FIRE.get()) {
            graphics.drawString(this.font, FIRE_INFO, 22, 65, TEXT_COLOR, false);
        }

        if (CursedConfig.ANGRY.get()) {
            graphics.drawString(this.font, ANGRY_INFO, 22, 82, TEXT_COLOR, false);
        }

        if (CursedConfig.SLEEP.get()) {
            graphics.drawString(this.font, SLEEP_INFO, 22, 99, TEXT_COLOR, false);
        }

        if (CursedConfig.EXP.get() > 100) {
            int expMuliplierPercentage = Math.min(MAX_SHOWABLE_PERCENTAGE, CursedConfig.EXP.get());
            graphics.drawString(
                    this.font,
                    //CurseManager.createComp(EXP_INFO_KEY, expMuliplierPercentage, "%"),
                    Component.translatable(EXP_INFO_KEY, expMuliplierPercentage, PERCENTAGE_LITERAL),
                    22,
                    116,
                    TEXT_COLOR,
                    false
            );
        }

        if (CursedConfig.DROPS.get()) {
            graphics.drawString(this.font, DROPS_INFO, 22, 133, 4210752, false);
        }

    }
}
