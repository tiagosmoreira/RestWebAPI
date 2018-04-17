package API.CompanyAPI.Controllers;


import API.CompanyAPI.Dtos.ProjectDtos.ProjectCreateDto;
import API.CompanyAPI.Dtos.ProjectDtos.ProjectUpdateDto;
import API.CompanyAPI.ErrorResultWrapper;
import API.CompanyAPI.Repositorys.IClientRepository;
import API.CompanyAPI.Repositorys.IDeveloperRepository;
import API.CompanyAPI.Repositorys.IProjectRepository;
import API.CompanyAPI.Services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/projects")
public class ProjectController {


    ProjectService projectService;

    @Inject
    public void setIProjectRepository(IProjectRepository iProjectRepository, IClientRepository iClientRepository, IDeveloperRepository iDeveloperRepository) {
        this.projectService = new ProjectService(iProjectRepository, iClientRepository, iDeveloperRepository);
    }

    @GetMapping(path = "")
    public @ResponseBody
    ResponseEntity<?> getAllProjects() {
        ErrorResultWrapper wrapper = projectService.findAllProjects();
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findProject(@PathVariable int id) {
        ErrorResultWrapper wrapper = projectService.getById(id);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editProject(@PathVariable int id, @RequestBody ProjectUpdateDto editDTO) {
        ErrorResultWrapper wrapper = projectService.updateProject(id, editDTO);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public @ResponseBody
    ResponseEntity<?> addNewProject(@RequestBody ProjectCreateDto createDTO) {
        ErrorResultWrapper wrapper = projectService.createProject(createDTO);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> deleteProject(@PathVariable int id) {
        ErrorResultWrapper wrapper = projectService.deleteProject(id);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }


    @PutMapping(path = "/{id1}/{id2}")
    public @ResponseBody
    ResponseEntity<?> addDeveloperToProject(@PathVariable("id1") int idProject, @PathVariable("id2") int idDev) {
        ErrorResultWrapper wrapper = projectService.addDeveloperToProject(idDev, idProject);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id1}/{id2}")
    public @ResponseBody
    ResponseEntity<?> removeDeveloperFromProject(@PathVariable("id1") int idProject, @PathVariable("id2") int idDev) {
        ErrorResultWrapper wrapper = projectService.removeDeveloperProject(idDev, idProject);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/developers")
    public @ResponseBody
    ResponseEntity<?> developersOfProject(@PathVariable int id){
        ErrorResultWrapper wrapper = projectService.getDevelopersOfProject(id);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/clients")
    public @ResponseBody
    ResponseEntity<?> clientOfProject(@PathVariable int id){
        ErrorResultWrapper wrapper = projectService.getClientOfProject(id);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }

}