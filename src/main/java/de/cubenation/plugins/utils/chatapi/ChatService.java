package de.cubenation.plugins.utils.chatapi;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.chatapi.Chatter.BroadcastResourceAsynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.BroadcastResourceSynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.BroadcastTextAsynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.BroadcastTextSynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.ChatResourceAsynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.ChatResourceSynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.ChatTextAsynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.ChatTextSynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.PermissionBroadcastResourceAsynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.PermissionBroadcastTextAsynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.PermissionChatResourceAsynchron;
import de.cubenation.plugins.utils.chatapi.Chatter.PermissionChatTextAsynchron;
import de.cubenation.plugins.utils.permissionapi.PermissionInterface;

public class ChatService {
    private static final Locale defaultLocale = Locale.GERMANY;

    private JavaPlugin plugin;
    private ResourceBundle resource = null;
    private PermissionInterface permissionInterface;

    public ChatService(JavaPlugin plugin) {
        this(plugin, null);
    }

    public ChatService(JavaPlugin plugin, PermissionInterface permissionInterface) {
        this(plugin, permissionInterface, null);
    }

    public ChatService(JavaPlugin plugin, PermissionInterface permissionInterface, String resourceName) {
        this(plugin, permissionInterface, resourceName, null);
    }

    public ChatService(JavaPlugin plugin, PermissionInterface permissionInterface, String resourceName, Locale locale) {
        if (plugin == null) {
            throw new NullPointerException("JavaPlugin could not be null");
        }

        this.plugin = plugin;
        this.permissionInterface = permissionInterface;

        if (resourceName == null) {
            resourceName = plugin.getName() + "_messages";
            plugin.getLogger().info("set default i18n resource file to: " + resourceName);
        }

        if (locale == null) {
            // load from config.yml
            String configLang = plugin.getConfig().getString("lang");
            if (configLang != null && !configLang.isEmpty()) {
                locale = new Locale(configLang);
                plugin.getLogger().info("set default i18n language from config to: " + locale);
            } else {
                locale = defaultLocale;
                plugin.getLogger().info("set default i18n language to: " + locale);
            }
        }

        try {
            resource = ResourceBundle.getBundle(resourceName, locale, plugin.getClass().getClassLoader(), new UTF8Control());
        } catch (MissingResourceException e) {
            if (plugin.getLogger() != null) {
                if (plugin.getLogger().getLevel().equals(Level.FINE)) {
                    plugin.getLogger().log(
                            Level.WARNING,
                            "could not load one of the i18n resource files: "
                                    + resourceName
                                    + ".properties, "
                                    + (locale.getCountry() != null && !locale.getCountry().isEmpty() ? resourceName + "_" + locale.getCountry()
                                            + ".properties, " : "")
                                    + resourceName
                                    + "_"
                                    + locale.getLanguage()
                                    + ".properties, "
                                    + (locale.getCountry() != null && !locale.getCountry().isEmpty() ? resourceName + "_" + locale.getLanguage() + "_"
                                            + locale.getCountry() + ".properties" : ""), e);
                    plugin.getLogger().warning("i18n disabled");
                }
            }
        }
    }

    public void oneText(CommandSender sender, String message) {
        ChatTextAsynchron.chat(plugin, sender, message);
    }

    public void oneTextSync(CommandSender sender, String message) {
        ChatTextSynchron.chat(sender, message);
    }

    public void one(CommandSender sender, String resourceString, Object... parameter) {
        ChatResourceAsynchron.chat(plugin, resource, sender, resourceString, parameter);
    }

    public void oneSync(CommandSender sender, String resourceString, Object... parameter) {
        ChatResourceSynchron.chat(plugin, resource, sender, resourceString, parameter);
    }

    public void allText(String message) {
        BroadcastTextAsynchron.chat(plugin, message);
    }

    public void allTextSync(String message) {
        BroadcastTextSynchron.chat(message);
    }

    public void all(String resourceString, Object... parameter) {
        BroadcastResourceAsynchron.chat(plugin, resource, resourceString, parameter);
    }

    public void allSync(String resourceString, Object... parameter) {
        BroadcastResourceSynchron.chat(plugin, resource, resourceString, parameter);
    }

    public void allTextPerms(String message, String[] permissions) {
        PermissionBroadcastTextAsynchron.chat(plugin, permissionInterface, message, permissions);
    }

    public void allPerms(String resourceString, String[] permissions, Object... parameter) {
        PermissionBroadcastResourceAsynchron.chat(plugin, resource, permissionInterface, resourceString, permissions, parameter);
    }

    public void allTextPerm(String message, String permission) {
        allTextPerms(message, new String[] { permission });
    }

    public void allPerm(String resourceString, String permission, Object... parameter) {
        allPerms(resourceString, new String[] { permission }, parameter);
    }

    public void oneTextPerms(CommandSender sender, String message, String[] permissions) {
        PermissionChatTextAsynchron.chat(plugin, permissionInterface, sender, message, permissions);
    }

    public void onePerms(CommandSender sender, String resourceString, String[] permissions, Object... parameter) {
        PermissionChatResourceAsynchron.chat(plugin, resource, permissionInterface, sender, resourceString, permissions, parameter);
    }

    public void oneTextPerm(CommandSender sender, String message, String permission) {
        oneTextPerms(sender, message, new String[] { permission });
    }

    public void onePerm(CommandSender sender, String resourceString, String permission, Object... parameter) {
        onePerms(sender, resourceString, new String[] { permission }, parameter);
    }

    public void setPermissionInterface(PermissionInterface permissionInterface) {
        this.permissionInterface = permissionInterface;
    }
}
