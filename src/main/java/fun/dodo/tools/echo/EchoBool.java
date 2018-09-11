package fun.dodo.tools.echo;

import java.io.Serializable;


public final class EchoBool implements Serializable {
    private static final long serialVersionUID = 8514435578161772933L;

    // Head
    private HeadPart head = new HeadPart();
    // Body
    private BodyBool body = new BodyBool();


    public EchoBool() {
        head.setItemCount(1);
    }


    public HeadPart getHead() {
        return head;
    }


    public EchoBool setHead(final HeadPart head) {
        this.head = head;
        return this;
    }


    public BodyBool getBody() {
        return body;
    }


    public EchoBool setBody(final BodyBool body) {
        this.body = body;
        return this;
    }

}
