package moe.fotone.mcusereventlog.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

import javax.annotation.Nullable;
import java.util.Objects;


public class DeathData {
    private final Entity victim;
    private final Entity killer;
    private final DamageCause cause;


    public DeathData(EntityDeathEvent event){
        victim = event.getEntity();
        EntityDamageEvent entityDamageEvent = Objects.requireNonNull(victim.getLastDamageCause());

        cause = entityDamageEvent.getCause();
        killer = getKiller(entityDamageEvent);
    }

    @Nullable
    private Entity getKiller(EntityDamageEvent entityDamageEvent ) {
        if (entityDamageEvent instanceof EntityDamageByEntityEvent) {
            Entity damager = ((EntityDamageByEntityEvent) entityDamageEvent).getDamager();

            if (damager instanceof Projectile) {
                LivingEntity shooter = (LivingEntity) ((Projectile) damager).getShooter();
                if (shooter != null) return shooter;
            }

            return damager;
        }
        return null;
    }

    public Entity getVictim() {
        return victim;
    }

    public Entity getKiller() {
        return killer;
    }

    public DamageCause getCause() {
        return cause;
    }
}
