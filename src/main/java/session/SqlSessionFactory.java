package session;

import config.Configuration;
import config.MapperStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import sun.applet.Main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class SqlSessionFactory {

    private Configuration configuration = new Configuration();

    public SqlSessionFactory(){
        //加载数据库信息
        loadDBinfo();
        //加载mapper文件信息
        loadMapinfo();
    }


    private void loadMapinfo() {
        InputStream is = SqlSessionFactory.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            configuration.setJdbcDriver(properties.getProperty("jdbc.Driver"));
            configuration.setJdbcUrl(properties.getProperty("jdbc.url"));
            configuration.setJdbcUsername(properties.getProperty("jdbc.username"));
            configuration.setJdbcPassword(properties.getProperty("jdbc.password"));
        } catch (IOException e) {
        }
    }

    private void loadDBinfo() {
        URL resources = null;
        //获取mapper包下的路径
        resources = SqlSessionFactory.class.getClassLoader().getResource("mapper");
        File mappers = new File(resources.getFile());
        if(mappers.isDirectory()){
            File[] files = mappers.listFiles();
            for(File file : files){
                loadMapperInfo(file);
            }
        }
    }


    private void loadMapperInfo(File file){
        //读取xml文件类
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //获得根元素
        Element node = document.getRootElement();
        //获取namespace
        String namespace = node.attribute("namespace").getData().toString();
        List<Element> selects = node.elements("select");
        for (Element element:selects) {
            MapperStatement mapperStatement = new MapperStatement();
            String id = element.attribute("id").getData().toString();
            System.out.println(id);
            String resultMap = element.attribute("resultType").getData().toString();
            System.out.println(resultMap);
             //= element.attribute("resultMap").getData().toString();
            String sql = element.getData().toString();
            String sourceid = namespace + "." + id;
            mapperStatement.setNamespace(namespace);
            mapperStatement.setSourceId(sourceid);
            mapperStatement.setReaultMap(resultMap);
            mapperStatement.setSql(sql);
            configuration.getMapperStatementMap().put(sourceid,mapperStatement);
        }

    }

    public SqlSession openSqlSession(){
        System.out.println(configuration.getMapperStatementMap().size());

        return new DefaultSqlSessino(configuration);
    }
}
