/**
 * Filename : TabelPengguna.javva
 * Programmer : D'Riski Maulana
 * Date : May 20, 2022
 * Email : D'Riski Maulana
 * Deskripsi : package model untuk mengakses tabel pengguna 
 */

package model;

import java.sql.SQLException;

public class TabelTexperiences extends DB {

    public TabelTexperiences() throws Exception, SQLException {
        super();
        //TODO Auto-generated constructor stub
    }

    public void getTexperiences()
    {
        // mengeksekusi query untuk mengambil semua data dari tabel pengguna
        try {
            String query = "SELECT * FROM texperiences";
            createQuery(query);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.toString());
        }
    }

    public void insert(String query)
    {
        try {
            // String query = "";
            createUpdate(query);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.toString());
        }
    }
    
}