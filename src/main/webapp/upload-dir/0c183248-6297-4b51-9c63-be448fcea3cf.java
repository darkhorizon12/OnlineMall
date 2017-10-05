TDD(Testhttp://jojoldu.tistory.com/33


	  
1. 각각의 테이블을 만든 후 조인 ==> 조인 전략
2. 통합 테이블 ==> 단일 테이블 전략
3. 서브타입마다 하나의 테이블 구성 ==> 테이블 전략



/** 조인 전략

부모 테이블을 포함한 자식 테이블이 모두 생성되며,
자식 테이블에는 부모의 PK에 해당하는 칼럼이 생성되어 자신의 PK 및 FK로 사용한다.
 * 
 * 장점
	테이블이 정규화된다.
	외래 키 참조 무결성 제약조건을 활용 할 수 있다.
	저장 공간을 효율적으로 사용한다.
 * 단점
	조회 할 때 조인이 많이 사용되므로 성능이 저하 될 수 있다.
	조회쿼리가 복잡하다.
	데이터들 등록할 때 insert 쿼리는 두번 날려야한다.
 **/

@Entity
@Inheritance(strategy = InheritanceType.JOINED)	// 상속 대상의 부모 클래스임을 선언. strategy를 통해 매핑 타입을 지정.
@DiscriminatorColumn(name = "DTYPE")	// 구분자 컬럼명
public abstract class Item {
	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	private int price;
}

@Entity
@DiscriminatorValue("A")	// 부모의 구분자 컬럼에 입력될 해당 엔티티의 값
public class Album extends Item {
	private String artist;
}

@Entity
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "BOOK_ID")	// 기본적으로 자식은 부모의 기본키 컬럼명을 사용. 만일 자식테이블의 기본키로 변경하고 싶을 때 선언
public class Book extends Item {
	private String author;
	private String isbn;
}

@Entity
@DiscriminatorValue("C")
public class Movie extends Item {
	private String director;
	private String actor;
}


/** 단일 테이블 전략
 * 소스를 수정할 필요는 없고 @Inheritance(strategy = InheritanceType.SINGLE_TABLE) 어노테이션의 값만 바꾸면 된다. 
 * 그러면 테이블이 한개만 생긴다.
 * 주의할 점은 자식 엔티티가 매핑한 컬럼은 모두 null을 허용 해야 한다는 점이다.
 
 * 
 * 장점
	조인이 필요 없으므로 일반적으로 조회 성능이 빠르다.
	조회 쿼리가 단순하다.
 * 단점
	자식 엔티티가 매핑한 컬럼은 모두 null을 허용해야 한다.
	단일 테이블에 모든 것이 저장 하므로 테이블이 커질 수 있다. 그러므로 상황에 따라서는 조회 성능이 오히려 느려질 수 있다.
 * 특징
	구분 컬럼을 꼭 사용해야 한다. 따라서 @DiscriminatorColumn을 꼭 설정해야 한다.
	@DiscriminatorValue를 지정하지 않으면 기본으로 엔티티 이름을 사용한다. 예) Movie, Album, Book
**/

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)	// 상속 대상의 부모 클래스임을 선언. strategy를 통해 매핑 타입을 지정.
@DiscriminatorColumn(name = "DTYPE")	// 구분자 컬럼명
public abstract class Item {
	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	private int price;
}

@Entity
@DiscriminatorValue("A")	// 부모의 구분자 컬럼에 입력될 해당 엔티티의 값
public class Album extends Item {
	private String artist;
}

@Entity
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "BOOK_ID")	// 기본적으로 자식은 부모의 기본키 컬럼명을 사용. 만일 자식테이블의 기본키로 변경하고 싶을 때 선언
public class Book extends Item {
	private String author;
	private String isbn;
}

@Entity
@DiscriminatorValue("C")
public class Movie extends Item {
	private String director;
	private String actor;
}


/** 테이블 전략
 * 이 전략 또한 어노테이션의 값만 바꾸면 된다. 이 전략은 구현 클래스마다 테이블 전략을 사용한다. 
 * 자식 엔티티마다 테이블을 만든다. 일반적으로는 추천하지 않는다.
 * 부모 엔티티의 변수를 상속한 자식 테이블이 각각 만들어진다. 부모 엔티티에 매핑되는 테이블은 없음.
 
 * 
 * 장점
	서브 타입을 구분해서 처리할 때 효과적이다.
	not null 제약 조건을 사용할 수 있다.
 * 단점
	여러 자식 테이블을 함께 조회할 때 성능이 느리다(SQL에 UNION을 사용해야 한다.).
	자식 테이블을 통합해서 쿼리하기 어렵다
 * 특징
	구분 컬럼을 사용하지 않는다.
 **/

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)	// 상속 대상의 부모 클래스임을 선언. strategy를 통해 매핑 타입을 지정.
public abstract class Item {
	@Id @GeneratedValue(strategy = GenerationType.TABLE) // 테이블별로 아이디가 생성되기 때문에 자동키 생성 전략 역시 테이블이어야 한다.
	// -**Cannot use identity column key generation with <union-subclass> mapping for -** 에러 발생
	private Long id;
	
