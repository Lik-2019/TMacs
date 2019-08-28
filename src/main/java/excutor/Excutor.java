package excutor;

import config.MapperStatement;

import java.util.List;

/*
底层查询的组件
 */
public interface Excutor {

    //查询方法
    <E> List<E> query(MapperStatement ms, Object parameter);

}
