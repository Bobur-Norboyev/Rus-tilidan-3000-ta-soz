package Baza;

import BoshqaKlass.Vaqtincha;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BazagaUlanish {

    private Connection aloqa;
    public List<Boolean> selection;

    public String tarj, audio, uzi;
    public Integer turkum, sevimli, id;

    public List ulanish(String sorov) {
        List< String> ruyhat = new ArrayList();
        selection = new ArrayList<>();
       
            try {
            Class.forName("org.sqlite.JDBC");
            try {
                aloqa = DriverManager.getConnection("jdbc:sqlite:dataBase\\words.sqlite");
            } catch (SQLException ex) {
                Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement stat = null;
            try {
                stat = aloqa.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs = null;
            try {
                rs = stat.executeQuery(sorov + Vaqtincha.bolimId);
            } catch (SQLException ex) {
                Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (rs.next()) {
                    try {
                        if (rs.getInt(3) == 0) {
                            selection.add(Boolean.FALSE);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        if (rs.getInt(3) == 1) {
                            selection.add(Boolean.TRUE);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        ruyhat.add(rs.getString(2));
                    } catch (SQLException ex) {
                        Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger ( BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch ( ClassNotFoundException e ) {
            System.out.println(e.getMessage () ) ;
        }
                
        return ruyhat ;
    }

    public List select() {
        return selection;
    }

    public void tarjimaQil(String query, int turi) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        aloqa = DriverManager.getConnection("jdbc:sqlite:dataBase\\words.sqlite");
        Statement stat = null;
        stat = aloqa.createStatement();
        ResultSet rs = null;
        rs = stat.executeQuery(query);
        while (rs.next()) {
            turkum = rs.getInt(3);
            audio = rs.getString(5);
            sevimli = rs.getInt(4);
            id = rs.getInt(6);
            if (turi == 1) {
                uzi = rs.getString(1);
                tarj = rs.getString(2);
            }
            if (turi == 2) {
                uzi = rs.getString(2);
                tarj = rs.getString(1);
            }
        }

    }

    public void selectionMethod(int ID, int qiymatcha) {
        Connection aloqa;
        try {
            Class.forName("org.sqlite.JDBC");
            aloqa = DriverManager.getConnection("jdbc:sqlite:dataBase\\words.sqlite");
            Statement stat = aloqa.createStatement();
            stat.executeQuery("UPDATE sozlar SET tanlangan=" + qiymatcha + " WHERE id=" + ID);
        } catch (ClassNotFoundException | SQLException a) {
        }
    }

    public List tanlabOl(String sorov, int til) {
        List< String> ruyhat = new ArrayList();
        selection = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                aloqa = DriverManager.getConnection("jdbc:sqlite:dataBase\\words.sqlite");
            } catch (SQLException ex) {
                Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement stat = null;
            try {
                stat = aloqa.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs = null;
            try {
                rs = stat.executeQuery(sorov);
            } catch (SQLException ex) {
                Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (rs.next()) {
                    try {
                        if (rs.getInt(3) == 0) {
                            selection.add(Boolean.FALSE);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        if (rs.getInt(3) == 1) {
                            selection.add(Boolean.TRUE);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        if (til == 1) {
                            ruyhat.add(rs.getString(2));
                        } else {
                            ruyhat.add(rs.getString(3));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(BazagaUlanish.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return ruyhat;
    }

}
