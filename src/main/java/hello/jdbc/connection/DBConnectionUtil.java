package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

/**
 * 각각의 DB는 접근법이나 사용법이 다 다르다.
 * JDBC는 자바에서 데이터베이스에 접속할 수 있도록 하는 자바 API다.
 * 3가지 기능을 표준 인터페이스로 정의해서 제공한다.
 * java.sql.Connection
 * java.sql.Statement
 * java.sql.ResultSet
 * 각 DB 회사에서 자신의 DB에 맞도록 구현해서 라이브러리를 제공함
 * 이것을 JDBC 드라이버라고 한다.
 * MySQL DB에 접근할 수 있는 것은 MySQL JDBC 드라이버
 * Oracle Db에 접근할 수 있는 것은 Oracle JDBC 드라이버
 */
@Slf4j
public class DBConnectionUtil {

    public static Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection={}, class={}", connection, connection.getClass());
            return  connection;
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }
}
