package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // Component Scan = @Component class를 찾아서 spring bean에 등록
    // excludeFilters = @ComponentScan.Filter(~~): 안에 넣은건 자동 등록에서 빠진다


}
