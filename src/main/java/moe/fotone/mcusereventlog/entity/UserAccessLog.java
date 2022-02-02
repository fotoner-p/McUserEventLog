package moe.fotone.mcusereventlog.entity;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "user_access_log")
public class UserAccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Date start_time;

    @Column(nullable = false)
    private Date end_time;

    @ManyToOne
    @JoinColumn(name="uuid")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
