//This is the default sessionId Generator     
class DefaultSessionIdGenerator implements SessionIdGenerator {
        int nextSessionId;

        DefaultSessionIdGenerator() {
            nextSessionId = 1;
        }

        public int getNextSessionId() {
            int id = nextSessionId;
            nextSessionId++;
            return id;
        }
    }
