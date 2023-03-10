package hello.config;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
//@EnableConfigurationProperties(MyDataSourcePropertiesV2.class) // (수동 등록) 자동등록은 메인에 @EnableConfigurationProperties 추가 해주면 된다.
public class MyDataSourceConfigV2 {

    private final MyDataSourcePropertiesV2 properties;

    public MyDataSourceConfigV2(MyDataSourcePropertiesV2 properties) {
        this.properties = properties;
    }

    @Bean
    public MyDataSource dataSource() {
        return new MyDataSource(
                properties.getUrl()
                , properties.getUsername()
                , properties.getPassword()
                , properties.getEtc().getMaxConnection()
                , properties.getEtc().getTimeout()
                , properties.getEtc().getOptions());
    }
}
