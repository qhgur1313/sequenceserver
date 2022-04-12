package SuperOfficeRepositoryServer.sequencing;

import SuperOfficeRepositoryServer.sequencing.BasicDTO.DTO;
import SuperOfficeRepositoryServer.sequencing.BasicDTO.EventServerDTO;
import SuperOfficeRepositoryServer.sequencing.BasicDTO.SequenceMessageDTO;
import SuperOfficeRepositoryServer.util.RequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SequenceController {

  private final Map<Integer, DocumentPartition> documentPartitionMap = new HashMap<>();

  @PutMapping("/")
  public void getSequenceNumber(@RequestBody DTO<SequenceMessageDTO> sequenceMessageDTO) throws JsonProcessingException {
    Integer documentId = Integer.parseInt(RequestUtil.getDocumentId());
    if (documentPartitionMap.get(documentId) == null) {
      documentPartitionMap.put(documentId, new DocumentPartition());
    }

    DocumentPartition documentPartition = documentPartitionMap.get(documentId);

    SequenceMessageDTO ticketedMessage = documentPartition.ticket(sequenceMessageDTO.getDto());
    post(ticketedMessage);
  }

  /**
   * Client broadcast를 위해 Event server로 POST
   */
  public void post(SequenceMessageDTO ticketedMessage) throws JsonProcessingException {
    int documentId = Integer.parseInt(RequestUtil.getDocumentId());
    WebClient webClient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    ObjectMapper mapper = new ObjectMapper();
    Gson gson = new Gson();
    String tmpMessage = gson.toJson(ticketedMessage);
    EventServerDTO parsedMessage = new EventServerDTO("websocket_push", ticketedMessage.getSessionId(), tmpMessage, Integer.toString(documentId));
    String message = mapper.writeValueAsString(parsedMessage);
    // 사내망 event server url
    //     String url = "http://192.168.152.45:8080/apis/v1/events/messages";
    String url = "http://eventserver.220.90.208.7.nip.io/apis/v1/events/messages";

    webClient.post().uri(url).accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(message)).retrieve().bodyToMono(String.class).block();
  }
}

