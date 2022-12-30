package medical_analytical_prescription.exception;

public class UserAlreadyRegisteredException extends RuntimeException {
  public UserAlreadyRegisteredException(String message) {
    super(message);
  }
}