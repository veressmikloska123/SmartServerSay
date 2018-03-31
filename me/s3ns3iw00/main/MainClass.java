package me.s3ns3iw00.main;

import me.s3ns3iw00.command.Commands;
import me.s3ns3iw00.event.EventHandlers;
import me.s3ns3iw00.manager.LanguageManager;
import me.s3ns3iw00.manager.UpdateManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.*;

public class MainClass extends JavaPlugin {

    public String current_version = "1.0";
    private EventHandlers eh = new EventHandlers(this);
    private Commands c = new Commands(this);
    public LanguageManager lmg = new LanguageManager(this);
    public UpdateManager um = new UpdateManager(this);
    private Player target;
    private List<Player> flood = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(eh, this);
        getServer().getLogger().info(lmg.pluginActiveMessage());
        saveDefaultConfig();
        reloadConfig();
        getCommand("smartserversay").setExecutor(c);
        if(um.needUpdate()){
            getServer().getLogger().info(lmg.needUpdateMessage(um.getDowloadLink()));
        }
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info(lmg.pluginDeactiveMessage());
    }

    public String getLanguage() {
        return getConfig().getString("language");
    }

    private String replaceTags(String string) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(getConfig().getString("time-format"));
        return string.replace("%player%", target.getName()).replace("%time%", sdf.format(date));
    }

    private String serverPrefix() {
        return getConfig().getBoolean("server-prefix.enable") ? getConfig().getString("server-prefix.prefix") + "§r" : "";
    }

    public void sendJoinMessage(Player player) {
        if (getConfig().getBoolean("join-message.enable")) {
            target = player;
            player.sendMessage(serverPrefix() + " " + replaceTags(getConfig().getString("join-message.message")));
        }
    }

    private boolean enabledAntiFlood() {
        return getConfig().getBoolean("chat-messages.anti-flood");
    }

    private boolean checkSingle(String message, String word) {
        boolean is = false;
        if (getConfig().getBoolean("chat-messages.words." + word + ".single-word")) {
            String[] splitm = message.split(" ");
            for (String aSplitm : splitm) {
                if (aSplitm.equalsIgnoreCase(word)) {
                    is = true;
                }
            }
        } else {
            is = true;
        }
        return is;
    }

    private boolean isFlood(Player player) {
        boolean is = false;
        if (enabledAntiFlood()) {
            if (flood.contains(player)) {
                is = true;
            }
        }
        return is;
    }

    public void sendChatMessages(Player player, String message) {
        target = player;
        if (getConfig().getBoolean("chat-messages.enable")) {
            ConfigurationSection cs = getConfig().getConfigurationSection("chat-messages.words");
            for (String keys : cs.getKeys(false)) {
                if (message.contains(keys)) {
                    if (!isFlood(player)) {
                        if (checkSingle(message, keys)) {
                            if (enabledAntiFlood()) flood.add(player);
                            Timer t;
                            TimerTask tt = new TimerTask() {
                                @Override
                                public void run() {
                                    player.sendMessage(serverPrefix() + " " + replaceTags(cs.getString(keys + ".message")));
                                }
                            };
                            t = new Timer();
                            t.schedule(tt, 1000);
                        }
                    }
                }
            }
            if (flood.contains(player)) {
                flood.remove(player);
            }
        }
    }

}
