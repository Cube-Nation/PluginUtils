package de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandMainSub2 {
    private TestPlugin plugin;

    public TestInvalidCommandMainSub2(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", sub = "bla")
    public void testThreeMainOneSubCommand(Player player, String[] args) {
        plugin.doSomeThing("testThreeMainOneSubCommand");
    }
}
