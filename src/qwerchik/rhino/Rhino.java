package qwerchik.rhino;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public final class Rhino extends JavaPlugin {

    Logger log = Logger.getLogger("minecraft");
    public RhinoVector vector = new RhinoVector();
    public RListener listener = new RListener(this);
    public RExecutor executor = new RExecutor(this);
    public RPermissions permissions;

    public boolean notOre(int Id) {
        switch (Id) {
            case 0x07:case 0x0e:
            case 0x0f:case 0x10:
            case 0x15:case 0x38:
            case 0x49:case 0x4a:
            case 0x81:return false;
            default:return true;
        }
    }

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