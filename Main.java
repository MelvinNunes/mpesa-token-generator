import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

// Download: https://commons.apache.org/proper/commons-codec/download_codec.cgi
import org.apache.commons.codec.binary.Base64;

public class Main {

    public static void main(String[] args) {
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAmptSWqV7cGUUJJhUBxsMLonux24u+FoTlrb+4Kgc6092JIszmI1QUoMohaDDXSVueXx6IXwYGsjjWY32HGXj1iQhkALXfObJ4DqXn5h6E8y5/xQYNAyd5bpN5Z8r892B6toGzZQVB7qtebH4apDjmvTi5FGZVjVYxalyyQkj4uQbbRQjgCkubSi45Xl4CGtLqZztsKssWz3mcKncgTnq3DHGYYEYiKq0xIj100LGbnvNz20Sgqmw/cH+Bua4GJsWYLEqf/h/yiMgiBbxFxsnwZl0im5vXDlwKPw+QnO2fscDhxZFAwV06bgG0oEoWm9FnjMsfvwm0rUNYFlZ+TOtCEhmhtFp+Tsx9jPCuOd5h2emGdSKD8A6jtwhNa7oQ8RtLEEqwAn44orENa1ibOkxMiiiFpmmJkwgZPOG/zMCjXIrrhDWTDUOZaPx/lEQoInJoE2i43VN/HTGCCw8dKQAwg0jsEXau5ixD0GUothqvuX3B9taoeoFAIvUPEq35YulprMM7ThdKodSHvhnwKG82dCsodRwY428kg2xM/UjiTENog4B6zzZfPhMxFlOSFX4MnrqkAS+8Jamhy1GgoHkEMrsT5+/ofjCx0HjKbT5NuA2V/lmzgJLl3jIERadLzuTYnKGWxVJcGLkWXlEPYLbiaKzbJb2sYxt+Kt5OxQqC1MCAwEAAQ==";
        String apiKey = "hqngfm8bau3w91ejq17a5mldng8jkp6m";

        System.out.println(getBearerToken(apiKey, publicKey));
    }

    private static String getBearerToken(String apiKey, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Cipher cipher = Cipher.getInstance("RSA");
            byte[] encodedPublicKey = Base64.decodeBase64(publicKey);
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
            PublicKey pk = keyFactory.generatePublic(publicKeySpec);

            cipher.init(Cipher.ENCRYPT_MODE, pk);
            byte[] encryptedApiKey =  Base64.encodeBase64(cipher.doFinal(apiKey.getBytes("UTF-8")));

            return new String(encryptedApiKey, "UTF-8");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}