package de.cubenation.plugins.utils;

import de.cubenation.plugins.utils.commandapi.CommandsManager;
import de.cubenation.plugins.utils.pluginapi.BasePlugin;

public class PluginUtils extends BasePlugin {
    @Override
    protected void postEnableActions() {
        CommandsManager.setOwnChatService(chatService);
    }
}
