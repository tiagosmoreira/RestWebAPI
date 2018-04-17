package API.CompanyAPI.Controllers;


import API.CompanyAPI.Dtos.ClientDtos.ClientCreateDto;
import API.CompanyAPI.Dtos.ClientDtos.ClientUpdateDto;
import API.CompanyAPI.ErrorResultWrapper;
import API.CompanyAPI.Repositorys.IClientRepository;
import API.CompanyAPI.Services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    @Inject
    public void setClientService(IClientRepository iClientRepository) {
        this.clientService = new ClientService(iClientRepository);
    }

    @GetMapping(path = "")
    public @ResponseBody
    ResponseEntity<?> getAllClients() {
        ErrorResultWrapper wrapper = clientService.findAllClients();
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findClient(@PathVariable int id) {
        ErrorResultWrapper wrapper = clientService.getById(id);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editClient(@PathVariable int id, @RequestBody ClientUpdateDto editDTO) {
        ErrorResultWrapper wrapper = clientService.upDateClient(id, editDTO);
        if(wrapper.haveErrors()){
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public @ResponseBody
    ResponseEntity<?> addNewClient(@RequestBody ClientCreateDto createDTO) {
        ErrorResultWrapper wrapper = clientService.createClient(createDTO);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<?> deleteClient(@PathVariable int id) {
        ErrorResultWrapper wrapper = clientService.deleteClient(id);
        if (wrapper.haveErrors()) {
            return new ResponseEntity<>(wrapper.getError(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wrapper.getResult(), HttpStatus.NOT_FOUND);
    }
}
