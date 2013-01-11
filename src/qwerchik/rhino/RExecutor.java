package qwerchik.rhino;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RExecutor implements CommandExecutor {

    private Rhino plugin;

    public Rhino getPlugin() {
        return plugin;
    }

    public RExecutor(Rhino instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        } else return true;
        if (cmd.getName().equalsIgnoreCase("rhino")) {
            if (getPlugin().permissions.has(sender, "rhino.cast")) {
                if (getPlugin().vector.getRhino(player) == null) {
                    getPlugin().vector.addRhino(player);
                    player.sendMessage(ChatColor.AQUA + "You're now stupid Rhino");
                } else {
                    getPlugin().vector.removeRhino(player);
                    player.sendMessage(ChatColor.AQUA + "You feel clever because you're now simple player");
                }
            }
        }
        return true;
    }
}
