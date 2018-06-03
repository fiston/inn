package inn.service;

import inn.model.Customer;
import inn.model.Person;
import inn.model.Staff;
import inn.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private PersonRepository personRepository;

    @Autowired
    public UserService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Customer newCustomer() {
        return new Customer();
    }

    public Staff newStaff() {
        return new Staff();
    }

    public Person authenticate(String username, String password) {
        Person person = personRepository.findFirstByUsername(username);
        if (person != null && password.equals(person.getPassword())) {
            return person;
        } else {
            return null;
        }
    }

    public void save(Person user) {
        personRepository.save(user);
    }

}
