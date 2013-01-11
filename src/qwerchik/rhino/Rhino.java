package qwerchik.rhino;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public final class Rhino extends JavaPlugin {

    Logger log = Logger.getLogger("minecraft");
    public RhinoVector vector = new RhinoVector();
    public RListener listener = new RListener(this);
    public RExecutor executor = new RExecutor(this);
    public RPermissions permissions;

    @Override
    public void onEnable() {
        getCommand("rhino").setExecutor(executor);
        getServer().getPluginManager().registerEvents(listener, this);
        permissions = new RPermissions(this);
    }

    @Override
    public void onDisable() {
        return;
    }
}