import java.util.Random;
//This is the random faculty id generator
class RandomIdGenerator implements IdGenerator {
        private Random random;

        public RandomIdGenerator() {
            random = new Random();
        }

        public int getNextId() {
            return random.nextInt(1000); // Adjust the range as needed
        }
        public int generateId() {
            // Generate a random 6-digit Faculty ID
            int facultyId = random.nextInt(900000) + 100000;
            return facultyId;
        }
    }
