package powermock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private Long superUserId;

    @Value("${system.userLimit")
    private Long userLimit;

    public boolean isNotSuperUser(Long userId) {
        return !isSuperUser(userId);
    }

    private boolean isSuperUser(Long userId) {
        return Objects.equals(userId, superUserId);
    }

    public Long getUserLimit() {
        return userLimit;
    }

    public long getUserCount() {
        System.out.println("-----------------------------");
        return 0;
    }


}
