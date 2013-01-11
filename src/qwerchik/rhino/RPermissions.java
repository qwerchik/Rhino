package qwerchik.rhino;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;

public class RPermissions {

    private Rhino plugin;
    public Permission perms;

    public RPermissions(Rhino instance) {
        this.plugin = instance;
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
                perms = rsp.getProvider();
                if (perms != null) {
                    plugin.log.info("[Rhino] Successfully connected to " + perms.getName() + ".");
                } else {
                    plugin.log.warning("[Rhino] Failed to connect to any permissions add-on, allowing everyone to use the plugin.");
                }
            }
        }, 100L);
    }

    public boolean has(CommandSender sender, String permission) {
        if(perms == null)
            return true;
        else return perms.has(sender, permission);
    }
}