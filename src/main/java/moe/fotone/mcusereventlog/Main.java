package moe.fotone.mcusereventlog;

import moe.fotone.mcusereventlog.listeners.EntityDeathListener;
import moe.fotone.mcusereventlog.store.Database;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public final class Main extends JavaPlugin {

    public static Main plugin;
    public static Database database;

    @Override
    public void onLoad(){
    }

    @Override
    public void onEnable() {
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        database = new Database();

        addEventListeners();

        plugin = this;
    }

    private void addEventListeners(){
        PluginManager manager = getServer().getPluginManager();
        List<Listener> listeners = Arrays.asList(
                new EntityDeathListener()
        );

        for(Listener listener: listeners) {
            manager.registerEvents(listener, this);
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }
}
