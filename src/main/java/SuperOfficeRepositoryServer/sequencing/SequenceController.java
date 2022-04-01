package SuperOfficeRepositoryServer.sequencing;

import SuperOfficeRepositoryServer.sequencing.BasicDTO.EventServerDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import SuperOfficeRepositoryServer.sequencing.BasicDTO.DTO;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SequenceController {

  private final Map<Integer, DocumentPartition> documentPartitionMap = new HashMap<>();

  @PostMapping("/")
  public void getSequenceNumber(@RequestBody DTO dto) throws IOException {
    Integer documentId = dto.getDocumentId();
    if (documentPartitionMap.get(documentId) == null) {
      documentPartitionMap.put(documentId, new DocumentPartition());
    }

    DocumentPartition documentPartition = documentPartitionMap.get(documentId);
    DTO ticketedMessage = documentPartition.ticket(dto);

    post(ticketedMessage);
  }

  /**
   * Client broadcast를 위해 Event server로 POST
   */
  public void post(DTO ticketedMessage) throws JsonProcessingException {
    WebClient webClient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    ObjectMapper mapper = new ObjectMapper();
    Gson gson = new Gson();
    String tmpMessage = gson.toJson(ticketedMessage);
    EventServerDTO parsedMessage = new EventServerDTO("websocket_push", ticketedMessage.getSessionId(), tmpMessage, ticketedMessage.getDocumentId().toString());
    String message = mapper.writeValueAsString(parsedMessage);
    String url = "http://192.168.152.45:8080/apis/v1/events/messages";

    webClient.post().uri(url).accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(message)).retrieve().bodyToMono(String.class).block();
  }
}

