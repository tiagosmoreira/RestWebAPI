package API.CompanyAPI.Services;

import API.CompanyAPI.Client;
import API.CompanyAPI.Dtos.ClientDtos.ClientCreateDto;
import API.CompanyAPI.Dtos.ClientDtos.ClientUpdateDto;
import API.CompanyAPI.ErrorResultWrapper;
import API.CompanyAPI.Repositorys.IClientRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ClientService {

    private IClientRepository iClientRepository;

    public ClientService(IClientRepository iClientRepository) {
        this.iClientRepository = iClientRepository;
    }


    public ErrorResultWrapper findAllClients() {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (iClientRepository.findAll().isEmpty()) {
            errorMap.put("Clients", "There is no client in the db");
            wrapper.addError(errorMap);
            return wrapper;
        }
        Iterable<Client> allClients = iClientRepository.findAll();
        wrapper.setResult(allClients);
        return wrapper;
    }

    public ErrorResultWrapper getById(int id) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iClientRepository.existsById(id)) {
            errorMap.put("Clients", "There is no client with that id");
            wrapper.addError(errorMap);
            return wrapper;

        }
        Optional<Client> optionalClient = iClientRepository.findById(id);

        Client client = optionalClient.get();

        wrapper.setResult(client);

        return wrapper;
    }

    public ErrorResultWrapper upDateClient(int id, ClientUpdateDto dto) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iClientRepository.existsById(id)) {
            errorMap.put("Clients", "Client does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }

        Client client = (Client) this.getById(id).getResult();

        client.setRepresentativeName(dto.getRepresentativeName());

        client.setRepresentativeEmail(dto.getRepresentativeEmail());

        iClientRepository.save(client);

        wrapper.setResult(client);

        return wrapper;
    }

    public ErrorResultWrapper createClient(ClientCreateDto dto) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();

        if (dto.getName().isEmpty()) {
            errorMap.put("Clients", "Client name cant be empty");
            wrapper.addError(errorMap);
            return wrapper;
        }
        if (iClientRepository.existsByName(dto.getName())) {
            errorMap.put("Clients", "Client already exists");
            wrapper.addError(errorMap);
            return wrapper;
        }
        Client client = iClientRepository.save(Client.createCliente(dto.getName(), dto.getRepresentativeName(), dto.getRepresentativeEmail()));

        wrapper.setResult(client);

        return wrapper;
    }

    public ErrorResultWrapper deleteClient(int id) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iClientRepository.existsById(id)) {
            errorMap.put("Clients", "Client does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }
        Optional<Client> client = iClientRepository.findById(id);

        Client result = client.get();

        wrapper.setResult(result);

        iClientRepository.deleteById(id);

        return wrapper;
    }

}