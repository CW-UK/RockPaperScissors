package eu.genesismc.genesisrps;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GenesisRPS extends JavaPlugin implements Listener, TabCompleter {

    String[] rps = {"rock", "paper", "scissors"};

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("GenesisRPS has been enabled.");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("GenesisRPS has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rps")) {
            Player p = (Player) sender;
            if (args.length < 2) {
                tellPlayer(p,"You need to specify rock, paper or scissors!");
            }
            else {
                int rnd = new Random().nextInt(rps.length);
                String myChoice = rps[rnd];
                String user = args[1].toLowerCase();
                switch (myChoice) {
                    case "rock":
                        if (user.equals("paper")) { playerWon(p, "rock"); }
                        else if (user.equals("rock")) { playerDraw(p, "rock"); }
                        else { playerLost(p, "rock"); }
                    case "paper":
                        if (user.equals("scissors")) { playerWon(p, "paper"); }
                        else if (user.equals("paper")) { playerDraw(p, "paper"); }
                        else { playerLost(p, "paper"); }
                    case "scissors":
                        if (user.equals("rock")) { playerWon(p, "scissors"); }
                        else if (user.equals("scissors")) { playerDraw(p, "scissors"); }
                        else { playerLost(p, "scissors"); }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rps")) {
            if (args.length == 1) {
                final List<String> commands = Arrays.asList("rock", "paper", "scissors");
                return StringUtil.copyPartialMatches(args[0], commands, new ArrayList<>());
            }
        }
        return null;
    }

    public void tellPlayer(Player p, String msg) {
        p.sendMessage(ChatColor.YELLOW + "[RPS] " + ChatColor.WHITE + msg);
    }

    public void playerLost(Player p, String choice) {
        tellPlayer(p, "I chose " + choice + ", so you lose!");
    }

    public void playerWon(Player p, String choice) {
        tellPlayer(p, "I chose " + choice + ", so you win!");
    }

    public void playerDraw(Player p, String choice) {
        tellPlayer(p, "I chose " + choice + " as well, so we drew!");
    }


}
