package API.CompanyAPI.Repositorys;

import API.CompanyAPI.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client,Integer> {
    boolean existsByName(String name);
    Client getById(int id);
}
