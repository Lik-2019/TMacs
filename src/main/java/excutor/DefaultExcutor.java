package excutor;


import config.Configuration;
import config.MapperStatement;
import reflection.ReflectionUtil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/*
查询组件的实现类
 */
public class DefaultExcutor implements Excutor {
    private Configuration configuration;

    public  DefaultExcutor(Configuration configuration){
        super();
        this.configuration = configuration;
    }


    public <E> List<E> query(MapperStatement ms, Object parameter) {
        List<E> ret = new ArrayList<E>();
        try {
            Class.forName(configuration.getJdbcDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            con = DriverManager.getConnection(configuration.getJdbcUrl(),configuration.getJdbcUsername(),
                    configuration.getJdbcPassword());

            preparedStatement = con.prepareStatement(ms.getSql());
            //System.out.println(ms.getSql());
            parameterized(preparedStatement ,parameter);

            resultSet = preparedStatement.executeQuery();

            handlerResultSet( resultSet,  ret, ms.getReaultMap());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    private<E> void handlerResultSet( ResultSet resultSet, List<E> ret,String reaultMap) {
        Class<E> clazz = null;
        try {
            clazz = (Class<E>) Class.forName(reaultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while(resultSet.next()){
               Object entity =  clazz.newInstance();
                ReflectionUtil.setToBeanFromResult(entity,resultSet);
                System.out.println(entity);
                ret.add((E)entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parameterized(PreparedStatement preparedStatement ,Object parameter) throws SQLException {

        System.out.println(parameter);

        if(parameter instanceof Integer){

            preparedStatement.setInt(1, (Integer) parameter);
        } else if(parameter instanceof String){
            preparedStatement.setString(1,(String) parameter);
        }else if(parameter instanceof Long){
            preparedStatement.setLong(1,(Long)parameter);
        }
    }
}
