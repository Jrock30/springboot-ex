package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * 서블릿 컨테이너 2
 */
@HandlesTypes({AppInit.class}) // 애노테이션에 애플리케이션 초기화 인터페이스를 지정한다.
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("c = " + c + ", ctx = " + ctx);

        for (Class<?> appInitClass : c) { // AppInit (AppInitV1Servlet)
            try {
                // new AppInitV1Servlet()
                AppInit appInit = (AppInit) appInitClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(ctx);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
