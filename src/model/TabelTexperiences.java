/**
 * Filename : TabelPengguna.javva
 * Programmer : D'Riski Maulana
 * Date : June 10, 2022
 * Email : D'Riski Maulana
 * Deskripsi : package model untuk mengakses tabel texperiences
 */

package model;

import java.sql.SQLException;

public class TabelTexperiences extends DB {

    public TabelTexperiences() throws Exception, SQLException {
        super();
    }

    public void getTexperiences()
    {
        // mengeksekusi query untuk mengambil semua data dari tabel pengguna
        try {
            String query = "SELECT * FROM texperiences";
            createQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void insert(String query)
    {
        // insert data
        try {
            createUpdate(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
}