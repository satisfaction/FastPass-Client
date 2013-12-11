import com.getsatisfaction.fastpass.FastPass;

public class FastPassTestClient {
  public static void main(String[] args) {
    try {
      String url;

      System.out.println("True");
      url = FastPass.url("key", "secret", "foo@bar.com", "name", "uid", true);
      System.out.println(url);

      System.out.println("False");
      url = FastPass.url("key", "secret", "foo@bar.com", "name", "uid", false);
      System.out.println(url);
    }
    catch (java.net.URISyntaxException e) {
    }
    catch (java.io.IOException e) {
    }
    catch (net.oauth.OAuthException e) {
    }
  }
}

