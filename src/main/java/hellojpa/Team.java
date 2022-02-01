package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    /*
    일대다 단방향 매핑의 경우 엔티티가 관리하는 외래 키가 다른 테이블에 있다.
    연관관계 관계를 위해 추가로 UPDATE SQL문을 실행하게 된다.
    일대다 단방향 매핑보다는 다대일 양방향 매핑을 사용하자!
     */
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();
    /*
    mappedBy = JPA의 멘탈붕괴 난이도..
    객체와 테이블의 관계를 맺는 차이
    객체 연관관계 = 2개(회원->팀 단방향, 팀->회원 단방향)
    테이블 연관관계 = 1개(회원<->팀의 연관관계 1개(양방향))
    개게의 양방향 관계는 사실 양방향 관계가 아니라 서로 다른 단방향 관계 2개다.
    객체를 양방향으로 참조하려면 단방향 연관관계를 2개 만들어야 한다.
    but, 테이블은 외래키 하나로 두 테이블의 연관관계를 관리

    연관관계의 주인(Owner)
    양방향 매핑 규칙
    - 객체의 두 관계중 하나를 연관관계의 주인으로 지정
    - 연관관계의 주인만이 외래 키를 관리(등록, 수정)
    - 주인이 아닌 쪽은 읽기만 가능
    - 주인이 mappedBy 속성 사용 X
    - 주인이 아니면 mappedBy 속성으로 주인 지정

    누구를 주인으로?
    - 외래 키가 있는 곳을 주인으로 하자.(일대 다에서 다가 주인이 됨)
    - 진짜 매핑 - 연관관계의 주인(Member.team)
    - 가짜 매핑 - 주인의 반대편(Team.members)
    - 자동차와 바퀴, 자동차가 비즈니스적으로는 훨씬 중요하나 바퀴가 연관관계의 주인이 된다.
   */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
