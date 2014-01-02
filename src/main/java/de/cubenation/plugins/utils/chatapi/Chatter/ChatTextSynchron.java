package de.cubenation.plugins.utils.chatapi.Chatter;

import java.util.logging.Logger;

import org.bukkit.command.CommandSender;

public class ChatTextSynchron {
    public static void chat(CommandSender sender, String message) {
        if (sender == null) {
            Logger.getAnonymousLogger().warning("sender is null");
            return;
        }

        if (message == null || message.isEmpty()) {
            return;
        }

        message = message.replace("\r\n", "\n").replace("\r", "\n");

        if (message.contains("\n")) {
            for (String msg : message.split("\n")) {
                if (msg.trim().isEmpty()) {
                    continue;
                }
                sender.sendMessage(msg);
            }
        } else {
            sender.sendMessage(message);
        }
    }
}
