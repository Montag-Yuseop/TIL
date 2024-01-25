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

            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("Member1");
//            member.changeTeam(team);
            em.persist(member);

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            team.addMember(member);

            System.out.println("============");
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
            System.out.println("============");

            tx.commit(); // persist에서 쌓고 있다가 commit때 보낸다
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
