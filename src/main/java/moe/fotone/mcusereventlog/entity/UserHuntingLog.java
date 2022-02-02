package moe.fotone.mcusereventlog.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user_hunting_log")
public class UserHuntingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hunting_log_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String death_type;

    @Column(nullable = false)
    private String victim;

    @Column
    private String projectile;

    @Column
    private Date time;

    @ManyToOne
    @JoinColumn(name="uuid")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getProjectile() {
        return projectile;
    }

    public void setProjectile(String projectile) {
        this.projectile = projectile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeath_type() {
        return death_type;
    }

    public void setDeath_type(String death_type) {
        this.death_type = death_type;
    }

    public String getVictim() {
        return victim;
    }

    public void setVictim(String victim) {
        this.victim = victim;
    }
}