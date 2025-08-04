package net.salju.curse.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.salju.curse.CurseManager;

public class CheckCurseCommand {

    private static final String LITERAL = "iscursed";
    private static final String TARGET_DESCRIPTION = "target";
    private static final String IS_CURSED_MESSAGE_KEY = "commands.curse.iscursed.positive";
    private static final String NOT_CURSED_MESSAGE_KEY = "commands.curse.iscursed.negative";
    private static final String ERROR_MESSAGE_KEY = "commands.curse.iscursed.error";

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> command = dispatcher.register(
                Commands.literal(LITERAL)
                .requires((source) -> source.hasPermission(2))
                        .executes(CheckCurseCommand::checkSender)
                        .then(Commands.argument(TARGET_DESCRIPTION, EntityArgument.entities())
                                .executes((CheckCurseCommand::checkPlayer))
                )
        );
    }

    private static int checkSender(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer sender = context.getSource().getPlayerOrException();
        checkStatusAndReportResult(sender, context.getSource());
        return 0;
    }

    private static int checkPlayer(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Entity target = EntityArgument.getEntity(context, TARGET_DESCRIPTION);
        CommandSourceStack source = context.getSource();

        if (target instanceof ServerPlayer player) {
            checkStatusAndReportResult(player, source);
            return 0;
        } else {
            source.sendFailure(Component.translatable(ERROR_MESSAGE_KEY, target.getName()));
            return 1;
        }
    }

    private static void checkStatusAndReportResult(ServerPlayer player, CommandSourceStack source) {
        Component message;
        if (CurseManager.isCursed(player)) {
            message = Component.translatable(IS_CURSED_MESSAGE_KEY, player.getName());
        } else {
            message = Component.translatable(NOT_CURSED_MESSAGE_KEY, player.getName());
        }
        source.sendSuccess(
                () -> message,
                true // allow logging
        );
    }

}
