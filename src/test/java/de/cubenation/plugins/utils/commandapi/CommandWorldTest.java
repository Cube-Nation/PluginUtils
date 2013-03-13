package de.cubenation.plugins.utils.commandapi;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.TestWorld;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.world.TestValidCommandMultiWorld;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.world.TestValidCommandOneWorld;

public class CommandWorldTest extends AbstractTest {
    @Test
    public void testValidOneWorldCommand() throws CommandException {
        commandsManager.add(TestValidCommandOneWorld.class);

        executeComannd("/test", new TestPlayer() {
            @Override
            public World getWorld() {
                return new TestWorld() {
                    @Override
                    public String getName() {
                        return "world";
                    }
                };
            }
        });

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneWorldCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOneWorldCommand"));
    }

    @Test
    public void testValidMultiWorldCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiWorld.class);

        executeComannd("/test", new TestPlayer() {
            @Override
            public World getWorld() {
                return new TestWorld() {
                    @Override
                    public String getName() {
                        return "world1";
                    }
                };
            }
        });
        executeComannd("/test", new TestPlayer() {
            @Override
            public World getWorld() {
                return new TestWorld() {
                    @Override
                    public String getName() {
                        return "world2";
                    }
                };
            }
        });

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testMultiWorldCommand"));
        Assert.assertEquals(new Short((short) 2), testValid.get("testMultiWorldCommand"));
    }

    @Test
    public void testInvalidOneWorldCommand() throws CommandException {
        commandsManager.add(TestValidCommandOneWorld.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestPlayer() {
            @Override
            public World getWorld() {
                return new TestWorld() {
                    @Override
                    public String getName() {
                        return "world0";
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
        Assert.assertEquals(ChatColor.RED + "Du befindest dich nicht in der richtigen Spielwelt! Der Befehl kann nur in world verwendet werden.",
                chatList.get(0));
    }

    @Test
    public void testInvalidMultiWorldCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiWorld.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestPlayer() {
            @Override
            public World getWorld() {
                return new TestWorld() {
                    @Override
                    public String getName() {
                        return "world0";
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
        Assert.assertEquals(ChatColor.RED + "Du befindest dich nicht in der richtigen Spielwelt! Der Befehl kann nur in world1, world2 verwendet werden.",
                chatList.get(0));
    }
}
