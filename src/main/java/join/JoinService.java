package join;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final �ʵ忡 ���� �����ڸ� �ڵ����� ������ݴϴ�.
public class JoinService {

    private final JoinMapper mapper; // DB�� ����ϴ� �������丮
    private final PasswordEncoder passwordEncoder; // SecurityConfig�� Bean���� ����� �װ�!
    
    public UserVO findUserById(int userId) {
        return mapper.findUserById(userId);
    }

    public int register(UserVO userVO) {
        
        // 1. ����ڰ� �Է��� �� ��й�ȣ�� �����ɴϴ�.
        String rawPassword = userVO.getPassword();

        // 2. PasswordEncoder�� ����� ��й�ȣ�� ��ȣȭ�մϴ�.
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // 3. ��ȣȭ�� ��й�ȣ�� UserVO ��ü�� �ٽ� �����մϴ�.
        userVO.setPassword(encodedPassword);

        // 4. ��ȣȭ�� ��й�ȣ�� ��� UserVO�� DB�� �����մϴ�.
        return mapper.insertUser(userVO);
    }
}