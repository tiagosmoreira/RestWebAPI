package API.CompanyAPI;

import API.CompanyAPI.Repositorys.IDeveloperRepository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private String code;
    @NotNull
    private String name;
    private Date beginDate;
    private Date endDate;
    @ManyToOne
    @NotNull
    private Client client;
    @ManyToMany
    @JoinColumn(name = "project_fk")
    private Set<Developer> developers = new HashSet<>();

    public static Project createProject(String code, String name, Date beginDate, Date endDate, Client client) {

        Project result = new Project();
        result.setCode(code);
        result.setName(name);
        result.setBeginDate(beginDate);
        result.setEndDate(endDate);
        result.setClient(client);

        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Set<Developer> getDevelopers(){
        return developers;
    }

    public void addDeveloper(int id, IDeveloperRepository repository){
        developers.add(repository.getById(id));
    }

    public void removeDeveloper(Developer developer){
        developers.remove(developer);
    }

    public boolean existDev(Developer developer){
        if (developers.contains(developer)){
            return true;
        }
        return false;
    }
}
