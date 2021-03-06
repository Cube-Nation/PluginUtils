package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissions;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissionsNot;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandPermissionBoth {
    private TestPlugin plugin;

    public TestInvalidCommandPermissionBoth(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    @CommandPermissions("perm1a")
    @CommandPermissionsNot("perm1b")
    public void testPermissionCommand(Player player, String[] args) {
        plugin.doSomeThing("testPermissionCommand");
    }

    @Command(main = "test")
    @CommandPermissions("perm2a")
    @CommandPermissionsNot("perm2b")
    public void testPermissionCommandNot(Player player, String[] args) {
        plugin.doSomeThing("testPermissionCommandNot");
    }
}
