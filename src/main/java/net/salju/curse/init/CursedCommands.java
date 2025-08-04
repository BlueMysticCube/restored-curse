package net.salju.curse.init;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.salju.curse.command.CheckCurseCommand;
import net.salju.curse.command.CurseChangeCommand;

@EventBusSubscriber
public class CursedCommands {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> commandDispatcher = event.getDispatcher();

        CurseChangeCommand.curse().register(commandDispatcher);
        CurseChangeCommand.bless().register(commandDispatcher);
        CheckCurseCommand.register(commandDispatcher);
    }

}
