package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandParameterString {
    private TestPlugin plugin;

    public TestValidCommandParameterString(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", min = 1, max = 1)
    public void testStringCommand(Player player, String arg) {
        plugin.doSomeThing("testStringCommand");
    }
}
