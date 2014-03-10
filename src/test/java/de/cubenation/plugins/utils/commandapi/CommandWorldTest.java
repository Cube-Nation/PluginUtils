package de.cubenation.plugins.utils.commandapi;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.world.TestValidCommandMultiWorld1;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.world.TestValidCommandOneWorld;

public class CommandWorldTest extends AbstractTest {
    @Test
    public void testValidOneWorldCommand() throws CommandException {
        commandsManager.add(TestValidCommandOneWorld.class);

        World world = mock(World.class);
        when(world.getName()).thenReturn("world");
        Player sender = mock(Player.class);
        when(sender.getWorld()).thenReturn(world);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                chatList.add((String) args[0]);
                return null;
            }
        }).when(sender).sendMessage(anyString());

        executeComannd("/test", sender);

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneWorldCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOneWorldCommand"));
    }

    @Test
    public void testValidMultiWorldCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiWorld1.class);

        World world1 = mock(World.class);
        when(world1.getName()).thenReturn("world1");
        Player sender1 = mock(Player.class);
        when(sender1.getWorld()).thenReturn(world1);

        World world2 = mock(World.class);
        when(world2.getName()).thenReturn("world2");
        Player sender2 = mock(Player.class);
        when(sender2.getWorld()).thenReturn(world2);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                chatList.add((String) args[0]);
                return null;
            }
        }).when(sender2).sendMessage(anyString());

        executeComannd("/test", sender1);
        executeComannd("/test", sender2);

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testMultiWorldCommand"));
        Assert.assertEquals(new Short((short) 2), testValid.get("testMultiWorldCommand"));
    }

    @Test
    public void testInvalidOneWorldCommand() throws CommandException {
        commandsManager.add(TestValidCommandOneWorld.class);

        World world = mock(World.class);
        when(world.getName()).thenReturn("world0");
        Player sender = mock(Player.class);
        when(sender.getWorld()).thenReturn(world);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                chatList.add((String) args[0]);
                return null;
            }
        }).when(sender).sendMessage(anyString());

        executeComannd("/test", sender);

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Du befindest dich nicht in der richtigen Spielwelt! Der Befehl kann nur in world verwendet werden.",
                chatList.get(0));
    }

    @Test
    public void testInvalidMultiWorldCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiWorld1.class);

        World world = mock(World.class);
        when(world.getName()).thenReturn("world0");
        Player sender = mock(Player.class);
        when(sender.getWorld()).thenReturn(world);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                chatList.add((String) args[0]);
                return null;
            }
        }).when(sender).sendMessage(anyString());

        executeComannd("/test", sender);

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Du befindest dich nicht in der richtigen Spielwelt! Der Befehl kann nur in world1, world2 verwendet werden.",
                chatList.get(0));
    }
}
