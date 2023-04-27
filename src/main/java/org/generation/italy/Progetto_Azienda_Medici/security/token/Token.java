package org.generation.italy.Progetto_Azienda_Medici.security.token;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import org.generation.italy.Progetto_Azienda_Medici.security.user.User;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

  @Id
  @GeneratedValue(generator = "token_generator", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "token_generator", sequenceName = "token_sequence", allocationSize = 1)
  @Column(name= "id_token")
  public Integer id;

  @Column(unique = true, name = "token_code")
  public String token;

  @Enumerated(EnumType.STRING)
  @Column(name = "token_type", columnDefinition = "token_type")
  @Type(PostgreSQLEnumType.class)
  public TokenType tokenType = TokenType.BEARER;

  public boolean revoked;

  public boolean expired;

  @ManyToOne(fetch =  FetchType.LAZY)
  @JoinColumn(name = "id_app_user")
  public User user;


}
