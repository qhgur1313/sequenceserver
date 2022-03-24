package SuperOfficeRepositoryServer.controller;

import SuperOfficeRepositoryServer.controller.BasicDTO.DTO;

import java.util.HashMap;
import java.util.Map;

public class DocumentPartition {
  private final Map<String, Integer> clientSequenceMap = new HashMap<>();
  private Integer sequenceNumber = 1;

  public DocumentPartition() {
  }

  public DTO ticket(DTO dto) {
    if(clientSequenceMap.get(dto.getSessionId()) == null) {
      clientSequenceMap.put(dto.getSessionId(), dto.getClientSequenceNumber());
    } else {
      Integer expectedClientSequenceNumber = clientSequenceMap.get(dto.getSessionId()) + 1;
      if(expectedClientSequenceNumber != dto.getClientSequenceNumber()) {
        System.out.println("Client Command leak occured");
      }
      clientSequenceMap.put(dto.getSessionId(), dto.getClientSequenceNumber());
    }

    dto.setSequenceNumber(sequenceNumber);
    sequenceNumber += 1;

    return dto;
  }
}
