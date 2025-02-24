package democom.example.thymeleafdemo.service;



import democom.example.thymeleafdemo.entity.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> findAll();

    Driver findById(int theId);

    Driver save(Driver theEmployee);

    void deleteById(int theId);

}
