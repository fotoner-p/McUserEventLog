package moe.fotone.mcusereventlog.listeners;

import moe.fotone.mcusereventlog.data.DeathData;
import moe.fotone.mcusereventlog.entity.User;
import moe.fotone.mcusereventlog.store.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityDeathListener implements Listener {

    @EventHandler
    private void onUserMonsterKilling(EntityDeathEvent event) {
        DeathData data = new DeathData(event);

        Entity killer = data.getKiller();
        Entity victim = data.getVictim();
        String deathReason = data.getCause().name();

        if (killer instanceof Player && victim instanceof Monster){

            Bukkit.broadcastMessage("플레이어 " + killer.getName() + "이(가) " + victim.getName() + " 살생(" + deathReason + ")");
        }
    }

    @EventHandler
    private void onUserDeath(PlayerDeathEvent event){
        DeathData data = new DeathData(event);
        Entity killer = data.getKiller();
        Player victim = (Player)data.getVictim();
        String deathReason = data.getCause().name();

//        Bukkit.broadcastMessage("플레이어 " + victim + "이(가) " + killer + " 에게 사망(" + deathReason + ")");

        Bukkit.broadcastMessage(victim.getUniqueId().toString());
    }

    @EventHandler
    private void onUserJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        EntityManager entityManager = Database.getEntityManager();

        User newPlayer = new User();
        newPlayer.setName(player.getName());
        newPlayer.setId(player.getUniqueId());

        if (entityManager.find(User.class, player.getUniqueId()) == null) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(newPlayer);
            } finally {
                transaction.commit();
            }
        }
    }

    @EventHandler
    private void asdf(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();

        Bukkit.broadcastMessage(player.getUniqueId().toString());
        Bukkit.broadcastMessage(event.getAdvancement().getKey().getKey());
    }
}
