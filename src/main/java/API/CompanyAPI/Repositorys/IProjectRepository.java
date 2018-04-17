package API.CompanyAPI.Repositorys;

import API.CompanyAPI.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project,Integer> {
    boolean existsByCode(String code);
    boolean existsByName(String name);
    boolean existsById(int id);
    Project getById(int id);
}