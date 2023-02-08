package medical_analytical_prescription.exception;

public class ApplicationNotFoundException extends RuntimeException {
  public ApplicationNotFoundException(String message) {
    super(message);
  }
}
