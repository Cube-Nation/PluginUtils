package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.entity.Player;

public class ChatTextSynchron {
    public static void chat(Player player, String message) {
        if (player == null) {
            throw new NullPointerException("player is null");
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
                player.sendMessage(msg);
            }
        } else {
            player.sendMessage(message);
        }
    }
}
