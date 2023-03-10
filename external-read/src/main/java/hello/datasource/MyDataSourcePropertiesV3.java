package hello.datasource;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.List;

@Getter
@ConfigurationProperties("my.datasource")
@Validated
public class MyDataSourcePropertiesV3 {

    @NotEmpty
    private String url;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private Etc etc;

    /**
     * @ConstructorBinding
     * > 스프링 3.0 이전에는 생성자 바인딩 시에 @ConstructorBinding 애노테이션을 필수로 사용해야 했다.
     * > 스프링 3.0 부터는 생성자가 하나일 때는 생략할 수 있다. 생성자가 둘 이상인 경우에는 사용할 생성자에
     *   @ConstructorBinding 애노테이션을 적용하면 된다.
     */
    @ConstructorBinding
    public MyDataSourcePropertiesV3(String url, String username, String password, @DefaultValue Etc etc) { // @DefaultValue 모든 값이 없어도 객체는 생성 된다.
        this.url = url;
        this.username = username;
        this.password = password;
        this.etc = etc;
    }

    @Getter
    public static class Etc {
        @Min(1)
        @Max(999)
        private int maxConnection;
        @DurationMin(seconds = 1) // 최소 1초
        @DurationMax(seconds = 60) // 최대 60초
        private Duration timeout;
        private List<String> options;

        @ConstructorBinding
        public Etc(int maxConnection, Duration timeout, @DefaultValue("DEFAULT") List<String> options) { // @DefaultValue 값이 없으면 기본 값을 넣어준다.
            this.maxConnection = maxConnection;
            this.timeout = timeout;
            this.options = options;
        }
    }

}
