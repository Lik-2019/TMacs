package reflection;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReflectionUtil {
    public static void setToBeanFromResult(Object entity,ResultSet resultSet) throws SQLException {
        Field[] decfields = entity.getClass().getDeclaredFields();
        for(int i = 0;i<decfields.length;i++){
            if(decfields[i].getType().getSimpleName().equals("String")){
                setProTOBean(entity,decfields[i].getName(),resultSet.getString(decfields[i].getName()));
            }else if(decfields[i].getType().getSimpleName().equals("Long")){
                setProTOBean(entity,decfields[i].getName(),resultSet.getLong(decfields[i].getName()));
            }else if (decfields[i].getType().getSimpleName().equals("String")){
                setProTOBean(entity,decfields[i].getName(),resultSet.getLong(decfields[i].getName()));
            }
        }
    }

    private static void setProTOBean(Object bean, String name,Object value) {
        Field field;
        try {
            field = bean.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(bean,value);
        } catch (Exception e) {
        }
    }

}