	private String name;
	
	private int price;
}

@Entity
public class Album extends Item {
	private String artist;
}

@Entity
public class Book extends Item {
	private String author;
	private String isbn;
}

@Entity
public class Movie extends Item {
	private String director;
	private String actor;
}

/**
 * 상속 관계 매핑은 부모 클래스와 자식 클래스를 모두 데이터베이스 테이블과 매핑했다. 
 * 부모 클래스는 테이블과 매핑하지 않고 부모 클래스를 상속받는 자식 클래스에게 매핑 정보만 제공하고 싶으면 
	@MappedSuperclass를 사용하면 된다.
	예) 모든 테이블에 들어가는 공통된 칼럼 등으로 사용(등록일, 등록자, 수정일, 수정자...)
 * @MappedSuperclass는 비유를 하자면 추상 클래스와 비슷한데 @Entity는 실제 테이블과 매핑되지만 
	@MappedSuperclass는 실제 테이블과 매핑 되지 않는다. 단순한 상속 목적으로 사용한다.

 * BaseEntity에는 객체들이 주로 사용하는 공통 매핑 정보를 정의했다. 
 * 그리고 자식 엔티티들은 상속을 통해 BaseEntity의 매핑 정보를 물려받았다. 
 * 여기서 BaseEntity는 테이블과 매핑할 필요가 없고 자식 엔티티에게 공통으로 사용되는 매핑 정보만 제공하면 된다.
 * 만약 부모로 부터 물려 받은 매핑 정보를 재정의 하려면 @AttributeOverrides나 @AttributeOverride를 사용하고 
	연관관계를 재정의하려면 @AssociationOverrides나 @AssociationOverride를 사용하면 된다.

좀 더 공부해야 할 사항은, 
먼저 @MappedSuperclass의 객체는 @Entity가 아니다. 다만 자식 엔티티의 공통된 변수를 정의한 부모 클래스일 뿐이다.
이것만 제외하면 결과적으로 
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)와 @MappedSuperclass의 결과는 동일한 것으로 보인다.
**/

@MappedSuperclass
상속 관계 매핑은 부모 클래스와 자식 클래스를 모두 데이터베이스 테이블과 매핑했다. 부모 클래스는 테이블과 매핑하지 않고 부모 클래스를 상속받는 자식 클래스에게 매핑 정보만 제공하고 싶으면 @MappedSuperclass를 사용하면 된다.
@MappedSuperclass는 비유를 하자면 추상 클래스와 비슷한데 @Entity는 실제 테이블과 매핑되지만 @MappedSuperclass는 실제 테이블과 매핑 되지 않는다. 단순한 상속 목적으로 사용한다.

@MappedSuperclass
public abstract class BaseEntity {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
}

@Entity
public class Member extends BaseEntity {

  //ID, NAME 상속
  private String email;
}

@Entity
public class Seller extends BaseEntity {

  //ID, NAME 상속
  private String shopName;
}

/**
BaseEntity에는 객체들이 주로 사용하는 공통 매핑 정보를 정의했다. 그리고 자식 엔티티들은 상속을 통해 BaseEntity의 매핑 정보를 물려받았다. 
여기서 BaseEntity는 테이블과 매핑할 필요가 없고 자식 엔티티에게 공통으로 사용되는 매핑 정보만 제공하면 된다.
만약 부모로 부터 물려 받은 매핑 정보를 재정의 하려면 @AttributeOverrides나 @AttributeOverride를 사용하고 연관관계를 재정의하려면 
@AssociationOverrides나 @AssociationOverride를 사용하면 된다.
**/

@Entity
@AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID"))
public class Member extends BaseEntity {

  //ID, NAME 상속
  private String email;
}
물려받은 id 속성을 MEMBER_ID로 재정의 했다.

@Entity
@AttributeOverrides({
  @AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID")),
  @AttributeOverride(name = "name", column = @Column(name = "MEMBER_NAME"))
})
public class Member extends BaseEntity {

  //ID, NAME 상속
  private String email;
}
만약 둘이상을 재정의하려면 @AttributeOverrides 사용하면 된다.

특징
테이블과 매핑되지 않고 자식 클래스에 엔티티의 매핑 정보를 상속하기 위해 사용한다.
@MappedSuperclass로 지정한 클래스는 엔티티가 아니므로 find()나 JPQL에서 사용 할 수 없다.
이 클래스를 직접 생성해서 사용할 일은 거의 없으므로 추상 클래스로 만드는 것을 권장한다.