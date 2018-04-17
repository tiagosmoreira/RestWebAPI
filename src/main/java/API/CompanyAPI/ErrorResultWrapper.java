package API.CompanyAPI;

import java.util.HashSet;
import java.util.Set;

public class ErrorResultWrapper<ERROR, RESULT> {

    private RESULT result;
    private Set<ERROR> error;

    public ErrorResultWrapper() {
        error = new HashSet<>();
    }

    public RESULT getResult() {
        return result;
    }

    public Set<ERROR> getError() {
        return new HashSet<>(error);
    }

    public void addError(ERROR error) {
        this.error.add(error);
    }

    public void setResult(RESULT result) {
        this.result = result;
    }

    public boolean haveErrors() {
        if(error.isEmpty()) {
            return false;
        }
        return true;
    }
}
