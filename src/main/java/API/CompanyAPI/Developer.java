package API.CompanyAPI;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull (message = "login is empty")
    private String login;
    @NotNull
    private String name;
    @NotNull
    private String email;
    private int tel;

    public static Developer createDeveloper(String login, String name, String email, int tel) {

        Developer result = new Developer();
        result.setLogin(login);
        result.setName(name);
        result.setEmail(email);
        result.setTel(tel);

        return result;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getTel() {
        return tel;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
