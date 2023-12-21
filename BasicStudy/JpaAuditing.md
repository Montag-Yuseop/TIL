# JpaAuditing

JPA Auditing은 각 엔티티 별로 누가, 언제 접근했는지 기록해 감시 체계를 꾸리는 것</br>
ex) 언제 데이터가 생성, 누가 생성, 언제 데이터가 변경

Spring Data Jpa에서 이 기능을 사용하기 위해서는 실행 파일에 @EnableJpaAuditing을 사용해야 한다

Configuration을 생성해서 사용하자(나중에 테스트를 위해서)

## EntityListener

엔티티 객체를 데이터베이스에 적용하기 전/후에 콜백을 요청하는 Annotation

@EntityListener의 파라미터로 콜백을 요청할 클래스를 지정하여 사용

요청 시점

- @PostLoad
- @PrePersist
- @PostPersist
- @PreUpdate
- @PostUpdate
- @PreRemove
- @PostRemove

## Jpa Auditing Annotation

- @CreatedDate: 엔티티가 저장되는 시점에 자동으로 시간 주입
- @CreatedBay: 엔티티가 저장되는 시점에 저장 주체가 누구인지 주입
- @LastModifiedDate: 엔티티가 수정되는 시점에 자동으로 시간을 주입
- @LastModifiedBy: 엔티티가 수정되는 시점에 수정 주체가 누구인지 주입

## 자동으로 Entity에 값을 생성할 때 사용

```java
// BaseEntity

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

  /*
  @CreatedBy
  @Column(updatable = false)
  private String createdBy;
  */

    @LastModifiedDate
    private LocalDateTime updatedAt;

  /*
  @LastModifiedBy
  private String updatedBy;
  */

}

```

MappedSuperclass를 통해 다른 Entity에서 상속받을 때 자동 주입한다

`updateable = false`를 통해 처음 생성시에만 작성되게 만든다

```java
// ShortUrlEntity

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "short_url")
public class ShortUrlEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String hash;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false, unique = true)
    private String orgUrl;

}
```

다만, 저렇게만 써서는 안되고, 실행파일(Application.java)에서 등록을 해주어야 한다

```java
// HubToyApplication

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HubToyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HubToyApplication.class, args);
	}

}
```

`EnableJpaAuditing` Annotation을 작성!

근데 왜 순서가 다르게 나오지?

![jpa 오류](img/jpa-error.png)

해결방법:</br>
DB를 지웠다가 다시 깔았다... 이유는 잘 모르겠다
