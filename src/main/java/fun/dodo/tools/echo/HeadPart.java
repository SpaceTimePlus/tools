package fun.dodo.tools.echo;

import java.io.Serializable;


public final class HeadPart implements Serializable {
  private static final long serialVersionUID = 7986178374381257440L;

  // 任务执行状态码
  private int code = 1; // 其他值都表示出现错误
  // 返回结果的数量
  private int itemCount = 0;
  // 文字描述的信息
  private String message = "";


  public HeadPart() {

  }


  public HeadPart(final int code, final int itemCount, final String message) {
    this.code = code;
    this.itemCount = itemCount;
    this.message = message;
  }


  public int getCode() {
    return code;
  }


  public HeadPart setCode(final int code) {
    this.code = code;
    return this;
  }


  public int getItemCount() {
    return itemCount;
  }


  public HeadPart setItemCount(final int itemCount) {
    this.itemCount = itemCount;
    return this;
  }


  public String getMessage() {
    return message;
  }


  public HeadPart setMessage(final String message) {
    this.message = message;
    return this;
  }

}
