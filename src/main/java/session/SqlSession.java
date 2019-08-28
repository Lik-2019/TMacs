package session;

import java.util.List;

public interface SqlSession {

    //查询一条记录 statement 对应传过来的namespace+id parater为参数
    <T> T selectOne(String statement,Object paramter);

    //查询多条记录
    <E> List<E>  selectList(String statement, Object paramter);

    //得到代理对象
    <T> T getMapper(Class<T> type);
}
