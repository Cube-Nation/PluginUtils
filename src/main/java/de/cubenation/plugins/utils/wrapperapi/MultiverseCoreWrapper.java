package de.cubenation.plugins.utils.wrapperapi;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class MultiverseCoreWrapper {
    private static com.onarandombox.MultiverseCore.MultiverseCore multiverseCore;
    private static Logger log;

    public static void setLogger(Logger log) {
        MultiverseCoreWrapper.log = log;
    }

    public static void loadPlugin() {
        if (multiverseCore == null) {
            multiverseCore = (com.onarandombox.MultiverseCore.MultiverseCore) Bukkit.getServer().getPluginManager()
                    .getPlugin(WrapperManager.PLUGIN_NAME_MULTIVERSE_CORE);
            if (multiverseCore == null) {
                log.info(WrapperManager.PLUGIN_NAME_MULTIVERSE_CORE + " not found");
            }
        }
    }

    public static SafeTTeleporter getSafeTTeleporter() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            new SafeTTeleporter(multiverseCore.getSafeTTeleporter());
        }

        return null;
    }

    public static MVWorldManager getMVWorldManager() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new MVWorldManager(multiverseCore.getMVWorldManager());
        }

        return null;
    }

    public static void decrementPluginCount() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.decrementPluginCount();
        }
    }

    public static Boolean deleteWorld(String name) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.deleteWorld(name);
        }
        return null;
    }

    public static AnchorManager getAnchorManager() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new AnchorManager(multiverseCore.getAnchorManager());
        }
        return null;
    }

    public static String getAuthors() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.getAuthors();
        }
        return null;
    }

    public static GenericBank getBank() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new GenericBank(multiverseCore.getBank());
        }
        return null;
    }

    public static AllPay getBanker() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new AllPay(multiverseCore.getBanker());
        }
        return null;
    }

    public static BlockSafety getBlockSafety() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new BlockSafety(multiverseCore.getBlockSafety());
        }
        return null;
    }

    public static CommandHandler getCommandHandler() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new CommandHandler(multiverseCore.getCommandHandler());
        }
        return null;
    }

    public static MultiverseCore getCore() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new MultiverseCore(multiverseCore.getCore());
        }
        return null;
    }

    public static DestinationFactory getDestFactory() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new DestinationFactory(multiverseCore.getDestFactory());
        }
        return null;
    }

    public static MVEntityListener getEntityListener() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new MVEntityListener(multiverseCore.getEntityListener());
        }
        return null;
    }

    public static LocationManipulation getLocationManipulation() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new LocationManipulation(multiverseCore.getLocationManipulation());
        }
        return null;
    }

    public static MultiverseCoreConfig getMVConfig() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new MultiverseCoreConfig(multiverseCore.getMVConfig());
        }
        return null;
    }

    public static MVPermissions getMVPerms() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new MVPermissions(multiverseCore.getMVPerms());
        }
        return null;
    }

    public static MultiverseMessaging getMessaging() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new MultiverseMessaging(multiverseCore.getMessaging());
        }
        return null;
    }

    public static MVPlayerListener getPlayerListener() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new MVPlayerListener(multiverseCore.getPlayerListener());
        }
        return null;
    }

    public static MVPlayerSession getPlayerSession(Player player) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new MVPlayerSession(multiverseCore.getPlayerSession(player));
        }
        return null;
    }

    public static int getPluginCount() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.getPluginCount();
        }
        return 0;
    }

    public static int getProtocolVersion() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.getProtocolVersion();
        }
        return 0;
    }

    public static File getServerFolder() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.getServerFolder();
        }
        return null;
    }

    public static String getTag() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.getTag();
        }
        return null;
    }

    public static MVWeatherListener getWeatherListener() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return new MVWeatherListener(multiverseCore.getWeatherListener());
        }
        return null;
    }

    public static void incrementPluginCount() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.incrementPluginCount();
        }
    }

    public static void loadConfigs() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.loadConfigs();
        }
    }

    public static void log(Level level, String msg) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.log(level, msg);
        }
    }

    public static boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String commandLabel, String[] args) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.onCommand(sender, command, commandLabel, args);
        }
        return false;
    }

    public static void onDisable() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.onDisable();
        }
    }

    public static void onEnable() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.onEnable();
        }
    }

    public static void onLoad() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.onLoad();
        }
    }

    public static Boolean regenWorld(String arg0, Boolean arg1, Boolean arg2, String arg3) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.regenWorld(arg0, arg1, arg2, arg3);
        }
        return null;
    }

    public static void removePlayerSession(Player player) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.removePlayerSession(player);
        }
    }

    public static boolean saveMVConfig() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.saveMVConfig();
        }
        return false;
    }

    public static boolean saveMVConfigs() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.saveMVConfigs();
        }
        return false;
    }

    public static boolean saveWorldConfig() {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            return multiverseCore.saveWorldConfig();
        }
        return false;
    }

    public static void setBank(GenericBank bank) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.setBank(bank.genericBank);
        }
    }

    public static void setBlockSafety(BlockSafety bs) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.setBlockSafety(bs.blockSafety);
        }
    }

    public static void setCore(MultiverseCore core) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.setCore(core.multiverseCore);
        }
    }

    public static void setLocationManipulation(LocationManipulation locationManipulation) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.setLocationManipulation(locationManipulation.locationManipulation);
        }
    }

    public static void setSafeTTeleporter(SafeTTeleporter safeTTeleporter) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.setSafeTTeleporter(safeTTeleporter.safeTTeleporter);
        }
    }

    public static void setServerFolder(File newServerFolder) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.setServerFolder(newServerFolder);
        }
    }

    public static void showNotMVWorldMessage(CommandSender sender, String worldName) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.showNotMVWorldMessage(sender, worldName);
        }
    }

    public static void teleportPlayer(CommandSender teleporter, Player p, Location l) {
        if (multiverseCore == null) {
            loadPlugin();
        }

        if (multiverseCore != null) {
            multiverseCore.teleportPlayer(teleporter, p, l);
        }
    }

    public static class SafeTTeleporter {
        private com.onarandombox.MultiverseCore.api.SafeTTeleporter safeTTeleporter;

        public SafeTTeleporter(com.onarandombox.MultiverseCore.api.SafeTTeleporter safeTTeleporter) {
            this.safeTTeleporter = safeTTeleporter;
        }

        public Location findPortalBlockNextTo(Location arg0) {
            return safeTTeleporter.findPortalBlockNextTo(arg0);
        }

        public Location getSafeLocation(Location arg0) {
            return safeTTeleporter.getSafeLocation(arg0);
        }

        public Location getSafeLocation(Entity arg0, MVDestination arg1) {
            return safeTTeleporter.getSafeLocation(arg0, arg1.mVDestination);
        }

        public Location getSafeLocation(Location arg0, int arg1, int arg2) {
            return safeTTeleporter.getSafeLocation(arg0, arg1, arg2);
        }

        public TeleportResult safelyTeleport(CommandSender arg0, Entity arg1, MVDestination arg2) {
            com.onarandombox.MultiverseCore.enums.TeleportResult safelyTeleport = safeTTeleporter.safelyTeleport(arg0, arg1, arg2.mVDestination);

            switch (safelyTeleport) {
            case FAIL_INVALID:
                return TeleportResult.FAIL_INVALID;
            case FAIL_OTHER:
                return TeleportResult.FAIL_OTHER;
            case FAIL_PERMISSION:
                return TeleportResult.FAIL_PERMISSION;
            case FAIL_TOO_POOR:
                return TeleportResult.FAIL_TOO_POOR;
            case FAIL_UNSAFE:
                return TeleportResult.FAIL_UNSAFE;
            case SUCCESS:
                return TeleportResult.SUCCESS;
            }

            return null;
        }

        public TeleportResult safelyTeleport(CommandSender arg0, Entity arg1, Location arg2, boolean arg3) {
            com.onarandombox.MultiverseCore.enums.TeleportResult safelyTeleport = safeTTeleporter.safelyTeleport(arg0, arg1, arg2, arg3);

            switch (safelyTeleport) {
            case FAIL_INVALID:
                return TeleportResult.FAIL_INVALID;
            case FAIL_OTHER:
                return TeleportResult.FAIL_OTHER;
            case FAIL_PERMISSION:
                return TeleportResult.FAIL_PERMISSION;
            case FAIL_TOO_POOR:
                return TeleportResult.FAIL_TOO_POOR;
            case FAIL_UNSAFE:
                return TeleportResult.FAIL_UNSAFE;
            case SUCCESS:
                return TeleportResult.SUCCESS;
            }
            return null;
        }
    }

    public static class MVDestination {
        private com.onarandombox.MultiverseCore.api.MVDestination mVDestination;

        public String getIdentifier() {
            return mVDestination.getIdentifier();
        }

        public Location getLocation(Entity arg0) {
            return mVDestination.getLocation(arg0);
        }

        public String getName() {
            return mVDestination.getName();
        }

        public String getRequiredPermission() {
            return mVDestination.getRequiredPermission();
        }

        public String getType() {
            return mVDestination.getType();
        }

        public Vector getVelocity() {
            return mVDestination.getVelocity();
        }

        public boolean isThisType(JavaPlugin arg0, String arg1) {
            return mVDestination.isThisType(arg0, arg1);
        }

        public boolean isValid() {
            return mVDestination.isValid();
        }

        public void setDestination(JavaPlugin arg0, String arg1) {
            mVDestination.setDestination(arg0, arg1);
        }

        public boolean useSafeTeleporter() {
            return mVDestination.useSafeTeleporter();
        }
    }

    public static enum TeleportResult {
        FAIL_PERMISSION, FAIL_UNSAFE, FAIL_TOO_POOR, FAIL_INVALID, FAIL_OTHER, SUCCESS;
    }

    public static class MVWorldManager {
        private com.onarandombox.MultiverseCore.api.MVWorldManager mvWorldManager;

        public MVWorldManager(com.onarandombox.MultiverseCore.api.MVWorldManager mvWorldManager) {
            this.mvWorldManager = mvWorldManager;
        }

        public boolean addWorld(String arg0, Environment arg1, String arg2, WorldType arg3, Boolean arg4, String arg5) {
            return mvWorldManager.addWorld(arg0, arg1, arg2, arg3, arg4, arg5);
        }

        public boolean addWorld(String arg0, Environment arg1, String arg2, WorldType arg3, Boolean arg4, String arg5, boolean arg6) {
            return mvWorldManager.addWorld(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
        }

        public boolean deleteWorld(String arg0) {
            return mvWorldManager.deleteWorld(arg0);
        }

        public boolean deleteWorld(String arg0, boolean arg1) {
            return mvWorldManager.deleteWorld(arg0, arg1);
        }

        public ChunkGenerator getChunkGenerator(String arg0, String arg1, String arg2) {
            return mvWorldManager.getChunkGenerator(arg0, arg1, arg2);
        }

        public void getDefaultWorldGenerators() {
            mvWorldManager.getDefaultWorldGenerators();
        }

        public MultiverseWorld getFirstSpawnWorld() {
            return new MultiverseWorld(mvWorldManager.getFirstSpawnWorld());
        }

        public MultiverseWorld getMVWorld(String arg0) {
            return new MultiverseWorld(mvWorldManager.getMVWorld(arg0));
        }

        public MultiverseWorld getMVWorld(World arg0) {
            return new MultiverseWorld(mvWorldManager.getMVWorld(arg0));
        }

        public Collection<MultiverseWorld> getMVWorlds() {
            Collection<com.onarandombox.MultiverseCore.api.MultiverseWorld> mvWorlds = mvWorldManager.getMVWorlds();

            Collection<MultiverseWorld> list = null;
            if (mvWorlds != null) {
                list = new ArrayList<MultiverseWorld>();

                for (com.onarandombox.MultiverseCore.api.MultiverseWorld mvWorld : mvWorlds) {
                    list.add(new MultiverseWorld(mvWorld));
                }
            }

            return list;
        }

        public MultiverseWorld getSpawnWorld() {
            return new MultiverseWorld(mvWorldManager.getSpawnWorld());
        }

        public WorldPurger getTheWorldPurger() {
            return new WorldPurger(mvWorldManager.getTheWorldPurger());
        }

        public List<String> getUnloadedWorlds() {
            return mvWorldManager.getUnloadedWorlds();
        }

        public boolean isMVWorld(String arg0) {
            return mvWorldManager.isMVWorld(arg0);
        }

        public boolean isMVWorld(World arg0) {
            return mvWorldManager.isMVWorld(arg0);
        }

        public void loadDefaultWorlds() {
            mvWorldManager.loadDefaultWorlds();
        }

        public boolean loadWorld(String arg0) {
            return mvWorldManager.loadWorld(arg0);
        }

        public FileConfiguration loadWorldConfig(File arg0) {
            return mvWorldManager.loadWorldConfig(arg0);
        }

        public void loadWorlds(boolean arg0) {
            mvWorldManager.loadWorlds(arg0);
        }

        public void removePlayersFromWorld(String arg0) {
            mvWorldManager.removePlayersFromWorld(arg0);
        }

        public boolean removeWorldFromConfig(String arg0) {
            return mvWorldManager.removeWorldFromConfig(arg0);
        }

        public boolean saveWorldsConfig() {
            return mvWorldManager.saveWorldsConfig();
        }

        public void setFirstSpawnWorld(String arg0) {
            mvWorldManager.setFirstSpawnWorld(arg0);
        }

        public boolean unloadWorld(String arg0) {
            return mvWorldManager.unloadWorld(arg0);
        }
    }

    public static class MultiverseWorld {
        private com.onarandombox.MultiverseCore.api.MultiverseWorld multiverseWorld;

        public MultiverseWorld(com.onarandombox.MultiverseCore.api.MultiverseWorld multiverseWorld) {
            this.multiverseWorld = multiverseWorld;
        }

        public void allowPortalMaking(AllowedPortalType arg0) {
            com.onarandombox.MultiverseCore.enums.AllowedPortalType allowedPortal = null;

            switch (arg0) {
            case ALL:
                allowedPortal = com.onarandombox.MultiverseCore.enums.AllowedPortalType.ALL;
                break;
            case END:
                allowedPortal = com.onarandombox.MultiverseCore.enums.AllowedPortalType.END;
                break;
            case NETHER:
                allowedPortal = com.onarandombox.MultiverseCore.enums.AllowedPortalType.NETHER;
                break;
            case NONE:
                allowedPortal = com.onarandombox.MultiverseCore.enums.AllowedPortalType.NONE;
                break;
            }

            multiverseWorld.allowPortalMaking(allowedPortal);
        }

        public boolean canAnimalsSpawn() {
            return multiverseWorld.canAnimalsSpawn();
        }

        public boolean canMonstersSpawn() {
            return multiverseWorld.canMonstersSpawn();
        }

        public Permission getAccessPermission() {
            return multiverseWorld.getAccessPermission();
        }

        public boolean getAdjustSpawn() {
            return multiverseWorld.getAdjustSpawn();
        }

        public String getAlias() {
            return multiverseWorld.getAlias();
        }

        public String getAllPropertyNames() {
            return multiverseWorld.getAllPropertyNames();
        }

        public AllowedPortalType getAllowedPortals() {
            com.onarandombox.MultiverseCore.enums.AllowedPortalType allowedPortals = multiverseWorld.getAllowedPortals();

            switch (allowedPortals) {
            case ALL:
                return AllowedPortalType.ALL;
            case END:
                return AllowedPortalType.END;
            case NETHER:
                return AllowedPortalType.NETHER;
            case NONE:
                return AllowedPortalType.NONE;
            }
            return null;
        }

        public List<String> getAnimalList() {
            return multiverseWorld.getAnimalList();
        }

        public boolean getAutoHeal() {
            return multiverseWorld.getAutoHeal();
        }

        public boolean getAutoLoad() {
            return multiverseWorld.getAutoLoad();
        }

        public boolean getBedRespawn() {
            return multiverseWorld.getBedRespawn();
        }

        public World getCBWorld() {
            return multiverseWorld.getCBWorld();
        }

        public ChatColor getColor() {
            return multiverseWorld.getColor();
        }

        public String getColoredWorldString() {
            return multiverseWorld.getColoredWorldString();
        }

        public int getCurrency() {
            return multiverseWorld.getCurrency();
        }

        public Difficulty getDifficulty() {
            return multiverseWorld.getDifficulty();
        }

        public Environment getEnvironment() {
            return multiverseWorld.getEnvironment();
        }

        public Permission getExemptPermission() {
            return multiverseWorld.getExemptPermission();
        }

        public GameMode getGameMode() {
            return multiverseWorld.getGameMode();
        }

        public String getGenerator() {
            return multiverseWorld.getGenerator();
        }

        public boolean getHunger() {
            return multiverseWorld.getHunger();
        }

        public List<String> getMonsterList() {
            return multiverseWorld.getMonsterList();
        }

        public String getName() {
            return multiverseWorld.getName();
        }

        public String getPermissibleName() {
            return multiverseWorld.getPermissibleName();
        }

        public double getPrice() {
            return multiverseWorld.getPrice();
        }

        public String getPropertyHelp(String arg0) throws PropertyDoesNotExistException {
            try {
                return multiverseWorld.getPropertyHelp(arg0);
            } catch (com.onarandombox.MultiverseCore.exceptions.PropertyDoesNotExistException e) {
                throw new PropertyDoesNotExistException(e);
            }
        }

        public String getPropertyValue(String arg0) throws PropertyDoesNotExistException {
            try {
                return multiverseWorld.getPropertyValue(arg0);
            } catch (com.onarandombox.MultiverseCore.exceptions.PropertyDoesNotExistException e) {
                throw new PropertyDoesNotExistException(e);
            }
        }

        public World getRespawnToWorld() {
            return multiverseWorld.getRespawnToWorld();
        }

        public double getScaling() {
            return multiverseWorld.getScaling();
        }

        public long getSeed() {
            return multiverseWorld.getSeed();
        }

        public Location getSpawnLocation() {
            return multiverseWorld.getSpawnLocation();
        }

        public ChatColor getStyle() {
            return multiverseWorld.getStyle();
        }

        public String getTime() {
            return multiverseWorld.getTime();
        }

        public List<String> getWorldBlacklist() {
            return multiverseWorld.getWorldBlacklist();
        }

        public WorldType getWorldType() {
            return multiverseWorld.getWorldType();
        }

        public boolean isHidden() {
            return multiverseWorld.isHidden();
        }

        public boolean isKeepingSpawnInMemory() {
            return multiverseWorld.isKeepingSpawnInMemory();
        }

        public boolean isPVPEnabled() {
            return multiverseWorld.isPVPEnabled();
        }

        public boolean isWeatherEnabled() {
            return multiverseWorld.isWeatherEnabled();
        }

        public void setAdjustSpawn(boolean arg0) {
            multiverseWorld.setAdjustSpawn(arg0);
        }

        public void setAlias(String arg0) {
            multiverseWorld.setAlias(arg0);
        }

        public void setAllowAnimalSpawn(boolean arg0) {
            multiverseWorld.setAllowAnimalSpawn(arg0);
        }

        public void setAllowMonsterSpawn(boolean arg0) {
            multiverseWorld.setAllowMonsterSpawn(arg0);
        }

        public void setAutoHeal(boolean arg0) {
            multiverseWorld.setAutoHeal(arg0);
        }

        public void setAutoLoad(boolean arg0) {
            multiverseWorld.setAutoLoad(arg0);
        }

        public void setBedRespawn(boolean arg0) {
            multiverseWorld.setBedRespawn(arg0);
        }

        public boolean setColor(String arg0) {
            return multiverseWorld.setColor(arg0);
        }

        public void setCurrency(int arg0) {
            multiverseWorld.setCurrency(arg0);
        }

        public boolean setDifficulty(Difficulty arg0) {
            return multiverseWorld.setDifficulty(arg0);
        }

        public void setEnableWeather(boolean arg0) {
            multiverseWorld.setEnableWeather(arg0);
        }

        public void setEnvironment(Environment arg0) {
            multiverseWorld.setEnvironment(arg0);
        }

        public boolean setGameMode(GameMode arg0) {
            return multiverseWorld.setGameMode(arg0);
        }

        public void setGenerator(String arg0) {
            multiverseWorld.setGenerator(arg0);
        }

        public void setHidden(boolean arg0) {
            multiverseWorld.setHidden(arg0);
        }

        public void setHunger(boolean arg0) {
            multiverseWorld.setHunger(arg0);
        }

        public void setKeepSpawnInMemory(boolean arg0) {
            multiverseWorld.setKeepSpawnInMemory(arg0);
        }

        public void setPVPMode(boolean arg0) {
            multiverseWorld.setPVPMode(arg0);
        }

        public void setPrice(double arg0) {
            multiverseWorld.setPrice(arg0);
        }

        public boolean setPropertyValue(String arg0, String arg1) throws PropertyDoesNotExistException {
            try {
                return multiverseWorld.setPropertyValue(arg0, arg1);
            } catch (com.onarandombox.MultiverseCore.exceptions.PropertyDoesNotExistException e) {
                throw new PropertyDoesNotExistException(e);
            }
        }

        public boolean setRespawnToWorld(String arg0) {
            return multiverseWorld.setRespawnToWorld(arg0);
        }

        public boolean setScaling(double arg0) {
            return multiverseWorld.setScaling(arg0);
        }

        public void setSeed(long arg0) {
            multiverseWorld.setSeed(arg0);
        }

        public void setSpawnLocation(Location arg0) {
            multiverseWorld.setSpawnLocation(arg0);
        }

        public boolean setStyle(String arg0) {
            return multiverseWorld.setStyle(arg0);
        }

        public boolean setTime(String arg0) {
            return multiverseWorld.setTime(arg0);
        }
    }

    public static class WorldPurger {
        private com.onarandombox.MultiverseCore.api.WorldPurger worldPurger;

        public WorldPurger(com.onarandombox.MultiverseCore.api.WorldPurger worldPurger) {
            this.worldPurger = worldPurger;
        }

        public void purgeWorld(MultiverseWorld arg0) {
            worldPurger.purgeWorld(arg0.multiverseWorld);
        }

        public void purgeWorld(MultiverseWorld arg0, List<String> arg1, boolean arg2, boolean arg3) {
            worldPurger.purgeWorld(arg0.multiverseWorld, arg1, arg2, arg3);
        }

        public void purgeWorld(MultiverseWorld arg0, List<String> arg1, boolean arg2, boolean arg3, CommandSender arg4) {
            worldPurger.purgeWorld(arg0.multiverseWorld, arg1, arg2, arg3, arg4);
        }

        public void purgeWorlds(List<MultiverseWorld> arg0) {
            List<com.onarandombox.MultiverseCore.api.MultiverseWorld> list = null;
            if (arg0 != null) {
                list = new ArrayList<com.onarandombox.MultiverseCore.api.MultiverseWorld>();
                for (MultiverseWorld arg : arg0) {
                    list.add(arg.multiverseWorld);
                }
            }

            worldPurger.purgeWorlds(list);
        }
    }

    public static enum AllowedPortalType {
        NONE, ALL, NETHER, END;
    }

    public static class PropertyDoesNotExistException extends Exception {
        private static final long serialVersionUID = 4739997593822009017L;

        public PropertyDoesNotExistException(com.onarandombox.MultiverseCore.exceptions.PropertyDoesNotExistException e) {
            super(e);
        }
    }

    public static class AnchorManager {
        private com.onarandombox.MultiverseCore.utils.AnchorManager anchorManager;

        public AnchorManager(com.onarandombox.MultiverseCore.utils.AnchorManager anchorManager) {
            this.anchorManager = anchorManager;
        }

        public boolean deleteAnchor(String s) {
            return anchorManager.deleteAnchor(s);
        }

        public Set<String> getAllAnchors() {
            return anchorManager.getAllAnchors();
        }

        public Location getAnchorLocation(String anchor) {
            return anchorManager.getAnchorLocation(anchor);
        }

        public Set<String> getAnchors(Player arg0) {
            return anchorManager.getAnchors(arg0);
        }

        public void loadAnchors() {
            anchorManager.loadAnchors();
        }

        public boolean saveAnchorLocation(String anchor, Location l) {
            return anchorManager.saveAnchorLocation(anchor, l);
        }

        public boolean saveAnchorLocation(String anchor, String location) {
            return anchorManager.saveAnchorLocation(anchor, location);
        }

        public boolean saveAnchors() {
            return anchorManager.saveAnchors();
        }
    }

    public static class GenericBank {
        private com.fernferret.allpay.multiverse.GenericBank genericBank;

        public GenericBank(com.fernferret.allpay.multiverse.GenericBank genericBank) {
            this.genericBank = genericBank;
        }

        public double getBalance(Player player, int itemId) {
            return genericBank.getBalance(player, itemId);
        }

        public String getEconUsed() {
            return genericBank.getEconUsed();
        }

        public boolean setBalance(Player player, int itemId, double amount) {
            return genericBank.setBalance(player, itemId, amount);
        }

        public void toggleReceipts(boolean showRecipts) {
            genericBank.toggleReceipts(showRecipts);
        }
    }

    public static class AllPay {
        private com.fernferret.allpay.multiverse.AllPay allPay;

        public AllPay(com.fernferret.allpay.multiverse.AllPay allPay) {
            this.allPay = allPay;
        }

        public com.fernferret.allpay.multiverse.GenericBank getEconPlugin() {
            return allPay.getEconPlugin();
        }

        public double getVersion() {
            return allPay.getVersion();
        }

        public com.fernferret.allpay.multiverse.GenericBank loadEconPlugin() {
            return allPay.loadEconPlugin();
        }
    }

    public static class BlockSafety {
        private com.onarandombox.MultiverseCore.api.BlockSafety blockSafety;

        public BlockSafety(com.onarandombox.MultiverseCore.api.BlockSafety blockSafety) {
            this.blockSafety = blockSafety;
        }

        public boolean canSpawnCartSafely(Minecart arg0) {
            return blockSafety.canSpawnCartSafely(arg0);
        }

        public boolean canSpawnVehicleSafely(Vehicle arg0) {
            return blockSafety.canSpawnVehicleSafely(arg0);
        }

        public Location getBottomBlock(Location arg0) {
            return blockSafety.getBottomBlock(arg0);
        }

        public Location getTopBlock(Location arg0) {
            return blockSafety.getTopBlock(arg0);
        }

        public boolean isBlockAboveAir(Location arg0) {
            return blockSafety.isBlockAboveAir(arg0);
        }

        public boolean isEntitiyOnTrack(Location arg0) {
            return blockSafety.isEntitiyOnTrack(arg0);
        }

        public boolean playerCanSpawnHereSafely(Location arg0) {
            return blockSafety.playerCanSpawnHereSafely(arg0);
        }

        public boolean playerCanSpawnHereSafely(World arg0, double arg1, double arg2, double arg3) {
            return blockSafety.playerCanSpawnHereSafely(arg0, arg1, arg2, arg3);
        }
    }

    public static class CommandHandler {
        private com.pneumaticraft.commandhandler.multiverse.CommandHandler commandHandler;

        public CommandHandler(com.pneumaticraft.commandhandler.multiverse.CommandHandler commandHandler) {
            this.commandHandler = commandHandler;
        }

        public void cancelQueuedCommand(CommandSender arg0) {
            commandHandler.cancelQueuedCommand(arg0);
        }

        public boolean confirmQueuedCommand(CommandSender arg0) {
            return commandHandler.confirmQueuedCommand(arg0);
        }

        public List<Command> getAllCommands() {
            List<com.pneumaticraft.commandhandler.multiverse.Command> allCommands = commandHandler.getAllCommands();

            List<Command> list = null;
            if (allCommands != null) {
                list = new ArrayList<Command>();
                for (com.pneumaticraft.commandhandler.multiverse.Command allCommand : allCommands) {
                    list.add(new Command(allCommand));
                }
            }
            return list;
        }

        public List<Command> getCommands(CommandSender arg0) {
            List<com.pneumaticraft.commandhandler.multiverse.Command> commands = commandHandler.getCommands(arg0);
            List<Command> list = null;
            if (commands != null) {
                list = new ArrayList<Command>();
                for (com.pneumaticraft.commandhandler.multiverse.Command command : commands) {
                    list.add(new Command(command));
                }
            }
            return list;
        }

        public double getVersion() {
            return commandHandler.getVersion();
        }

        public boolean locateAndRunCommand(CommandSender sender, List<String> args, boolean notifySender) {
            return commandHandler.locateAndRunCommand(sender, args, notifySender);
        }

        public boolean locateAndRunCommand(CommandSender sender, List<String> args) {
            return commandHandler.locateAndRunCommand(sender, args);
        }

        public void queueCommand(CommandSender sender, String commandName, String methodName, List<? extends Object> args, Class<?>[] paramTypes,
                String message, String message2, String success, String fail, int seconds) {
            commandHandler.queueCommand(sender, commandName, methodName, args, paramTypes, message, message2, success, fail, seconds);
        }

        public void queueCommand(CommandSender sender, String commandName, String methodName, List<? extends Object> args, Class<?>[] paramTypes,
                String success, String fail) {
            commandHandler.queueCommand(sender, commandName, methodName, args, paramTypes, success, fail);
        }

        public void registerCommand(Command command) {
            commandHandler.registerCommand(command.command);
        }
    }

    public static class Command {
        private com.pneumaticraft.commandhandler.multiverse.Command command;

        public Command(com.pneumaticraft.commandhandler.multiverse.Command command) {
            this.command = command;
        }

        public void addAdditonalPermission(Permission otherPerm) {
            command.addAdditonalPermission(otherPerm);
        }

        public void addCommandExample(String example) {
            command.addCommandExample(example);
        }

        public void addKey(String key, int minArgs, int maxArgs) {
            command.addKey(key, minArgs, maxArgs);
        }

        public void addKey(String key) {
            command.addKey(key);
        }

        public boolean checkArgLength(List<String> args) {
            return command.checkArgLength(args);
        }

        public List<String> getAllPermissionStrings() {
            return command.getAllPermissionStrings();
        }

        public String getCommandDesc() {
            return command.getCommandDesc();
        }

        public List<String> getCommandExamples() {
            return command.getCommandExamples();
        }

        public String getCommandName() {
            return command.getCommandName();
        }

        public String getCommandUsage() {
            return command.getCommandUsage();
        }

        public CommandKey getKey(List<String> arg0) {
            return new CommandKey(command.getKey(arg0));
        }

        public List<String> getKeyStrings() {
            return command.getKeyStrings();
        }

        public List<CommandKey> getKeys() {
            List<com.pneumaticraft.commandhandler.multiverse.CommandKey> keys = command.getKeys();

            List<CommandKey> list = null;
            if (keys != null) {
                list = new ArrayList<CommandKey>();
                for (com.pneumaticraft.commandhandler.multiverse.CommandKey key : keys) {
                    list.add(new CommandKey(key));
                }
            }

            return list;
        }

        public Integer getMaxArgs() {
            return command.getMaxArgs();
        }

        public Integer getMinArgs() {
            return command.getMinArgs();
        }

        public int getNumKeyArgs(String key) {
            return command.getNumKeyArgs(key);
        }

        public Permission getPermission() {
            return command.getPermission();
        }

        public String getPermissionString() {
            return command.getPermissionString();
        }

        public boolean isOpRequired() {
            return command.isOpRequired();
        }

        public List<String> removeKeyArgs(List<String> arg0, String arg1) {
            return command.removeKeyArgs(arg0, arg1);
        }

        public void runCommand(CommandSender arg0, List<String> arg1) {
            command.runCommand(arg0, arg1);
        }

        public void setArgRange(int min, int max) {
            command.setArgRange(min, max);
        }

        public void setCommandUsage(String usage) {
            command.setCommandUsage(usage);
        }

        public void setName(String name) {
            command.setName(name);
        }

        public void setPermission(Permission arg0) {
            command.setPermission(arg0);
        }

        public void setPermission(String p, String desc, PermissionDefault defaultPerm) {
            command.setPermission(p, desc, defaultPerm);
        }

        public void showHelp(CommandSender arg0) {
            command.showHelp(arg0);
        }
    }

    public static class CommandKey {
        private com.pneumaticraft.commandhandler.multiverse.CommandKey commandKey;

        public CommandKey(com.pneumaticraft.commandhandler.multiverse.CommandKey commandKey) {
            this.commandKey = commandKey;
        }

        public String getKey() {
            return commandKey.getKey();
        }

        public boolean hasValidNumberOfArgs(int args) {
            return commandKey.hasValidNumberOfArgs(args);
        }
    }

    public static class DestinationFactory {
        private com.onarandombox.MultiverseCore.destination.DestinationFactory destinationFactory;

        public DestinationFactory(com.onarandombox.MultiverseCore.destination.DestinationFactory destinationFactory) {
            this.destinationFactory = destinationFactory;
        }

        public com.onarandombox.MultiverseCore.api.MVDestination getDestination(String arg0) {
            return destinationFactory.getDestination(arg0);
        }

        public boolean registerDestinationType(Class<? extends com.onarandombox.MultiverseCore.api.MVDestination> c, String identifier) {
            return destinationFactory.registerDestinationType(c, identifier);
        }
    }

    public static class MVEntityListener {
        private com.onarandombox.MultiverseCore.listeners.MVEntityListener mVEntityListener;

        public MVEntityListener(com.onarandombox.MultiverseCore.listeners.MVEntityListener mVEntityListener) {
            this.mVEntityListener = mVEntityListener;
        }

        public void creatureSpawn(CreatureSpawnEvent event) {
            mVEntityListener.creatureSpawn(event);
        }

        public void entityRegainHealth(EntityRegainHealthEvent event) {
            mVEntityListener.entityRegainHealth(event);
        }

        public void foodLevelChange(FoodLevelChangeEvent arg0) {
            mVEntityListener.foodLevelChange(arg0);
        }
    }

    public static class LocationManipulation {
        private com.onarandombox.MultiverseCore.api.LocationManipulation locationManipulation;

        public LocationManipulation(com.onarandombox.MultiverseCore.api.LocationManipulation locationManipulation) {
            this.locationManipulation = locationManipulation;
        }

        public Location getBlockLocation(Location arg0) {
            return locationManipulation.getBlockLocation(arg0);
        }

        public String getDirection(Location arg0) {
            return locationManipulation.getDirection(arg0);
        }

        public Location getNextBlock(Vehicle arg0) {
            return locationManipulation.getNextBlock(arg0);
        }

        public float getSpeed(Vector arg0) {
            return locationManipulation.getSpeed(arg0);
        }

        public Vector getTranslatedVector(Vector arg0, String arg1) {
            return locationManipulation.getTranslatedVector(arg0, arg1);
        }

        public float getYaw(String arg0) {
            return locationManipulation.getYaw(arg0);
        }

        public String locationToString(Location arg0) {
            return locationManipulation.locationToString(arg0);
        }

        public String strCoords(Location arg0) {
            return locationManipulation.strCoords(arg0);
        }

        public String strCoordsRaw(Location arg0) {
            return locationManipulation.strCoordsRaw(arg0);
        }

        public Location stringToLocation(String arg0) {
            return locationManipulation.stringToLocation(arg0);
        }
    }

    public static class MultiverseCoreConfig {
        private com.onarandombox.MultiverseCore.api.MultiverseCoreConfig multiverseCoreConfig;

        public MultiverseCoreConfig(com.onarandombox.MultiverseCore.api.MultiverseCoreConfig multiverseCoreConfig) {
            this.multiverseCoreConfig = multiverseCoreConfig;
        }

        public Map<String, Object> serialize() {
            return multiverseCoreConfig.serialize();
        }

        public boolean getDisplayPermErrors() {
            return multiverseCoreConfig.getDisplayPermErrors();
        }

        public boolean getEnforceAccess() {
            return multiverseCoreConfig.getEnforceAccess();
        }

        public boolean getFirstSpawnOverride() {
            return multiverseCoreConfig.getFirstSpawnOverride();
        }

        public String getFirstSpawnWorld() {
            return multiverseCoreConfig.getFirstSpawnWorld();
        }

        public int getGlobalDebug() {
            return multiverseCoreConfig.getGlobalDebug();
        }

        public int getMessageCooldown() {
            return multiverseCoreConfig.getMessageCooldown();
        }

        public boolean getPrefixChat() {
            return multiverseCoreConfig.getPrefixChat();
        }

        public int getTeleportCooldown() {
            return multiverseCoreConfig.getTeleportCooldown();
        }

        public boolean getTeleportIntercept() {
            return multiverseCoreConfig.getTeleportIntercept();
        }

        public double getVersion() {
            return multiverseCoreConfig.getVersion();
        }

        public boolean setConfigProperty(String arg0, String arg1) {
            return multiverseCoreConfig.setConfigProperty(arg0, arg1);
        }

        public void setDisplayPermErrors(boolean arg0) {
            multiverseCoreConfig.setDisplayPermErrors(arg0);
        }

        public void setEnforceAccess(boolean arg0) {
            multiverseCoreConfig.setEnforceAccess(arg0);
        }

        public void setFirstSpawnOverride(boolean arg0) {
            multiverseCoreConfig.setFirstSpawnOverride(arg0);
        }

        public void setFirstSpawnWorld(String arg0) {
            multiverseCoreConfig.setFirstSpawnWorld(arg0);
        }

        public void setGlobalDebug(int arg0) {
            multiverseCoreConfig.setGlobalDebug(arg0);
        }

        public void setMessageCooldown(int arg0) {
            multiverseCoreConfig.setMessageCooldown(arg0);
        }

        public void setPrefixChat(boolean arg0) {
            multiverseCoreConfig.setPrefixChat(arg0);
        }

        public void setTeleportCooldown(int arg0) {
            multiverseCoreConfig.setTeleportCooldown(arg0);
        }

        public void setTeleportIntercept(boolean arg0) {
            multiverseCoreConfig.setTeleportIntercept(arg0);
        }

        public void setVersion(int arg0) {
            multiverseCoreConfig.setVersion(arg0);
        }
    }

    public static class MVPermissions {
        private com.onarandombox.MultiverseCore.utils.MVPermissions mVPermissions;

        public MVPermissions(com.onarandombox.MultiverseCore.utils.MVPermissions mVPermissions) {
            this.mVPermissions = mVPermissions;
        }

        public Permission addPermission(String arg0, PermissionDefault arg1) {
            return mVPermissions.addPermission(arg0, arg1);
        }

        public boolean canEnterDestination(CommandSender sender, com.onarandombox.MultiverseCore.api.MVDestination d) {
            return mVPermissions.canEnterDestination(sender, d);
        }

        public boolean canEnterWorld(Player p, com.onarandombox.MultiverseCore.api.MultiverseWorld w) {
            return mVPermissions.canEnterWorld(p, w);
        }

        public boolean canIgnoreGameModeRestriction(Player p, com.onarandombox.MultiverseCore.api.MultiverseWorld w) {
            return mVPermissions.canIgnoreGameModeRestriction(p, w);
        }

        public boolean canTravelFromLocation(CommandSender sender, Location location) {
            return mVPermissions.canTravelFromLocation(sender, location);
        }

        public boolean canTravelFromWorld(Player arg0, com.onarandombox.MultiverseCore.api.MultiverseWorld arg1) {
            return mVPermissions.canTravelFromWorld(arg0, arg1);
        }

        public String getType() {
            return mVPermissions.getType();
        }

        public boolean hasAllPermission(CommandSender arg0, List<String> arg1, boolean arg2) {
            return mVPermissions.hasAllPermission(arg0, arg1, arg2);
        }

        public boolean hasAnyPermission(CommandSender arg0, List<String> arg1, boolean arg2) {
            return mVPermissions.hasAnyPermission(arg0, arg1, arg2);
        }

        public boolean hasPermission(CommandSender sender, String node, boolean isOpRequired) {
            return mVPermissions.hasPermission(sender, node, isOpRequired);
        }

        public void tellMeWhyICantDoThis(CommandSender asker, CommandSender playerInQuestion, com.onarandombox.MultiverseCore.api.MVDestination d) {
            mVPermissions.tellMeWhyICantDoThis(asker, playerInQuestion, d);
        }
    }

    public static class MultiverseMessaging {
        private com.onarandombox.MultiverseCore.api.MultiverseMessaging multiverseMessaging;

        public MultiverseMessaging(com.onarandombox.MultiverseCore.api.MultiverseMessaging multiverseMessaging) {
            this.multiverseMessaging = multiverseMessaging;
        }

        public int getCooldown() {
            return multiverseMessaging.getCooldown();
        }

        public boolean sendMessage(CommandSender arg0, String arg1, boolean arg2) {
            return multiverseMessaging.sendMessage(arg0, arg1, arg2);
        }

        public boolean sendMessages(CommandSender arg0, String[] arg1, boolean arg2) {
            return multiverseMessaging.sendMessages(arg0, arg1, arg2);
        }

        public boolean sendMessages(CommandSender arg0, Collection<String> arg1, boolean arg2) {
            return multiverseMessaging.sendMessages(arg0, arg1, arg2);
        }

        public void setCooldown(int arg0) {
            multiverseMessaging.setCooldown(arg0);
        }
    }

    public static class MVPlayerListener {
        private com.onarandombox.MultiverseCore.listeners.MVPlayerListener mVPlayerListener;

        public MVPlayerListener(com.onarandombox.MultiverseCore.listeners.MVPlayerListener mVPlayerListener) {
            this.mVPlayerListener = mVPlayerListener;
        }

        public void handleGameMode(Player player, com.onarandombox.MultiverseCore.api.MultiverseWorld world) {
            mVPlayerListener.handleGameMode(player, world);
        }

        public void playerChangedWorld(PlayerChangedWorldEvent event) {
            mVPlayerListener.playerChangedWorld(event);
        }

        public void playerJoin(PlayerJoinEvent event) {
            mVPlayerListener.playerJoin(event);
        }

        public void playerPortal(PlayerPortalEvent event) {
            mVPlayerListener.playerPortal(event);
        }

        public void playerPortalCheck(PlayerPortalEvent arg0) {
            mVPlayerListener.playerPortalCheck(arg0);
        }

        public void playerQuit(PlayerQuitEvent event) {
            mVPlayerListener.playerQuit(event);
        }

        public void playerRespawn(PlayerRespawnEvent event) {
            mVPlayerListener.playerRespawn(event);
        }

        public void playerTeleport(PlayerTeleportEvent event) {
            mVPlayerListener.playerTeleport(event);
        }
    }

    public static class MVPlayerSession {
        private com.onarandombox.MultiverseCore.utils.MVPlayerSession mVPlayerSession;

        public MVPlayerSession(com.onarandombox.MultiverseCore.utils.MVPlayerSession mVPlayerSession) {
            this.mVPlayerSession = mVPlayerSession;
        }

        public boolean getTeleportable() {
            return mVPlayerSession.getTeleportable();
        }

        public void teleport() {
            mVPlayerSession.teleport();
        }
    }

    public static class MVWeatherListener {
        private com.onarandombox.MultiverseCore.listeners.MVWeatherListener mVWeatherListener;

        public MVWeatherListener(com.onarandombox.MultiverseCore.listeners.MVWeatherListener mVWeatherListener) {
            this.mVWeatherListener = mVWeatherListener;
        }

        public void thunderChange(ThunderChangeEvent event) {
            mVWeatherListener.thunderChange(event);
        }

        public void weatherChange(WeatherChangeEvent event) {
            mVWeatherListener.weatherChange(event);
        }
    }

    public static class MultiverseCore {
        private com.onarandombox.MultiverseCore.MultiverseCore multiverseCore;

        public MultiverseCore(com.onarandombox.MultiverseCore.MultiverseCore multiverseCore) {
            this.multiverseCore = multiverseCore;
        }

        public void decrementPluginCount() {
            multiverseCore.decrementPluginCount();
        }

        public Boolean deleteWorld(String name) {
            return multiverseCore.deleteWorld(name);
        }

        public AnchorManager getAnchorManager() {
            return new AnchorManager(multiverseCore.getAnchorManager());
        }

        public String getAuthors() {
            return multiverseCore.getAuthors();
        }

        public GenericBank getBank() {
            return new GenericBank(multiverseCore.getBank());
        }

        public AllPay getBanker() {
            return new AllPay(multiverseCore.getBanker());
        }

        public BlockSafety getBlockSafety() {
            return new BlockSafety(multiverseCore.getBlockSafety());
        }

        public CommandHandler getCommandHandler() {
            return new CommandHandler(multiverseCore.getCommandHandler());
        }

        public MultiverseCore getCore() {
            return new MultiverseCore(multiverseCore.getCore());
        }

        public DestinationFactory getDestFactory() {
            return new DestinationFactory(multiverseCore.getDestFactory());
        }

        public MVEntityListener getEntityListener() {
            return new MVEntityListener(multiverseCore.getEntityListener());
        }

        public LocationManipulation getLocationManipulation() {
            return new LocationManipulation(multiverseCore.getLocationManipulation());
        }

        public MultiverseCoreConfig getMVConfig() {
            return new MultiverseCoreConfig(multiverseCore.getMVConfig());
        }

        public MVPermissions getMVPerms() {
            return new MVPermissions(multiverseCore.getMVPerms());
        }

        public MVWorldManager getMVWorldManager() {
            return new MVWorldManager(multiverseCore.getMVWorldManager());
        }

        public MultiverseMessaging getMessaging() {
            return new MultiverseMessaging(multiverseCore.getMessaging());
        }

        public MVPlayerListener getPlayerListener() {
            return new MVPlayerListener(multiverseCore.getPlayerListener());
        }

        public MVPlayerSession getPlayerSession(Player player) {
            return new MVPlayerSession(multiverseCore.getPlayerSession(player));
        }

        public int getPluginCount() {
            return multiverseCore.getPluginCount();
        }

        public int getProtocolVersion() {
            return multiverseCore.getProtocolVersion();
        }

        public SafeTTeleporter getSafeTTeleporter() {
            return new SafeTTeleporter(multiverseCore.getSafeTTeleporter());
        }

        public File getServerFolder() {
            return multiverseCore.getServerFolder();
        }

        public String getTag() {
            return multiverseCore.getTag();
        }

        public MVWeatherListener getWeatherListener() {
            return new MVWeatherListener(multiverseCore.getWeatherListener());
        }

        public void incrementPluginCount() {
            multiverseCore.incrementPluginCount();
        }

        public void loadConfigs() {
            multiverseCore.loadConfigs();
        }

        public void log(Level level, String msg) {
            multiverseCore.log(level, msg);
        }

        public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String commandLabel, String[] args) {
            return multiverseCore.onCommand(sender, command, commandLabel, args);
        }

        public void onDisable() {
            multiverseCore.onDisable();
        }

        public void onEnable() {
            multiverseCore.onEnable();
        }

        public void onLoad() {
            multiverseCore.onLoad();
        }

        public Boolean regenWorld(String arg0, Boolean arg1, Boolean arg2, String arg3) {
            return multiverseCore.regenWorld(arg0, arg1, arg2, arg3);
        }

        public void removePlayerSession(Player player) {
            multiverseCore.removePlayerSession(player);
        }

        public boolean saveMVConfig() {
            return multiverseCore.saveMVConfig();
        }

        public boolean saveMVConfigs() {
            return multiverseCore.saveMVConfigs();
        }

        public boolean saveWorldConfig() {
            return multiverseCore.saveWorldConfig();
        }

        public void setBank(GenericBank bank) {
            multiverseCore.setBank(bank.genericBank);
        }

        public void setBlockSafety(BlockSafety bs) {
            multiverseCore.setBlockSafety(bs.blockSafety);
        }

        public void setCore(MultiverseCore core) {
            multiverseCore.setCore(core.multiverseCore);
        }

        public void setLocationManipulation(LocationManipulation locationManipulation) {
            multiverseCore.setLocationManipulation(locationManipulation.locationManipulation);
        }

        public void setSafeTTeleporter(SafeTTeleporter safeTTeleporter) {
            multiverseCore.setSafeTTeleporter(safeTTeleporter.safeTTeleporter);
        }

        public void setServerFolder(File newServerFolder) {
            multiverseCore.setServerFolder(newServerFolder);
        }

        public void showNotMVWorldMessage(CommandSender sender, String worldName) {
            multiverseCore.showNotMVWorldMessage(sender, worldName);
        }

        public void teleportPlayer(CommandSender teleporter, Player p, Location l) {
            multiverseCore.teleportPlayer(teleporter, p, l);
        }
    }
}
