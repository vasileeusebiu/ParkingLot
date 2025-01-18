package com.parking.parkinlot3.ejb;

import com.parking.parkinlot3.common.CarDto;
import com.parking.parkinlot3.entites.Car;
import com.parking.parkinlot3.entites.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    // Method to create a car
    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        LOG.info("createCar");
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user = entityManager.find(User.class, userId);
        if (user != null) {
            user.getCars().add(car);
            car.setOwner(user); // Set the car owner
            entityManager.persist(car); // Persist the new car
        } else {
            throw new EJBException("User not found with ID: " + userId);
        }
    }
    public int getTotalCars() {
        LOG.info("getTotalCars");
        try {
            TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM Car c", Long.class);
            return query.getSingleResult().intValue();  // Returnează numărul total de mașini
        } catch (Exception ex) {
            throw new EJBException("Error getting total cars count", ex);
        }
    }

    public List<CarDto> findAllCars() {
        LOG.info("findAllCars");
        try {
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars); // Convert Car entities to CarDto objects
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    public List<String> getAllLicensePlates() {
        LOG.info("getAllLicensePlates");
        try {
            TypedQuery<String> query = entityManager.createQuery("SELECT c.licensePlate FROM Car c", String.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new EJBException("Error fetching license plates", ex);
        }
    }

    private List<CarDto> copyCarsToDto(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarDto(
                        car.getId(),
                        car.getLicensePlate(),
                        car.getParkingSpot(),
                        car.getOwner() != null ? car.getOwner().getUsername() : null,
                        car.getOwner() != null ? car.getOwner().getId() : null // Added ownerId for consistency
                ))
                .collect(Collectors.toList());
    }


    public CarDto findById(Long carId) {
        LOG.info("Searching for car with ID: " + carId);
        Car car = entityManager.find(Car.class, carId);
        if (car != null) {
            LOG.info("Found car with ID: " + car.getId());
            return new CarDto(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot(),
                    car.getOwner().getUsername(),
                    car.getOwner().getId()
            );
        } else {
            LOG.warning("Car not found with ID: " + carId);
            return null;  // Handle case where car is not found
        }
    }


    // Method to update car details using CarDto
    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("updateCar");
        Car car = entityManager.find(Car.class, carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User oldUser = entityManager.find(User.class, userId);
        oldUser.getCars().remove(car);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);
    }
    public void deleteCarsByIds(Collection<Long> carIds) {
        LOG.info("deleteCarsByIds");

        for(Long carId : carIds) {
            Car car = entityManager.find(Car.class, carId);

            if (car != null) {
                entityManager.remove(car); // Ștergem mașina din baza de date
                LOG.info("Car with ID " + carId + " deleted.");
            } else {
                LOG.warning("Car with ID " + carId + " not found.");
            }
        }
    }
}