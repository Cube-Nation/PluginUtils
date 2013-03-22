package de.cubenation.plugins.utils;

import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.chatapi.ChatService;
import de.cubenation.plugins.utils.commandapi.CommandsManager;

public class PluginUtils extends JavaPlugin {
    private ChatService chatService;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();

        chatService = new ChatService(this);
        CommandsManager.setOwnChatService(chatService);
    }
}
