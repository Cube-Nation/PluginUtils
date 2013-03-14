package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissions;
import de.cubenation.plugins.utils.commandapi.annotation.SenderBlock;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.annotation.SenderRemoteConsole;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class GeneralTestCommand {
    TestPlugin plugin;

    public GeneralTestCommand(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test1", min = 0, max = 0)
    public void test1a(Player player) {
        plugin.doSomeThing("test1a");
    }

    @Command(main = "test1", min = 0, max = 0)
    @SenderConsole
    public void test1b(ConsoleCommandSender sender) {
        plugin.doSomeThing("test1b");
    }

    @Command(main = "test1", min = 0, max = 0)
    @SenderRemoteConsole
    @SenderBlock
    public void test1c(CommandSender sender) {
        plugin.doSomeThing("test1c");
    }

    @Command(main = "test1", min = 1, max = 1)
    @World("world1")
    public void test1d(Player player, String arg) {
        plugin.doSomeThing("test1d");
    }

    @Command(main = "test1", min = 1, max = 1)
    @World("world2")
    public void test1e(Player player, String arg) {
        plugin.doSomeThing("test1e");
    }

    @Command(main = "test1", min = 2, max = -1)
    @CommandPermissions("perm1")
    public void test1f(Player player, String[] args) {
        plugin.doSomeThing("test1f");
    }
}
