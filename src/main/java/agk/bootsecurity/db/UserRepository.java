package agk.bootsecurity.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import agk.bootsecurity.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
   /* @Modifying
    @Query("update User u set u.password=?3 where u.ID=?1 and u.username=?2")
    void setUserInfoById(Integer id,String username,String password);
    */
}
