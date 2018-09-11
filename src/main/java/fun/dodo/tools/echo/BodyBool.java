package fun.dodo.tools.echo;

import java.io.Serializable;

public final class BodyBool implements Serializable {
    private static final long serialVersionUID = 8802893567739399196L;

    // 执行结果
    private boolean data = true;


    public BodyBool() {

    }


    public boolean isData() {
        return data;
    }


    public BodyBool setData(final boolean data) {
        this.data = data;
        return this;
    }

}
