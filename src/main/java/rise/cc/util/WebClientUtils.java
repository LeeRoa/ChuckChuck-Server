
package rise.cc.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import rise.cc.config.WebClientConfig;

@Component
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class WebClientUtils {
    private final WebClientConfig webClientConfig;

    public String get(String uri) {
        return webClientConfig.webClient().method(HttpMethod.GET)
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String post(String url, String jsonStr) {
        return webClientConfig.webClient().method(HttpMethod.POST)
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonStr)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String patch(String url, String jsonStr) {
        return webClientConfig.webClient().method(HttpMethod.PATCH)
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonStr)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String delete(String url, String jsonStr) {
        return webClientConfig.webClient().method(HttpMethod.DELETE)
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonStr)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
