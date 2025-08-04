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

public class CurseChangeCommand {

    private static final String CURSE_LITERAL = "curse";
    private static final String BLESS_LITERAL = "bless";
    private static final String DEFAULT_TARGET_DESCRIPTION = "target";
    private static final String CURSE_SUCCESS_KEY = "commands.curse.curse.success";
    private static final String CURSE_FAILURE_KEY = "commands.curse.curse.error";
    private static final String BLESS_SUCCESS_KEY = "commands.curse.bless.success";
    private static final String BLESS_FAILURE_KEY = "commands.curse.bless.error";

    private final String literal;
    private final String targetDescription;
    private final boolean curse;
    private final String successMessageKey;
    private final String failureMessageKey;

    private CurseChangeCommand(
            String commandLiteral,
            String targetDescription,
            boolean curse,
            String successMessageKey,
            String failureMessageKey
    ) {
        this.literal = commandLiteral;
        this.targetDescription = targetDescription;
        this.curse = curse;
        this.successMessageKey = successMessageKey;
        this.failureMessageKey = failureMessageKey;
    }

    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> command = dispatcher.register(
                Commands.literal(this.literal)
                .requires((source) -> source.hasPermission(2))
                        .executes(this::curseSender)
                        .then(Commands.argument(this.targetDescription, EntityArgument.entities())
                                .executes((this::cursePlayer))
                )
        );
    }

    private int curseSender(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer sender = context.getSource().getPlayerOrException();
        setStatusAndReportSuccess(sender, context.getSource());
        return 0;
    }

    private int cursePlayer(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Entity target = EntityArgument.getEntity(context, this.targetDescription);
        CommandSourceStack source = context.getSource();

        if (target instanceof ServerPlayer player) {
            setStatusAndReportSuccess(player, source);
            return 0;
        } else {
            source.sendFailure(Component.translatable(this.failureMessageKey, target.getName()));
            return 1;
        }
    }

    private void setStatusAndReportSuccess(ServerPlayer player, CommandSourceStack source) {
        if (CurseManager.isCursed(player) != this.curse) {
            // curse status can and should be changed
            CurseManager.setCursed(player, this.curse);
            source.sendSuccess(
                    () -> Component.translatable(this.successMessageKey, player.getName()),
                    true // allow logging
            );
        }
        // otherwise do nothing, like when changing game mode
    }

    public static CurseChangeCommand curse() {
        return new CurseChangeCommand(
                CURSE_LITERAL, DEFAULT_TARGET_DESCRIPTION, true, CURSE_SUCCESS_KEY, CURSE_FAILURE_KEY
        );
    }

    public static CurseChangeCommand bless() {
        return new CurseChangeCommand(
                BLESS_LITERAL, DEFAULT_TARGET_DESCRIPTION, false, BLESS_SUCCESS_KEY, BLESS_FAILURE_KEY
        );
    }
}
