package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.command.BlockCommandSender;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderBlock;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandBlock {
    private TestPlugin plugin;

    public TestValidCommandBlock(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    @SenderBlock
    public void testBlockCommand(BlockCommandSender console, String[] args) {
        plugin.doSomeThing("testBlockCommand");
    }
}
