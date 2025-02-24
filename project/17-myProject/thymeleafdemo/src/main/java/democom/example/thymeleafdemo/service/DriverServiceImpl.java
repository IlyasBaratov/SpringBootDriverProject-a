package democom.example.thymeleafdemo.service;

import democom.example.thymeleafdemo.dao.DriverRepository;
import democom.example.thymeleafdemo.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Driver findById(int id) {
        Optional<Driver> result = driverRepository.findById(id);

        Driver driver = null;

        if (result.isPresent()) {
            driver = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find driver id - " + id);
        }

        return driver;
    }

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteById(int id) {
        driverRepository.deleteById(id);
    }
    public Optional<Driver> findByEmail(String email) {
        return driverRepository.findByEmail(email);
    }
}






