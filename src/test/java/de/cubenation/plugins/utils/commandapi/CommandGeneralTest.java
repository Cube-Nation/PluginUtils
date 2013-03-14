package de.cubenation.plugins.utils.commandapi;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestBlock;
import de.cubenation.plugins.utils.commandapi.testutils.TestBlockCommandSender;
import de.cubenation.plugins.utils.commandapi.testutils.TestConsoleCommandSender;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.TestRemoteConsole;
import de.cubenation.plugins.utils.commandapi.testutils.TestWorld;
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

        executeComannd("/test", new TestConsoleCommandSender() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("test2"));
        Assert.assertEquals(new Short((short) 1), testValid.get("test2"));
    }

    @Test
    public void testGeneralCommand3() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        executeComannd("/test", new TestBlockCommandSender() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });
        executeComannd("/test", new TestRemoteConsole() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("test3"));
        Assert.assertEquals(new Short((short) 2), testValid.get("test3"));
    }

    @Test
    public void testGeneralCommand4() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        executeComannd("/test 5", new TestPlayer() {
            @Override
            public World getWorld() {
                return new TestWorld() {
                    @Override
                    public String getName() {
                        return "world1";
                    }
                };
            }

            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("test4"));
        Assert.assertEquals(new Short((short) 1), testValid.get("test4"));
    }

    @Test
    public void testGeneralCommand5() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        executeComannd("/test 5", new TestPlayer() {
            @Override
            public World getWorld() {
                return new TestWorld() {
                    @Override
                    public String getName() {
                        return "world2";
                    }
                };
            }

            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("test5"));
        Assert.assertEquals(new Short((short) 1), testValid.get("test5"));
    }

    @Test
    public void testGeneralCommand6() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        executeComannd("/test 6 5 7", new TestPlayer() {
            @Override
            public boolean hasPermission(String name) {
                return "perm1".equals(name);
            }

            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("test6"));
        Assert.assertEquals(new Short((short) 1), testValid.get("test6"));
    }

    @Test
    public void testGeneralCommand7() throws CommandException {
        commandsManager.add(GeneralTestCommand.class);

        executeComannd("/test3 5", new TestBlockCommandSender() {
            @Override
            public Block getBlock() {
                return new TestBlock() {
                    @Override
                    public World getWorld() {
                        return new TestWorld() {
                            @Override
                            public String getName() {
                                return "world4";
                            }
                        };
                    }
                };
            }

            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("Der Block befindet sich nicht in der richtigen Spielwelt! Der Befehl kann nur in world3, foo verwendet werden.", chatList.get(0));
    }
}
