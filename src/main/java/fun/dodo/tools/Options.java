package fun.dodo.tools;

import com.moandjiezana.toml.Toml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static fun.dodo.tools.Constants.JSON_FORMAT;


public final class Options {
  private static final Logger LOGGER = LoggerFactory.getLogger(Options.class);

  // 配置管理
  private final Toml tomlReader;

  // 项目信息
  private final long id;
  private final String name;

  // vertx配置
  private final long poolSize;
  private final long maxExecuteTime;

  // 运行模式
  private final String runMode;
  public static boolean debugMode = true;
  public static String defaultFormat = JSON_FORMAT;

  // redis缓存时间
  private final long expire;

  // 服务器端口和协议
  private final int serverPort;
  private final String serverType;

  // Aliyun MNS
  private String mnsEndpoint;
  private String mnsAccessKey;
  private String mnsAccessSecret;
  private String mnsApiLogQueue;

  // cors
  private String corsHost;

  public Options(final Toml toml) {
    tomlReader = toml;

    // 项目信息
    id = tomlReader.getLong("project.id", 0l);
    name = tomlReader.getString("project.name", "vertx-starter");

    // vertx配置
    poolSize = tomlReader.getLong("vertx.pool_size", 512L);
    maxExecuteTime = tomlReader.getLong("vertx.max_execute_time", 60000L);

    // 读取运行环境
    runMode = tomlReader.getString("runtime.service_mode", "pro");
    defaultFormat = tomlReader.getString("runtime.payload_format", JSON_FORMAT).toUpperCase();
    debugMode = tomlReader.getBoolean("runtime.print_debug_info", false);
    System.out.println("运行环境:         " + runMode);
    System.out.println("数据格式:         " + defaultFormat);
    System.out.println("调试模式:         " + debugMode);

    serverType = tomlReader.getString("server.type", "http");
    serverPort = tomlReader.getLong("server.port", 8000L).intValue();
    System.out.println("Current 通讯协议: " + serverType);
    System.out.println("Current 绑定端口: " + serverPort);
    System.out.println("---------------------------------------------------------------");

    // redis缓存时长
    expire = tomlReader.getLong("redis.expire", 3600L).intValue();

    // MNS 参数
    mnsEndpoint = toml.getString("MNS.endpoint", "");
    mnsAccessKey = toml.getString("MNS.access_key", "");
    mnsAccessSecret = toml.getString("MNS.access_secret", "");
    mnsApiLogQueue = toml.getString("MNS.api_log_queue", "api-log");


    System.out.println("MNS.endpoint:      " + mnsEndpoint);
    System.out.println("MNS.api_log_queue: " + mnsApiLogQueue);
    System.out.println("---------------------------------------------------------------");

    // cors
    corsHost = toml.getString("cors.host", "*");

  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Toml getTomlReader() {
    return tomlReader;
  }

  public String getRunMode() {
    return runMode;
  }

  public static boolean isDebugMode() {
    return debugMode;
  }

  public static String getDefaultFormat() {
    return defaultFormat;
  }

  public int getServerPort() {
    return serverPort;
  }

  public String getServerType() {
    return serverType;
  }

  public String getMnsEndpoint() {
    return mnsEndpoint;
  }

  public String getMnsAccessKey() {
    return mnsAccessKey;
  }

  public String getMnsAccessSecret() {
    return mnsAccessSecret;
  }

  public String getMnsApiLogQueue() {
    return mnsApiLogQueue;
  }

  public long getPoolSize() {
    return poolSize;
  }

  public long getMaxExecuteTime() {
    return maxExecuteTime;
  }

  public long getExpire() {
    return expire;
  }

  public String getCorsHost() {
    return corsHost;
  }
}
