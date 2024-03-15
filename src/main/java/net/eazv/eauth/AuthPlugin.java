package net.eazv.eauth;

import net.eazv.eauth.command.SetSpawnCommand;
import net.eazv.eauth.listeners.ProtectionListener;
import net.eazv.eauth.utils.scoreboard.Assemble;
import net.eazv.eauth.utils.scoreboard.impl.ScoreboardImpl;

import org.bukkit.plugin.java.JavaPlugin;

public class AuthPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getServer().getPluginManager().registerEvents(new ProtectionListener(), this);

        new Assemble(this, new ScoreboardImpl()).setTicks(this.getConfig().getInt("SCOREBOARD.TICKS"));
    }
    
    public static AuthPlugin getInstance() {
        return getPlugin(AuthPlugin.class);
    }

}
