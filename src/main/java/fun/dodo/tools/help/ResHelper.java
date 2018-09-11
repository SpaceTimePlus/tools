package fun.dodo.tools.help;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import fun.dodo.tools.Options;
import fun.dodo.tools.echo.EchoList;
import fun.dodo.tools.echo.EchoOne;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static fun.dodo.tools.Constants.*;
import static fun.dodo.tools.help.ReqHelper.desiredJson;
import static javax.servlet.http.HttpServletResponse.*;

public final class ResHelper {
  private static final Logger LOGGER = LoggerFactory.getLogger(ResHelper.class);

  // JSON 处理程序
  private static final Gson GSON = new GsonBuilder()
    .enableComplexMapKeySerialization()
    .serializeNulls()
    .setDateFormat(DateFormat.LONG)
    .setPrettyPrinting()
    .setVersion(1.0)
    .create();

  private static final JsonFormat jsonFormat = new JsonFormat();
  private static final JsonParser jsonParser = new JsonParser();

  // 避免实例化
  private ResHelper() {
  }


  /**
   * 向前端反馈数据
   *
   * @param context: HTTP 路由上下文
   */
  public static void echoItem(final RoutingContext context, final Message message) {
    if (desiredJson(context)) {
      echoItemJson(context, message);
    } else {
      echoItemProto(context, message);
    }
  }


