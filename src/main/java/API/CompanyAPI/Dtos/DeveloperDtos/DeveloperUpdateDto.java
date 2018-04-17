package API.CompanyAPI.Dtos.DeveloperDtos;

public class DeveloperUpdateDto {

    private String email;
    private int tel;

    public String getEmail() {
        return email;
    }

    public int getTel() {
        return tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
}