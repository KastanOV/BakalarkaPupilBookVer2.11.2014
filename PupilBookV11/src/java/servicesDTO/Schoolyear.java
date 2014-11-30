package servicesDTO;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * Created by Kastan on 11/25/2014.
 */
@XmlRootElement
public class Schoolyear {


    private final int _id;
    private final String _name;

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public Schoolyear(int id, String name){
        this._id = id;
        this._name = name;
    }

    public String get_name() {
        return _name;
    }

    public int get_id() {
        return _id;
    }
}
