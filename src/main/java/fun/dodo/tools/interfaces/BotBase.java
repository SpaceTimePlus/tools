package fun.dodo.tools.interfaces;

import io.vertx.reactivex.core.WorkerExecutor;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

public interface BotBase {

    void register(final Router router, final WorkerExecutor executor);

    void add(final RoutingContext context);

    void update(final RoutingContext context);

    void get(final RoutingContext context);

    void delete(final RoutingContext context);

    void getList(final RoutingContext context);

}