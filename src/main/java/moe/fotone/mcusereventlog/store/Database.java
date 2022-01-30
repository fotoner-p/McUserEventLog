package moe.fotone.mcusereventlog.store;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public Database() {
        emf = Persistence.createEntityManagerFactory("mcdb");
        em = emf.createEntityManager();
    }

    public static EntityManager getEntityManager(){
        return em;
    }

    protected void finalize() throws Throwable{
        em.close();
        emf.close();
    }
}
