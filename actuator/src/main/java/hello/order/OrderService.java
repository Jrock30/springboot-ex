package hello.order;

import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {

    void order();
    void cancel();
    AtomicInteger getStock(); // AtomicInteger 멀티 스레드에서 안전하게 값을 증가 시킬 수 있음

}
