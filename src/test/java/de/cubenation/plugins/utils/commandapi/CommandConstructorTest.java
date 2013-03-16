package de.cubenation.plugins.utils.commandapi;

import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.annotation.TestCommandErrorHandler;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.constructor.TestValidCommandBiggerConsturctor;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.constructor.TestValidCommandEmptyConsturctor;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.constructor.TestValidCommandNoConsturctor;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestValidCommandMain;

public class CommandConstructorTest extends AbstractTest {
    @Test
    public void testEmptyConsturctorCommand() throws CommandException {
        commandsManager.add(TestValidCommandEmptyConsturctor.class);

        executeComannd("/test");

        Assert.assertEquals(0, chatList.size());
    }

    @Test
    public void testNoConsturctorCommand() throws CommandException {
        commandsManager.add(TestValidCommandNoConsturctor.class);

        executeComannd("/test");

        Assert.assertEquals(0, chatList.size());
    }

    @Test
    public void testNormalConsturctorCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test");

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneMainCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOneMainCommand"));
    }

    @Test
    public void testAddBiggerConsturctorCommand() throws CommandException {
        commandsManager.add(TestValidCommandBiggerConsturctor.class, new TestPlugin() {
            @Override
            public void doSomeThing(String string) {
                if (testValid.containsKey(string)) {
                    testValid.put(string, new Short((short) (testValid.get(string) + 1)));
                } else {
                    testValid.put(string, new Short((short) 1));
                }
            }
        }, new TestPlugin() {
            @Override
            public void doSomeThing(String string) {
                if (testValid.containsKey(string)) {
                    testValid.put(string, new Short((short) (testValid.get(string) + 1)));
                } else {
                    testValid.put(string, new Short((short) 1));
                }
            }
        });

        executeComannd("/test");

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testBiggerConstructorCommand"));
        Assert.assertEquals(new Short((short) 2), testValid.get("testBiggerConstructorCommand"));
    }

    @Test
    public void testConstructorBiggerConsturctorCommand() throws CommandException {
        CommandsManager commandsManager = new CommandsManager(new TestPlugin() {
            @Override
            public void doSomeThing(String string) {
                if (testValid.containsKey(string)) {
                    testValid.put(string, new Short((short) (testValid.get(string) + 1)));
                } else {
                    testValid.put(string, new Short((short) 1));
                }
            }
        }, new TestPlugin() {
            @Override
            public void doSomeThing(String string) {
                if (testValid.containsKey(string)) {
                    testValid.put(string, new Short((short) (testValid.get(string) + 1)));
                } else {
                    testValid.put(string, new Short((short) 1));
                }
            }
        });

        commandsManager.add(TestValidCommandBiggerConsturctor.class);

        executeComannd(commandsManager, "/test");

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testBiggerConstructorCommand"));
        Assert.assertEquals(new Short((short) 2), testValid.get("testBiggerConstructorCommand"));
    }

    @Test
    public void testConstructorNoConsturctorCommand() throws CommandException {
        CommandsManager commandsManager = new CommandsManager();

        commandsManager.add(TestValidCommandNoConsturctor.class);

        executeComannd(commandsManager, "/test");

        Assert.assertEquals(0, chatList.size());
    }

    @Test
    public void testConstructorEmptyConsturctorCommand() throws CommandException {
        CommandsManager commandsManager = new CommandsManager();

        commandsManager.add(TestValidCommandEmptyConsturctor.class);

        executeComannd(commandsManager, "/test");

        Assert.assertEquals(0, chatList.size());
    }

    @Test
    public void testConstructorNormalConsturctorCommand() throws CommandException {
        CommandsManager commandsManager = new CommandsManager(new TestPlugin() {
            @Override
            public void doSomeThing(String string) {
                if (testValid.containsKey(string)) {
                    testValid.put(string, new Short((short) (testValid.get(string) + 1)));
                } else {
                    testValid.put(string, new Short((short) 1));
                }
            }
        });

        commandsManager.add(TestValidCommandMain.class);

        executeComannd(commandsManager, "/test");

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneMainCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOneMainCommand"));
    }

    @Test
    public void testAddNormalConsturctorCommand() throws CommandException {
        CommandsManager commandsManager = new CommandsManager();

        commandsManager.add(TestValidCommandMain.class, new TestPlugin() {
            @Override
            public void doSomeThing(String string) {
                if (testValid.containsKey(string)) {
                    testValid.put(string, new Short((short) (testValid.get(string) + 1)));
                } else {
                    testValid.put(string, new Short((short) 1));
                }
            }
        });

        executeComannd(commandsManager, "/test");

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneMainCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOneMainCommand"));
    }

    @Test
    public void testErrorHandlerCommand() throws CommandException, InterruptedException {
        commandsManager.add(TestCommandErrorHandler.class);
        commandsManager.setErrorHandler(new ErrorHandler() {

            @Override
            public void onError(Exception e) {
            }
        });

        executeComannd("/test1");
        executeComannd("/test1 3");
    }
}
