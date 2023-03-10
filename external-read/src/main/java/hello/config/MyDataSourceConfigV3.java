package hello.config;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV2;
import hello.datasource.MyDataSourcePropertiesV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
//@EnableConfigurationProperties(MyDataSourcePropertiesV3.class) // (수동 등록) 자동등록은 메인에 @EnableConfigurationProperties 추가 해주면 된다.
public class MyDataSourceConfigV3 {

    private final MyDataSourcePropertiesV3 properties;

    public MyDataSourceConfigV3(MyDataSourcePropertiesV3 properties) {
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
