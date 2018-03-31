package me.s3ns3iw00.event;

import me.s3ns3iw00.main.MainClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventHandlers implements Listener {

    private MainClass plugin;
    public EventHandlers(MainClass plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        plugin.sendJoinMessage(player);
        if(plugin.getConfig().getBoolean("update-notification")){
            if(player.isOp()){
                if(plugin.um.needUpdate()){
                    player.sendMessage(plugin.lmg.needUpdateMessage(plugin.um.getDowloadLink()));
                }
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        plugin.sendChatMessages(player, event.getMessage());
    }

}
