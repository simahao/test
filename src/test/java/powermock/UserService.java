package powermock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${system.userLimit")
    private Long userLimit;

    public Long getUserLimit() {
        return userLimit;
    }

    public long getUserCount() {
        System.out.println("-----------------------------");
        return 0;
    }


}
