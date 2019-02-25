package core.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="last_audit")
    private Date lastAudit;

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        this.id = _id;
    }

    public Date getLastAudit() {
        return lastAudit;
    }

    public void setLastAudit(Date _lastAudit) {
        this.lastAudit = _lastAudit;
    }
}
