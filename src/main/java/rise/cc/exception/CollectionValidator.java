package rise.cc.exception;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Collection;
import java.util.List;

public class CollectionValidator implements Validator {
    private final Validator validator;

    // LocalValidatorFactoryBean를 통해 validator에 collectionValidator 할당
    public CollectionValidator(LocalValidatorFactoryBean validatorFactory) {
        this.validator = validatorFactory;
    }

    // Class<?>로 인해 모든 클래스 타입에 검증 지원
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /*  target에 대한 검증 수행
        target 이 List 타입인 경우 Collection 으로 캐스팅 후 반복 통해 검증
     */
    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof List) {
            Collection collection = (Collection) target;
            for (Object object : collection) {
                ValidationUtils.invokeValidator(validator, object, errors);
            }
        }
    }
}