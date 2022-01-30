package moe.fotone.mcusereventlog.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "monster_hunting_log")
public class MonsterHuntingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date created_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}
