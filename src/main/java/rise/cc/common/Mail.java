package rise.cc.common;

import lombok.Data;
import lombok.Getter;
import rise.cc.util.RandomNumberUtils;

import java.util.Random;

@Data
public class Mail {
    String sendTitle;
    String sendContent;
    String from = "admin@risecc.kro.kr";
    String to;

    public enum Type {
        USER_VALIDATE;
    }

    public void setMailType(Type mailType) {
        switch (mailType) {
            case USER_VALIDATE:
                sendTitle = "[ChuckChuck] 회원가입 인증번호 안내";
                break;
        }
    }
}
