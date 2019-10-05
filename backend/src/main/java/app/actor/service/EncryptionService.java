package app.actor.service;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class EncryptionService {

  public static String encrypt(String message) {
    return Hashing.sha256()
                  .hashString(message, StandardCharsets.UTF_8)
                  .toString();
  }
}
