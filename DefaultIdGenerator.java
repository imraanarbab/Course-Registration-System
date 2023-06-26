    // Student id generator
import java.util.Random;
    class DefaultIdGenerator implements IdGenerator {
        int nextId;
        Random random;

        DefaultIdGenerator() {
            nextId = 1;
        }

        public int getNextId() {
            int id = nextId;
            nextId++;
            return id;
        }
        
        public int generateId() {
            // Generate a random 6-digit student ID
            int studentId = random.nextInt(900000) + 100000;
            return studentId;
        }
    }
