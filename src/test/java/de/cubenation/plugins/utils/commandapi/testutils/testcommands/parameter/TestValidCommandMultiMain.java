package de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter;

import org.bukkit.command.CommandSender;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMultiMain {
    private TestPlugin plugin;

    public TestValidCommandMultiMain(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = { "test1", "test2" })
    public void testMultiMainCommand(CommandSender player, String[] args) {
        plugin.doSomeThing("testMultiMainCommand");
    }
}
