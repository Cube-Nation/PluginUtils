package de.cubenation.plugins.utils.commandapi.testutils;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TestCommand extends Command {
    public TestCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    public TestCommand(String name) {
        super(name);
    }

    public TestCommand() {
        super("test");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return false;
    }
}
