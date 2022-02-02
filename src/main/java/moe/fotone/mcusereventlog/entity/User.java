package moe.fotone.mcusereventlog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "user")
public class User {
    @Id
    @Column(name="uuid")
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int advancement_progress;

    @Column(nullable = false)
    private Date server_first_join;

    @OneToMany
    @JoinColumn(name="uuid")
    private List<UserAccessLog> userAccessLogs = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="uuid")
    private List<UserDeathLog> userDeathLogs = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="uuid")
    private List<UserHuntingLog> userHuntingLogs = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="uuid")
    private List<MatingLog> matingLogs = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="uuid")
    private List<VillagerTransactionLog> villagerTransactionLogs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdvancement_progress() {
        return advancement_progress;
    }

    public void setAdvancement_progress(int advancement_progress) {
        this.advancement_progress = advancement_progress;
    }

    public Date getServer_first_join() {
        return server_first_join;
    }

    public void setServer_first_join(Date server_first_join) {
        this.server_first_join = server_first_join;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<UserAccessLog> getUserAccessLogs() {
        return userAccessLogs;
    }

    public void setUserAccessLogs(List<UserAccessLog> userAccessLogs) {
        this.userAccessLogs = userAccessLogs;
    }

    public List<UserDeathLog> getUserDeathLogs() {
        return userDeathLogs;
    }

    public void setUserDeathLogs(List<UserDeathLog> userDeathLogs) {
        this.userDeathLogs = userDeathLogs;
    }

    public List<UserHuntingLog> getUserHuntingLogs() {
        return userHuntingLogs;
    }

    public void setUserHuntingLogs(List<UserHuntingLog> userHuntingLogs) {
        this.userHuntingLogs = userHuntingLogs;
    }

    public List<VillagerTransactionLog> getVillagerTransactionLogs() {
        return villagerTransactionLogs;
    }

    public void setVillagerTransactionLogs(List<VillagerTransactionLog> villagerTransactionLogs) {
        this.villagerTransactionLogs = villagerTransactionLogs;
    }

    public List<MatingLog> getMatingLogs() {
        return matingLogs;
    }

    public void setMatingLogs(List<MatingLog> matingLogs) {
        this.matingLogs = matingLogs;
    }
}
