package model;

/**
 * Filename : DB.java
 * Programmer : D'Riski Maulana
 * Date : 10 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : package model untuk mengakses basis data
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    // class attribute
    private String ConAddress = "jdbc:mysql://localhost:3306/survive_hop?user=root&password="; // detail databae used
    private Statement stmt = null;
    private ResultSet rs = null;
    private Connection conn = null;

    public DB() throws Exception, SQLException {
        /**
         * Method DB
         * Konstruktor: melakukan koneksi ke mysql dan basis data
         * Menerima masukan berupa string alamat koneksi ke mysql dan basis data
         */

        try {
            // membuat driver MySql
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // membuat koneksi mysql dan basisdata
            conn = DriverManager.getConnection(ConAddress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        } catch (Exception e) {
            throw e;
        }
    }

    public void createQuery(String query) throws Exception, SQLException {
        /**
         * Method createQuery
         * mengeksekusi query tanpa mengubah data (read)
         * menerima masukkan berupa string query
         */
        try {
            stmt = conn.createStatement();
            // eksekusi query
            rs = stmt.executeQuery(query);
            if(stmt.execute(query)){
                // ambil hasil query
                rs = stmt.getResultSet();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    

    public void createUpdate(String query) throws Exception, SQLException {
        /**
         * mehod createUpdate
         * mengeksekusi query dengan mengubah isi data
         * menerima masukkan berupa sring query
         */
        try {
            stmt = conn.createStatement();
            // eksekusi query
            int hasil = stmt.executeUpdate(query);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResultSet getResult() throws Exception {
        /**
         * method getResult
         * memberikan hasil query
         */
        ResultSet temp = null;
        try {
            return rs;
        } catch (Exception e) {
            // eksespsi jika hasil tidak dapat dikembalikan
            return temp;
        }
    }

    public void closeResult() throws SQLException, Exception {
        /**
         * method closeResult
         * menutup hubungan dari eksekusi query
         */
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException sqlE) {
                rs = null;
                throw sqlE;
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException sqlE) {
                stmt = null;
                throw sqlE;
            }
        }
    }

    public void closeConnection() throws Exception, SQLException {
        /**
         * mehod closeConnectino
         * menutup hubungan dengan mysql dan basisdata
         */
        if(conn!= null){
            try {
                conn.close();
            } catch (SQLException e) {
                conn = null;
                throw e;
            }
        }
    }
    
}