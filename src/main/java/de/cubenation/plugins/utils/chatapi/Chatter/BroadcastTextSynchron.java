package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastTextSynchron {
    public static void chat(String message) {
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            ChatTextSynchron.chat(onlinePlayer, message);
        }
    }
}
