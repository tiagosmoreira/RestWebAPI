package API.CompanyAPI.Controllers;


import API.CompanyAPI.Dtos.DeveloperDtos.DeveloperCreateDto;
import API.CompanyAPI.Dtos.DeveloperDtos.DeveloperUpdateDto;
import API.CompanyAPI.ErrorResultWrapper;
import API.CompanyAPI.Repositorys.IDeveloperRepository;
import API.CompanyAPI.Services.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/developers")
public class DeveloperController {

    private DeveloperService developerService;

    @Inject
    public void setClientService(IDeveloperRepository iDeveloperRepository) {
        this.developerService = new DeveloperService(iDeveloperRepository);
    }

    @GetMapping(path = "")
    public @ResponseBody
    ResponseEntity<?> getAllDevelopers() {
        ErrorResultWrapper wrapper = developerService.findAllDevelopers();
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findDeveloper(@PathVariable int id) {
        ErrorResultWrapper wrapper = developerService.getById(id);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editDeveloper(@PathVariable int id, @RequestBody DeveloperUpdateDto editDTO) {
        ErrorResultWrapper wrapperFind = developerService.getById(id);
        if(wrapperFind.haveErrors()){
            return new ResponseEntity<>(wrapperFind.getError(), HttpStatus.NOT_FOUND);
        }
        ErrorResultWrapper wrapper = developerService.updateDeveloper(id, editDTO);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public @ResponseBody
    ResponseEntity<?> addNewDeveloper(@RequestBody DeveloperCreateDto createDTO) {
        ErrorResultWrapper wrapper = developerService.createDeveloper(createDTO);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> deleteDeveloper(@PathVariable int id) {
        ErrorResultWrapper wrapper = developerService.deleteDeveloper(id);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }
}