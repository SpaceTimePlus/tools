package fun.dodo.tools;

public final class Constants {
  // 避免被实例化
  private Constants() {

  }

  // 空字符串
  public static final String EMPTY_VALUE = "";
  // SQL通配符
  public static final String WILDS_MATCH = "%";
  // 标识
  public static final String ID_HOLDER = "id";
  // 拥有者ID
  public static final String OWNER_HOLDER = "owner_id";

  /**
   * 分页
   */
  public static final String PAGE_SIZE = "size";
  public static final String PAGE_INDEX = "index";
  public static final String PAGE_LIMIT = "limit";


  /**
   * HTTP 访问参数
   */
  public static final String CONTENT_TYPE = "Content-Type";
  public static final String CONTENT_TYPE_JSON = "application/json;chatset=utf-8";
  public static final String CONTENT_TYPE_BYTE = "application/json;chatset=utf-8";

  public static final String CONTENT_CONTROL = "Access-Control-Allow-Origin";
  public static final String CONTENT_CONTROL_VALUE = "*";

  /**
   * 反馈格式
   */
  public static final String PAYLOAD_DESIRED = "Accept";
  public static final String JSON_FORMAT = "application/json";

  /**
   * Oss图片压缩后缀
   */
  public static final String OSS_IMG_COMPRESS_SUFFIX = "?x-oss-process=image/resize,m_mfit,h_600,w_1200";


  /**
   * Message
   */
  public static final String MESSAGE_ID_CONDITION = "ID 必须是大于 0 的整数";
  public static final String MESSAGE_NAME_CONDITION = "Name 不能为空";

  public static final String DATABASE_ACCESS_ERROR = "数据库连接失败";
  public static final String PROTOBUF_PARSE_ERROR = "Protobuf格式转换失败";



}
