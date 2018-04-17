package API.CompanyAPI.Dtos.ProjectDtos;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProjectCreateDto {

    @NotNull
    private String code;
    @NotNull
    private String name;
    private Date beginDate;
    private Date endDate;
    @NotNull
    //id
    private int idClient;
//
//    private String developer;


    public boolean requiredParametersEmpty() {
        if (this.code.equals("") || this.name.equals("")) {
            return true;
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getClient() {
        return idClient;
    }

//    public String getDeveloper() {
//        return developer;
//    }

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

    public void setClient(int client) {
        this.idClient = client;
    }

//    public void setDeveloper(String developers) {
//        this.developer = developers;
//    }
}
