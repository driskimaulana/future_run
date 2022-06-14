package presenter;

import java.util.ArrayList;

import model.TabelTexperiences;
import model.Texperiences;

public class ProsesTexperiences implements KontrakPresenter {

    private String error; // error message
    private TabelTexperiences tabelTexperiences;
    private ArrayList<Texperiences> data;

    public ProsesTexperiences(){

        // konstruktor
        try {
            tabelTexperiences = new TabelTexperiences();
            data = new ArrayList<Texperiences>();
        } catch (Exception e) {
            //TODO: handle exception
            error = e.toString();
        }

    }

    public void prosesData(){
        try {
            // mengambil data di tabel pengguna dengan mengakses kelas model pengguna
            tabelTexperiences.getTexperiences();
            while (tabelTexperiences.getResult().next()){
                Texperiences texperiences = new Texperiences();
                // ambil hasil query
                texperiences.setId(Integer.parseInt(tabelTexperiences.getResult().getString(1)));
                texperiences.setUsername(tabelTexperiences.getResult().getString(2));
                texperiences.setAdapt(Integer.parseInt(tabelTexperiences.getResult().getString(3)));
                texperiences.setFall(Integer.parseInt(tabelTexperiences.getResult().getString(4)));
                // texperiences.setTelepon(tabelTexperiences.getResult().getString(4));
                data.add(texperiences); 
            }

            // tutup koneksi 
            tabelTexperiences.closeResult();
            tabelTexperiences.closeConnection();
        } catch (Exception e) {
            error = e.toString();
        }
    }

    public void insertData(String query)
    {
        try {
            tabelTexperiences.insert(query);
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
        }
    }

    @Override
    public int getId(int i) {
        return data.get(i).getId();
    }

    

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public String getError() {
        return this.error;
    }

    @Override
    public int getFall(int i) {
        return data.get(i).getFall();
    }

    @Override
    public int getAdapt(int i) {
        return data.get(i).getAdapt();
    }

    @Override
    public String getUsername(int i) {
        return data.get(i).getUsername();
    }
    
    
    
}