package de.cubenation.plugins.utils.commandapi.testutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import de.cubenation.plugins.utils.chatapi.ChatService;
import de.cubenation.plugins.utils.chatapi.ColorParser;
import de.cubenation.plugins.utils.commandapi.CommandsManager;
import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.exception.CommandManagerException;
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;

public abstract class AbstractTest {
    protected HashMap<String, Short> testValid = new HashMap<String, Short>();
    protected ArrayList<String> chatList = new ArrayList<String>();
    protected CommandsManager commandsManager;

    @Before
    public void setUp() throws CommandWarmUpException, CommandManagerException {
        if (Bukkit.getServer() == null) {
            Bukkit.setServer(new TestServer());
        }

        TestPlugin testPlugin = new TestPlugin() {
            @Override
            public void doSomeThing(String string) {
                if (testValid.containsKey(string)) {
                    testValid.put(string, new Short((short) (testValid.get(string) + 1)));
                } else {
                    testValid.put(string, new Short((short) 1));
                }
            }
        };

        ChatService chatService = new ChatService(testPlugin, null, "messages", new Locale("de")) {
            @Override
            public void one(CommandSender sender, String resourceString, Object... parameter) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("all.noPermission", "{RED}Nicht ausreichende Berechtigungen");
                map.put("all.helpMessage", "{0}");
                map.put("player.commandNotFound", "{RED}Befehl nicht gefunden. Versuche /{0} help");
                map.put("player.commandNotFoundSub", "{RED}Befehl nicht gefunden. Versuche /{0} help oder /{0} {1} help");
                map.put("player.wrongWorld", "{RED}Du befindest dich nicht in der richtigen Spielwelt! Der Befehl kann nur in {0} verwendet werden.");
                map.put("player.parameterMissing", "{RED}Mindest Anzahl an Parameter nicht angegeben");
                map.put("player.parameterNo", "{RED}Befehl unterstützt keine Parameter");
                map.put("player.parameterToMany", "{RED}Zu viel Parameter angegeben");
                map.put("player.commandUsage", "{RED}Befehlssyntax: /{0} {1}{2}");
                map.put("block.wrongWorld", "Der Block befindet sich nicht in der richtigen Spielwelt! Der Befehl kann nur in {0} verwendet werden.");
                map.put("other.commandNotFound", "Befehl nicht gefunden. Versuche /{0} help");
                map.put("other.commandNotFoundSub", "Befehl nicht gefunden. Versuche /{0} help oder /{0} {1} help");
                map.put("other.parameterMissing", "Mindest Anzahl an Parameter nicht angegeben");
                map.put("other.parameterNo", "Befehl unterstützt keine Parameter");
                map.put("other.parameterToMany", "Zu viel Parameter angegeben");
                map.put("other.commandUsage", "Befehlssyntax: /{0} {1}{2}");

                String str = "";
                if (map.containsKey(resourceString)) {
                    str = map.get(resourceString);
                    str = ColorParser.replaceColor(str);

                    int i = 0;
                    for (Object param : parameter) {
                        str = str.replace("{" + i + "}", param.toString());
                        i++;
                    }
                    sender.sendMessage(str);
                } else {
                    Assert.fail("resourceKey(" + resourceString + ") not found");
                }
            }
        };

        CommandsManager.setOwnChatService(chatService);
        commandsManager = new CommandsManager(testPlugin);
    }

    protected void executeComannd(String args) throws CommandException {
        executeComannd(commandsManager, args, new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });
    }

    protected void executeComannd(CommandsManager commandsManager, String args) throws CommandException {
        executeComannd(commandsManager, args, new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });
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
        chatList.clear();
    }
}
