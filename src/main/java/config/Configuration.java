package config;


import java.util.HashMap;
import java.util.Map;

/*
Mybatis配置文件信息属性类
 */
public class Configuration {

    private String jdbcDriver;

    private String jdbcUrl;

    private String jdbcUsername;

    private String jdbcPassword;

    //每次连接都是一个SqlSession，都会许多查询的配置信息
    private Map<String ,MapperStatement> mapperStatementMap = new HashMap<String ,MapperStatement>();

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public Map<String, MapperStatement> getMapperStatementMap() {
        return mapperStatementMap;
    }

    public void setMapperStatementMap(Map<String, MapperStatement> mapperStatementMap) {
        this.mapperStatementMap = mapperStatementMap;
    }
}
