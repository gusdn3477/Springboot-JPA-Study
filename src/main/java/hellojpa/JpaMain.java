package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(new Address("city", "street", "10000"));
            member.setWorkPeriod(new Period());

            em.persist(member);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

//    public static void printMember(Member member) {
//        System.out.println("member = " + member.getUsername());
//    }
//
//    private static void printMemberAndTeam(Member member){
//        String username = member.getUsername();
//        System.out.println("username = " + username);
//
//        Team team = member.getTeam();
//        System.out.println("team = " + team.getName());
//    }
//
//    // 타입 비교는 무조건 instanceof로!
//    private static void logic(Member m1, Member m2){
//        System.out.println("m1 == m2 " + (m1 instanceof Member));
//        System.out.println("m1 == m2 " + (m2 instanceof Member));
//    }
}
