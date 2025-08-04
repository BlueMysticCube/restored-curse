package net.salju.curse.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent.Context;
import net.salju.curse.CurseManager;

import java.util.function.Supplier;

public class CursedMessage {

    private static final Component CURSE_MESSAGE = Component.translatable("gui.curse.curse_message");

    private final boolean cursed;

    public CursedMessage(boolean cursed) {
        this.cursed = cursed;
    }

    public void encoder(FriendlyByteBuf buffer) {
        buffer.writeBoolean(this.cursed);
    }

    public static CursedMessage decoder(FriendlyByteBuf buffer) {
        return new CursedMessage(buffer.readBoolean());
    }

    public void messageConsumer(Supplier<Context> supply) {
        Context context = supply.get();
        context.enqueueWork(() -> {
            Player player = context.getSender();
            handleCurse(player);
        });
        context.setPacketHandled(true);
    }

    private static void handleCurse(Player player) {
        CurseManager.setCursed(player, true);
        if (!player.level().isClientSide()) {
            player.displayClientMessage(CURSE_MESSAGE, true);
        }
    }
}
