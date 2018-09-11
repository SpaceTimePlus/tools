package fun.dodo.tools.echo;

import java.io.Serializable;

public final class EchoList implements Serializable {
    private static final long serialVersionUID = 750912869407071391L;

    // Head
    private HeadPart head = new HeadPart();
    // Body
    private BodyList body = new BodyList();

    public EchoList() {

    }

    public HeadPart getHead() {
        return head;
    }


    public EchoList setHead(final HeadPart head) {
        this.head = head;
        return this;
    }


    public BodyList getBody() {
        return body;
    }


    public EchoList setBody(final BodyList body) {
        this.body = body;
        return this;
    }

}
