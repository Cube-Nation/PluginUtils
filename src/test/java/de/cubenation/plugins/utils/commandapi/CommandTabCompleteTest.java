package de.cubenation.plugins.utils.commandapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.exception.CommandManagerException;
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestCommand;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.TestServer;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.tab.TestWarn;

public class CommandTabCompleteTest extends AbstractTest {
    private TestPlayer testPlayer1;
    private TestPlayer testPlayer2;

    @Before
    @Override
    public void setUp() throws CommandWarmUpException, CommandManagerException {
        super.setUp();

        testPlayer1 = new TestPlayer("checkPlayer");
        testPlayer2 = new TestPlayer("testPlayer");
        ((TestServer) Bukkit.getServer()).setOnlinePlayers(new Player[] { testPlayer1, testPlayer2 });
    }

    @Test
    public void testWithoutArgs() throws CommandException {
        commandsManager.add(TestWarn.class);

        List<String> tabCompleteList = commandsManager.getTabCompleteList(testPlayer1, new TestCommand("warn"), "warn", new String[] {});

        assertNull(tabCompleteList);
    }

    @Test
    public void testWithArgsComplete() throws CommandException {
        commandsManager.add(TestWarn.class);

        List<String> tabCompleteList = commandsManager.getTabCompleteList(testPlayer1, new TestCommand("warn"), "warn", new String[] { "s" });

        assertNotNull(tabCompleteList);
        assertEquals(1, tabCompleteList.size());
        assertEquals("search", tabCompleteList.get(0));
    }

    @Test
    public void testWithUserArgs() throws CommandException {
        commandsManager.add(TestWarn.class);

        List<String> tabCompleteList = commandsManager.getTabCompleteList(testPlayer1, new TestCommand("warn"), "warn", new String[] { "t" });

        assertNotNull(tabCompleteList);
        assertEquals(1, tabCompleteList.size());
        assertEquals(testPlayer2.getName(), tabCompleteList.get(0));
    }

    @Test
    public void testWithoutArgsHelpComplete() throws CommandException {
        commandsManager.add(TestWarn.class);

        List<String> tabCompleteList = commandsManager.getTabCompleteList(testPlayer1, new TestCommand("warn"), "warn", new String[] { "h" });

        assertNotNull(tabCompleteList);
        assertEquals(1, tabCompleteList.size());
        assertEquals("help", tabCompleteList.get(0));
    }

    @Test
    public void testWithArgsHelpComplete() throws CommandException {
        commandsManager.add(TestWarn.class);

        List<String> tabCompleteList = commandsManager.getTabCompleteList(testPlayer1, new TestCommand("warn"), "warn", new String[] { "bla", "h" });

        assertNotNull(tabCompleteList);
        assertEquals(1, tabCompleteList.size());
        assertEquals("help", tabCompleteList.get(0));
    }

    @Test
    public void testWithArgsCompleteUser() throws CommandException {
        commandsManager.add(TestWarn.class);

        List<String> tabCompleteList = commandsManager.getTabCompleteList(testPlayer1, new TestCommand("warn"), "warn", new String[] { "ch" });

        assertNotNull(tabCompleteList);
        assertEquals(2, tabCompleteList.size());
        assertEquals("check", tabCompleteList.get(0));
        assertEquals(testPlayer1.getName(), tabCompleteList.get(1));
    }
}
