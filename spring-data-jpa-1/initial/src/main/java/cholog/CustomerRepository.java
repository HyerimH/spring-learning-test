package cholog;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByLastNameIgnoreCase(String lastName);

    List<Customer> findByLastNameOrderByFirstNameDesc(String lastName);

}
