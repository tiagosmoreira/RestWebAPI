package API.CompanyAPI;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private String name;
    private String representativeName;
    private String representativeEmail;

    public static Client createCliente(String name, String representativeName, String representativeEmail) {
        Client result = new Client();
        result.setName(name);
        result.setRepresentativeName(representativeName);
        result.setRepresentativeEmail(representativeEmail);

        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public String getName() {
        return name;
    }

    public String getRepresentativeEmail() {
        return representativeEmail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public void setRepresentativeEmail(String representativeEmail) {
        this.representativeEmail = representativeEmail;
    }
}