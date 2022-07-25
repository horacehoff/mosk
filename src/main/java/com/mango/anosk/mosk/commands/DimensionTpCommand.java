package com.mango.anosk.mosk.commands;

import com.mango.anosk.mosk.util.IEntityDataSaver;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.EntityDataObject;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.dimension.DimensionTypes;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Collection;

public class DimensionTpCommand {
    public static LiteralCommandNode<ServerCommandSource> src;

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        src = dispatcher.register(CommandManager.literal("dimensiontp").requires(source -> source.hasPermissionLevel(2)).executes(DimensionTpCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        IEntityDataSaver player = (IEntityDataSaver)context.getSource().getPlayer().getCommandSource();
        return 1;
    }
}
