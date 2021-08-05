package appMain.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import appMain.jpa.entity.City;

@Service
public interface CityInterface extends JpaRepository<City, Integer> {

}
