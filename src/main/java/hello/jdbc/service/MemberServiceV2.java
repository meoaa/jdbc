package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public MemberServiceV2(DataSource dataSource, MemberRepositoryV2 memberRepository) {
        this.dataSource = dataSource;
        this.memberRepository = memberRepository;
    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException{
        Connection con = dataSource.getConnection();
        try {
            con.setAutoCommit(false);
            boolean autoCommit = con.getAutoCommit();
            log.info("auto commit={}", autoCommit);
            bizLogic(con, fromId, toId, money);
            log.info("after bizLogic");
            con.commit();
            log.info("after commit");
        } catch (Exception e){
            log.info("예외 영역");
            con.rollback();
            throw new IllegalStateException(e);
        } finally {
            release(con);
        }
    }

    private void release(Connection con) {
        if(con != null){
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (Exception e) {
                log.info("error ", e);
            }
        }
    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(con, toId, toMember.getMoney() + money);
    }

    private static void validation(Member toMember) {
        if(toMember.getMemberId().equals("ex")){
            log.info("예외 발생!!");
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
