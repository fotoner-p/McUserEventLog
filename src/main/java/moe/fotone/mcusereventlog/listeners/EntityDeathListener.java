package moe.fotone.mcusereventlog.listeners;

import moe.fotone.mcusereventlog.data.DeathData;
import moe.fotone.mcusereventlog.entity.User;
import moe.fotone.mcusereventlog.entity.UserDeathLog;
import moe.fotone.mcusereventlog.entity.UserHuntingLog;
import moe.fotone.mcusereventlog.store.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import org.bukkit.entity.Projectile;
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
import java.util.Date;

public class EntityDeathListener implements Listener {

    @EventHandler
    private void onUserMonsterKilling(EntityDeathEvent event) {
        DeathData data = new DeathData(event);

        Entity killer = data.getKiller();
        Entity victim = data.getVictim();
        String deathReason = data.getCause().name();
        Projectile projectile = data.getProjectile();

        if (killer instanceof Player && victim instanceof Monster){
            EntityManager entityManager = Database.getEntityManager();

            UserHuntingLog log = new UserHuntingLog();
            log.setVictim(victim.getType().name());
            log.setDeath_type(deathReason);
            log.setProjectile(projectile != null? projectile.getName(): null);
            log.setTime(new Date(System.currentTimeMillis()));

            User user = entityManager.find(User.class, killer.getUniqueId());

            if (user != null){
                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    log.setUser(user);
                    entityManager.persist(log);
                } finally {
                    transaction.commit();
                }
            }
        }
    }

    @EventHandler
    private void onUserDeath(PlayerDeathEvent event){
        DeathData data = new DeathData(event);
        Entity killer = data.getKiller();
        Player victim = (Player)data.getVictim();
        Projectile projectile = data.getProjectile();
        String deathReason = data.getCause().name();

        EntityManager entityManager = Database.getEntityManager();

        UserDeathLog log = new UserDeathLog();
        log.setKiller(killer != null? killer.getType().name() : null);
        log.setTime(new Date(System.currentTimeMillis()));
        log.setDeath_type(deathReason);
        log.setProjectile(projectile != null? projectile.getName(): null);

        User user = entityManager.find(User.class, victim.getUniqueId());

        if (user != null){
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                log.setUser(user);
                entityManager.persist(log);
            } finally {
                transaction.commit();
            }
        }
    }

    @EventHandler
    private void onUserJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        EntityManager entityManager = Database.getEntityManager();

        User newPlayer = new User();
        newPlayer.setName(player.getName());
        newPlayer.setUuid(player.getUniqueId());
        newPlayer.setServer_first_join(new Date(System.currentTimeMillis()));
        newPlayer.setAdvancement_progress(0);

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
