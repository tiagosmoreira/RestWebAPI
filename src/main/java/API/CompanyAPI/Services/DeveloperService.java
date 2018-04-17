package API.CompanyAPI.Services;

import API.CompanyAPI.Developer;
import API.CompanyAPI.Dtos.DeveloperDtos.DeveloperCreateDto;
import API.CompanyAPI.Dtos.DeveloperDtos.DeveloperUpdateDto;
import API.CompanyAPI.ErrorResultWrapper;
import API.CompanyAPI.Repositorys.IDeveloperRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DeveloperService {

    private IDeveloperRepository iDeveloperRepository;

    public DeveloperService(IDeveloperRepository iDeveloperRepository) {
        this.iDeveloperRepository = iDeveloperRepository;
    }

    public ErrorResultWrapper findAllDevelopers() {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (iDeveloperRepository.findAll().isEmpty()) {
            errorMap.put("Developers", "There is no developer in the db");
            wrapper.addError(errorMap);
            return wrapper;
        }
        Iterable<Developer> allClients = iDeveloperRepository.findAll();
        wrapper.setResult(allClients);
        return wrapper;
    }


    public ErrorResultWrapper getById(int id) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iDeveloperRepository.existsById(id)) {
            errorMap.put("Developers", "Developer does not exist");
            wrapper.addError(errorMap);
            return wrapper;

        }
        Optional<Developer> developer = iDeveloperRepository.findById(id);

        Developer result = developer.get();
        wrapper.setResult(result);
        return wrapper;

    }

    public ErrorResultWrapper updateDeveloper(int id, DeveloperUpdateDto dto) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();

        Developer developer = (Developer) this.getById(id).getResult();

        if (dto.getEmail().isEmpty()) {
            errorMap.put("Developers", "Email cant be empty");
            wrapper.addError(errorMap);
            return wrapper;
        }
        developer.setEmail(dto.getEmail());

        developer.setTel(dto.getTel());

        iDeveloperRepository.save(developer);

        wrapper.setResult(developer);


        return wrapper;
    }

    public ErrorResultWrapper createDeveloper(DeveloperCreateDto dto) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();

        if (dto.getName().isEmpty()) {
            errorMap.put("Developers", "Developer name cant be empty");
            wrapper.addError(errorMap);
        }
        if (dto.getLogin().isEmpty()) {
            errorMap.put("Developers", "Developer login cant be empty");
            wrapper.addError(errorMap);
        }
        if (dto.getEmail().isEmpty()) {
            errorMap.put("Developers", "Developer email cant be empty");
            wrapper.addError(errorMap);
        }

        if (iDeveloperRepository.existsByEmail(dto.getEmail()) || iDeveloperRepository.existsByLogin(dto.getLogin())) {
            errorMap.put("Developers", "Developer already exists");
            wrapper.addError(errorMap);
        }

        if(wrapper.haveErrors()){
            return wrapper;
        }

        Developer developer = iDeveloperRepository.save(Developer.createDeveloper(dto.getLogin(), dto.getName(), dto.getEmail(), dto.getTel()));

        wrapper.setResult(developer);

        return wrapper;
    }

    public ErrorResultWrapper deleteDeveloper(int id) {
        ErrorResultWrapper wrapper = new ErrorResultWrapper();
        Map<String, String> errorMap = new HashMap<>();
        if (!iDeveloperRepository.existsById(id)) {
            errorMap.put("Developers", "Developer does not exist");
            wrapper.addError(errorMap);
            return wrapper;
        }
        Optional<Developer> developer = iDeveloperRepository.findById(id);

        Developer result = developer.get();
        wrapper.setResult(result);

        iDeveloperRepository.deleteById(id);

        return wrapper;
    }
}
