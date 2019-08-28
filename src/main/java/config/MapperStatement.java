package config;

/*
mapper.xml配置信息的属性类
 */

public class MapperStatement {

    //Mapper的namespace
    private String namespace;

    //方法的id
    private String sourceId;

    //方法的返回值
    private String reaultMap;

    //sql语句
    private String Sql;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getReaultMap() {
        return reaultMap;
    }

    public void setReaultMap(String reaultMap) {
        this.reaultMap = reaultMap;
    }

    public String getSql() {
        return Sql;
    }

    public void setSql(String sql) {
        Sql = sql;
    }
}
