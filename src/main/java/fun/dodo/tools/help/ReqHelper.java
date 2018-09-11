package fun.dodo.tools.help;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import fun.dodo.tools.Options;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Objects;

import static fun.dodo.tools.Constants.EMPTY_VALUE;
import static fun.dodo.tools.Constants.JSON_FORMAT;
import static fun.dodo.tools.Constants.PAYLOAD_DESIRED;
import static java.util.Optional.ofNullable;


public final class ReqHelper {
  private static final Logger LOGGER = LoggerFactory.getLogger(ReqHelper.class);

  // JSON 处理程序
  private static final Gson GSON = new GsonBuilder()
    .enableComplexMapKeySerialization()
    .serializeNulls()
    .setDateFormat(DateFormat.LONG)
    .setPrettyPrinting()
    .setVersion(1.0)
    .create();

  // 避免实例化
  private ReqHelper() {

  }

  // request.params 的返回值是 String, 可能是空值
  public static String getParamStringValue(final HttpServerRequest request, final String paramName) {
    return (ofNullable(request.getParam(paramName))).orElse(EMPTY_VALUE);
  }

  // request.params 的返回值是 String, 可能是空值
  public static long getParamSafeLongValue(final HttpServerRequest request, final String paramName) {
    final String strValue = ofNullable(request.getParam(paramName)).orElse(EMPTY_VALUE);

    long result = 0L;
    if (!StringUtil.isNullOrEmpty(strValue)) {
      try {
        result = Long.parseLong(strValue);
      } catch (final Exception e) {
        LOGGER.error("参数解析错误: {}, Error: {}", strValue, Arrays.toString(e.getStackTrace()));
      }
    }
    return result;
  }


  // request.params 的返回值是 String, 可能是空值
  public static int getParamSafeIntegerValue(final HttpServerRequest request, final String paramName) {
    final String strValue = ofNullable(request.getParam(paramName)).orElse(EMPTY_VALUE);

    int result = 0;
    if (!StringUtil.isNullOrEmpty(strValue)) {
      try {
        result = Integer.parseInt(strValue);
      } catch (final Exception e) {
        LOGGER.error("参数解析错误: {}, Error: {}", strValue, Arrays.toString(e.getStackTrace()));
      }
    }
    return result;
  }


  // request.params 的返回值是 double, 可能是空值
  public static double getParamSafeDoubleValue(final HttpServerRequest request, final String paramName) {
    final String strValue = ofNullable(request.getParam(paramName)).orElse(EMPTY_VALUE);

    double result = 0d;
    if (!StringUtil.isNullOrEmpty(strValue)) {
      try {
        result = Double.parseDouble(strValue);
      } catch (final Exception e) {
        LOGGER.error("参数解析错误: {}, Error: {}", strValue, Arrays.toString(e.getStackTrace()));
      }
    }
    return result;
  }


  // request.body 的返回值是 LinkedTreeMap, 可能是空值, 这里通过转换来读取
  public static String getBodyStringValue(final RoutingContext context, final String itemName) {
    String result = EMPTY_VALUE;

    try {
      final LinkedTreeMap<String, Object> queryMap = GSON.fromJson(context.getBodyAsString(),
        new TypeToken<LinkedTreeMap<String, Object>>() {
        }.getType());

      if ((null != queryMap) && (!queryMap.isEmpty()) && queryMap.containsKey(itemName)) {
        final String queryValue = (String) queryMap.get(itemName);

        if (null != queryValue) {
          result = queryValue;
        }
      }
    } catch (final Exception e) {
      LOGGER.error("参数解析错误: {}, Error: {}", itemName, Arrays.toString(e.getStackTrace()));
    }
    return result.trim();
  }


  // request.body 的返回值是 LinkedTreeMap, 可能是空值, 这里通过转换来读取
  public static Boolean getBodyBooleanValue(final RoutingContext context, final String itemName) {
    Boolean result = false;

    try {
      final LinkedTreeMap<String, Object> queryMap = GSON.fromJson(context.getBodyAsString(),
        new TypeToken<LinkedTreeMap<String, Object>>() {
        }.getType());

      if ((null != queryMap) && (!queryMap.isEmpty()) && queryMap.containsKey(itemName)) {
        final Boolean queryValue = (Boolean) queryMap.get(itemName);

        if (null != queryValue) {
          result = queryValue;
        }
      }
    } catch (final Exception e) {
      LOGGER.error("参数解析错误: {}, Error: {}", itemName, Arrays.toString(e.getStackTrace()));
    }
    return result;
  }


  // request.body 的返回值是 LinkedTreeMap, 可能是空值, 这里通过转换来读取
  public static long getBodySafeLongValue(final RoutingContext context, final String itemName) {
    long result = 0L;

    try {
      final LinkedTreeMap<String, Object> queryMap = GSON.fromJson(context.getBodyAsString(),
        new TypeToken<LinkedTreeMap<String, Object>>() {
        }.getType());

      // 安全监测, 必须关注类型问题
      if ((null != queryMap) && (!queryMap.isEmpty()) && queryMap.containsKey(itemName)) {
        final Object objTemp = queryMap.get(itemName);

        // Value 如果是用引号包裹, 默认是 String, 否则为 Double
        if (Double.class == objTemp.getClass()) {
          final Double douTemp = (Double) queryMap.get(itemName);

          if (null != douTemp) {
            result = douTemp.longValue();
          }
        } else {
          if (String.class == objTemp.getClass()) {
            final String strTemp = (String) queryMap.get(itemName);

            if (null != strTemp) {
              result = Long.parseLong(strTemp);
            }
          } else {
            if (Long.class == objTemp.getClass()) {
              final Long tmpLong = (Long) queryMap.get(itemName);

              if (null != tmpLong) {
                result = tmpLong;
              }
            }
          }
        }
      }
    } catch (Exception e) {
      LOGGER.error("参数解析错误: {};  {}", e.getMessage(), Arrays.toString(e.getStackTrace()));
    }
    return result;
  }


