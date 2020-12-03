package sample;

import java.util.ArrayList;
import java.util.Date;

public class Addition {
    Date additionDate;
    ArrayList<ProductPair> additionProductNames;

    public Addition(Date additionDate, ArrayList<ProductPair> additionProductNames) {
        this.additionDate = additionDate;
        this.additionProductNames = additionProductNames;
    }

    public Addition() {
    }

    public Addition(Addition other) {
        if(this!=other && other!=null) {
            this.additionDate = other.additionDate;
            this.additionProductNames = other.additionProductNames;
        }
    }
}
