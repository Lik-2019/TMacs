package test;

import pojo.Dept;
import session.SqlSession;
import session.SqlSessionFactory;

public class Test {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        System.out.println(sqlSession);
        Dept one = sqlSession.selectOne("mapper.DeptMapper.getOne", "1");
        System.out.println(one);
        // DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        //Dept one1 = mapper.getOne(2);
        //System.out.println(one1);
    }


}
