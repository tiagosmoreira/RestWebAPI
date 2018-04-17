package API.CompanyAPI.Dtos.DeveloperDtos;

import javax.validation.constraints.NotNull;

public class DeveloperCreateDto {

        @NotNull(message = "login cannot be null")
        private String login;
        @NotNull(message = "name cannot be null")
        private String name;
        @NotNull(message = "email cannot be null")
        private String email;
        private int tel;

    public boolean requiredParametersEmpty(){
        if(this.name.equals("") || this.login.equals("") || this.email.equals("")){
            return true;
        }
        return false;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getTel() {
        return tel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
}