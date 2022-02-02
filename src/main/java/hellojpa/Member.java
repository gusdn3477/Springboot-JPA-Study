package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    @Entity가 붙은 클래스는 JPA가 관리, 엔티티라 한다.
    JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
    주의!
    - 기본 생성자 필수(파라미터가 없는 public 또는 protected 생성자
    - final 클래스, enum, interface, inner 클래스 사용 X
    - 저장할 필드에 final 사용 X
    Update는 컬럼 추가 시에만 작동!
 */

// unique 옵션 => 제약키 이름이 이상해서 운영에서는 잘 사용하지 않음.
// @Table에서의 unique 옵션을 더 사용하는 편.
// insertable, updatable 기본값 true. nullable은 not null 조건과 동일

// Enum의 ORDINAL의 경우 숫자로 나타남.
// Enum에서 맨 앞에 새로운 요소가 추가되면, 기존 컬럼의 값은 예전 값으로 유지되고, 새로 들어오는 것만 업데이트 된 값이라 알아볼 수 없게 된다.

@Entity
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    // 읽기 전용으로 설정. update를 하지 않음. 양방향 매핑과 유사함. 관리는 Team으로, 읽기는 Member로
    // 일대다 양방향의 경우 공식적으로 존재하지 X, 가끔 사용하긴 하지만 다대일 양방향을 사용하자.
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Member(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
