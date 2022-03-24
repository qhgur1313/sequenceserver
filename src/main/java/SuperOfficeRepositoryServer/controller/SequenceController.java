package SuperOfficeRepositoryServer.controller;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import SuperOfficeRepositoryServer.controller.BasicDTO.DTO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RestController
public class SequenceController {

  private final Map<Integer, DocumentPartition> documentPartitionMap = new HashMap<>();

  @PostMapping("/")
  public String getSequenceNumber(@RequestBody DTO dto) throws IOException {
    Integer documentId = dto.getDocumentId();
    if (documentPartitionMap.get(documentId) == null) {
      documentPartitionMap.put(documentId, new DocumentPartition());
    }

    DocumentPartition documentPartition = documentPartitionMap.get(documentId);
    DTO ticketedMessage = documentPartition.ticket(dto);

    String broadcastMessage = parseToBroadcast(ticketedMessage);

    Executor executor = Executors.newFixedThreadPool(1);
    CompletableFuture.runAsync(() -> {
      try {
        postToBroadcast(broadcastMessage);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }, executor);

    return broadcastMessage;
  }

  /** Client broadcast를 위해 Event server로 POST */
  private void postToBroadcast(String message) throws IOException {
    URL url = new URL("http://192.168.152.45:8080/apis/v1/events/messages");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("POST");
    con.setRequestProperty("Content-Type", "application/json");
    con.setDoOutput(true);

    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.write(message.getBytes(StandardCharsets.UTF_8));
    wr.flush();
    wr.close();
    int responseCode = con.getResponseCode();
    System.out.println(responseCode);
  }

  private void postToUpdate(String message) throws IOException {

  }

  private String parseToBroadcast(DTO ticketedMessage) {
    Gson gson = new Gson();
    String message = gson.toJson(ticketedMessage);

    JSONObject parameter = new JSONObject();
    parameter.put("eventType", "websocket_push");
    parameter.put("senderId", ticketedMessage.getSessionId());
    parameter.put("message", message);
    parameter.put("roomId", ticketedMessage.getDocumentId());
    return parameter.toString();
  }
}

