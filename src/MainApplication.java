import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainApplication {

  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    System.out.print("Ingrese un usuario: ");
    String username = consola.nextLine();
    try {
      int totalCommits = 0;

      // Obtener todos los repositorios del usuario
      String reposUrl = "https://api.github.com/users/" + username + "/repos";
      String reposResponse = sendGetRequest(reposUrl);

      // Parsear manualmente los repositorios (Esto es un ejemplo básico y limitado)
      String[] repoNames = extractRepoNames(reposResponse);

      // Iterar sobre los repositorios y contar los commits
      for (String repoName : repoNames) {
        int repoCommits = countCommits(username, repoName);
        totalCommits += repoCommits;
      }

      System.out.println("Total de commits realizados por " + username + ": " + totalCommits);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  private static String[] extractRepoNames(String jsonResponse) {
    String[] repos = jsonResponse.split("\"full_name\":\"");
    String[] repoNames = new String[repos.length - 1];
    for (int i = 1; i < repos.length; i++) {
      repoNames[i - 1] = repos[i].split("\",")[0].split("/")[1];
    }
    return repoNames;
  }

  private static String sendGetRequest(String url) throws IOException {
    URL obj = new URL(url);
    HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
    connection.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuilder response = new StringBuilder();
    String inputLine = in.readLine();

    while (inputLine != null) {
      response.append(inputLine);
      inputLine = in.readLine();
    }
    in.close();

    return response.toString();
  }


  private static int countCommits(String username, String repoName) throws Exception {
    int totalCommits = 0;
    int page = 1;
    int commitsPerPage = 100;

    while (true) {
      String commitsUrl =
          "https://api.github.com/repos/" + username + "/" + repoName + "/commits?per_page="
              + commitsPerPage + "&page=" + page;
      String commitsResponse = sendGetRequest(commitsUrl);

      if (commitsResponse.equals("[]")) {
        break;
      }

      String[] commits = commitsResponse.split("\"sha\":");
      totalCommits += (commits.length - 1);

      page++;
    }

    System.out.println("Repositorio: " + repoName + " - Commits: " + totalCommits);
    return totalCommits;
  }
}
