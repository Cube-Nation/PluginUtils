package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissions;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissionsNot;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandPermissionBothWithoutMulti {
    private TestPlugin plugin;

    public TestInvalidCommandPermissionBothWithoutMulti(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    public void testWithoutPermissionCommand(Player player, String[] args) {
        plugin.doSomeThing("testWithoutPermissionCommand");
    }

    @Command(main = "test")
    @CommandPermissions({ "perm1a", "perm2a" })
    @CommandPermissionsNot({ "perm1b", "perm2b" })
    public void testPermissionCommand(Player player, String[] args) {
        plugin.doSomeThing("testPermissionCommand");
    }
}
