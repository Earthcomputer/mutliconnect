package net.earthcomputer.multiconnect.protocols.v1_12_2.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.SuggestionProviders;

import static com.mojang.brigadier.arguments.StringArgumentType.*;
import static net.earthcomputer.multiconnect.protocols.v1_12_2.command.Commands_1_12_2.*;

public class WhitelistCommand {

    public static void register(CommandDispatcher<ISuggestionProvider> dispatcher) {
        dispatcher.register(literal("whitelist")
            .then(literal("on")
                .executes(ctx -> 0))
            .then(literal("off")
                .executes(ctx -> 0))
            .then(literal("list")
                .executes(ctx -> 0))
            .then(literal("add")
                .then(argument("player", word())
                    .suggests(SuggestionProviders.ASK_SERVER)
                    .executes(ctx -> 0)))
            .then(literal("remove")
                .then(argument("player", word())
                    .suggests(SuggestionProviders.ASK_SERVER)
                    .executes(ctx -> 0)))
            .then(literal("reload")
                .executes(ctx -> 0)));
    }

}
