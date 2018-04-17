package API.CompanyAPI.Dtos.ClientDtos;

public class ClientUpdateDto {
    private String representativeName;
    private String representativeEmail;

    public String getRepresentativeName() {
        return representativeName;
    }

    public String getRepresentativeEmail() {
        return representativeEmail;
    }

    public void setRepresentativeEmail(String representativeEmail) {
        this.representativeEmail = representativeEmail;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }
}