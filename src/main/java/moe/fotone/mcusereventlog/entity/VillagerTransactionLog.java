package moe.fotone.mcusereventlog.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "villager_transaction_log")
public class VillagerTransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private int emerald_value;

    @Column(nullable = false)
    private String transaction_type;

    @Column(nullable = false)
    private String item_type;

    @Column(nullable = false)
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

    public int getEmerald_value() {
        return emerald_value;
    }

    public void setEmerald_value(int emerald_value) {
        this.emerald_value = emerald_value;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
