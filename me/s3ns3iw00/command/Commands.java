package me.s3ns3iw00.command;

import me.s3ns3iw00.main.MainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    private MainClass plugin;
    public Commands(MainClass plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("sss.reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage(plugin.lmg.reloadMessage());
                } else {
                    sender.sendMessage(plugin.lmg.permissionErrorMessage());
                }
            } else if (args[0].equalsIgnoreCase("about")) {
                sender.sendMessage(plugin.lmg.aboutMessage());
            } else {
                sender.sendMessage(plugin.lmg.argumentErrorMessage());
            }
        } else {
            sender.sendMessage(plugin.lmg.argumentErrorMessage());
        }
        return true;
    }

}
