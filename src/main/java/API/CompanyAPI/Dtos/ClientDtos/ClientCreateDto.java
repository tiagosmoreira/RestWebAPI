package API.CompanyAPI.Dtos.ClientDtos;

import javax.validation.constraints.NotNull;

public class ClientCreateDto {

    @NotNull (message = "Name cannot be null")
    private String name;
    private String representativeName;
    private String representativeEmail;

    public boolean nameIsEmpty(){
        if(this.name.equals("")){
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public String getRepresentativeEmail() {
        return representativeEmail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRepresentativeEmail(String representativeEmail) {
        this.representativeEmail = representativeEmail;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public void getRepresentativeEmail(String representativeEmail) {
    }

    //capturar erros deste lado do dto
    //wraper errors
}
