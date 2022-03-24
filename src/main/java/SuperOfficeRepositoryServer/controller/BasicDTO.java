package SuperOfficeRepositoryServer.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicDTO {

  @Getter
  @Setter
  public static class DTO {
    String sessionId;
    Integer clientSequenceNumber;
    Integer sequenceNumber;
    Integer documentId;
    Integer elementId;
    Integer parentId;
    Integer prevId;
    Integer nextId;
    Integer childId;
    String elementType;
    String objectType;
    String contentType;
    String behavior;
  }
}
