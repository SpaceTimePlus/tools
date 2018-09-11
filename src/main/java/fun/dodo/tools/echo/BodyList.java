package fun.dodo.tools.echo;

import com.google.gson.JsonObject;
import com.google.protobuf.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fun.dodo.tools.help.ResHelper.parseProtobufToJson;

public final class BodyList implements Serializable {
    private static final long serialVersionUID = 6294374554738939054L;

    private List data = new ArrayList<>();


    public BodyList() {
    }


    public List getData() {
        return data;
    }


    public List addData(final Object other) {
        JsonObject jsonObject;
        if (other instanceof Message) {
            jsonObject = parseProtobufToJson((Message) other);
            data.add(jsonObject);
        } else {
            data.add(other);
        }

        return data;
    }


    public BodyList setData(final List data) {

        Optional<List> list = Optional.ofNullable(data);
        list.ifPresent(itemList -> itemList.forEach(item -> this.addData(item)));

        return this;
    }

}
