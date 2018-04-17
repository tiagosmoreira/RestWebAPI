package API.CompanyAPI.Dtos.ProjectDtos;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProjectUpdateDto {
    @NotNull
    private String name;
    private Date beginDate;
    private Date endDate;



    public String getName() {
        return name;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
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
}
