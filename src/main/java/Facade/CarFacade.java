package Facade;

import Entity.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * @author Joachim
 */
public class CarFacade implements ICarFacade {
    
    EntityManagerFactory emf;
    EntityManager em;
    public CarFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public void addEntityManager(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }
    
    @Override
    public Car addCar(Car car) {
        addEntityManager(emf);
            try{
                em.getTransaction().begin();

                em.persist(car);

                em.getTransaction().commit();
            }finally{
                em.close();
            }
        return car;
    }

    @Override
    public Car deleteCar(long id) {
        addEntityManager(emf);
        Car car = null;

        try {
            em.getTransaction().begin();
            car = em.find(Car.class, id);
            em.remove(car);
            em.getTransaction().commit();
        }finally{
                em.close();
        }
        return car;
    }

    @Override
    public List<Car> getCars() {
        addEntityManager(emf);
        List<Car> cars; 
            try{
                em.getTransaction().begin();
                Query query = em.createQuery("SELECT e FROM Car e");
                cars = query.getResultList();
                em.getTransaction().commit();
            }finally{
                em.close();
            }
        return cars;
    }

    @Override
    public Car editCar(Car car, long id) {
        addEntityManager(emf);
        Car c = null;

        try {
            em.getTransaction().begin();
            car.setId(id);
            em.merge(car);
            em.getTransaction().commit();
            c = em.find(Car.class, id);
        } finally {
            em.close();
        }
        return c;
    }
    
}
