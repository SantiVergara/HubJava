import java.lang.annotation.Repeatable;
import java.text.NumberFormat;
import java.time.Clock;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SummaryJava {
  public static void main(String[] args) {

    // Java 8

    // Expresión Lambda
    //La expresión Lambda es una función anónima (una función sin nombre) que ayuda a escribir código en un cierto estilo funcional.
    // Dado que los datos se pueden iterar, filtrar y extraer, es muy útil, especialmente en la biblioteca de recopilación. La expresión Lambda también ayuda a reducir la complejidad del código.

    // En ambos casos se está definiendo una función que dado un String devolverá la longitud de la cadena de caracteres que almacene

    /*Function<String,Integer> sizeOf = (String s) -> {
      return s.length();
    };*/

    // version compacta

    Function<String, Integer> sizeOf = s -> s.length();

    //System.out.println("sizeOf = " + sizeOf);

    //Stream API
    //Stream API ofrece una técnica para el procesamiento de datos de diferentes maneras,
    // como filtrado, extracción, transformación, con la ayuda del paquete java.util.stream.

    List<Persona> personas = new ArrayList<>();
    Persona persona1 = new Persona("Santi", "Vergara", 21);
    var persona2 = new Persona("Fulanito", "Detal", 25);
    var personaNull = new Persona(null, "nullo", 30);
    personas.add(persona1);
    personas.add(persona2);
    personas.add(personaNull);

    Stream<Persona> stream = personas.stream();

    List<String> nombres = stream
        // filtrado de los elementos que tienen nombre nullo
        .filter(p -> p.getNombre() != null)

        // aplicar una conversión, de Persona a String
        // quedándonos con el nombre
        .map(p -> p.getNombre())
        // a partir de aquí se tiene un Stream<String>

        // recolectar los elementos en una lista
        .collect(Collectors.toList());

    System.out.println("nombres = " + nombres);



    //map y flatmap, reviews anymatch y filter

    //flatMap()
    List<List<Integer>> listaBidimensional = new ArrayList<List<Integer>>(Arrays.asList(
        new ArrayList<Integer>(Arrays.asList(1, 2)),
        new ArrayList<Integer>(Arrays.asList(3, 4))
    ));
    // [
    //   [1, 2],
    //   [3, 4]
    // ]


    List<Integer> listaAplanada = listaBidimensional
        .stream()
        .flatMap(listaInterna -> listaInterna.stream())
        .collect(Collectors.toList());


    for (Integer n : listaAplanada) {
      System.out.println(n);
    }

    // Imprime los números de entrada en una sola lista de 1 dimensión.
    // 2 elementos de entrada (2 listas), 4 de salida
    // 1
    // 2
    // 3
    // 4






    //ForEach ()
    // El bucle ForEach () se puede usar en una clase de colección que extiende la interfaz Iterable. Como este método ingresa un solo parámetro,
    // una expresión lambda también se puede pasar como parámetro.

    personas.forEach(p -> {
      System.out.println(p.getEdad());
    });

    //dada una lista de numeros enteros, sumar esa lista (de 3 formas: con reviews, foreach modificando variable externas variables atómicas )

    //.....


    //En Java 8, se ha introducido una nueva API de tiempo y API de fecha donde las fechas de manejo están en un método diferente en
    // comparación con otras versiones de Java. Algunas de las siguientes son clases de hora y fecha que están disponibles en Java.
    // paquete de tiempo:
    //Clase Jtime.LocalDate
    //Clase LocalTime
    //Clase LocalDateTime
    //Clase de MonthDay

    Clock cl = Clock.systemDefaultZone();
    System.out.println(cl.getZone());


    //Java 10
    //Inferencia de tipo variable local
    //La inferencia de tipo variable local introducirá la palabra clave var, es decir,
    // puede definir libremente las variables sin tener que especificar el tipo de variable, como por ej:
    //Pasamos de esto:
    List<String> list = new ArrayList<String>();

    //A esto:
    var list1 = new ArrayList<String>();

    // Java 11
    /*
    * Cliente HTTP
    La meta con la nueva característica API HttpClient es proporcionar una manera estándar de consumir ApiRest, usando patrones modernos
    y mejores prácticas ya aplicadas en las librerías de terceros mencionadas anteriormente.

    Tiene también, soporte para conexiones seguras, HTTP2, websockets y request asíncronos. Hay 3 conceptos básicos en este API:

    HttpClient: Se encarga de crear la conexión y su configuración. Proxies, Authenticators, executors pools, cookie manager, etc.
    HttpRequest: Es básicamente el request a un URI. Podemos indicar el timeout, headers, si el request es asíncrono o síncrono.
    HttpResponse: Objeto para manejar la respuesta del request. En él se puede ver el status code, body response, headers y más.
    *
    * */


    // Ejecución desde archivo de código fuente único
    //Para ejecutar un programa Java es necesario primero compilarlo a bytecode para posteriormente ejecutarlo.
    // Se necesitan dos pasos para facilitar la ejecución de los programas que se componen de un único archivo de código fuente,
    // se suma la posibilidad de lanzar un programa desde el código fuente.
    // Esto es útil para programas pequeños o para los casos de estar aprendiendo el lenguaje.

    NumberFormat fmt = NumberFormat.getCompactNumberInstance(
        new Locale("hi", "IN"), NumberFormat.Style.SHORT);
    String result = fmt.format(1000);
    System.out.println("result = " + result);


  }

}


