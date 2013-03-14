package de.cubenation.plugins.utils.commandapi;

import org.bukkit.ChatColor;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestConsoleCommandSender;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.help.TestValidCommandHelp;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.help.TestValidCommandHelpCommand;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.help.TestValidCommandHelpConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.help.TestValidCommandHelpMulti;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.help.TestValidCommandHelpPermission;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.help.TestValidCommandHelpSub;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.help.TestValidCommandHelpUsage;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.help.TestValidCommandHelpUsageConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.help.TestValidCommandHelpWorld;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestValidCommandMain;

public class CommandHelpTest extends AbstractTest {
    @Test
    public void testOneHelpCommand() throws CommandException {
        commandsManager.add(TestValidCommandHelp.class);

        executeComannd("/test help");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.AQUA + "/test" + ChatColor.WHITE + " - this is a help test", chatList.get(0));
    }

    @Test
    public void testOneHelpUsageCommand() throws CommandException {
        commandsManager.add(TestValidCommandHelpUsage.class);

        executeComannd("/kill help");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.AQUA + "/kill [Spieler]" + ChatColor.WHITE + " - this is a help test", chatList.get(0));
    }

    @Test
    public void testSubHelpIndirectCommand() throws CommandException {
        commandsManager.add(TestValidCommandHelpSub.class);

        executeComannd("/test help");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.AQUA + "/test foo" + ChatColor.WHITE + " - this is a sub help test", chatList.get(0));
    }

    @Test
    public void testSubHelpDirectCommand() throws CommandException {
        commandsManager.add(TestValidCommandHelpSub.class);

        executeComannd("/test foo help");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.AQUA + "/test foo" + ChatColor.WHITE + " - this is a sub help test", chatList.get(0));
    }

    @Test
    public void testMultiHelpCommand() throws CommandException {
        commandsManager.add(TestValidCommandHelp.class);
        commandsManager.add(TestValidCommandHelpSub.class);

        executeComannd("/test help");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(2, chatList.size());
        Assert.assertEquals(ChatColor.AQUA + "/test" + ChatColor.WHITE + " - this is a help test", chatList.get(0));
        Assert.assertEquals(ChatColor.AQUA + "/test foo" + ChatColor.WHITE + " - this is a sub help test", chatList.get(1));
    }

    @Test
    public void testHelpCommandCommand() throws CommandException {
        commandsManager.add(TestValidCommandHelpCommand.class);

        executeComannd("/test help");
        executeComannd("/test ?");

        Assert.assertEquals(2, testValid.size());
        Assert.assertTrue(testValid.containsKey("testHelpCommandCommand1"));
        Assert.assertTrue(testValid.containsKey("testHelpCommandCommand2"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testHelpCommandCommand1"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testHelpCommandCommand2"));
        Assert.assertEquals(0, chatList.size());
    }

    @Test
    public void testEmptyHelpCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test help");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(0, chatList.size());
    }

    @Test
    public void testConsoleHelpCommand() throws CommandException {
        commandsManager.add(TestValidCommandHelpConsole.class);

        executeComannd("/test help", new TestConsoleCommandSender() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("/test - this is a help test", chatList.get(0));
    }

    @Test
    public void testPermissionHelpCommand() throws CommandException {
        commandsManager.add(TestValidCommandHelpPermission.class);

        executeComannd("/test help");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Nicht ausreichende Berechtigungen", chatList.get(0));
    }

    @Test
    public void testConsoleHelpCommandUsage() throws CommandException {
        commandsManager.add(TestValidCommandHelpUsageConsole.class);

        executeComannd("/time set help", new TestConsoleCommandSender() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("/time set [TIME] - this is a help test", chatList.get(0));
    }

    @Test
    public void testConsoleHelpMultiCommand() throws CommandException {
        commandsManager.add(TestValidCommandHelpMulti.class);

        executeComannd("/test1 help");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(2, chatList.size());
        Assert.assertEquals(ChatColor.AQUA + "/test1 foo/bar" + ChatColor.WHITE + " - this is a help test", chatList.get(0));
        Assert.assertEquals(ChatColor.AQUA + "/test2 foo/bar" + ChatColor.WHITE + " - this is a help test", chatList.get(1));
    }

    @Test
    public void testConsoleHelpWorldCommand() throws CommandException {
        commandsManager.add(TestValidCommandHelpWorld.class);

        executeComannd("/test help");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.AQUA + "/test" + ChatColor.WHITE + " - this is a help test", chatList.get(0));
    }
}
