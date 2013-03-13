package de.cubenation.plugins.utils.commandapi.testutils.testcommands.world;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandWorld1 {
    private TestPlugin plugin;

    public TestInvalidCommandWorld1(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", min = 0, max = 0)
    @World("world")
    public void testOneWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testOneWorldCommand");
    }
}
