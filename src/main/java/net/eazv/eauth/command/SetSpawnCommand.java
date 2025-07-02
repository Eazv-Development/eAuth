package net.eazv.eauth.command;

import net.eazv.eauth.EAuthPlugin;
import net.eazv.eauth.utils.LocationUtil;
import net.eazv.eauth.utils.MessageUtil;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class SetSpawnCommand implements CommandExecutor {

    private final EAuthPlugin plugin;

    public SetSpawnCommand(EAuthPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtil.translate(config.getString("MESSAGES.ONLY_PLAYERS")));
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage(MessageUtil.translate(config.getString("MESSAGES.NO_PERMISSIONS")));
            return true;
        }



        config.set("LOCATION.SPAWN", LocationUtil.parseToString(player.getLocation()));
        plugin.saveConfig();

        player.sendMessage(MessageUtil.translate(config.getString("MESSAGES.SPAWN_SET")));

        return true;
    }

}
