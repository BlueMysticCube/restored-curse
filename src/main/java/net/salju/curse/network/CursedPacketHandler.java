package net.salju.curse.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.salju.curse.CurseMod;

public final class CursedPacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    private static int messagesRegistered = 0;

    private CursedPacketHandler() {
    }

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(CurseMod.MODID, "curse"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    @SuppressWarnings("ValueOfIncrementOrDecrementUsed")
    public static void registerMessages(FMLCommonSetupEvent event) {
        CHANNEL.registerMessage(
                messagesRegistered++,
                CursedMessage.class,
                CursedMessage::encoder,
                CursedMessage::decoder,
                CursedMessage::messageConsumer
        );
    }
}
