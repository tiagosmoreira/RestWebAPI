package API.CompanyAPI.Repositorys;

import API.CompanyAPI.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeveloperRepository extends JpaRepository<Developer, Integer> {
    boolean existsByLogin(String login);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    Developer getById(int id);
}
