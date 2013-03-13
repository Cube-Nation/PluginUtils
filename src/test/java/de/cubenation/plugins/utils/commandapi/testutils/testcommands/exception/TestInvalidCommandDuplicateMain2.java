package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandDuplicateMain2 {
    private TestPlugin plugin;

    public TestInvalidCommandDuplicateMain2(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    public void testThreeMainCommand(Player player, String[] args) {
        plugin.doSomeThing("testThreeMainCommand");
    }
}
