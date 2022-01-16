package com.bolly.rsacore.annotation;

import com.bolly.rsacore.advice.EncryptRequestBodyAdvice;
import com.bolly.rsacore.advice.EncryptResponseBodyAdvice;
import com.bolly.rsacore.config.SecretKeyConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Author:Bobby
 * DateTime:2019/4/9 16:44
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({SecretKeyConfig.class,
        EncryptResponseBodyAdvice.class,
        EncryptRequestBodyAdvice.class})
public @interface EnableSecurity{

}
