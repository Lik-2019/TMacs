package binding;

import session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession){
        super();
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returntype = method.getReturnType();
        if(Collection.class.isAssignableFrom(returntype)){
            return sqlSession.selectList(method.getDeclaringClass().getName()+"."+method.getName(),
                    args==null?null:args[0]);
        }else{
            return sqlSession.selectOne(method.getDeclaringClass().getName()+"."+method.getName(),
                    args==null?null:args[0]);
        }
    }
}
