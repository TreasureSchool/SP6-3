package SchemaGenerator;

import javax.persistence.Persistence;

/**
 * @author Joachim
 */
public class SchemaGenerator {
    public static void main(String[] args){
        Persistence.generateSchema("0PU", null);
    }
}
