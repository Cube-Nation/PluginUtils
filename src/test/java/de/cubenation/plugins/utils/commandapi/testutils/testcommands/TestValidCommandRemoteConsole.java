package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.RemoteConsole;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandRemoteConsole {
    private TestPlugin plugin;

    public TestValidCommandRemoteConsole(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test")
    @RemoteConsole
    public void testRemoteConsoleCommand(RemoteConsoleCommandSender console, String[] args) {
        plugin.doSomeThing("testRemoteConsoleCommand");
    }
}
