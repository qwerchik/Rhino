package qwerchik.rhino;

import org.bukkit.ChatColor;
import org.bukkit.Location;
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
        } else {
            sender.sendMessage("[Rhino] You can only use it if you player");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("rhino")) {
            if (getPlugin().permissions.has(sender, "rhino.cast")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("chunk")) {
                        Location origin = player.getLocation();
                        origin.setY(origin.getY() - 8);
                        origin.setX(origin.getX() - 8);
                        origin.setZ(origin.getZ() - 8);
                        int origin_y = origin.getBlockY();
                        int origin_x = origin.getBlockX();
                        int origin_z = origin.getBlockZ();
                        for (int y = 0; y < 16; y++) {
                            for (int x = 0; x < 16; x++) {
                                for (int z = 0; z < 16; z++) {
                                    if (getPlugin().notOre(
                                            player.getWorld().getBlockAt(
                                            origin_x + x,
                                            origin_y + y,
                                            origin_z + z).getTypeId())) {
                                        player.getWorld().getBlockAt(
                                                origin_x + x,
                                                origin_y + y,
                                                origin_z + z).setTypeId(0);
                                    }
                                }
                            }
                        }
                        player.sendMessage(ChatColor.YELLOW + "Chunk cleaned by Rhino's fury");
                    }
                } else {
                    if (getPlugin().vector.getRhino(player) == null) {
                        getPlugin().vector.addRhino(player);
                        player.sendMessage(ChatColor.AQUA + "You're now stupid Rhino");
                    } else {
                        getPlugin().vector.removeRhino(player);
                        player.sendMessage(ChatColor.AQUA + "You feel clever because you're now simple player");
                    }
                }
            }
        }
        return true;
    }
}
