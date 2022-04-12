package SuperOfficeRepositoryServer.sequencing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class BasicDTO {

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class UpdateContentInputDto {
    String documentContent;
    String updateElementList;
    List<MultipartFile> file;
  }


  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class DTO<T> {
    T dto;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class EventServerDTO {
    private String eventType;
    private String senderId;
    private String message;
    private String roomId;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class SequenceMessageDTO {
    private String sessionId;
    private Integer userId;
    private Integer clientSeqNum;
    private Integer seqNum;
    private Integer refSeqNum;
    private List<OperationDTO> operationMessages;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "elementType", visible = true, defaultImpl = OperationDTO.class)
  @JsonSubTypes({
      @JsonSubTypes.Type(value = ParagraphOperationDTO.class, name = "OFFICE_PARAGRAPH")
  })
  public static class OperationDTO {
    private Integer elementId;
    private Integer parentId;
    private Integer prevId;
    private Integer nextId;
    private Integer childId;
    private String elementType;
    private String objectType;
    private String contentType;
    private String behavior;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class ParagraphOperationDTO extends OperationDTO {
    private String plainText;
    private String longPlainText;
    private Long commonParaStyleId;
    private Long directCharStyleId;
    private Long directParaStyleId;
    private String alignment;
    private Double leftIndent;
    private Double leftIndentChar;
    private Double rightIndent;
    private Double rightIndentChar;
    private Double hangingIndent;
    private Double hangingIndentChar;
    private Double firstLineIndent;
    private Double firstLineIndentChar;
    private Double spacingLine;
    private String paraShade;
    private Long numId;
    private Integer numLevel;
    private String font;
    private Double fontSize;
    private String highLightColor;
    private String charShade;
    private String bold;
    private String italic;
    private String underlineColor;
    private String underlineType;
    private String strike;
    private String fontColor;
    private Double superScript;
    private Double subScript;

    public ParagraphOperationDTO(Integer elementId, Integer parentId, Integer prevId, Integer nextId, Integer childId, String elementType, String objectType, String contentType,
                                 String behavior, String plainText, String longPlainText, Long commonParaStyleId, Long directParaStyleId, Long directCharStyleId,
                                 String alignment, Double leftIndent, Double leftIndentChar, Double rightIndent, Double rightIndentChar, Double hangingIndent,
                                 Double hangingIndentChar, Double firstLineIndent, Double firstLineIndentChar, Double spacingLine, String paraShade, Long numId,
                                 Integer numLevel, String font, Double fontSize, String highLightColor, String charShade, String bold, String italic, String underlineColor,
                                 String underlineType, String strike, String fontColor, Double superScript, Double subScript) {
      super(elementId, parentId, prevId, nextId, childId, elementType, objectType, contentType, behavior);
      this.plainText = plainText;
      this.longPlainText = longPlainText;
      this.commonParaStyleId = commonParaStyleId;
      this.directParaStyleId = directParaStyleId;
      this.directCharStyleId = directCharStyleId;
      this.alignment = alignment;
      this.leftIndent = leftIndent;
      this.leftIndentChar = leftIndentChar;
      this.rightIndent = rightIndent;
      this.rightIndentChar = rightIndentChar;
      this.hangingIndent = hangingIndent;
      this.hangingIndentChar = hangingIndentChar;
      this.firstLineIndent = firstLineIndent;
      this.firstLineIndentChar = firstLineIndentChar;
      this.spacingLine = spacingLine;
      this.paraShade = paraShade;
      this.numId = numId;
      this.numLevel = numLevel;
      this.font = font;
      this.fontSize = fontSize;
      this.highLightColor = highLightColor;
      this.charShade = charShade;
      this.bold = bold;
      this.italic = italic;
      this.underlineColor = underlineColor;
      this.underlineType = underlineType;
      this.strike = strike;
      this.fontColor = fontColor;
      this.superScript = superScript;
      this.subScript = subScript;
    }
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class MasterSlideOperationDTO extends OperationDTO {
    private Long firstLayoutSlideId;
    private String themeId;
    private String preserve;
    private String headerFooter;
    private String sldNum;
    private String hdr;
    private String ftr;
    private String dt;


    public MasterSlideOperationDTO(Integer elementId, Integer parentId, Integer prevId, Integer nextId, Integer childId, String elementType, String objectType,
                                   String contentType, String behavior, Long firstLayoutSlideId, String themeId, String preserve, String headerFooter,
                                   String sldNum, String hdr, String ftr, String dt) {
      super(elementId, parentId, prevId, nextId, childId, elementType, objectType, contentType, behavior);
      this.firstLayoutSlideId = firstLayoutSlideId;
      this.themeId = themeId;
      this.preserve = preserve;
      this.headerFooter = headerFooter;
      this.sldNum = sldNum;
      this.hdr = hdr;
      this.ftr = ftr;
      this.dt = dt;
    }
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class LayoutSlideOperationDTO extends OperationDTO {
    private Long masterSlideId;
    private String showMasterSp;
    private String showMasterPhAnim;
    private String show;
    private String matchingName;
    private String type;
    private String preserve;
    private String userDrawn;
    private String headerFooter;
    private String sldNum;
    private String hdr;
    private String ftr;
    private String dt;

    public LayoutSlideOperationDTO(Integer elementId, Integer parentId,
                                   Integer prevId, Integer nextId, Integer childId, String elementType, String objectType, String contentType, String behavior,
                                   Long masterSlideId, String showMasterSp, String showMasterPhAnim, String show, String matchingName, String type, String preserve,
                                   String userDrawn, String headerFooter, String sldNum, String hdr, String ftr, String dt) {
      super(elementId, parentId, prevId, nextId, childId, elementType, objectType, contentType, behavior);
      this.masterSlideId = masterSlideId;
      this.showMasterSp = showMasterSp;
      this.showMasterPhAnim = showMasterPhAnim;
      this.show = show;
      this.matchingName = matchingName;
      this.type = type;
      this.preserve = preserve;
      this.userDrawn = userDrawn;
      this.headerFooter = headerFooter;
      this.sldNum = sldNum;
      this.hdr = hdr;
      this.ftr = ftr;
      this.dt = dt;
    }
  }
//
//  @Getter
//  @Setter
//  @JsonInclude(Include.NON_NULL)
//  public static class NormalSlideOperationDTO extends DTO {
}
