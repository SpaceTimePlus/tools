package fun.dodo.tools.echo;

import com.google.protobuf.Message;

import java.io.Serializable;

import static fun.dodo.tools.help.ResHelper.parseProtobufToJson;


public final class BodyOne implements Serializable {
    private static final long serialVersionUID = 5700275373319682721L;

    private Object data;


    public BodyOne() {

    }


    public Object getData() {
        return data;
    }


    public BodyOne setData(final Object data) {

        if(data instanceof Message){
            System.out.print("data 参数属于 Message 类型");
            this.data = parseProtobufToJson((Message)data);
        }else{
            this.data = data;
        }

        return this;
    }

}
