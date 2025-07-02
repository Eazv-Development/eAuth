package net.eazv.eauth;

import net.eazv.eauth.command.SetSpawnCommand;
import net.eazv.eauth.listeners.EAuthProtectionListener;
import net.eazv.eauth.utils.scoreboard.Assemble;
import net.eazv.eauth.implementation.ScoreboardImpl;

import org.bukkit.plugin.java.JavaPlugin;

public class EAuthPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        this.getServer().getPluginManager().registerEvents(new EAuthProtectionListener(this), this);

        new Assemble(this, new ScoreboardImpl(this))
                .setTicks(this.getConfig().getInt("SCOREBOARD.TICKS"));
    }

}
