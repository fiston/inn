package inn.service;

import inn.model.Customer;
import inn.model.Person;
import inn.model.Staff;
import inn.repository.PersonRepository;
import lombok.val;
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

    public boolean existsByUsername(String username) {
        return personRepository.findFirstByUsername(username) != null;
    }

    public Person authenticate(String username, String password) {
        val user = personRepository.findFirstByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            return null;
        }
        return user;
    }

    public void save(Person user) {
        personRepository.save(user);
    }

}
