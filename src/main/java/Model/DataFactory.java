package Model;

import Entity.Car;
import Facade.CarFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Joachim
 */
public class DataFactory {
    
    List<Car> cars = new ArrayList();
    private final Gson gson = new GsonBuilder().
            setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").
            setPrettyPrinting().create();
    
    public DataFactory(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("0PU");
        CarFacade cf = new CarFacade(emf);
        cars = cf.getCars();
    }
    public String getCarsAsJson(){
    JsonArray carsJson = new JsonArray();
    for(Car c: cars){
      JsonObject cj = getCarAsJsonObject(c);
      carsJson.add(cj);
    }
    return gson.toJson(carsJson);
  };
    private JsonObject getCarAsJsonObject(Car c) {
    JsonObject cj = new JsonObject();
    cj.addProperty("id",c.getId());
    cj.addProperty("model_year",c.getModel_year());
    cj.addProperty("make",c.getMake());
    cj.addProperty("description",c.getDescription());
    cj.addProperty("model",c.getModel());
    cj.addProperty("price",c.getPrice());
    return cj;
  }
    public static void main(String[] args) {
        DataFactory f = new DataFactory();       
        System.out.println(f.getCarsAsJson());
    }
}
