package utils;

public class Log {
    private static Log instance;
    private StringBuilder logBuffer;

    private Log() {
        logBuffer = new StringBuilder();
    }

    public static synchronized Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEvent(String event) {
        logBuffer.append(event).append("\n");
    }

    public String getLog() {
        return logBuffer.toString();
    }

    public void writeLogToFile(String filePath) {
        // Write logBuffer contents to file
    }
}
