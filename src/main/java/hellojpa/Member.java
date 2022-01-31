package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/*
    @Entity가 붙은 클래스는 JPA가 관리, 엔티티라 한다.
    JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
    주의!
    - 기본 생성자 필수(파라미터가 없는 public 또는 protected 생성자
    - final 클래스, enum, interface, inner 클래스 사용 X
    - 저장할 필드에 final 사용 X
    Update는 컬럼 추가 시에만 작동!
 */

@Entity
public class Member {

    // unique 옵션 => 제약키 이름이 이상해서 운영에서는 잘 사용하지 않음.
    // @Table에서의 unique 옵션을 더 사용하는 편.
    // insertable, updatable 기본값 true. nullable은 not null 조건과 동일
    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    // Enum의 ORDINAL의 경우 숫자로 나타남.
    // Enum에서 맨 앞에 새로운 요소가 추가되면, 기존 컬럼의 값은 예전 값으로 유지되고, 새로 들어오는 것만 업데이트 된 값이라 알아볼 수 없게 된다.
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //  LocalDatetime 덕분에 Temporal을 사용하지 않아도 됨.
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDatetime;

    //Lob => varchar를 넘어서는 큰 컨텐츠를 넣고 싶을 때 사용
    @Lob
    private String description;

    public Member(){
    }

    public Member(Long id, String name){
        this.id = id;
        this.username = name;
    }
}
