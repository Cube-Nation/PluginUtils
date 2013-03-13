package de.cubenation.plugins.utils.commandapi;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestConsole;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.usage.TestValidCommandUsageWith;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.usage.TestValidCommandUsageWithConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.usage.TestValidCommandUsageWithExact;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.usage.TestValidCommandUsageWithExactConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.usage.TestValidCommandUsageWithout;

public class CommandUsageTest extends AbstractTest {
    @Test
    public void testNoUsageCommand() throws CommandException {
        commandsManager.add(TestValidCommandUsageWith.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/gamemode 1 somePlayer", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testUsageCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testUsageCommand"));
        Assert.assertEquals(0, chatList.size());
    }

    @Test
    public void testWithCustomUsageCommand() throws CommandException {
        commandsManager.add(TestValidCommandUsageWith.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        TestPlayer sender = new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        };

        executeComannd("/gamemode 1", sender);
        executeComannd("/gamemode 1 2 5", sender);

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(4, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Mindest Anzahl an Parameter nicht angegeben", chatList.get(0));
        Assert.assertEquals(ChatColor.RED + "Befehlssyntax: /gamemode [mode] [player]", chatList.get(1));
        Assert.assertEquals(ChatColor.RED + "Zu viel Parameter angegeben", chatList.get(2));
        Assert.assertEquals(ChatColor.RED + "Befehlssyntax: /gamemode [mode] [player]", chatList.get(3));
    }

    @Test
    public void testWithExactCustomUsageCommand() throws CommandException {
        commandsManager.add(TestValidCommandUsageWithExact.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        TestPlayer sender = new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        };

        executeComannd("/gamemode 1", sender);

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(2, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Befehl unterstützt keine Parameter", chatList.get(0));
        Assert.assertEquals(ChatColor.RED + "Befehlssyntax: /gamemode [mode] [player]", chatList.get(1));
    }

    @Test
    public void testWithGenericUsageCommand() throws CommandException {
        commandsManager.add(TestValidCommandUsageWithout.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/gamemode 1", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Mindest Anzahl an Parameter nicht angegeben", chatList.get(0));
    }

    @Test
    public void testWithCustomUsageConsoleCommand() throws CommandException {
        commandsManager.add(TestValidCommandUsageWithConsole.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        TestConsole sender = new TestConsole() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        };

        executeComannd("/gamemode 1", sender);
        executeComannd("/gamemode 1 2 5", sender);

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(4, chatList.size());
        Assert.assertEquals("Mindest Anzahl an Parameter nicht angegeben", chatList.get(0));
        Assert.assertEquals("Befehlssyntax: /gamemode [mode] [player]", chatList.get(1));
        Assert.assertEquals("Zu viel Parameter angegeben", chatList.get(2));
        Assert.assertEquals("Befehlssyntax: /gamemode [mode] [player]", chatList.get(3));
    }

    @Test
    public void testWithExactCustomUsageConsoleCommand() throws CommandException {
        commandsManager.add(TestValidCommandUsageWithExactConsole.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        TestConsole sender = new TestConsole() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        };

        executeComannd("/gamemode 1", sender);

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(2, chatList.size());
        Assert.assertEquals("Befehl unterstützt keine Parameter", chatList.get(0));
        Assert.assertEquals("Befehlssyntax: /gamemode [mode] [player]", chatList.get(1));
    }
}
