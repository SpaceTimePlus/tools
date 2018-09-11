package fun.dodo.tools.echo;

import java.io.Serializable;


public final class EchoOne implements Serializable {
    private static final long serialVersionUID = 7159311332159466038L;

    // Head
    private HeadPart head = new HeadPart();
    // Body
    private BodyOne body = new BodyOne();


    public EchoOne() {
        head.setItemCount(1);
    }


    public HeadPart getHead() {
        return head;
    }


    public EchoOne setHead(final HeadPart head) {
        this.head = head;
        return this;
    }


    public BodyOne getBody() {
        return body;
    }


    public EchoOne setBody(final BodyOne body) {
        this.body = body;
        return this;
    }

}