  /**
   * 转换为 JSON 格式
   *
   * @param context: HTTP 路由上下文
   */
  private static void echoItemJson(final RoutingContext context, final Message message) {
    final HttpServerResponse response = context.response();

    try {
      final EchoOne echo = new EchoOne();
      echo.getHead().setMessage("提取数据：成功");
      echo.getBody().setData(message);

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_OK)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      echoTransError(context, Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 转换为 ProtoBuf 格式
   *
   * @param context: HTTP 路由上下文
   */
  private static void echoItemProto(final RoutingContext context, final Message message) {
    final HttpServerResponse response = context.response();

    try {
      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_BYTE)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_OK)
        .end(Buffer.buffer(Base64.getEncoder().encode(message.toByteArray())));
    } catch (final Exception e) {
      echoTransError(context, Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 向前端反馈数据
   *
   * @param context: HTTP 路由上下文
   */
  public static void echoList(final RoutingContext context, final List list, final int lineCount, final int pageCount, final int totalCount) {
    if (desiredJson(context)) {
      echoListJson(context, list, totalCount);
    } else {
      echoListProto(context, list, lineCount, pageCount);
    }
  }


  /**
   * 把集合转换为 JSON 格式
   *
   * @param context: HTTP 路由上下文
   * @param list:    对象清单
   */
  private static void echoListJson(final RoutingContext context, final List<Message> list, final int totalCount) {
    final HttpServerResponse response = context.response();

    try {
      final EchoList echo = new EchoList();
      echo.getHead().setItemCount(totalCount).setMessage("读取清单");

      // 添加到结果集
      list.forEach(demo -> echo.getBody().addData(demo));

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_OK)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      echoTransError(context, Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 把集合转换为 ProtoBuf 格式
   *
   * @param context: HTTP 路由上下文
   * @param list:    对象清单
   */
  private static void echoListProto(final RoutingContext context, final List<Message> list, final int lineCount, final int pageCount) {
    final HttpServerResponse response = context.response();

    try {
      final fun.dodo.tools.meta.EchoList.Builder builder = fun.dodo.tools.meta.EchoList.newBuilder();

      list.forEach(item -> builder.addObject(Any.pack(item)));
      builder.setLineCount(lineCount).setPageCount(pageCount);

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_BYTE)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_OK)
        .end(Buffer.buffer(Base64.getEncoder().encode(builder.build().toByteArray())));
    } catch (final Exception e) {
      echoTransError(context, Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 任务顺利完成
   *
   * @param message: 信息
   */
  public static void echoDoneMessage(final RoutingContext context, final int echoStatus, final String message) {
    final HttpServerResponse response = context.response();

    if (Options.debugMode) {
      LOGGER.info(message);
    }

    if (desiredJson(context)) {
      echoDoneMessageJson(response, echoStatus, message);
    } else {
      echoDoneMessageProto(response, echoStatus);
    }
  }


  /**
   * 任务顺利完成, 向调用方返回 JSON 格式的信息
   */
  private static void echoDoneMessageJson(final HttpServerResponse response, final int echoStatus, final String message) {
    try {
      final EchoOne echo = new EchoOne();
      echo.getHead().setCode(1)
        .setItemCount(1)
        .setMessage(message);
      echo.getBody().setData(new ArrayList<String>());

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_OK)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 向调用方返回 ProtoBuf 格式的信息
   */
  private static void echoDoneMessageProto(final HttpServerResponse response, final int echoStatus) {
    try {
      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_BYTE)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(echoStatus)
        .end();
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 数据未找到
   *
   * @param message: 错误信息
   */
  public static void echoNotContent(final RoutingContext context, final String message) {
    final HttpServerResponse response = context.response();

    if (Options.debugMode) {
      LOGGER.info(message);
    }

    if (desiredJson(context)) {
      echoNotContentJson(response, message);
    } else {
      echoNotContentProto(response);
    }
  }


  /**
   * 分页数据未找到
   *
   * @param message: 错误信息
   */
  public static void pageEchoNotContent(final RoutingContext context, final String message, final int count) {
    final HttpServerResponse response = context.response();

    if (Options.debugMode) {
      LOGGER.info(message);
    }

    if (desiredJson(context)) {
      pageEchoNotContentJson(response, message, count);
    } else {
      echoNotContentProto(response);
    }
  }


  /**
   * 数据未找到, 向调用方返回 JSON 格式的信息
   */
  private static void echoNotContentJson(final HttpServerResponse response, final String message) {
    try {
      final EchoOne echo = new EchoOne();
      echo.getHead().setCode(1)
        .setItemCount(0)
        .setMessage(message);
      echo.getBody().setData(null);

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_OK)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 分页数据未找到, 向调用方返回 JSON 格式的信息
   */
  private static void pageEchoNotContentJson(final HttpServerResponse response, final String message, final int count) {
    try {
      final EchoOne echo = new EchoOne();
      echo.getHead().setCode(1)
        .setItemCount(count)
        .setMessage(message);
      echo.getBody().setData(null);

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_OK)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 数据未找到, 向调用方返回 ProtoBuf 格式的信息
   */
  private static void echoNotContentProto(final HttpServerResponse response) {
    try {
      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_BYTE)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_NO_CONTENT)
        .end();
    } catch (final Exception e) {
      LOGGER.error("数据未找到, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 数据格式转换错误
   *
   * @param message: 错误信息
   */
  public static void echoTransError(final RoutingContext context, final String message) {
    final HttpServerResponse response = context.response();

    if (Options.debugMode) {
      LOGGER.info(message);
    }

    if (desiredJson(context)) {
      echoTransErrorJson(response, message);
    } else {
      echoTransErrorProto(response);
    }
  }


  /**
   * 数据转为 JSON 格式时发生错误
   */
  private static void echoTransErrorJson(final HttpServerResponse response, final String message) {
    try {
      final EchoOne echo = new EchoOne();
      echo.getHead().setCode(-2)
        .setItemCount(0)
        .setMessage("转换为 JSON 格式时出现错误");
      echo.getBody().setData(message);

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_INTERNAL_SERVER_ERROR)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 数据转为 ProtoBuf 格式时发生错误
   */
  private static void echoTransErrorProto(final HttpServerResponse response) {
    try {
      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_BYTE)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_INTERNAL_SERVER_ERROR)
        .end();
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 任务或函数调用时发生错误
   *
   * @param message: 错误信息
   */
  public static void echoFoundError(final RoutingContext context, final String message) {
    final HttpServerResponse response = context.response();

    if (Options.debugMode) {
      LOGGER.info(message);
    }

    if (desiredJson(context)) {
      echoFoundErrorJson(response, message);
    } else {
      echoFoundErrorProto(response);
    }
  }


  /**
   * 任务或函数调用时发生错误
   */
  private static void echoFoundErrorJson(final HttpServerResponse response, final String message) {
    try {
      final EchoOne echo = new EchoOne();
      echo.getHead().setCode(1)
        .setItemCount(0)
        .setMessage("调用出错");
      echo.getBody().setData(message);

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_BAD_REQUEST)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 任务或函数调用时发生错误
   */
  private static void echoFoundErrorProto(final HttpServerResponse response) {
    try {
      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_BYTE)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_INTERNAL_SERVER_ERROR)
        .end();
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 处理调用常数的错误信息
   *
   * @param message: 错误信息摘要
   */
  public static void echoParamError(final RoutingContext context, final String message) {
    final HttpServerRequest request = context.request();
    final HttpServerResponse response = context.response();

    if (Options.debugMode) {
      LOGGER.info(message);
    }

    if (desiredJson(context)) {
      echoParamErrorJson(request, response, message);
    } else {
      echoParamErrorProto(response);
    }
  }


  /**
   * 处理调用常数的错误信息, 并转换为 JSON 格式, 向前端返回
   */
  private static void echoParamErrorJson(final HttpServerRequest request, final HttpServerResponse response, final String message) {
    try {
      final EchoOne echo = new EchoOne();
      echo.getHead().setCode(2)
        .setItemCount(0)
        .setMessage(message);
      echo.getBody().setData(null);

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_OK)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 处理调用常数的错误信息, 并转换为 ProtoBuf 格式, 向前端返回
   */
  private static void echoParamErrorProto(final HttpServerResponse response) {
    try {
      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_BYTE)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_BAD_REQUEST)
        .end();
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 上传文件的大小超出了预设的限制
   *
   * @param message: 错误信息摘要
   */
  public static void echoTooBiggerError(final RoutingContext context, final String message) {
    final HttpServerResponse response = context.response();

    if (Options.debugMode) {
      LOGGER.info(message);
    }

    if (desiredJson(context)) {
      echoTooBiggerErrorJson(response, message);
    } else {
      echoTooBiggerErrorProto(response);
    }
  }


  /**
   * 上传文件的大小超出了预设的限制, 并转换为 Json 格式, 向前端返回
   */
  private static void echoTooBiggerErrorJson(final HttpServerResponse response, final String message) {
    try {
      final EchoOne echo = new EchoOne();
      echo.getHead().setCode(4)
        .setItemCount(0)
        .setMessage("上传文件的大小超出了预设的限制");
      echo.getBody().setData(message);

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_BAD_REQUEST)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      LOGGER.error("上传文件的大小超出了预设的限制, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 上传文件的大小超出了预设的限制, 并转换为 ProtoBuf 格式, 向前端返回
   */
  private static void echoTooBiggerErrorProto(final HttpServerResponse response) {
    try {
      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_BYTE)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_BAD_REQUEST)
        .end();
    } catch (final Exception e) {
      LOGGER.error("上传文件的大小超出了预设的限制, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 数据重复
   *
   * @param message: 错误信息摘要
   */
  public static void echoDuplicateError(final RoutingContext context, final String message) {
    final HttpServerResponse response = context.response();

    if (Options.debugMode) {
      LOGGER.info(message);
    }

    if (desiredJson(context)) {
      echoDuplicateErrorJson(response, message);
    } else {
      echoDuplicateErrorProto(response);
    }
  }


  /**
   * 数据重复, 并转换为 Json 格式, 向前端返回
   */
  private static void echoDuplicateErrorJson(final HttpServerResponse response, final String message) {
    try {
      final EchoOne echo = new EchoOne();
      echo.getHead().setCode(4)
        .setItemCount(0)
        .setMessage("录入的数据已存在, 不能重复提交");
      echo.getBody().setData(message);

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_BAD_REQUEST)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 数据重复, 并转换为 ProtoBuf 格式, 向前端返回
   */
  private static void echoDuplicateErrorProto(final HttpServerResponse response) {
    try {
      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_BYTE)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_BAD_REQUEST)
        .end();
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }


  /**
   * 打印执行日志
   */
  public static void printResponse(final RoutingContext context, final String message) {
    if (!Options.debugMode) {
      return;
    }

    final HttpServerResponse response = context.response();

    LOGGER.info("Status:      " + response.getStatusCode());
    LOGGER.info("Message:     " + response.getStatusMessage());
    LOGGER.info("Headers:     " + StringUtil.expressMultiMap(response.headers()));
    LOGGER.info("Trailers:    " + StringUtil.expressMultiMap(response.trailers()));
    LOGGER.info("---------------------------------------------------------------");
    LOGGER.info(message);
    LOGGER.info("DateTime:    " + LocalDateTime.now());
    LOGGER.info("===============================================================");
  }


  /***
   * 将 PB 转为 JSON
   * @param message
   * @return
   */
  public static JsonObject parseProtobufToJson(Message message) {

    JsonObject result = null;

    Optional<Message> optMessage = Optional.ofNullable(message);

    try {
      if (optMessage.isPresent()) {

        String jsonStr = jsonFormat.printToString(optMessage.get());
        if (!StringUtil.isNullOrEmpty(jsonStr)) {
          result = jsonParser.parse(jsonStr).getAsJsonObject();
        }
      }

    } catch (Exception e) {
      LOGGER.error("将 pb 转换为 json 出错，{}", Arrays.toString(e.getStackTrace()));
    }

    return result;
  }


  /**
   * 修改Json串属性
   *
   * @param jsonSrc
   * @param propertys
   */
  public static String jsonStringAddProperty(String jsonSrc, Map<String, String> propertys) {
    if (StringUtil.isNullOrEmpty(jsonSrc)) {
      return null;
    }

    String jsonTaget = null;
    try {
      JsonObject jsonObject = new JsonParser().parse(jsonSrc).getAsJsonObject();
      for (Map.Entry<String, String> entry : propertys.entrySet()) {
        jsonObject.addProperty(entry.getKey(), entry.getValue());
      }
      jsonTaget = jsonObject.toString();
    } catch (final Exception e) {
      LOGGER.error("修改Json串属性出错, {}", Arrays.toString(e.getStackTrace()));
    }

    return jsonTaget;
  }


  /**
   * 修改tourNotes的info
   *
   * @param tourNotes
   * @return
   */
  public static String modifyTourNotesInfo(String tourNotes, Map<String, String> propertys) {
    if (StringUtil.isNullOrEmpty(tourNotes)) {
      return null;
    }

    String newTourNotes = null;

    try {
      JsonObject jsonTourNotes = new JsonParser().parse(tourNotes).getAsJsonObject();
      JsonObject jsonInfo = jsonTourNotes.getAsJsonObject("info");
      for (Map.Entry<String, String> entry : propertys.entrySet()) {
        jsonInfo.addProperty(entry.getKey(), entry.getValue());
      }
      newTourNotes = jsonTourNotes.toString();
    } catch (final Exception e) {
      LOGGER.error("修改vtourNotes的info出错, {}", Arrays.toString(e.getStackTrace()));
    }
    return newTourNotes;
  }


  /**
   * 修改tourNotes的用户信息
   *
   * @param tourNotes
   * @return
   */
  public static String modifyVtourNotesInfo(String tourNotes, Map<String, String> propertys) {
    if (StringUtil.isNullOrEmpty(tourNotes)) {
      return null;
    }

    String newTourNotes = null;

    try {
      JsonObject jsonTourNotes = new JsonParser().parse(tourNotes).getAsJsonObject();
      for (Map.Entry<String, String> entry : propertys.entrySet()) {
        jsonTourNotes.addProperty(entry.getKey(), entry.getValue());
      }
      newTourNotes = jsonTourNotes.toString();
    } catch (final Exception e) {
      LOGGER.error("修改vtourNotes的info出错, {}", Arrays.toString(e.getStackTrace()));
    }
    return newTourNotes;
  }


  /**
   * 转换日期格式为yyyy-MM-dd HH:mm:ss
   *
   * @param localDateTime
   * @return
   */
  public static String DateFormat(LocalDateTime localDateTime) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return localDateTime.format(dateTimeFormatter);
  }


  /**
   * 返回统计结果
   *
   * @param context
   * @param message
   * @param count
   */
  public static void echoDoneCount(final RoutingContext context, final String message, final int count) {
    final HttpServerResponse response = context.response();

    if (Options.debugMode) {
      LOGGER.info(message);
    }

    echoDoneCountJson(response, message, count);
  }


  /**
   * 任务顺利完成, 向调用方返回 JSON 格式的信息
   */
  private static void echoDoneCountJson(final HttpServerResponse response, final String message, final int count) {
    try {
      final EchoOne echo = new EchoOne();
      echo.getHead().setCode(1)
        .setItemCount(count)
        .setMessage(message);
      echo.getBody().setData(count);

      response.putHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
        .putHeader(CONTENT_CONTROL, CONTENT_CONTROL_VALUE)
        .setStatusCode(SC_OK)
        .end(GSON.toJson(echo));
    } catch (final Exception e) {
      LOGGER.error("向前端反馈信息时出错, {}", Arrays.toString(e.getStackTrace()));
    }
  }
}
