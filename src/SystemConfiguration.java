package src;

/**
 * Singleton class for managing system configurations.
 */
public class SystemConfiguration {
    private static SystemConfiguration instance = null;
    // Example configuration property
    private String authFilePath;
	private String teachingRequirementFilePath;
	private String trainingSessionFilePath;

    private SystemConfiguration() {
        // Initialize default configurations
    	authFilePath = "src/database/auth.txt";
    	teachingRequirementFilePath = "src/database/teachingRequirement.txt";
    	trainingSessionFilePath = "src/database/trainList.txt";
    }

    /**
     * Returns the single instance of this class.
     * @return The single instance of SystemConfiguration.
     */
    public static synchronized SystemConfiguration getInstance() {
        if (instance == null) {
            instance = new SystemConfiguration();
        }
        return instance;
    }

    /**
     * Gets the resource path.
     * @return The resource path.
     */
    public String getAuthFilePath() {
        return this.authFilePath;
    }
    public String getteachingRequirementFilePath() {
        return teachingRequirementFilePath;
    }
    public String getTrainingSessionFilePath() {
        return trainingSessionFilePath;
    }

    // Add other system configuration methods here
}