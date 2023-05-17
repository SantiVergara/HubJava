public class Persona {

  private String nombre;
  private String apellido;
  private Integer edad;

  public Persona(String nombre, String apellido) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
  }

  public Persona(String nombre, String apellido, Integer edad) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
  }

  @Override
  public String toString() {
    return nombre + " " + apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public Integer getEdad() {
    return edad;
  }
}
