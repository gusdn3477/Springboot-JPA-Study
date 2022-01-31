package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /*
        플러시는?
        - 영속성 컨테스트를 비우지 않음
        - 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
        - 트랜잭선이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면 됨

        플러시 후 커밋
        FlushModeType.AUTO : 커밋이나 쿼리를 실행할 때 플러시(기본값)
        FlushModeType.COMMIT : 커밋할 때만 플러시
        */
        /*
            준영속 상태
            영속 => 준영속
         */
        try {
            // 저장하는 코드
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
