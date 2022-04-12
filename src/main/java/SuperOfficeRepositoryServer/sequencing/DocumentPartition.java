package SuperOfficeRepositoryServer.sequencing;

import SuperOfficeRepositoryServer.sequencing.BasicDTO.SequenceMessageDTO;

import java.util.HashMap;
import java.util.Map;

public class DocumentPartition {
  private final Map<String, Integer> clientSequenceMap = new HashMap<>();
  private Integer sequenceNumber = 1;

  public DocumentPartition() {
  }

  public SequenceMessageDTO ticket(SequenceMessageDTO operationDto) {
    if(clientSequenceMap.get(operationDto.getSessionId()) == null) {
      clientSequenceMap.put(operationDto.getSessionId(), operationDto.getClientSeqNum());
    } else {
      Integer expectedClientSequenceNumber = clientSequenceMap.get(operationDto.getSessionId()) + 1;
      if(!expectedClientSequenceNumber.equals(operationDto.getClientSeqNum())) {
        System.out.println("Client Command leak occured");
      }
      clientSequenceMap.put(operationDto.getSessionId(), operationDto.getClientSeqNum());
    }

    operationDto.setSeqNum(sequenceNumber);
    sequenceNumber += 1;

    return operationDto;
  }
}