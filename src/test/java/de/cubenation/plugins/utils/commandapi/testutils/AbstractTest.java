package de.cubenation.plugins.utils.commandapi.testutils;

import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.junit.After;
import org.junit.Before;

import de.cubenation.plugins.utils.commandapi.CommandsManager;
import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;

public abstract class AbstractTest {
    protected HashMap<String, Short> testValid = new HashMap<String, Short>();
    protected CommandsManager commandsManager;

    @Before
    public void setUp() throws CommandWarmUpException {
        commandsManager = new CommandsManager(new TestPlugin() {
            @Override
            public void doSomeThing(String string) {
                if (testValid.containsKey(string)) {
                    testValid.put(string, new Short((short) (testValid.get(string) + 1)));
                } else {
                    testValid.put(string, new Short((short) 1));
                }
            }
        });
    }

    protected void executeComannd(String args) throws CommandException {
        executeComannd(commandsManager, args, new TestPlayer());
    }

    protected void executeComannd(CommandsManager commandsManager, String args) throws CommandException {
        executeComannd(commandsManager, args, new TestPlayer());
    }

    protected void executeComannd(String args, CommandSender sender) throws CommandException {
        executeComannd(commandsManager, args, sender);
    }

    protected void executeComannd(CommandsManager commandsManager, String args, CommandSender sender) throws CommandException {
        args = args.substring(1);

        String[] split = args.split(" ");
        String commandLabel = split[0];
        String[] paramArgs = new String[split.length - 1];
        System.arraycopy(split, 1, paramArgs, 0, split.length - 1);

        commandsManager.execute(sender, new TestCommand(), commandLabel, paramArgs);
    }

    @After
    public void tearDown() {
        testValid.clear();
    }
}
