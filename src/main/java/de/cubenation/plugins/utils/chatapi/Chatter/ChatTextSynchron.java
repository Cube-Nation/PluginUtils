package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.command.CommandSender;

public class ChatTextSynchron {
    public static void chat(CommandSender sender, String message) {
        if (sender == null) {
            throw new NullPointerException("sender is null");
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
