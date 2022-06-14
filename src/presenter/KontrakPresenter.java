/**
 * Filename : KontrakPresenter.java
 * Programmer : D'Riski Maulana
 * Date : May 10, 2022
 * Email : driskimaulana.upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : package presenter, sebagai janji untuk diterapkan pada implemented class
 */

package presenter;

public interface KontrakPresenter {

    public void prosesData();
    public int getId(int i);
    public int getFall(int i);
    public int getAdapt(int i);
    public String getUsername(int i);
    public int getSize();
    public String getError();

}