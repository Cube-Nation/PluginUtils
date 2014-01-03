package de.cubenation.plugins.utils.wrapperapi;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import de.cubenation.plugins.utils.wrapperapi.VaultWrapper.ChatService;

public class HerochatWrapper {
    private static com.dthielke.herochat.Herochat herochat;
    private static Logger log;

    public static void setLogger(Logger log) {
        HerochatWrapper.log = log;
    }

    public static void loadPlugin() {
        if (herochat == null) {
            herochat = (com.dthielke.herochat.Herochat) Bukkit.getServer().getPluginManager().getPlugin(WrapperManager.PLUGIN_NAME_HEROCHAT);
            if (herochat == null) {
                log.info(WrapperManager.PLUGIN_NAME_HEROCHAT + " not found");
            }
        }

    }

    public static ChannelManager getChannelManager() {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            return new ChannelManager(com.dthielke.herochat.Herochat.getChannelManager());
        }

        return null;
    }

    public static ChatService getChatService() {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null && WrapperManager.isPluginEnabled(WrapperManager.PLUGIN_NAME_VAULT)) {
            return new ChatService(com.dthielke.herochat.Herochat.getChatService());
        }

        return null;
    }

    public static ChatterManager getChatterManager() {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            return new ChatterManager(com.dthielke.herochat.Herochat.getChatterManager());
        }

        return null;
    }

    public static ConfigManager getConfigManager() {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            return new ConfigManager(com.dthielke.herochat.Herochat.getConfigManager());
        }

        return null;
    }

    public static String getMessage(String key) throws MessageNotFoundException {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            try {
                return com.dthielke.herochat.Herochat.getMessage(key);
            } catch (com.dthielke.herochat.MessageNotFoundException e) {
                throw new MessageNotFoundException(e);
            }
        }

        return null;
    }

    public static MessageHandler getMessageHandler() {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            return new MessageHandler(com.dthielke.herochat.Herochat.getMessageHandler());
        }

        return null;
    }

    public static boolean hasChannelPermission(CommandSender user, Channel channel, Permission permission) {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            return com.dthielke.herochat.Herochat.hasChannelPermission(user, channel.channel, Permission.convert(permission));
        }

        return false;
    }

    public static void info(String message) {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            com.dthielke.herochat.Herochat.info(message);
        }
    }

    public static void logChat(String message) {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            com.dthielke.herochat.Herochat.logChat(message);
        }
    }

    public static void setChatLogEnabled(boolean chatLogEnabled) {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            com.dthielke.herochat.Herochat.setChatLogEnabled(chatLogEnabled);
        }
    }

    public static void setLocale(Locale locale) throws ClassNotFoundException {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            com.dthielke.herochat.Herochat.setLocale(locale);
        }
    }

    public static void setLogToBukkitEnabled(boolean enabled) {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            com.dthielke.herochat.Herochat.setLogToBukkitEnabled(enabled);
        }
    }

    public static void severe(String message) {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            com.dthielke.herochat.Herochat.severe(message);
        }
    }

    public static void warning(String message) {
        if (herochat == null) {
            loadPlugin();
        }

        if (herochat != null) {
            com.dthielke.herochat.Herochat.warning(message);
        }
    }

    public static class Channel {
        private com.dthielke.herochat.Channel channel;

        public Channel(com.dthielke.herochat.Channel channel) {
            this.channel = channel;
        }

        public boolean isTransient() {
            return channel.isTransient();
        }

        public String getName() {
            return channel.getName();
        }

        public boolean addMember(Chatter chatter, boolean announce, boolean flagUpdate) {
            return channel.addMember(chatter.chatter, announce, flagUpdate);
        }

        public void addWorld(String arg0) {
            channel.addWorld(arg0);
        }

        public void announce(String arg0) {
            channel.announce(arg0);
        }

        public String applyFormat(String arg0, String arg1) {
            return channel.applyFormat(arg0, arg1);
        }

        public String applyFormat(String arg0, String arg1, Player arg2) {
            return channel.applyFormat(arg0, arg1, arg2);
        }

        public void attachStorage(ChannelStorage arg0) {
            channel.attachStorage(arg0.channelStorage);
        }

        public boolean banMember(Chatter arg0, boolean arg1) {
            return channel.banMember(arg0.chatter, arg1);
        }

        public void emote(Chatter arg0, String arg1) {
            channel.emote(arg0.chatter, arg1);
        }

        public Set<String> getBans() {
            return channel.getBans();
        }

        public ChatColor getColor() {
            return channel.getColor();
        }

        public int getDistance() {
            return channel.getDistance();
        }

        public String getFormat() {
            return channel.getFormat();
        }

        public Set<Chatter> getMembers() {
            Set<com.dthielke.herochat.Chatter> members = channel.getMembers();

            HashSet<Chatter> set = null;
            if (members != null) {
                set = new HashSet<Chatter>();
                for (com.dthielke.herochat.Chatter member : members) {
                    set.add(new Chatter(member));
                }
            }
            return set;
        }

        public Set<String> getModerators() {
            return channel.getModerators();
        }

        public Set<String> getMutes() {
            return channel.getMutes();
        }

        public String getNick() {
            return channel.getNick();
        }

        public String getPassword() {
            return channel.getPassword();
        }

        public ChannelStorage getStorage() {
            return new ChannelStorage(channel.getStorage());
        }

        public Set<String> getWorlds() {
            return channel.getWorlds();
        }

        public boolean hasWorld(String arg0) {
            return channel.hasWorld(arg0);
        }

        public boolean hasWorld(World arg0) {
            return channel.hasWorld(arg0);
        }

        public boolean isBanned(String arg0) {
            return channel.isBanned(arg0);
        }

        public boolean isCrossWorld() {
            return channel.isCrossWorld();
        }

        public boolean isHidden() {
            return channel.isHidden();
        }

        public boolean isLocal() {
            return channel.isLocal();
        }

        public boolean isMember(Chatter arg0) {
            return channel.isMember(arg0.chatter);
        }

        public boolean isModerator(String arg0) {
            return channel.isModerator(arg0);
        }

        public boolean isMuted() {
            return channel.isMuted();
        }

        public boolean isMuted(String arg0) {
            return channel.isMuted(arg0);
        }

        public boolean isShortcutAllowed() {
            return channel.isShortcutAllowed();
        }

        public boolean isVerbose() {
            return channel.isVerbose();
        }

        public boolean kickMember(Chatter arg0, boolean arg1) {
            return channel.kickMember(arg0.chatter, arg1);
        }

        public void onFocusGain(Chatter arg0) {
            channel.onFocusGain(arg0.chatter);
        }

        public void onFocusLoss(Chatter arg0) {
            channel.onFocusLoss(arg0.chatter);
        }

        public void processChat(ChannelChatEvent arg0) {
            channel.processChat(arg0.channelChatEvent);
        }

        public boolean removeMember(Chatter chatter, boolean announce, boolean flagUpdate) {
            return channel.removeMember(chatter.chatter, announce, flagUpdate);
        }

        public void removeWorld(String arg0) {
            channel.removeWorld(arg0);
        }

        public void sendRawMessage(String arg0) {
            channel.sendRawMessage(arg0);
        }

        public void setBanned(String arg0, boolean arg1) {
            channel.setBanned(arg0, arg1);
        }

        public void setBans(Set<String> arg0) {
            channel.setBans(arg0);
        }

        public void setColor(ChatColor arg0) {
            channel.setColor(arg0);
        }

        public void setCrossWorld(boolean arg0) {
            channel.setCrossWorld(arg0);
        }

        public void setDistance(int arg0) {
            channel.setDistance(arg0);
        }

        public void setFormat(String arg0) {
            channel.setFormat(arg0);
        }

        public void setModerator(String arg0, boolean arg1) {
            channel.setModerator(arg0, arg1);
        }

        public void setModerators(Set<String> arg0) {
            channel.setModerators(arg0);
        }

        public void setMuted(boolean arg0) {
            channel.setMuted(arg0);
        }

        public void setMuted(String arg0, boolean arg1) {
            channel.setMuted(arg0, arg1);
        }

        public void setMutes(Set<String> arg0) {
            channel.setMutes(arg0);
        }

        public void setNick(String arg0) {
            channel.setNick(arg0);
        }

        public void setPassword(String arg0) {
            channel.setPassword(arg0);
        }

        public void setShortcutAllowed(boolean arg0) {
            channel.setShortcutAllowed(arg0);
        }

        public void setVerbose(boolean arg0) {
            channel.setVerbose(arg0);
        }

        public void setWorlds(Set<String> arg0) {
            channel.setWorlds(arg0);
        }
    }

    public static class Chatter {
        private com.dthielke.herochat.Chatter chatter;

        public Chatter(com.dthielke.herochat.Chatter chatter) {
            this.chatter = chatter;
        }

        public boolean addChannel(Channel channel, boolean announce, boolean flagUpdate) {
            return chatter.addChannel(channel.channel, announce, flagUpdate);
        }

        public void attachStorage(ChatterStorage arg0) {
            chatter.attachStorage(arg0.chatterStorage);
        }

        public Result canBan(Channel arg0) {
            return Result.convert(chatter.canBan(arg0.channel));
        }

        public Result canColorMessages(Channel arg0, ChatColor arg1) {
            return Result.convert(chatter.canColorMessages(arg0.channel, arg1));
        }

        public Result canEmote(Channel arg0) {
            return Result.convert(chatter.canEmote(arg0.channel));
        }

        public Result canFocus(Channel arg0) {
            return Result.convert(chatter.canFocus(arg0.channel));
        }

        public Result canIgnore(Chatter arg0) {
            return Result.convert(chatter.canIgnore(arg0.chatter));
        }

        public Result canJoin(Channel arg0, String password) {
            return Result.convert(chatter.canJoin(arg0.channel, password));
        }

        public Result canKick(Channel arg0) {
            return Result.convert(chatter.canKick(arg0.channel));
        }

        public Result canLeave(Channel arg0) {
            return Result.convert(chatter.canLeave(arg0.channel));
        }

        public Result canModify(String arg0, Channel arg1) {
            return Result.convert(chatter.canModify(arg0, arg1.channel));
        }

        public Result canMute(Channel arg0) {
            return Result.convert(chatter.canMute(arg0.channel));
        }

        public Result canRemove(Channel arg0) {
            return Result.convert(chatter.canRemove(arg0.channel));
        }

        public Result canSpeak(Channel arg0) {
            return Result.convert(chatter.canSpeak(arg0.channel));
        }

        public Result canViewInfo(Channel arg0) {
            return Result.convert(chatter.canViewInfo(arg0.channel));
        }

        public void disconnect() {
            chatter.disconnect();
        }

        public String getAFKMessage() {
            return chatter.getAFKMessage();
        }

        public Channel getActiveChannel() {
            return new Channel(chatter.getActiveChannel());
        }

        public Set<Channel> getChannels() {
            Set<com.dthielke.herochat.Channel> channels = chatter.getChannels();

            HashSet<Channel> set = null;
            if (channels != null) {
                set = new HashSet<Channel>();
                for (com.dthielke.herochat.Channel channel : channels) {
                    set.add(new Channel(channel));
                }
            }
            return set;
        }

        public Set<String> getIgnores() {
            return chatter.getIgnores();
        }

        public Channel getLastActiveChannel() {
            return new Channel(chatter.getLastActiveChannel());
        }

        public Channel getLastFocusableChannel() {
            return new Channel(chatter.getLastFocusableChannel());
        }

        public Chatter getLastPrivateMessageSource() {
            return new Chatter(chatter.getLastPrivateMessageSource());
        }

        public String getName() {
            return chatter.getName();
        }

        public Player getPlayer() {
            return chatter.getPlayer();
        }

        public ChatterStorage getStorage() {
            return new ChatterStorage(chatter.getStorage());
        }

        public boolean hasChannel(Channel arg0) {
            return chatter.hasChannel(arg0.channel);
        }

        public boolean isAFK() {
            return chatter.isAFK();
        }

        public boolean isIgnoring(Chatter arg0) {
            return chatter.isIgnoring(arg0.chatter);
        }

        public boolean isIgnoring(String arg0) {
            return chatter.isIgnoring(arg0);
        }

        public boolean isInRange(Chatter arg0, int arg1) {
            return chatter.isInRange(arg0.chatter, arg1);
        }

        public boolean isMuted() {
            return chatter.isMuted();
        }

        public void refocus() {
            chatter.refocus();
        }

        public boolean removeChannel(Channel channel, boolean announce, boolean flagUpdate) {
            return chatter.removeChannel(channel.channel, announce, flagUpdate);
        }

        public void setAFK(boolean arg0) {
            chatter.setAFK(arg0);
        }

        public void setAFKMessage(String arg0) {
            chatter.setAFKMessage(arg0);
        }

        public void setActiveChannel(Channel channel, boolean announce, boolean flagUpdate) {
            chatter.setActiveChannel(channel.channel, announce, flagUpdate);
        }

        public void setIgnore(String arg0, boolean arg1, boolean arg2) {
            chatter.setIgnore(arg0, arg1, arg2);
        }

        public void setLastPrivateMessageSource(Chatter arg0) {
            chatter.setLastPrivateMessageSource(arg0.chatter);
        }

        public void setMuted(boolean arg0, boolean arg1) {
            chatter.setMuted(arg0, arg1);
        }

        public boolean shouldAutoJoin(Channel arg0) {
            return chatter.shouldAutoJoin(arg0.channel);
        }

        public boolean shouldForceJoin(Channel arg0) {
            return chatter.shouldForceJoin(arg0.channel);
        }

        public boolean shouldForceLeave(Channel arg0) {
            return chatter.shouldForceLeave(arg0.channel);
        }
    }

    public static enum Result {
        NO_PERMISSION, NO_CHANNEL, INVALID, BANNED, MUTED, ALLOWED, BAD_WORLD, BAD_PASSWORD, FAIL;

        private static com.dthielke.herochat.Chatter.Result convert(Result result) {
            switch (result) {
            case ALLOWED:
                return com.dthielke.herochat.Chatter.Result.ALLOWED;
            case BAD_PASSWORD:
                return com.dthielke.herochat.Chatter.Result.BAD_PASSWORD;
            case BAD_WORLD:
                return com.dthielke.herochat.Chatter.Result.BAD_WORLD;
            case BANNED:
                return com.dthielke.herochat.Chatter.Result.BANNED;
            case FAIL:
                return com.dthielke.herochat.Chatter.Result.FAIL;
            case INVALID:
                return com.dthielke.herochat.Chatter.Result.INVALID;
            case MUTED:
                return com.dthielke.herochat.Chatter.Result.MUTED;
            case NO_CHANNEL:
                return com.dthielke.herochat.Chatter.Result.NO_CHANNEL;
            case NO_PERMISSION:
                return com.dthielke.herochat.Chatter.Result.NO_PERMISSION;
            }
            return null;
        }

        private static Result convert(com.dthielke.herochat.Chatter.Result result) {
            switch (result) {
            case ALLOWED:
                return ALLOWED;
            case BAD_PASSWORD:
                return BAD_PASSWORD;
            case BAD_WORLD:
                return BAD_WORLD;
            case BANNED:
                return BANNED;
            case FAIL:
                return FAIL;
            case INVALID:
                return INVALID;
            case MUTED:
                return MUTED;
            case NO_CHANNEL:
                return NO_CHANNEL;
            case NO_PERMISSION:
                return NO_PERMISSION;
            }
            return null;
        }
    }

    public static enum Permission {
        JOIN, LEAVE, SPEAK, EMOTE, KICK, BAN, MUTE, REMOVE, COLOR, INFO, FOCUS, AUTOJOIN, FORCE_JOIN, FORCE_LEAVE, MODIFY_NICK, MODIFY_COLOR, MODIFY_DISTANCE, MODIFY_FORMAT, MODIFY_SHORTCUT, MODIFY_PASSWORD, MODIFY_VERBOSE, MODIFY_FOCUSABLE, MODIFY_CROSSWORLD, MODIFY_CHATCOST, BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE, MAGIC, BOLD, STRIKETHROUGH, UNDERLINE, ITALIC, RESET;

        private static com.dthielke.herochat.Chatter.Permission convert(Permission permission) {
            switch (permission) {
            case AQUA:
                return com.dthielke.herochat.Chatter.Permission.AQUA;
            case AUTOJOIN:
                return com.dthielke.herochat.Chatter.Permission.AUTOJOIN;
            case BAN:
                return com.dthielke.herochat.Chatter.Permission.BAN;
            case BLACK:
                return com.dthielke.herochat.Chatter.Permission.BLACK;
            case BLUE:
                return com.dthielke.herochat.Chatter.Permission.BLUE;
            case BOLD:
                return com.dthielke.herochat.Chatter.Permission.BOLD;
            case COLOR:
                return com.dthielke.herochat.Chatter.Permission.COLOR;
            case DARK_AQUA:
                return com.dthielke.herochat.Chatter.Permission.DARK_AQUA;
            case DARK_BLUE:
                return com.dthielke.herochat.Chatter.Permission.DARK_BLUE;
            case DARK_GRAY:
                return com.dthielke.herochat.Chatter.Permission.DARK_GRAY;
            case DARK_GREEN:
                return com.dthielke.herochat.Chatter.Permission.DARK_GREEN;
            case DARK_PURPLE:
                return com.dthielke.herochat.Chatter.Permission.DARK_PURPLE;
            case DARK_RED:
                return com.dthielke.herochat.Chatter.Permission.DARK_RED;
            case EMOTE:
                return com.dthielke.herochat.Chatter.Permission.EMOTE;
            case FOCUS:
                return com.dthielke.herochat.Chatter.Permission.FOCUS;
            case FORCE_JOIN:
                return com.dthielke.herochat.Chatter.Permission.FORCE_JOIN;
            case FORCE_LEAVE:
                return com.dthielke.herochat.Chatter.Permission.FORCE_LEAVE;
            case GOLD:
                return com.dthielke.herochat.Chatter.Permission.GOLD;
            case GRAY:
                return com.dthielke.herochat.Chatter.Permission.GRAY;
            case GREEN:
                return com.dthielke.herochat.Chatter.Permission.GREEN;
            case INFO:
                return com.dthielke.herochat.Chatter.Permission.INFO;
            case ITALIC:
                return com.dthielke.herochat.Chatter.Permission.ITALIC;
            case JOIN:
                return com.dthielke.herochat.Chatter.Permission.JOIN;
            case KICK:
                return com.dthielke.herochat.Chatter.Permission.KICK;
            case LEAVE:
                return com.dthielke.herochat.Chatter.Permission.LEAVE;
            case LIGHT_PURPLE:
                return com.dthielke.herochat.Chatter.Permission.LIGHT_PURPLE;
            case MAGIC:
                return com.dthielke.herochat.Chatter.Permission.MAGIC;
            case MODIFY_CHATCOST:
                return com.dthielke.herochat.Chatter.Permission.MODIFY_CHATCOST;
            case MODIFY_COLOR:
                return com.dthielke.herochat.Chatter.Permission.MODIFY_COLOR;
            case MODIFY_CROSSWORLD:
                return com.dthielke.herochat.Chatter.Permission.MODIFY_CROSSWORLD;
            case MODIFY_DISTANCE:
                return com.dthielke.herochat.Chatter.Permission.MODIFY_DISTANCE;
            case MODIFY_FOCUSABLE:
                return com.dthielke.herochat.Chatter.Permission.MODIFY_FOCUSABLE;
            case MODIFY_FORMAT:
                return com.dthielke.herochat.Chatter.Permission.MODIFY_FORMAT;
            case MODIFY_NICK:
                return com.dthielke.herochat.Chatter.Permission.MODIFY_NICK;
            case MODIFY_PASSWORD:
                return com.dthielke.herochat.Chatter.Permission.MODIFY_PASSWORD;
            case MODIFY_SHORTCUT:
                return com.dthielke.herochat.Chatter.Permission.MODIFY_SHORTCUT;
            case MODIFY_VERBOSE:
                return com.dthielke.herochat.Chatter.Permission.MODIFY_VERBOSE;
            case MUTE:
                return com.dthielke.herochat.Chatter.Permission.MUTE;
            case RED:
                return com.dthielke.herochat.Chatter.Permission.RED;
            case REMOVE:
                return com.dthielke.herochat.Chatter.Permission.REMOVE;
            case RESET:
                return com.dthielke.herochat.Chatter.Permission.RESET;
            case SPEAK:
                return com.dthielke.herochat.Chatter.Permission.SPEAK;
            case STRIKETHROUGH:
                return com.dthielke.herochat.Chatter.Permission.STRIKETHROUGH;
            case UNDERLINE:
                return com.dthielke.herochat.Chatter.Permission.UNDERLINE;
            case WHITE:
                return com.dthielke.herochat.Chatter.Permission.WHITE;
            case YELLOW:
                return com.dthielke.herochat.Chatter.Permission.YELLOW;
            }
            return null;
        }

        private static Permission convert(com.dthielke.herochat.Chatter.Permission permission) {
            switch (permission) {
            case AQUA:
                return AQUA;
            case AUTOJOIN:
                return AUTOJOIN;
            case BAN:
                return BAN;
            case BLACK:
                return BLACK;
            case BLUE:
                return BLUE;
            case BOLD:
                return BOLD;
            case COLOR:
                return COLOR;
            case DARK_AQUA:
                return DARK_AQUA;
            case DARK_BLUE:
                return DARK_BLUE;
            case DARK_GRAY:
                return DARK_GRAY;
            case DARK_GREEN:
                return DARK_GREEN;
            case DARK_PURPLE:
                return DARK_PURPLE;
            case DARK_RED:
                return DARK_RED;
            case EMOTE:
                return EMOTE;
            case FOCUS:
                return FOCUS;
            case FORCE_JOIN:
                return FORCE_JOIN;
            case FORCE_LEAVE:
                return FORCE_LEAVE;
            case GOLD:
                return GOLD;
            case GRAY:
                return GRAY;
            case GREEN:
                return GREEN;
            case INFO:
                return INFO;
            case ITALIC:
                return ITALIC;
            case JOIN:
                return JOIN;
            case KICK:
                return KICK;
            case LEAVE:
                return LEAVE;
            case LIGHT_PURPLE:
                return LIGHT_PURPLE;
            case MAGIC:
                return MAGIC;
            case MODIFY_CHATCOST:
                return MODIFY_CHATCOST;
            case MODIFY_COLOR:
                return MODIFY_COLOR;
            case MODIFY_CROSSWORLD:
                return MODIFY_CROSSWORLD;
            case MODIFY_DISTANCE:
                return MODIFY_DISTANCE;
            case MODIFY_FOCUSABLE:
                return MODIFY_FOCUSABLE;
            case MODIFY_FORMAT:
                return MODIFY_FORMAT;
            case MODIFY_NICK:
                return MODIFY_NICK;
            case MODIFY_PASSWORD:
                return MODIFY_PASSWORD;
            case MODIFY_SHORTCUT:
                return MODIFY_SHORTCUT;
            case MODIFY_VERBOSE:
                return MODIFY_VERBOSE;
            case MUTE:
                return MUTE;
            case RED:
                return RED;
            case REMOVE:
                return REMOVE;
            case RESET:
                return RESET;
            case SPEAK:
                return SPEAK;
            case STRIKETHROUGH:
                return STRIKETHROUGH;
            case UNDERLINE:
                return UNDERLINE;
            case WHITE:
                return WHITE;
            case YELLOW:
                return YELLOW;
            }
            return null;
        }

        public String form(Channel channel) {
            return convert(this).form(channel.channel);
        }

        public String formAll() {
            return convert(this).formAll();
        }

        public String formWildcard() {
            return convert(this).formWildcard();
        }
    }

    public static class ChannelManager {
        private com.dthielke.herochat.ChannelManager channelManager;

        public ChannelManager(com.dthielke.herochat.ChannelManager channelManager) {
            this.channelManager = channelManager;
        }

        public List<Channel> getChannels() {
            List<com.dthielke.herochat.Channel> channels = channelManager.getChannels();

            ArrayList<Channel> list = null;
            if (channels != null) {
                list = new ArrayList<Channel>();
                for (com.dthielke.herochat.Channel channel : channels) {
                    list.add(new Channel(channel));
                }
            }

            return list;
        }

        public void addChannel(Channel arg0) {
            channelManager.addChannel(arg0.channel);
        }

        public void addModPermission(Permission permission) {
            channelManager.addModPermission(Permission.convert(permission));
        }

        public boolean checkModPermission(Permission permission) {
            return channelManager.checkModPermission(Permission.convert(permission));
        }

        public void clear() {
            channelManager.clear();
        }

        public String getAnnounceFormat() {
            return channelManager.getAnnounceFormat();
        }

        public Channel getChannel(String identifier) {
            return new Channel(channelManager.getChannel(identifier));
        }

        public String getConversationFormat() {
            return channelManager.getConversationFormat();
        }

        public Channel getDefaultChannel() {
            return new Channel(channelManager.getDefaultChannel());
        }

        public String getEmoteFormat() {
            return channelManager.getEmoteFormat();
        }

        public Set<Permission> getModPermissions() {
            Set<com.dthielke.herochat.Chatter.Permission> modPermissions = channelManager.getModPermissions();

            HashSet<Permission> set = null;
            if (modPermissions != null) {
                set = new HashSet<Permission>();
                for (com.dthielke.herochat.Chatter.Permission modPermission : modPermissions) {
                    set.add(Permission.convert(modPermission));
                }
            }

            return set;
        }

        public String getStandardFormat() {
            return channelManager.getStandardFormat();
        }

        public ChannelStorage getStorage() {
            return new ChannelStorage(channelManager.getStorage());
        }

        public boolean hasChannel(String identifier) {
            return channelManager.hasChannel(identifier);
        }

        public boolean isUsingEmotes() {
            return channelManager.isUsingEmotes();
        }

        public void loadChannels() {
            channelManager.loadChannels();
        }

        public void registerChannelPermissions() {
            channelManager.registerChannelPermissions();
        }

        public void removeChannel(Channel arg0) {
            channelManager.removeChannel(arg0.channel);
        }

        public void setAnnounceFormat(String announceFormat) {
            channelManager.setAnnounceFormat(announceFormat);
        }

        public void setConversationFormat(String conversationFormat) {
            channelManager.setConversationFormat(conversationFormat);
        }

        public void setDefaultChannel(Channel channel) {
            channelManager.setDefaultChannel(channel.channel);
        }

        public void setEmoteFormat(String emoteFormat) {
            channelManager.setEmoteFormat(emoteFormat);
        }

        public void setModPermissions(Set<Permission> modPermissions) {
            Set<com.dthielke.herochat.Chatter.Permission> set = null;
            if (modPermissions != null) {
                set = new HashSet<com.dthielke.herochat.Chatter.Permission>();
                for (Permission modPermission : modPermissions) {
                    set.add(Permission.convert(modPermission));
                }
            }
            channelManager.setModPermissions(set);
        }

        public void setStandardFormat(String standardFormat) {
            channelManager.setStandardFormat(standardFormat);
        }

        public void setStorage(ChannelStorage storage) {
            channelManager.setStorage(storage.channelStorage);
        }

        public void setUsingEmotes(boolean usingEmotes) {
            channelManager.setUsingEmotes(usingEmotes);
        }
    }

    @SuppressWarnings("serial")
    public static class MessageNotFoundException extends Exception {
        public MessageNotFoundException(com.dthielke.herochat.MessageNotFoundException e) {
            super(e);
        }
    }

    public static class ChatterStorage {
        private com.dthielke.herochat.ChatterStorage chatterStorage;

        public ChatterStorage(com.dthielke.herochat.ChatterStorage chatterStorage) {
            this.chatterStorage = chatterStorage;
        }

        public void flagUpdate(Chatter arg0) {
            chatterStorage.flagUpdate(arg0.chatter);
        }

        public Chatter load(String arg0) {
            return new Chatter(chatterStorage.load(arg0));
        }

        public void removeChatter(Chatter arg0) {
            chatterStorage.removeChatter(arg0.chatter);
        }

        public void update() {
            chatterStorage.update();
        }

        public void update(Chatter arg0) {
            chatterStorage.update(arg0.chatter);
        }
    }

    public static class ChannelStorage {
        private com.dthielke.herochat.ChannelStorage channelStorage;

        public ChannelStorage(com.dthielke.herochat.ChannelStorage channelStorage) {
            this.channelStorage = channelStorage;
        }
    }

    public static class ChannelChatEvent {
        private com.dthielke.herochat.ChannelChatEvent channelChatEvent;

        public ChannelChatEvent(com.dthielke.herochat.ChannelChatEvent channelChatEvent) {
            this.channelChatEvent = channelChatEvent;
        }

        public String getBukkitFormat() {
            return channelChatEvent.getBukkitFormat();
        }

        public Channel getChannel() {
            return new Channel(channelChatEvent.getChannel());
        }

        public String getFormat() {
            return channelChatEvent.getFormat();
        }

        public HandlerList getHandlers() {
            return channelChatEvent.getHandlers();
        }

        public String getMessage() {
            return channelChatEvent.getMessage();
        }

        public Result getResult() {
            return Result.convert(channelChatEvent.getResult());
        }

        public Chatter getSender() {
            return new Chatter(channelChatEvent.getSender());
        }

        public void setBukkitFormat(String bukkitFormat) {
            channelChatEvent.setBukkitFormat(bukkitFormat);
        }

        public void setChannel(Channel channel) {
            channelChatEvent.setChannel(channel.channel);
        }

        public void setFormat(String format) {
            channelChatEvent.setFormat(format);
        }

        public void setMessage(String msg) {
            channelChatEvent.setMessage(msg);
        }

        public void setResult(Result result) {
            channelChatEvent.setResult(Result.convert(result));
        }
    }

    public static class ChatterManager {
        private com.dthielke.herochat.ChatterManager chatterManager;

        public ChatterManager(com.dthielke.herochat.ChatterManager chatterManager) {
            this.chatterManager = chatterManager;
        }

        public Chatter addChatter(Player player) {
            return new Chatter(chatterManager.addChatter(player));
        }

        public void clear() {
            chatterManager.clear();
        }

        public Chatter getChatter(Player player) {
            return new Chatter(chatterManager.getChatter(player));
        }

        public Chatter getChatter(String name) {
            return new Chatter(chatterManager.getChatter(name));
        }

        public Collection<Chatter> getChatters() {
            Collection<com.dthielke.herochat.Chatter> chatters = chatterManager.getChatters();

            ArrayList<Chatter> collection = null;
            if (chatters != null) {
                collection = new ArrayList<Chatter>();
                for (com.dthielke.herochat.Chatter chatter : chatters) {
                    collection.add(new Chatter(chatter));
                }
            }
            return collection;
        }

        public ChatterStorage getStorage() {
            return new ChatterStorage(chatterManager.getStorage());
        }

        public boolean hasChatter(Player player) {
            return chatterManager.hasChatter(player);
        }

        public void removeChatter(Chatter chatter) {
            chatterManager.removeChatter(chatter.chatter);
        }

        public void removeChatter(Player player) {
            chatterManager.removeChatter(player);
        }

        public void reset() {
            chatterManager.reset();
        }

        public void setStorage(ChatterStorage storage) {
            chatterManager.setStorage(storage.chatterStorage);
        }
    }

    public static class ConfigManager {
        private com.dthielke.herochat.ConfigManager configManager;

        public ConfigManager(com.dthielke.herochat.ConfigManager configManager) {
            this.configManager = configManager;
        }

        public void load(File arg0) throws ClassNotFoundException {
            configManager.load(arg0);
        }
    }

    public static class MessageHandler {
        private com.dthielke.herochat.MessageHandler messageHandler;

        public MessageHandler(com.dthielke.herochat.MessageHandler messageHandler) {
            this.messageHandler = messageHandler;
        }

        public void handle(Player arg0, String arg1, String arg2) {
            messageHandler.handle(arg0, arg1, arg2);
        }

        public void setCensors(List<String> censors) {
            messageHandler.setCensors(censors);
        }

        public void setTwitterStyleMsgs(boolean twitterStyleMsgs) {
            messageHandler.setTwitterStyleMsgs(twitterStyleMsgs);
        }
    }
}
