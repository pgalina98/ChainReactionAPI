package hr.pgalina.chain_reaction.domain.exception.contants;

public final class ExceptionMessages {

    public static final String INVALID_JWT_TOKEN = "Invalid JWT token.";
    public static final String UNKNOWN_JWT_TOKEN = "Current user's JWT is unknown.";

    public static final String UNAUTHORIZED_REQUEST = "Authorization is required to access API endpoints.";

    public static final String USER_DOES_NOT_EXIST = "User with provided username doesn't exist.";
    public static final String INVALID_USERNAME_OR_PASSWORD = "Username or password doesn't match.";

    public static final String USER_WITH_PROVIDED_USERNAME_ALREADY_EXISTS = "User with provided username already exists.";

    public static final String PRODUCT_TYPE_DOES_NOT_EXIST = "Product type doesn't exist.";
}
