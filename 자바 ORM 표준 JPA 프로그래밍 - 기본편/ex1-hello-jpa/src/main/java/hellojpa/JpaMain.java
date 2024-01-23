package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속
//            Member member = new Member(200L, "member200");
//
//            em.persist(member);
//
//            System.out.println("======= 플러시 실행 ========");
//            em.flush();
//            System.out.println("==========================");
//
//            tx.commit(); // persist에서 쌓고 있다가 commit때 보낸다
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
