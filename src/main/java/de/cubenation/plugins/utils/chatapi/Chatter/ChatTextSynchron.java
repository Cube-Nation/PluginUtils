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

        player.sendMessage(message);
    }
}
