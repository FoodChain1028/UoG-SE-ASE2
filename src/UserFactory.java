package src;
/**
 * Factory for creating User objects.
 */
class UserFactory {
    /**
     * Creates a User object based on the given type.
     * @param userType The type of user to create.
     * @param name The name of the user.
     * @return A new User object of the specified type.
     */
    public static User createUser(String userType, String name) {
        switch (userType.toLowerCase()) {
            case "administrator":
                return new Administrator(name);
            case "classdirector":
                return new ClassDirector(name);
            case "teacher":
                return new Teacher(name);
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}
