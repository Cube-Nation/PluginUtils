package de.cubenation.plugins.utils;

import de.cubenation.plugins.utils.commandapi.CommandsManager;
import de.cubenation.plugins.utils.pluginapi.BasePlugin;
import de.cubenation.plugins.utils.wrapperapi.WrapperManager;

public class PluginUtils extends BasePlugin {
    @Override
    protected void preEnableActions() {
        WrapperManager.setLogger(getLogger());
    }

    @Override
    protected void postEnableActions() {
        CommandsManager.setOwnChatService(chatService);
    }
}
