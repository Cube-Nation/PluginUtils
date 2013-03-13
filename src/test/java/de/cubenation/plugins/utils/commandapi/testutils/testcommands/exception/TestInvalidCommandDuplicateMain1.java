package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandDuplicateMain1 {
    private TestPlugin plugin;

    public TestInvalidCommandDuplicateMain1(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    public void testOneMainCommand(Player player, String[] args) {
        plugin.doSomeThing("testOneMainCommand");
    }

    @Command(main = "test1")
    public void testTwoMainCommand(Player player, String[] args) {
        plugin.doSomeThing("testTwoMainCommand");
    }
}
