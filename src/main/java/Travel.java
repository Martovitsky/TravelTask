import javax.persistence.*;

@Entity
@Table (name = "penis")
public class Travel {

    private Integer id;
    private String person;
    private String misc;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

}
