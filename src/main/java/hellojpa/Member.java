package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
    @Entity가 붙은 클래스는 JPA가 관리, 엔티티라 한다.
    JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
    주의!
    - 기본 생성자 필수(파라미터가 없는 public 또는 protected 생성자
    - final 클래스, enum, interface, inner 클래스 사용 X
    - 저장할 필드에 final 사용 X
 */

@Entity
public class Member {

    @Id
    private Long id;
    private String name;

    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
