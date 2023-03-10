package hello.order.v2;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV2 implements OrderService {

    private AtomicInteger stock = new AtomicInteger(100); // AtomicInteger 멀티 스레드에서 안전하게 값을 증가 시킬 수 있음

    /**
     * aop 를 활용하여 구현한 어노테이션 (counter 를 편리하게 등록할 수 있다.)
     * 이렇게 사용하면 tag 에 method 를 기준으로 분류해서 적용한다.
     */
    @Counted("my.order")
    @Override
    public void order() {
        log.info("주문");
        stock.decrementAndGet();

    }

    @Counted("my.order")
    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet();

    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
