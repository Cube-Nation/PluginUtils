package de.cubenation.plugins.utils.commandapi;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.GeneralTestCommand;

public class CommandGeneralTest extends AbstractTest {
    @Test
    public void testGeneralCommand1() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        executeComannd("/test");

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("test1"));
        Assert.assertEquals(new Short((short) 1), testValid.get("test1"));
    }

    @Test
    public void testGeneralCommand2() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        ConsoleCommandSender sender = mock(ConsoleCommandSender.class);
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
        Assert.assertTrue(testValid.containsKey("test2"));
        Assert.assertEquals(new Short((short) 1), testValid.get("test2"));
    }

    @Test
    public void testGeneralCommand3() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        BlockCommandSender blockCommandSender = mock(BlockCommandSender.class);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                chatList.add((String) args[0]);
                return null;
            }
        }).when(blockCommandSender).sendMessage(anyString());
        RemoteConsoleCommandSender remoteConsoleCommandSender = mock(RemoteConsoleCommandSender.class);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                chatList.add((String) args[0]);
                return null;
            }
        }).when(remoteConsoleCommandSender).sendMessage(anyString());
        executeComannd("/test", blockCommandSender);
        executeComannd("/test", remoteConsoleCommandSender);

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("test3"));
        Assert.assertEquals(new Short((short) 2), testValid.get("test3"));
    }

    @Test
    public void testGeneralCommand4() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        World world = mock(World.class);
        when(world.getName()).thenReturn("world1");
        Player sender = mock(Player.class);
        when(sender.getWorld()).thenReturn(world);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                chatList.add((String) args[0]);
                return null;
            }
        }).when(sender).sendMessage(anyString());
        executeComannd("/test 5", sender);

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("test4"));
        Assert.assertEquals(new Short((short) 1), testValid.get("test4"));
    }

    @Test
    public void testGeneralCommand5() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        World world = mock(World.class);
        when(world.getName()).thenReturn("world2");
        Player sender = mock(Player.class);
        when(sender.getWorld()).thenReturn(world);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                chatList.add((String) args[0]);
                return null;
            }
        }).when(sender).sendMessage(anyString());

        executeComannd("/test 5", sender);

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("test5"));
        Assert.assertEquals(new Short((short) 1), testValid.get("test5"));
    }

    @Test
    public void testGeneralCommand6() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        Player sender = mock(Player.class);
        doAnswer(new Answer<Boolean>() {
            public Boolean answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return "perm1".equals((String) args[0]);
            }
        }).when(sender).hasPermission(anyString());
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                chatList.add((String) args[0]);
                return null;
            }
        }).when(sender).sendMessage(anyString());

        executeComannd("/test 6 5 7", sender);

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("test6"));
        Assert.assertEquals(new Short((short) 1), testValid.get("test6"));
    }

    @Test
    public void testGeneralCommand7() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        World world = mock(World.class);
        when(world.getName()).thenReturn("world4");
        Block block = mock(Block.class);
        when(block.getWorld()).thenReturn(world);
        BlockCommandSender sender = mock(BlockCommandSender.class);
        when(sender.getBlock()).thenReturn(block);
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                chatList.add((String) args[0]);
                return null;
            }
        }).when(sender).sendMessage(anyString());

        executeComannd("/test3 5", sender);

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("Der Block befindet sich nicht in der richtigen Spielwelt! Der Befehl kann nur in world3, foo verwendet werden.", chatList.get(0));
    }
}
