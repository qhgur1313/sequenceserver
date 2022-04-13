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
    private String operationMessages;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "elementType", visible = true, defaultImpl = OperationMessage.class)
  @JsonSubTypes({
      @JsonSubTypes.Type(value = ParagraphOperationMessage.class, name = "OFFICE_PARAGRAPH"),
      @JsonSubTypes.Type(value = ParagraphRunOperationMessage.class, name = "OFFICE_PARAGRAPH_RUN"),
      @JsonSubTypes.Type(value = AnimationSequenceOperationMessage.class, name = "POINT_SEQUENCE"),
      @JsonSubTypes.Type(value = AnimationEditableOperationMessage.class, name = "POINT_EDITABLE"),
      @JsonSubTypes.Type(value = NoteMasterOperationMessage.class, name = "POINT_NOTEMASTER"),
      @JsonSubTypes.Type(value = SlideNoteOperationMessage.class, name = "POINT_NOTE"),
      @JsonSubTypes.Type(value = CustomShowOperationMessage.class, name = "POINT_CUSTOMSHOW"),
      @JsonSubTypes.Type(value = SlideShowOperationMessage.class, name = "POINT_SLIDESHOW"),
      @JsonSubTypes.Type(value = NormalSlideOperationMessage.class, name = "POINT_SLIDE"),
      @JsonSubTypes.Type(value = LayoutSlideOperationMessage.class, name = "POINT_SLIDELAYOUT"),
      @JsonSubTypes.Type(value = MasterSlideOperationMessage.class, name = "POINT_SLIDEMASTER"),
      @JsonSubTypes.Type(value = MultimediaContentOperationMessage.class, name = "OFFICE_MULTIMEDIA"),
      @JsonSubTypes.Type(value = PictureOperationMessage.class, name = "OFFICE_PICTURE"),
      @JsonSubTypes.Type(value = GroupShapeOperationMessage.class, name = "OFFICE_GROUP_SHAPE"),
  })
  public static class OperationMessage {
    private Long elementId;
    private Long parentId;
    private Long prevId;
    private Long nextId;
    private Long childId;
    private String elementType;
    private String objectType;
    private String contentType;
    private String behavior;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class GroupShapeOperationMessage extends OperationMessage {
    // coordinateInfo
    String positionUnitH;
    String positionUnitV;
    String positionRelFromH;
    String positionRelFromV;
    String positionRelFromHType;
    String positionRelFromVType;
    String sizeRefFromH;
    String sizeRefFromV;
    Integer x;
    Integer y;
    Integer w;
    Integer h;
    Integer relX;
    Integer relY;
    Integer relW;
    Integer relH;
    Integer groupX;
    Integer groupY;
    Integer groupW;
    Integer groupH;
    String flipH;
    String flipV;
    Integer rotation;
    Integer skewX;
    Integer skewY;
    Integer distT;
    Integer distR;
    Integer distB;
    Integer distL;
    Integer effectExtentT;
    Integer effectExtentR;
    Integer effectExtentB;
    Integer effectExtentL;
    String wrapType;
    String inline;
    String resizeLock;
    String overlap;
    String renderingMatrix;
    // fillInfo
    String noFill;
    String solidFill;
    String solidFillColor;
    String patternFill;
    String patternFillPrst;
    String patternFillForegroundColor;
    String patternFillBackgroundColor;
    String gradientFill;
    String gradientFillRotWithShape;
    String gradientFillStopList;
    String gradientFillPathType;
    String gradientFillPathRectL;
    String gradientFillPathRectT;
    String gradientFillPathRectR;
    String gradientFillPathRectB;
    Integer gradientFillLinearAngle;
    String gradientFillLinearScaled;
    String gradientFillTileRectL;
    String gradientFillTileRectT;
    String gradientFillTileRectR;
    String gradientFillTileRectB;
    // blipFillInfo
    String blipFill;
    Integer blipFillAlphaBiLevel;
    String blipFillAlphaCeiling;
    String blipFillAlphaFloor;
    String blipFillAlphaInv;
    String blipFillAlphaMod;
    String blipFillAlphaModFix;
    String blipFillAlpahaRepl;
    String blipFillBiLevel;
    Integer blipFillBlurRad;
    String blipFillBlurGrow;
    String blipFillClrChange;
    String blipFillClrRepl;
    String blipFillDuoTone;
    String blipFillOverlay;
    String blipFillGrayScl;
    Integer blipFillHue;
    Integer blipFillSaturation;
    Integer blipFillLightness;
    Integer blipFillLumContrast;
    Integer blipFillLumBright;
    Integer blipFillTintHue;
    Integer blipFillTintAmt;
    String blipFillBlipExtLst;
    String blipFillCState;
    Integer blipFillSrcRectL;
    Integer blipFillSrcRectT;
    Integer blipFillSrcRectR;
    Integer blipFillSrcRectB;
    Integer blipFillTileTx;
    Integer blipFillTileTy;
    Integer blipFillTileSx;
    Integer blipFillTileSy;
    String blipFillTileFlip;
    String blipFillTileAlgn;
    Integer blipFillStretchL;
    Integer blipFillStretchT;
    Integer blipFillStretchR;
    Integer blipFillStretchB;
    Integer blipFillDpi;
    String blipFillRotWithShape;
    String groupFill;
    // effectInfo
    String glow;
    Integer glowRad;
    String glowColor;
    String outerShadow;
    Integer outerShadowBlurRad;
    Integer outerShadowDist;
    Integer outerShadowDir;
    String outerShadowScaleX;
    String outerShadowScaleY;
    Integer outerShadowSkewX;
    Integer outerShadowSkewY;
    String outerShadowAlign;
    String outerShadowRotWithShape;
    String outerShadowColor;
    String innerShadow;
    Integer innerShadowBlurRad;
    Integer innerShadowDist;
    Integer innerShadowDir;
    String innerShadowColor;
    String reflection;
    Integer reflectionBlurRad;
    String reflectionStartAlpha;
    String reflectionstartPosition;
    String reflectionEndAlpha;
    String reflectionEndPosition;
    Integer reflectionDist;
    Integer reflectionDir;
    Integer reflectionFadeDir;
    String reflectionScaleX;
    String reflectionScaleY;
    Integer reflectionSkewX;
    Integer reflectionSkewY;
    String reflectionAlign;
    String reflectionRotWithShape;
    String softedge;
    Integer softedgeRad;
    // etc
    String nonVisualProperty;
  }


  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class PictureOperationMessage extends OperationMessage {
    String commonShapeStyleId;
    // coordinateInfo
    String positionUnitH;
    String positionUnitV;
    String positionRelFromH;
    String positionRelFromV;
    String positionRelFromHType;
    String positionRelFromVType;
    String sizeRefFromH;
    String sizeRefFromV;
    Integer x;
    Integer y;
    Integer w;
    Integer h;
    Integer relX;
    Integer relY;
    Integer relW;
    Integer relH;
    Integer groupX;
    Integer groupY;
    Integer groupW;
    Integer groupH;
    String flipH;
    String flipV;
    Integer rotation;
    Integer skewX;
    Integer skewY;
    Integer distT;
    Integer distR;
    Integer distB;
    Integer distL;
    Integer effectExtentT;
    Integer effectExtentR;
    Integer effectExtentB;
    Integer effectExtentL;
    String wrapType;
    String inline;
    String resizeLock;
    String overlap;
    String renderingMatrix;
    // pathInfo
    String shapeType;
    String path;
    // fillInfo
    String noFill;
    String solidFill;
    String solidFillColor;
    String patternFill;
    String patternFillPrst;
    String patternFillForegroundColor;
    String patternFillBackgroundColor;
    String gradientFill;
    String gradientFillRotWithShape;
    String gradientFillStopList;
    String gradientFillPathType;
    String gradientFillPathRectL;
    String gradientFillPathRectT;
    String gradientFillPathRectR;
    String gradientFillPathRectB;
    Integer gradientFillLinearAngle;
    String gradientFillLinearScaled;
    String gradientFillTileRectL;
    String gradientFillTileRectT;
    String gradientFillTileRectR;
    String gradientFillTileRectB;
    // blipFillInfo
    String blipFill;
    Integer blipFillAlphaBiLevel;
    String blipFillAlphaCeiling;
    String blipFillAlphaFloor;
    String blipFillAlphaInv;
    String blipFillAlphaMod;
    String blipFillAlphaModFix;
    String blipFillAlpahaRepl;
    String blipFillBiLevel;
    Integer blipFillBlurRad;
    String blipFillBlurGrow;
    String blipFillClrChange;
    String blipFillClrRepl;
    String blipFillDuoTone;
    String blipFillOverlay;
    String blipFillGrayScl;
    Integer blipFillHue;
    Integer blipFillSaturation;
    Integer blipFillLightness;
    Integer blipFillLumContrast;
    Integer blipFillLumBright;
    Integer blipFillTintHue;
    Integer blipFillTintAmt;
    String blipFillBlipExtLst;
    String blipFillCState;
    Integer blipFillSrcRectL;
    Integer blipFillSrcRectT;
    Integer blipFillSrcRectR;
    Integer blipFillSrcRectB;
    Integer blipFillTileTx;
    Integer blipFillTileTy;
    Integer blipFillTileSx;
    Integer blipFillTileSy;
    String blipFillTileFlip;
    String blipFillTileAlgn;
    Integer blipFillStretchL;
    Integer blipFillStretchT;
    Integer blipFillStretchR;
    Integer blipFillStretchB;
    Integer blipFillDpi;
    String blipFillRotWithShape;
    String groupFill;
    // strokeInfo
    Integer strokeWidth;
    String strokeCap;
    String strokeCompoundLine;
    String strokeAlign;
    String strokePrstDash;
    String strokeRound;
    String strokeBevel;
    String strokeMiter;
    String strokeMiterLimit;
    String strokeHeadEndType;
    String strokeHeadEndWidth;
    String strokeHeadEndLength;
    String strokeTailEndType;
    String strokeTailEndWidth;
    String strokeTailEndLength;
    String strokeNoFill;
    String strokeSolidFill;
    String strokeSolidFillColor;
    String strokeGradientFill;
    String strokeGradientFillRotWithShape;
    String strokeGradientFillStopList;
    String strokeGradientFillPathType;
    String strokeGradientFillPathRectL;
    String strokeGradientFillPathRectT;
    String strokeGradientFillPathRectR;
    String strokeGradientFillPathRectB;
    String strokeGradientFillLinearAngle;
    String strokeGradientFillLinearScaled;
    String strokeGradientFillTileRectL;
    String strokeGradientFillTileRectT;
    Integer strokeGradientFillTileRectR;
    String strokeGradientFillTileRectB;
    String strokePatternFill;
    String strokePatternFillPrst;
    String strokePatternFillForegroundColor;
    String strokePatternFillBackgroundColor;
    // effectInfo
    String glow;
    Integer glowRad;
    String glowColor;
    String outerShadow;
    Integer outerShadowBlurRad;
    Integer outerShadowDist;
    Integer outerShadowDir;
    String outerShadowScaleX;
    String outerShadowScaleY;
    Integer outerShadowSkewX;
    Integer outerShadowSkewY;
    String outerShadowAlign;
    String outerShadowRotWithShape;
    String outerShadowColor;
    String innerShadow;
    Integer innerShadowBlurRad;
    Integer innerShadowDist;
    Integer innerShadowDir;
    String innerShadowColor;
    String reflection;
    Integer reflectionBlurRad;
    String reflectionStartAlpha;
    String reflectionstartPosition;
    String reflectionEndAlpha;
    String reflectionEndPosition;
    Integer reflectionDist;
    Integer reflectionDir;
    Integer reflectionFadeDir;
    String reflectionScaleX;
    String reflectionScaleY;
    Integer reflectionSkewX;
    Integer reflectionSkewY;
    String reflectionAlign;
    String reflectionRotWithShape;
    String softedge;
    Integer softedgeRad;
    // imageFillInfo
    Integer imageFillAlphaBiLevel;
    String imageFillAlphaCeiling;
    String imageFillAlphaFloor;
    String imageFillAlphaInv;
    String imageFillAlphaMod;
    String imageFillAlphaModFix;
    String imageFillAlpahaRepl;
    String imageFillBiLevel;
    Integer imageFillBlurRad;
    String imageFillBlurGrow;
    String imageFillClrChange;
    String imageFillClrRepl;
    String imageFillDuoTone;
    String imageFillOverlay;
    String imageFillGrayScl;
    Integer imageFillHue;
    Integer imageFillSaturation;
    Integer imageFillLightness;
    Integer imageFillLumContrast;
    Integer imageFillLumBright;
    Integer imageFillTintHue;
    Integer imageFillTintAmt;
    String imageFillBlipExtLst;
    String imageFillCState;
    Integer imageFillSrcRectL;
    Integer imageFillSrcRectT;
    Integer imageFillSrcRectR;
    Integer imageFillSrcRectB;
    Integer imageFillTileTx;
    Integer imageFillTileTy;
    Integer imageFillTileSx;
    Integer imageFillTileSy;
    String imageFillTileFlip;
    String imageFillTileAlgn;
    Integer imageFillStretchL;
    Integer imageFillStretchT;
    Integer imageFillStretchR;
    Integer imageFillStretchB;
    Integer imageFillDpi;
    String imageFillRotWithShape;
    String imageFillChoice;
    Integer imageFillTransparency;
    Integer imageFillBright;
    Integer imageFillContrast;
    Integer imageFillThresh;
    String imageFillIsIncludeFile;
    String imageFillIsInverse;
    String imageFillImageType;
    String imageFillImageEffect;
    Integer imageFillImageMarginT;
    Integer imageFillImageMarginR;
    Integer imageFillImageMarginB;
    Integer imageFillImageMarginL;
    String multimediaUniqueID;
    // placeHolder
    String phType;
    String phSz;
    Integer phIdx;
    String phHasCustomPropt;
    // etc
    String nonVisualProperty;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class MultimediaContentOperationMessage extends OperationMessage {
    String positionUnitH;
    String positionUnitV;
    String positionRelFromH;
    String positionRelFromV;
    String positionRelFromHType;
    String positionRelFromVType;
    String sizeRefFromH;
    String sizeRefFromV;
    Integer x;
    Integer y;
    Integer w;
    Integer h;
    Integer relX;
    Integer relY;
    Integer relW;
    Integer relH;
    Integer groupX;
    Integer groupY;
    Integer groupW;
    Integer groupH;
    String flipH;
    String flipV;
    Integer rotation;
    Integer skewX;
    Integer skewY;
    Integer distT;
    Integer distR;
    Integer distB;
    Integer distL;
    Integer effectExtentT;
    Integer effectExtentR;
    Integer effectExtentB;
    Integer effectExtentL;
    String wrapType;
    String inline;
    String resizeLock;
    String overlap;
    String renderingMatrix;
    String multimediaUniqueID;
    String videoIsOnline;
    String videoType;
    String nonVisualProperty;
    Long zindex;
    String extension;
    String multimediaType;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class ParagraphRunOperationMessage extends OperationMessage {
    Long commonCharStyleId;
    Long directCharStyleId;
    String font;
    Double fontSize;
    String highLightColor;
    Integer textShadeType;
    String textShadeVal;
    String textShadeColor;
    String textShadeFill;
    String bold;
    String italic;
    String underlineColor;
    String underlineType;
    String strike;
    String fontColor;
    Double superScript;
    Double subScript;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class LayoutSlideOperationMessage extends OperationMessage {
    Long masterSlideId;
    Long slideAnimationsId;
    String showMasterSp;
    String showMasterPhAnim;
    String show;
    String matchingName;
    String type;
    String preserve;
    String userDrawn;
    String transition;
    String transitionType;
    String direction;
    String transitionInOutType;
    String transitionEightType;
    String transitionSideType;
    String transitionCornerType;
    Integer spokes;
    String thruBlk;
    String isContent;
    String isInverted;
    String prst;
    String pattern;
    String spd;
    String advClick;
    Integer advTm;
    Integer dur;
    String headerFooter;
    String sldNum;
    String hdr;
    String ftr;
    String dt;
    String name;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class NormalSlideOperationMessage extends OperationMessage {
    Long layoutSlideId;
    Long noteId;
    Long firstCommentId;
    Long slideAnimationsId;
    String showMasterSp;
    String showMasterPhAnim;
    String show;
    String transition;
    String transitionType;
    String direction;
    String transitionInOutType;
    String transitionEightType;
    String transitionSideType;
    String transitionCornerType;
    Integer spokes;
    String thruBlk;
    String isContent;
    String isInverted;
    String prst;
    String pattern;
    String spd;
    String advClick;
    Integer advTm;
    Integer dur;
    String name;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class SlideShowOperationMessage extends OperationMessage {
    String showType;
    String showScrollbar;
    Integer restart;
    String slideListChoice;
    Integer sldRgStart;
    Integer sldRgEnd;
    Integer custShowId;
    String loop;
    String showNarration;
    String showAnimation;
    String useTimings;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class CustomShowOperationMessage extends OperationMessage {
    String showTitle;
    String slideId;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class SlideNoteOperationMessage extends OperationMessage {
    Long noteMasterId;
    Long slideId;
    String showMasterSp;
    String showMasterPhAnim;
    String name;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class NoteMasterOperationMessage extends OperationMessage {
    String themeId;
    String headerFooter;
    String sldNum;
    String hdr;
    String ftr;
    String dt;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class AnimationEditableOperationMessage extends OperationMessage {
    String duration;
    String toValue;
    String fromValue;
    String byValue;
    String color;
    String scale;
    Integer groupId;
    String presetType;
    String nodeType;
    String startConditionList;
    String endConditionList;
    String iterate;
    Integer accel;
    Integer decel;
    String autoRev;
    String fill;
    String target;
    String repeatCount;
    String restart;
    Integer speed;
    String syncBehavior;
    String endSync;
    String motionBehavior;
    String commandType;
    String nodePh;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class AnimationSequenceOperationMessage extends OperationMessage {
    String nodeType;
    String concurrent;
    String nextAction;
    String prevAction;
    String nextCondList;
    String prevCondList;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class ParagraphOperationMessage extends OperationMessage {
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
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_NULL)
  public static class MasterSlideOperationMessage extends OperationMessage {
    Long firstLayoutSlideId;
    Long slideAnimationsId;
    String themeId;
    String preserve;
    String transition;
    String transitionType;
    String direction;
    String transitionInOutType;
    String transitionEightType;
    String transitionSideType;
    String transitionCornerType;
    Integer spokes;
    String thruBlk;
    String isContent;
    String isInverted;
    String prst;
    String pattern;
    String spd;
    String advClick;
    Integer advTm;
    Integer dur;
    String headerFooter;
    String sldNum;
    String hdr;
    String ftr;
    String dt;
    String name;
  }

}
