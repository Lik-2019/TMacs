package session;

import binding.MapperProxy;
import config.Configuration;
import config.MapperStatement;
import excutor.DefaultExcutor;
import excutor.Excutor;

import java.lang.reflect.Proxy;
import java.util.List;

public class DefaultSqlSessino implements SqlSession {

    private Configuration configuration;

    private Excutor excutor;

    public DefaultSqlSessino(Configuration configuration){
        super();
        this.configuration = configuration;
        excutor = new DefaultExcutor(configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T selectOne(String statement, Object paramter) {
        List<Object> selectlist = this.selectList(statement,paramter);
        if(selectlist == null || selectlist.size() == 0){
            return null;
        }
        if(selectlist.size() == 1){
            return (T) selectlist.get(0);
        }else {
            throw new RuntimeException("查询异常");
        }
    }

    public <E> List<E> selectList(String statement, Object paramter) {
        MapperStatement ms = configuration.getMapperStatementMap().get(statement);
        //System.out.println(11111);
       // System.out.println(ms.getSql());
        return  excutor.query(ms,paramter);
    }


    public <T> T getMapper(Class<T> type) {
        MapperProxy mapperProxy = new MapperProxy(this);
        return (T) Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},mapperProxy);
    }
}
