package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.command.BlockCommandSender;
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

    @Command(main = "test", min = 0, max = 0)
    public void test1(Player player) {
        plugin.doSomeThing("test1");
    }

    @Command(main = "test", min = 0, max = 0)
    @SenderConsole
    public void test2(ConsoleCommandSender sender) {
        plugin.doSomeThing("test2");
    }

    @Command(main = "test1", min = 0, max = 0)
    @SenderBlock
    public void test2a(BlockCommandSender sender) {
        plugin.doSomeThing("test2a");
    }

    @Command(main = "test2", min = 0, max = 0)
    @SenderRemoteConsole
    public void test2b(CommandSender sender) {
        plugin.doSomeThing("test2b");
    }

    @Command(main = "test", min = 0, max = 0)
    @SenderRemoteConsole
    @SenderBlock
    public void test3(CommandSender sender) {
        plugin.doSomeThing("test3");
    }

    @Command(main = "test", min = 1, max = 1)
    @World("world1")
    public void test4(Player player, String arg) {
        plugin.doSomeThing("test4");
    }

    @Command(main = "test", min = 1, max = 1)
    @World("world2")
    public void test5(Player player, String arg) {
        plugin.doSomeThing("test5");
    }

    @Command(main = "test", min = 2, max = -1)
    @CommandPermissions("perm1")
    public void test6(Player player, String[] args) {
        plugin.doSomeThing("test6");
    }

    @Command(main = "test3", min = 1, max = 1)
    @SenderBlock
    @World({ "world3", "foo" })
    public void test7(BlockCommandSender sender, String[] arg) {
        plugin.doSomeThing("test7");
    }
}
