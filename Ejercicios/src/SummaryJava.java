import java.text.NumberFormat;
import java.time.Clock;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
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

    //anymatch y filter

    List<Persona> personas = new ArrayList<>();
    Persona persona1 = new Persona("Santi", "Vergara", 21);
    var persona2 = new Persona("Fulanito", "Detal", 25);
    var personaNull = new Persona(null, "nullo", 30);
    personas.add(persona1);
    personas.add(persona2);
    personas.add(personaNull);

    Stream<Persona> stream = personas.stream();

    //e utiliza para seleccionar elementos de una secuencia que cumplan cierta condición. Toma como argumento un predicado, que es una función que devuelve un valor booleano.
    // El resultado de filter es un nuevo flujo (Stream) que contiene solo los elementos que cumplen con el predicado.
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


    List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

    List<Integer> numerosPares = numeros.stream()
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());
    System.out.println("\nnumerosPares = " + numerosPares);

    List<Integer> list2 = Arrays.asList(3, 4, 6, 12, 20);


    //anyMatch
    //se utiliza para verificar si al menos un elemento de una secuencia cumple cierta condición. También toma como argumento un predicado y devuelve un valor booleano:
    // true si al menos un elemento cumple la condición, o false en caso contrario.
    //verificar si algún elemento en la lista cumple con la condición dada.


    boolean hayNumerosPares = numeros.stream().anyMatch(n -> n % 2 == 0);
    System.out.println("hayNumerosPares = " + hayNumerosPares);

    boolean answer = list2.stream().anyMatch(n -> (n * (n + 1)) / 4 == 5);
    System.out.println("\nanyMatch: \n" + answer);

    Stream<String> stream1 = Stream.of("Geeks", "fOr",
        "GEEKSQUIZ", "GeeksforGeeks");

    // comprobar si algún elemento de la lista tiene mayúsculas en el primer índice.
    boolean answer1 = stream1.anyMatch(str -> Character.isUpperCase(str.charAt(1)));
    System.out.println(answer1);




    //map y flatmap

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

    System.out.println("\nLista con flatMap: ");
    for (Integer n : listaAplanada) {
      System.out.println(n);
    }

    // Imprime los números de entrada en una sola lista de 1 dimensión.
    // 2 elementos de entrada (2 listas), 4 de salida
    // 1
    // 2
    // 3
    // 4


    List<Integer> numeros2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)); // [1, 2, 3, 4]

    List<Integer> cuadrados = numeros2
        .stream()
        .map(x -> x * x)
        .collect(Collectors.toList());
    System.out.println("\nLista con map: ");
    for (Integer n : cuadrados) {
      System.out.println(n);
    }

    // Imprime el cuadrado de los números de entrada. 4 elementos de entrada, 4 de salida
    // 1
    // 4
    // 9
    // 16


    //dada una lista de numeros enteros, sumar esa lista (de 2 formas: con reviews, foreach modificando variable externas variables atómicas )

    //reduce
    Optional<Integer> suma = numeros.stream().reduce((a, b) -> a + b);
    System.out.println("\nsuma con reduce()= " + suma);


    AtomicInteger suma1 = new AtomicInteger(0);
    numeros.forEach(numero -> {
      // Operación utilizando la variable atómica
      suma1.addAndGet(numero);
    });

    System.out.println("\nSuma atomic: " + suma.get());




    //ForEach ()
    // El bucle ForEach () se puede usar en una clase de colección que extiende la interfaz Iterable. Como este método ingresa un solo parámetro,
    // una expresión lambda también se puede pasar como parámetro.

    System.out.println("\nforEach de edad: ");
    personas.forEach(p -> {
      System.out.println(p.getEdad());
    });




    //En Java 8, se ha introducido una nueva API de tiempo y API de fecha donde las fechas de manejo están en un método diferente en
    // comparación con otras versiones de Java. Algunas de las siguientes son clases de hora y fecha que están disponibles en Java.
    // paquete de tiempo:
    //Clase Jtime.LocalDate
    //Clase LocalTime
    //Clase LocalDateTime
    //Clase de MonthDay

    System.out.println("\nApi de Tiempo");
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


    //Java 12
    //clase final pública CompactNumberFormat
    //extiende NumberFormat
    //CompactNumberFormat es una subclase concreta de NumberFormat que formatea un número decimal en su forma compacta.
    // El formato de número compacto está diseñado para el entorno donde el espacio es limitado y la cadena formateada se puede mostrar en ese espacio limitado

    NumberFormat fmt = NumberFormat.getCompactNumberInstance(
        new Locale("hi", "IN"), NumberFormat.Style.SHORT);
    String result = fmt.format(1000);
    System.out.println("\nCompactNumberFormat = " + result );



    // nuevos metodos en la api de String
    // indent() ajusta la sangría de cada línea de la cadena en función del argumento que se le pasa.
    String multilineStr = "This is\na multiline\nstring.";
    String postIndent = multilineStr.indent(3);
    System.out.println("postIndent = " + postIndent);

    // transform()
    int result1 = "42".transform(Integer::parseInt);
    System.out.println("result convertido a entero= " + result1);


    //Java 14
    //Switch expressions:
    /*Proponemos introducir una nueva forma de etiqueta de cambio, " case L ->", para indicar que solo se ejecutará el código a la derecha de la etiqueta si la etiqueta coincide.
     También proponemos permitir múltiples constantes por caso, separadas por comas. El código anterior ahora se puede escribir:

    switch (day) {
      case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
      case TUESDAY                -> System.out.println(7);
      case THURSDAY, SATURDAY     -> System.out.println(8);
      case WEDNESDAY              -> System.out.println(9);
    }*/
    Ejemplo:
    howMany(1);

    //Java 15
    //Text blocks:
    //formatea automáticamente la cadena de una manera predecible y le da al desarrollador control sobre el formato cuando lo desee.
    //ejemplo SQL: Uso de literales de cadena "unidimensionales"

    String query = "SELECT \"EMP_ID\", \"LAST_NAME\" FROM \"EMPLOYEE_TB\"\n" +
        "WHERE \"CITY\" = 'INDIANAPOLIS'\n" +
        "ORDER BY \"EMP_ID\", \"LAST_NAME\";\n";

    //Usar un bloque de texto "bidimensional"
    String query2 = """
               SELECT "EMP_ID", "LAST_NAME" FROM "EMPLOYEE_TB"
               WHERE "CITY" = 'INDIANAPOLIS'
               ORDER BY "EMP_ID", "LAST_NAME";
               """;

    //Java 16
    /*var x = "";
    //
    if (x.instanceof String s) { String a = s; }*/


    //Java 17
    //Clases Selladas
    //Las clases e interfaces selladas restringen qué otras clases o interfaces pueden extenderlas o implementarlas.

    //Java 18
    //UTF-8 by Default
    //Con este cambio, las API que dependen del juego de caracteres predeterminado se comportarán de manera uniforme en todas las implementaciones,
    // sistemas operativos, entornos locales y configuraciones.



    //Java 18
    /*
    * Simple Web Server (a.k.a com.sun.net.httpserver.SimpleFileServer)
    * SimpleFileServer.createFileServer(new InetSocketAddress(9000), path, logLevel).start();
    * Cmd Line: jwebserver -p 9000*/


  }


  static void howMany(int k) {
    switch (k) {
      case 1  -> System.out.println("one");
      case 2  -> System.out.println("two");
      default -> System.out.println("many");
    }
  }

}


