package fun.dodo.tools.help;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static fun.dodo.tools.Constants.DATABASE_ACCESS_ERROR;
import static fun.dodo.tools.Constants.PROTOBUF_PARSE_ERROR;


public class CommonMapper<T> implements ResultSetMapper<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMapper.class);
    private Class<T> entityClass;

    @Override
    public T map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        T result = null;

        try {

            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            entityClass = (Class) params[0];

            Method parseFrom = entityClass.getMethod("parseFrom", byte[].class);

            result = (T) parseFrom.invoke(entityClass, r.getBytes("entity"));

        } catch (final SQLException se) {
            LOGGER.error(DATABASE_ACCESS_ERROR + "： {}", Arrays.toString(se.getStackTrace()));
        } catch (final Exception e) {
            LOGGER.error(PROTOBUF_PARSE_ERROR + "： {}", Arrays.toString(e.getStackTrace()));
        }

        return result;
    }

}