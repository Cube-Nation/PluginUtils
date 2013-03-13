package de.cubenation.plugins.utils.commandapi.testutils.testcommands.constructor;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandBiggerConsturctor {
    private TestPlugin plugin;
    private TestPlugin plugin1;

    public TestValidCommandBiggerConsturctor(TestPlugin plugin, TestPlugin plugin1) {
        this.plugin = plugin;
        this.plugin1 = plugin1;
    }

    @Command(main = "test")
    public void testBiggerConstructorCommand(Player player, String[] args) {
        plugin.doSomeThing("testBiggerConstructorCommand");
        plugin1.doSomeThing("testBiggerConstructorCommand");
    }
}
