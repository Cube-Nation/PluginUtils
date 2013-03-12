package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMain {
    private TestPlugin plugin;

    public TestValidCommandMain(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    public void testOneMainCommand(Player player, String[] args) {
        plugin.doSomeThing("testOneMainCommand");
    }
}
