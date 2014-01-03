package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissionsNot;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandPermissionNotWithoutMulti {
    private TestPlugin plugin;

    public TestInvalidCommandPermissionNotWithoutMulti(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    public void testWithoutPermissionCommand(Player player, String[] args) {
        plugin.doSomeThing("testWithoutPermissionCommand");
    }

    @Command(main = "test")
    @CommandPermissionsNot({ "perm1", "perm2" })
    public void testPermissionCommand(Player player, String[] args) {
        plugin.doSomeThing("testPermissionCommand");
    }
}
