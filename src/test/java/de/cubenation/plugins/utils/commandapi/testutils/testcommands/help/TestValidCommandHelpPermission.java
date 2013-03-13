package de.cubenation.plugins.utils.commandapi.testutils.testcommands.help;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissions;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelpPermission {
    private TestPlugin plugin;

    public TestValidCommandHelpPermission(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", help = "this is a help test")
    @CommandPermissions("test.per")
    public void testHelpCommand(Player player, String[] args) {
        plugin.doSomeThing("testHelpCommand");
    }
}
