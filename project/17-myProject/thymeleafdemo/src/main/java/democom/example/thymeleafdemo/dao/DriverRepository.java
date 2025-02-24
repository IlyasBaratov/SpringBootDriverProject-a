package democom.example.thymeleafdemo.dao;


import democom.example.thymeleafdemo.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    // that's it ... no need to write any code LOL!



    // add a methos to sort by a last name
    public List<Driver> findAllByOrderByFirstNameAsc();
    public List<Driver> findAllByOrderByEmailAsc();
//    public Comparable<String> findByEmail(String email);
//    public List<Driver> findAllByEmail(String email);

    Optional<Driver> findByEmail(String email);
//@Query("SELECT d FROM Driver d WHERE LOWER(d.email) = LOWER(:email)")
//List<Driver> findAllByEmail(@Param("email") String email);
}
