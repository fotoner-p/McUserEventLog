package moe.fotone.mcusereventlog.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "mating_log")
public class MatingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mating_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String animal_type;

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

    public String getAnimal_type() {
        return animal_type;
    }

    public void setAnimal_type(String animal_type) {
        this.animal_type = animal_type;
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
