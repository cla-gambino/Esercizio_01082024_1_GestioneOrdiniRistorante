package ordineDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAOImpl implements OrdineDAO {

    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test";

    final String USER = "root";

    final String PASS = "claudio";

    @Override
    public void inserisciOrdine(Ordine o) {

        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO ORDINE VALUES (" + o.getNumeroTavolo() + "," + o.getNumeroPersone() + "," + o.getConto() + ")";
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {

            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    @Override
    public void modificaOrdine(int numeroTavolo, Ordine o) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to the selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "UPDATE ORDINE SET numeroPersone = ?, conto = ? WHERE numeroTavolo = ?";
            System.out.println("Updating records in the table...");
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, o.getNumeroPersone());
            pstmt.setDouble(2, o.getConto());
            pstmt.setInt(3, numeroTavolo);
            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public void eliminaOrdine(int numeroTavolo) {

        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "DELETE FROM ORDINE WHERE numeroTavolo= " + numeroTavolo;
            System.out.println("Deleting records from the table...");
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {

            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    @Override
    public List<Ordine> leggiOrdini() {

        Statement stmt = null;
        Connection conn = null;
        List<Ordine> ordini = new ArrayList<Ordine>();

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM ORDINE";
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int numeroTavoloTemp = rs.getInt("numeroTavolo");
                int numeroPersoneTemp = rs.getInt("numeroPersone");
                double contoTemp = rs.getInt("conto");

                Ordine o = new Ordine(numeroTavoloTemp, numeroPersoneTemp, contoTemp);
                ordini.add(o);

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {

            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return ordini;
    }

    @Override
    public Ordine leggiOrdine(int numeroTavolo) {
        Statement stmt = null;
        Connection conn = null;
        Ordine ordine = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM ORDINE WHERE numeroTavolo =" + numeroTavolo;
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int numeroTavoloTemp = rs.getInt("numeroTavolo");
                int numeroPersoneTemp = rs.getInt("numeroPersone");
                double contoTemp = rs.getInt("conto");

                ordine = new Ordine(numeroTavoloTemp, numeroPersoneTemp, contoTemp);

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {

            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return ordine;

    }
}
