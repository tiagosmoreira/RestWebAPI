package API.CompanyAPI.Services;

import API.CompanyAPI.Developer;
import API.CompanyAPI.Dtos.ProjectDtos.ProjectCreateDto;
import API.CompanyAPI.Dtos.ProjectDtos.ProjectUpdateDto;
import API.CompanyAPI.ErrorResultWrapper;
import API.CompanyAPI.Project;
import API.CompanyAPI.Repositorys.IClientRepository;
import API.CompanyAPI.Repositorys.IDeveloperRepository;
import API.CompanyAPI.Repositorys.IProjectRepository;

import java.util.*;

public class ProjectService {

    private IProjectRepository iProjectRepository;
    private IDeveloperRepository iDeveloperRepository;
    private IClientRepository iClientRepository;

    public ProjectService(IProjectRepository iProjectRepository, IClientRepository iClientRepository, IDeveloperRepository iDeveloperRepository) {
        this.iProjectRepository = iProjectRepository;
        this.iClientRepository = iClientRepository;
        this.iDeveloperRepository = iDeveloperRepository;
    }

    public ErrorResultWrapper findAllProjects() {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (iProjectRepository.findAll().isEmpty()) {
            errorMap.put("Projects", "There are no project in the db");
            wrapper.addError(errorMap);
            return wrapper;
        }
        Iterable<Project> allProjects = iProjectRepository.findAll();
        wrapper.setResult(allProjects);
        return wrapper;
    }

    public ErrorResultWrapper getById(int id) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iProjectRepository.existsById(id)) {
            errorMap.put("Projects", "Project does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }
        Optional<Project> project = iProjectRepository.findById(id);

        Project result = project.get();
        wrapper.setResult(result);
        return wrapper;
    }

    public ErrorResultWrapper updateProject(int id, ProjectUpdateDto dto) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iProjectRepository.existsById(id)) {
            errorMap.put("Projects", "Project does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }

        Project project = (Project) this.getById(id).getResult();

        project.setName(dto.getName());
        project.setBeginDate(dto.getBeginDate());
        project.setEndDate(dto.getEndDate());

        iProjectRepository.save(project);
        wrapper.setResult(project);
        return wrapper;
    }


    public ErrorResultWrapper createProject(ProjectCreateDto dto) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();

        if (dto.getName().isEmpty()) {
            errorMap.put("Projects", "Project name cant be empty");
            wrapper.addError(errorMap);
        }
        if (dto.getCode().isEmpty()) {
            errorMap.put("Projects", "Project code cant be empty");
            wrapper.addError(errorMap);
        }
        if (iProjectRepository.existsByCode(dto.getCode()) || iProjectRepository.existsByName(dto.getName())) {
            errorMap.put("Projects", "Project already exists");
            wrapper.addError(errorMap);
        }
        if (!iClientRepository.existsById(dto.getClient())) {
            errorMap.put("Projects", "Client does not exist");
            wrapper.addError(errorMap);
        }
        if (wrapper.haveErrors()) {
            return wrapper;
        }

        Project project = iProjectRepository.save(Project.createProject(dto.getCode(), dto.getName(), dto.getBeginDate(), dto.getEndDate(), iClientRepository.getById(dto.getClient())));

        wrapper.setResult(project);
        return wrapper;
    }

    public ErrorResultWrapper deleteProject(int id) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iProjectRepository.existsById(id)) {
            errorMap.put("Projects", "Project does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }

        Optional<Project> project = iProjectRepository.findById(id);

        Project result = project.get();
        result.setDevelopers(Collections.emptySet());
        wrapper.setResult(result);

        iProjectRepository.deleteById(id);

        return wrapper;
    }

    public ErrorResultWrapper addDeveloperToProject(int idDev, int idProject) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();

        Project project = iProjectRepository.getById(idProject);
        if (!project.getDevelopers().isEmpty()) {
            Set<Developer> developers = project.getDevelopers();

            if (developers.contains(iDeveloperRepository.getById(idDev))) {
                    errorMap.put("Projects", "Developer is already in the project");
                wrapper.addError(errorMap);
                return wrapper;
            }
        }
        if (!iProjectRepository.existsById(idProject)) {
            errorMap.put("Projects", "Project does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }
        if (!iDeveloperRepository.existsById(idDev)) {
            errorMap.put("Projects", "Developer does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }

        project.addDeveloper(idDev, iDeveloperRepository);

        iProjectRepository.save(project);
        wrapper.setResult(project);
        return wrapper;
    }

    public ErrorResultWrapper removeDeveloperProject(int idDev, int idProject) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iProjectRepository.existsById(idProject)) {
            errorMap.put("Projects", "Project does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }

        Project project = (Project) this.getById(idProject).getResult();

        if (!project.getDevelopers().contains(iDeveloperRepository.getById(idDev))) {
            errorMap.put("Projects", "Project does not have that developer");
            wrapper.addError(errorMap);
            return wrapper;
        }

        Set<Developer> developers = project.getDevelopers();

        developers.remove(iDeveloperRepository.getById(idDev));

        project.setDevelopers(developers);

        iProjectRepository.save(project);
        wrapper.setResult(project);
        return wrapper;
    }

    public ErrorResultWrapper getDevelopersOfProject(int id) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iProjectRepository.existsById(id)) {
            errorMap.put("Projects", "Project does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }

        Project project = iProjectRepository.getOne(id);
        wrapper.setResult(project.getDevelopers());
        return wrapper;
    }

    public ErrorResultWrapper getClientOfProject(int id) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iProjectRepository.existsById(id)) {
            errorMap.put("Projects", "Project does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }

        Project project = iProjectRepository.getOne(id);
        wrapper.setResult(project.getClient());
        return wrapper;
    }
}