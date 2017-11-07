/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papa;

import Entidades.Admin;
import Entidades.Projetos;
import Entidades.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adoniran
 */
public class TestesJPQL {

    public TestesJPQL() {
    }

    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getGlobal();
    private EntityManager em;
    private EntityTransaction et;

    @BeforeClass
    public static void setUpClass() {
        logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("SC_exercicioPU");
        DbUnitUtil.inserirDados();

    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        beginTransaction();
    }

    @After
    public void tearDown() {
        commitTransaction();
        em.close();
    }

    private void beginTransaction() {
        et = em.getTransaction();
        et.begin();
    }

    private void commitTransaction() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            et.rollback();
            fail(ex.getMessage());
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void t01_JPQL_namedQuery() {
        TypedQuery<Projetos> query
                = em.createNamedQuery("Projetos.todos", Projetos.class);
        List<Projetos> list = query.getResultList();
        assertEquals(3, list.size());
    }

    @Test
    public void t02_JPQL_namedQuery() {
        TypedQuery<Usuario> query
                = em.createNamedQuery("Usuarios.todos", Usuario.class);
        List<Usuario> list = query.getResultList();
        assertEquals(7, list.size());
    }

    @Test
    public void t03_JPQL_SelectLogin() {
        TypedQuery<Usuario> query
                = em.createQuery("select u from Usuario u where u.login = :login ", Usuario.class);
        query.setParameter("login", "percy");
        Usuario u = query.getSingleResult();
        assertEquals("Percy Jackson", u.getNome());
    }

//    @Test
//    public void t04_Namedquery_TelSelect() {
//        TypedQuery<Usuario> query
//                = em.createNamedQuery("Usuarios.DDDTel", Usuario.class);
//        query.setParameter("tel", "(81)3426-0011");
//        List<Usuario> u = query.getResultList();
//        assertEquals(4, u.size());
//    }

    @Test
    public void t05_JPQL_parados() {
        TypedQuery<Projetos> query
                = em.createQuery("select u from Projetos u where u.situacao IN('PARADO','ABANDONADO')", Projetos.class);
        List<Projetos> u = query.getResultList();
        assertEquals(1, u.size());

    }

    @Test
    public void t06_JPQL() {
        TypedQuery<Projetos> query
                = em.createQuery("select u from Projetos u where u.situacao IN('DESENVOLVENDO','CONCLUIDO')  ", Projetos.class);
        List<Projetos> u = query.getResultList();
        assertEquals(2, u.size());
    }

    @Test
    public void t07_JPQL() {
        TypedQuery<Projetos> query
                = em.createQuery("select u from Projetos u where u.situacao IN('DESENVOLVENDO','CONCLUIDO')  ", Projetos.class);
        List<Projetos> u = query.getResultList();
        assertEquals(2, u.size());
    }

    @Test
    public void t08_JPQLUpdate() {
        Long id = (long) 7;
        Query query = em.createQuery("UPDATE Admin AS a SET a.confirmacao = ?1 WHERE a.id = ?2");
        query.setParameter(1, "123456");
        query.setParameter(2, id);
        query.executeUpdate();
        Admin ad=em.find(Admin.class, id);
        assertEquals(ad.getConfirmacao(),"123456");

    }
    @Test
    public void t09_JPQL_DELETE(){
    Long id = (long) 1;
    Query query = em.createQuery("DELETE Mensagem AS m WHERE m.id = ?1");
     query.setParameter(1, id);
     query.executeUpdate();
     Usuario usuario =em.find(Usuario.class, id);
        assertNull(usuario.getId());
    
    }
    @Test
    public void t10_nativeQuery(){
    
    Query query = em.createNativeQuery("SELECT id, nome_project, motivacao_necessidade, necessidade, situacao, area_projeto FROM tb_projetos WHERE nome_project ='Dracoin'", Projetos.class);
//    query.setParameter(1,"Dracoin");
     List<Projetos> projetos = query.getResultList();
     assertEquals(1, projetos.size());
     
    }

}
