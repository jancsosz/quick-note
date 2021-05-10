package notes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Note {

    public enum Priority {
        VERY_LOW,
        LOW,
        NORMAL,
        HIGH,
        VERY_HIGH
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userFk;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Priority priority;

    @Column()
    private Date commentedAt;
}