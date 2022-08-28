package Models;

class PersonNotFoundException extends RuntimeException {
    PersonNotFoundException(Long id) {
        System.out.println("Person not found ID: " + id);
    }
}