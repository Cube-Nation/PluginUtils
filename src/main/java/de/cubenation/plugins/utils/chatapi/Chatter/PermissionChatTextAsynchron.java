package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.permissionapi.PermissionInterface;

public class PermissionChatTextAsynchron {
    public static void chat(JavaPlugin plugin, final PermissionInterface permissionInterface, final CommandSender sender, final String message,
            final String[] permissions) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Thread("ChatService->PermissionChatTextAsynchron") {
            @Override
            public void run() {
                if (sender instanceof Player) {
                    if (permissionInterface != null) {
                        boolean found = true;
                        for (String permission : permissions) {
                            if (!permissionInterface.hasPermission((Player) sender, permission)) {
                                found = false;
                                break;
                            }
                        }

                        if (found) {
                            ChatTextSynchron.chat(sender, message);
                        }
                    } else {
                        boolean found = true;
                        for (String permission : permissions) {
                            if (!sender.hasPermission(permission)) {
                                found = false;
                                break;
                            }
                        }

                        if (found) {
                            ChatTextSynchron.chat(sender, message);
                        }
                    }
                } else {
                    ChatTextSynchron.chat(sender, message);
                }
            }
        });
    }
}
