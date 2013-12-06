package de.cubenation.plugins.utils.wrapperapi;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.cubenation.plugins.utils.wrapperapi.WorldEditWrapper.Selection;

public class LogBlockWrapper {
    private static de.diddiz.LogBlock.LogBlock logBlock;
    private static Logger log;

    public static void setLogger(Logger log) {
        LogBlockWrapper.log = log;
    }

    public static void loadPlugin() {
        if (logBlock == null) {
            logBlock = (de.diddiz.LogBlock.LogBlock) Bukkit.getServer().getPluginManager().getPlugin(WrapperManager.Plugins.LOG_BLOCK.getName());
            if (logBlock == null) {
                log.info(WrapperManager.Plugins.LOG_BLOCK.getName() + " not found");
            }
        }
    }

    public static List<BlockChange> getBlockChanges(QueryParams arg0) throws SQLException {
        List<de.diddiz.LogBlock.BlockChange> blockChanges = logBlock.getBlockChanges(arg0.queryParams);

        List<BlockChange> list = new ArrayList<BlockChange>();
        for (de.diddiz.LogBlock.BlockChange blockChange : blockChanges) {
            list.add(new BlockChange(blockChange));
        }

        return list;
    }

    public static CommandsHandler getCommandsHandler() {
        return new CommandsHandler(logBlock.getCommandsHandler());
    }

    public static Connection getConnection() {
        return logBlock.getConnection();
    }

    public static Consumer getConsumer() {
        return new Consumer(logBlock.getConsumer());
    }

    public static int getCount(QueryParams arg0) throws SQLException {
        return logBlock.getCount(arg0.queryParams);
    }

    public static boolean hasPermission(CommandSender sender, String permission) {
        return logBlock.hasPermission(sender, permission);
    }

    public static class BlockChange {
        private de.diddiz.LogBlock.BlockChange blockChange;

        public BlockChange(de.diddiz.LogBlock.BlockChange blockChange) {
            this.blockChange = blockChange;
        }

        public Location getLocation() {
            return blockChange.getLocation();
        }

        public String getMessage() {
            return blockChange.getMessage();
        }

        @Override
        public String toString() {
            return blockChange.toString();
        }
    }

    public static class CommandsHandler {
        private de.diddiz.LogBlock.CommandsHandler commandsHandler;

        public CommandsHandler(de.diddiz.LogBlock.CommandsHandler commandsHandler) {
            this.commandsHandler = commandsHandler;
        }

        public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
            return commandsHandler.onCommand(arg0, arg1, arg2, arg3);
        }
    }

    public static class Consumer extends TimerTask {
        private de.diddiz.LogBlock.Consumer consumer;

        public Consumer(de.diddiz.LogBlock.Consumer consumer) {
            this.consumer = consumer;
        }

        public void queueBlock(String playerName, Location loc, int typeBefore, int typeAfter, byte data) {
            consumer.queueBlock(playerName, loc, typeBefore, typeAfter, data);
        }

        public void queueBlockBreak(String playerName, BlockState before) {
            consumer.queueBlockBreak(playerName, before);
        }

        public void queueBlockBreak(String playerName, Location loc, int typeBefore, byte dataBefore) {
            consumer.queueBlockBreak(playerName, loc, typeBefore, dataBefore);
        }

        public void queueBlockPlace(String playerName, BlockState after) {
            consumer.queueBlockPlace(playerName, after);
        }

        public void queueBlockPlace(String playerName, Location loc, int type, byte data) {
            consumer.queueBlockPlace(playerName, loc, type, data);
        }

        public void queueBlockReplace(String playerName, BlockState before, BlockState after) {
            consumer.queueBlockReplace(playerName, before, after);
        }

        public void queueBlockReplace(String playerName, BlockState before, int typeAfter, byte dataAfter) {
            consumer.queueBlockReplace(playerName, before, typeAfter, dataAfter);
        }

        public void queueBlockReplace(String playerName, int typeBefore, byte dataBefore, BlockState after) {
            consumer.queueBlockReplace(playerName, typeBefore, dataBefore, after);
        }

        public void queueBlockReplace(String playerName, Location loc, int typeBefore, byte dataBefore, int typeAfter, byte dataAfter) {
            consumer.queueBlockReplace(playerName, loc, typeBefore, dataBefore, typeAfter, dataAfter);
        }

        public void queueChat(String arg0, String arg1) {
            consumer.queueChat(arg0, arg1);
        }

        public void queueChestAccess(String playerName, BlockState container, short itemType, short itemAmount, byte itemData) {
            consumer.queueChestAccess(playerName, container, itemType, itemAmount, itemData);
        }

        public void queueChestAccess(String playerName, Location loc, int type, short itemType, short itemAmount, byte itemData) {
            consumer.queueChestAccess(playerName, loc, type, itemType, itemAmount, itemData);
        }

        public void queueContainerBreak(String playerName, BlockState container) {
            consumer.queueContainerBreak(playerName, container);
        }

        public void queueContainerBreak(String arg0, Location arg1, int arg2, byte arg3, Inventory arg4) {
            consumer.queueContainerBreak(arg0, arg1, arg2, arg3, arg4);
        }

        public void queueJoin(Player player) {
            consumer.queueJoin(player);
        }

        public void queueKill(Entity killer, Entity victim) {
            consumer.queueKill(killer, victim);
        }

        public void queueKill(Location location, String killerName, String victimName, int weapon) {
            consumer.queueKill(location, killerName, victimName, weapon);
        }

        public void queueLeave(Player player) {
            consumer.queueLeave(player);
        }

        public void queueSignBreak(String playerName, Location loc, int type, byte data, String[] lines) {
            consumer.queueSignBreak(playerName, loc, type, data, lines);
        }

        public void queueSignBreak(String playerName, Sign sign) {
            consumer.queueSignBreak(playerName, sign);
        }

        public void queueSignPlace(String playerName, Location loc, int type, byte data, String[] lines) {
            consumer.queueSignPlace(playerName, loc, type, data, lines);
        }

        public void queueSignPlace(String playerName, Sign sign) {
            consumer.queueSignPlace(playerName, sign);
        }

        @Override
        public void run() {
            consumer.run();
        }

        public void writeToFile() throws FileNotFoundException {
            consumer.writeToFile();
        }
    }

    public static class QueryParams {
        private de.diddiz.LogBlock.QueryParams queryParams;

        public String getLimit() {
            return queryParams.getLimit();
        }

        public String getQuery() {
            return queryParams.getQuery();
        }

        public String getTable() {
            return queryParams.getTable();
        }

        public String getTitle() {
            return queryParams.getTitle();
        }

        public String getWhere() {
            return queryParams.getWhere();
        }

        public void parseArgs(CommandSender sender, List<String> args) {
            queryParams.parseArgs(sender, args);
        }

        public void setLocation(Location loc) {
            queryParams.setLocation(loc);
        }

        public void setSelection(Selection sel) {
            if (!WrapperManager.isPluginEnabled(WrapperManager.Plugins.WORLD_EDIT)) {
                return;
            }

            queryParams.setSelection(sel.selection);
        }

        public void setPlayer(String playerName) {
            queryParams.setPlayer(playerName);
        }

        public void merge(QueryParams p) {
            queryParams.merge(p.queryParams);
        }

        public static boolean isKeyWord(String param) {
            return de.diddiz.LogBlock.QueryParams.isKeyWord(param);
        }
    }
}
