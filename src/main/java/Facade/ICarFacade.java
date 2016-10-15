package Facade;

import Entity.Car;
import java.util.List;

/**
 * @author Joachim
 */
public interface ICarFacade {
    public Car addCar(Car car);
    public Car deleteCar(long id);
    public List<Car> getCars();
    public Car editCar(Car car, long id);
}
