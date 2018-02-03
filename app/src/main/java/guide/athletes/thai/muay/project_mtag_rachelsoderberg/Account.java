package guide.athletes.thai.muay.project_mtag_rachelsoderberg;

/**
 * Created by Rachel on 1/27/2018.
 */

public class Account {
    public String _id;
    public String userName;
    public String email;
    public String password;

    public Account(String _id, String userName, String email, String password) {
        this._id = _id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
