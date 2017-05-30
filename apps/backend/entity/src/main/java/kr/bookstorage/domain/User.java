package kr.bookstorage.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USER")
@Data
public class User implements Serializable {

	private static final long serialVersionUID = -5940694792721955482L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Type(type="uuid-char")
	@Column(name = "UNIQUE_ID")
	private UUID uniqueId;

	@Column(name = "EMAIL", length = 150, unique = true)
	@Email
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "ENABLED", nullable = false)
	private boolean enabled;

	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<UserRole> userRoleList;

}
