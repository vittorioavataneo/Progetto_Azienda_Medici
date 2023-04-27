package org.generation.italy.Progetto_Azienda_Medici.security.user;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import org.generation.italy.Progetto_Azienda_Medici.security.token.Token;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//@Data  fa getter, setter, equals e hashcode ma per entity sconsigliato
@Getter
@Setter
@Builder //genera codice per creare un user builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(generator = "app_user_generator", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "app_user_generator", sequenceName = "app_user_sequence", allocationSize = 1)
  @Column(name= "id_app_user")
  private Integer id;

  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", columnDefinition = "role")
  @Type(PostgreSQLEnumType.class)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
