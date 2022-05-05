package DAO;

import Entity.Librarian;
import Util.SQLoper;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @author jack li
 * @create 2021-03-14 10:10
 */
public class LibrarianDAOTest {


    @org.junit.Test
    public void add() {

    }

    @org.junit.Test
    public void upDat() {
    }

    @org.junit.Test
    public void delete() {
    }

    @org.junit.Test
    public void searchAll() throws Exception {
        LibrarianDAO dao = new LibrarianDAO();
        Connection con = SQLoper.creatCon();
        dao.searchAll(con);
        SQLoper.closeCon(con,null);
    }

    @org.junit.Test
    public void search() {
    }
}