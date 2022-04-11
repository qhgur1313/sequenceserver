package SuperOfficeRepositoryServer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class RequestUtil {
  /**
   * 현재 RequestThread 내에서 HttpServletRequest 에 접근합니다.
   * 주의 사항으로 새로운 쓰레드를 생성할 때에는 사용이 불가합니다.(RequestThread Context 에 기반한 방식이기 떄문)
   */
  public static HttpServletRequest getRequest() {
    ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    return sra.getRequest();
  }

  /**
   * 현재 RequestThread 내에서 HttpServletRequest 에 접근한 뒤, RequestHeader 에 기록된 DocumentId 를 조회합니다.
   */
  public static String getDocumentId() {
    return getRequest().getHeader("office-documentid");
  }

  /**
   * 현재 RequestThread 내에서 HttpServletRequest 에 접근한 뒤, RequestHeader 에 기록된 DocumentId 를 조회합니다.
   */
  public static String getUserId() {
    return getRequest().getHeader("office-userid");
  }
}