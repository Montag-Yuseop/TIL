package hello.core.singleton;

import org.springframework.stereotype.Component;

//@Component
public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
        
    }

    public static void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
