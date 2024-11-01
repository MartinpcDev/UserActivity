import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainApplication {

  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    System.out.print("Ingrese el nombre: ");
    String username = consola.nextLine();
    String apiUrl = "https://api.github.com/users/" + username + "/events";

    try {
      // Realiza la solicitud a la API de GitHub
      URL url = new URL(apiUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");

      int responseCode = conn.getResponseCode();

      if (responseCode == 404) {
        System.out.println("Usuario no encontrado.");
        return;
      } else if (responseCode != 200) {
        System.out.println(
            "Error al obtener los datos de GitHub. Código de respuesta: " + responseCode);
        return;
      }

      // Leer y analizar la respuesta JSON
      Scanner scanner = new Scanner(url.openStream());
      StringBuilder jsonResponse = new StringBuilder();
      while (scanner.hasNext()) {
        jsonResponse.append(scanner.nextLine());
      }
      scanner.close();

      // Procesa el JSON y muestra la actividad
      displayUserActivity(jsonResponse.toString());

    } catch (IOException e) {
      System.out.println("Ocurrió un error al conectar con la API de GitHub: " + e.getMessage());
    }
  }

  private static void displayUserActivity(String jsonResponse) {
    // Este es un análisis simple; ajusta según las necesidades de tu JSON
    String[] events = jsonResponse.split("},\\{");

    for (String event : events) {
      if (event.contains("\"type\":\"PushEvent\"")) {
        System.out.println("Pushed commits a un repositorio");
      } else if (event.contains("\"type\":\"IssuesEvent\"")) {
        System.out.println("Abrió un nuevo issue en un repositorio");
      } else if (event.contains("\"type\":\"WatchEvent\"")) {
        System.out.println("Starred un repositorio");
      }
      // Puedes agregar más condiciones para otros eventos
    }

  }
}
