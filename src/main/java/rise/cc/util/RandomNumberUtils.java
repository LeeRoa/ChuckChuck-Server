package rise.cc.util;

import java.security.SecureRandom;

public class RandomNumberUtils {

    public static String getValidNo(int length) {
        StringBuilder result = new StringBuilder();
        SecureRandom sr = new SecureRandom();
        for (int i = 0; i < length; i++) {
            result.append(sr.nextInt(10) + 1);
        }

        return result.toString();
    }
}
