package de.cubenation.plugins.utils.commandapi.testutils.testcommands.permission;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissions;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMultiPermission {
    private TestPlugin plugin;

    public TestValidCommandMultiPermission(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    @CommandPermissions({ "perm1", "perm2", "" })
    public void testMultiWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testMultiWorldCommand");
    }
}
