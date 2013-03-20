package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.permissionapi.PermissionInterface;

public class PermissionChatTextAsynchron {
    public static void chat(JavaPlugin plugin, final PermissionInterface permissionInterface, final Player player, final String message,
            final String[] permissions) {
        Bukkit.getScheduler().runTask(plugin, new Thread("ChatService->PermissionChatTextAsynchron") {
            @Override
            public void run() {
                if (permissionInterface != null) {
                    boolean found = true;
                    for (String permission : permissions) {
                        if (!permissionInterface.hasPermission(player, permission)) {
                            found = false;
                            break;
                        }
                    }

                    if (found) {
                        ChatTextSynchron.chat(player, message);
                    }
                } else {
                    boolean found = true;
                    for (String permission : permissions) {
                        if (!player.hasPermission(permission)) {
                            found = false;
                            break;
                        }
                    }

                    if (found) {
                        ChatTextSynchron.chat(player, message);
                    }
                }
            }
        });
    }
}
