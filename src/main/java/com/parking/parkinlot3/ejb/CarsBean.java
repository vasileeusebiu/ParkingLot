package com.parking.parkinlot3.ejb;

import com.parking.parkinlot3.common.CarDto;
import com.parking.parkinlot3.entites.Car;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;



@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> findAllCars(){
        LOG.info("findAllCars");
        try{
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        }
        catch(Exception ex){
            throw new EJBException(ex);
        }
    }
    //pentru a transforma din lista cars in carsdto
    private List<CarDto> copyCarsToDto(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarDto(
                        car.getId(),
                        car.getLicensePlate(),
                        car.getParkingSpot(),
                        car.getOwner() != null ? car.getOwner().getUsername() : null  // Aflăm numele proprietarului
                ))
                .collect(Collectors.toList());
    }

}