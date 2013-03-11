package de.cubenation.plugins.utils.commandapi;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandUsageWith;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandUsageWithout;

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
        executeComannd("/gamemode 1", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(2, chatList.size());
        Assert.assertEquals("§cMindest Anzahl an Parameter nicht angegeben", chatList.get(0));
        Assert.assertEquals("§cBefehlssyntax: /gamemode [mode] [player]", chatList.get(1));
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
        Assert.assertEquals("§cMindest Anzahl an Parameter nicht angegeben", chatList.get(0));
    }
}
