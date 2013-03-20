package de.cubenation.plugins.utils.chatapi.Chatter;

import java.util.ResourceBundle;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.permissionapi.PermissionInterface;

public class PermissionChatResourceAsynchron {
    public static void chat(final JavaPlugin plugin, final ResourceBundle resource, final PermissionInterface permissionInterface, final Player player,
            final String resourceString, final String[] permissions, final Object... parameter) {
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
                        ChatResourceSynchron.chat(plugin, resource, player, resourceString, parameter);
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
                        ChatResourceSynchron.chat(plugin, resource, player, resourceString, parameter);
                    }
                }
            }
        });
    }
}
