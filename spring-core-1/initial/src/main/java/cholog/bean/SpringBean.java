package cholog.bean;

import org.springframework.stereotype.Component;

/*
어떤 어노테이션을 붙였을 때 Bean으로 생성되는지 학습하기
 */
@Component  // @Component 어노테이션으로 Bean 등록
public class SpringBean {
    public String hello() {
        return "Hello";
    }
}
