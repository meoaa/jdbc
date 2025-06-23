package hello.jdbc.connection;

/**
 * JDBC는 DriverManager를 제공한다
 * DriverManager는 라이브러리에 등록된 DB 드라이버들(H2 driver , MySql driver 등)
 * 을 관리한다.
 * 애플리케이션 로직에서 Connection이 필요하면 DriverManager.getConnection()을 실행한다.
 * 각 DB드라이버에게 필요한 정보(ex. url ,username , password 등)을 넘겨서
 * connection을 획득할 수 있는지 확인
 */
public abstract class ConnectionConst {
    public static final String URL = "jdbc:h2:tcp://localhost/~/test";
    //jdbc:h2로 시작하면 h2 데이터베이스에 접근하기 위한 규칙
    //커넥션을 획득할 수 없으면 다음 드라이버, 만약 획득할 수 있으면
    //클라이언트에게 커넥션 반환
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";
}