  // request.body 的返回值是 LinkedTreeMap, 可能是空值, 这里通过转换来读取
  public static double getBodySafeDoubleValue(final RoutingContext context, final String itemName) {
    double result = 0d;

    try {
      final LinkedTreeMap<String, Object> queryMap = GSON.fromJson(context.getBodyAsString(),
        new TypeToken<LinkedTreeMap<String, Object>>() {
        }.getType());

      // 安全监测, 必须关注类型问题
      if ((null != queryMap) && (!queryMap.isEmpty()) && queryMap.containsKey(itemName)) {
        final Object objTemp = queryMap.get(itemName);

        // Value 如果是用引号包裹, 默认是 String, 否则为 Double
        if (Double.class == objTemp.getClass()) {
          final Double douTemp = (Double) queryMap.get(itemName);

          if (null != douTemp) {
            result = douTemp.doubleValue();
          }
        } else {
          if (String.class == objTemp.getClass()) {
            final String strTemp = (String) queryMap.get(itemName);

            if (null != strTemp) {
              result = Double.parseDouble(strTemp);
            }
          } else {
            if (Long.class == objTemp.getClass()) {
              final Double tmpDouble = (Double) queryMap.get(itemName);

              if (null != tmpDouble) {
                result = tmpDouble.doubleValue();
              }
            }
          }
        }
      }
    } catch (Exception e) {
      LOGGER.error("参数解析错误: {};  {}", e.getMessage(), Arrays.toString(e.getStackTrace()));
    }
    return result;
  }


  /**
   * 是否 ID 传入错误
   */
  public static boolean wrongLongId(final RoutingContext context, final long targetId, final String message) {
    if (0L >= targetId) {
      ResHelper.echoParamError(context, message);
      return true;
    }
    return false;
  }


  /**
   * 是否 ID 传入错误
   */
  public static boolean litterLongId(final RoutingContext context, final long targetId, final String message) {
    if (0L > targetId) {
      ResHelper.echoParamError(context, message);
      return true;
    }
    return false;
  }


  /**
   * 是否 经纬度 传入错误
   */
  public static boolean wrongLngLat(final RoutingContext context, final double targetId, final String message) {
    if (-180d > targetId || 180d < targetId) {
      ResHelper.echoParamError(context, message);
      return true;
    }
    return false;
  }


  /**
   * 是否 String 传入错误
   */
  public static boolean wrongString(final RoutingContext context, final String targets, final String message) {
    if (StringUtil.isNullOrEmpty(targets)) {
      ResHelper.echoParamError(context, message);
      return true;
    }
    return false;
  }


  /**
   * 转换Json串为Map对象
   */
  public static LinkedTreeMap<String, Object> TransfJsonToMap(final String strJson) {
    LinkedTreeMap<String, Object> queryMap = null;

    try {
      queryMap = GSON.fromJson(strJson,
        new TypeToken<LinkedTreeMap<String, Object>>() {
        }.getType());
    } catch (final Exception e) {
      LOGGER.error("转换Json串为对象失败, Error: ", Arrays.toString(e.getStackTrace()));
    }
    return queryMap;
  }


  /**
   * 反馈格式诊断
   */
  public static boolean desiredJson(final RoutingContext context) {
    final String format = context.request().getHeader(PAYLOAD_DESIRED);

    if (StringUtil.isNullOrEmpty(format)) {
      if (Objects.equals(Options.defaultFormat, JSON_FORMAT)) {
        return true;
      }
    } else {
      if (JSON_FORMAT.equalsIgnoreCase(format)) {
        return true;
      }
    }
    return false;
  }
  /**
   * 打印执行日志
   */
  public static void printRequest(final RoutingContext context) {

    final HttpServerRequest request = context.request();

    System.out.println("");
    System.out.println("==================================================== 日志开始 ===============================================");

    System.out.println("Scheme:      " + request.scheme());

    final String contentType = context.getAcceptableContentType();
    if (!StringUtil.isNullOrEmpty(contentType)) {
      System.out.println("Type:        " + contentType);
    }

    System.out.println("Method:      " + request.method());
    System.out.println("Path:        " + context.currentRoute().getPath());
    System.out.println("Access:      " + context.normalisedPath());
    System.out.println("Complete:    " + context.request().absoluteURI());
    System.out.println("Client:      " + request.remoteAddress());
    System.out.println("Server:      " + request.localAddress());
    if (null != context.session()) {
      System.out.println("Session:     " + context.session());
    }

    System.out.println("Headers:     " + StringUtil.expressMultiMap(request.headers()));

    System.out.println("Params:      " + StringUtil.expressMultiMap(request.params()));
    System.out.println("Attributes:  " + StringUtil.expressMultiMap(request.formAttributes()));

    final String bodyString = context.getBodyAsString();
    if (!StringUtil.isNullOrEmpty(bodyString)) {
      System.out.println("Body:         " + bodyString);
    }
    System.out.println("================================================== 日志结束 =================================================");
    System.out.println("");
  }

}
