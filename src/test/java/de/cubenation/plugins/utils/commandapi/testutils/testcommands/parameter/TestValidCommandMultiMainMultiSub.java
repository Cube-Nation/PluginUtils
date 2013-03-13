package de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMultiMainMultiSub {
    private TestPlugin plugin;

    public TestValidCommandMultiMainMultiSub(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = { "test1", "test2" }, sub = { "bla1", "bla2" })
    public void testMultiMainMultiSubCommand(Player player, String[] args) {
        plugin.doSomeThing("testMultiMainMultiSubCommand");
    }
}
