package inn.service;

import inn.model.Customer;
import inn.model.Person;
import inn.model.Staff;
import inn.repository.PersonRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    public boolean existsByUsername(String username) {
        return personRepository.findByUsername(username).isPresent();
    }

    public Optional<Person> authenticate(String username, String password) {
        val user = personRepository.findByUsername(username);
        if (user.isPresent() && !password.equals(user.get().getPassword())) {
            return Optional.empty();
        }
        return user;
    }

    public void save(Person user) {
        personRepository.save(user);
    }

}
