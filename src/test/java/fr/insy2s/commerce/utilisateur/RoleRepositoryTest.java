package fr.insy2s.commerce.utilisateur;

import fr.insy2s.commerce.models.Role;
import fr.insy2s.commerce.models.Utilisateur;
import fr.insy2s.commerce.repositories.RoleRepository;
import fr.insy2s.commerce.repositories.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {


     @Autowired RoleRepository repo;

     @Autowired
     private UtilisateurRepository utilisateurRepository;

    @Test
    public void testCreateRoles() {
        Role client = new Role("ROLE_CLIENT");

        Role customer = new Role("ROLE_CUSTOMER");

        Role admin = new Role("ROLE_ADMIN");

        repo.saveAll(List.of(client, customer, admin));
//        repo.save(client);
        long count = repo.count();
        assertEquals(3, count);
    }


    @Test
    public void testAssignRoleToUser() {
        int userId = 2;
        Long roleId = 2L;     /* role_id 1 = ADMIN, role_id  2 = CLIENT, role_id 3 = CUSTOMER*/
        Optional<Role> role = repo.findById(roleId);
        Role role2 = new Role();
        role2.setNomRole(role.get().getNomRole());
        role2.setId(role.get().getId());
        Optional<Utilisateur> user = utilisateurRepository.findById(Long.valueOf(userId));
        Utilisateur user2 = new Utilisateur();
        user2.addRole(role2);
        user2.setId(user.get().getId());
        user2.setEmail(user.get().getEmail());
        user2.setPassword(user.get().getPassword());
        user2.setNom((user.get().getNom()));
        user2.setPrenom((user.get().getPrenom()));
        utilisateurRepository.save(user2);

//        Utilisateur user = repo.findById((long) userId).get();
//        user.addRole(new Role(roleId));
//        user.addRole(new Role(roleId));

//        Utilisateur updatedUser = repo.save(user);
//        assertThat(updatedUser.getRoles()).hasSize(1);

    }
}
