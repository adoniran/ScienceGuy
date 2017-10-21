/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papa;

import Entidades.Conta;
import Entidades.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author adoniran
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings("JPQLValidation")
public class TestesManeger {
    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getGlobal();
    private EntityManager em;
    private EntityTransaction et;
    public TestesManeger() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("SC_exercicioPU");

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

    @Test
    public void t01_inserir_userEconta(){
        Usuario user = new Usuario();
        Conta conta = new Conta(); 
        user.setEmail("User@teste.com");
        user.setNome("Userteste");
        user.setLogin("Userteste00");
        user.setSenha("Userteste00");
        
        conta.setAgencia("123456");
        conta.setNumero("123456");
        conta.setTipo("Poupança");
        user.setConta(conta);
        em.persist(user);
        assertNotNull(conta.getId());
        assertNotNull(user.getId());
    }
    
    @Test
    public void t02_inserir_userEtelefone(){
        
        Usuario user2 = new Usuario();
        user2.setEmail("User2@teste.com");
        user2.setNome("User2teste");
        user2.setLogin("User2teste02");
        user2.setSenha("User2teste02");
        user2.addTelefone("20900-45654");
        user2.addTelefone("4003-4154");
        user2.addTelefone("5465-1111");
        em.persist(user2);
        
        assertEquals(3, user2.getTelefones().size());
    
    
    }
    @Test
    public void t03_inserir_admin(){
    
    
    
    }
    @Test
    public void t04_inserir_Msg(){}
    @Test
    public void t05_Deletar_(){}
    @Test
    public void t06_Deletar_r(){}
    @Test
    public void t07_JPQL(){}
    @Test
    public void t08_JPQL(){}
    @Test
    public void t09_inserir(){}
    @Test
    public void t10_inserir(){}
    @Test
    public void t11_inserir(){}
    @Test
    public void t12_inserir(){}
    @Test
    public void t13_inserir(){}
    @Test
    public void t14_inserir(){}
    @Test
    public void t15_inserir(){}
    
    
    
    
    
    
}
