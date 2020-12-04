package sample;

import java.sql.Date;

public class Addition {
    Date additionDate;
    ProductPair additionProductNames;

    public Addition(Date additionDate, ProductPair additionProductNames) {
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

    public Date getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }

    public ProductPair getAdditionProductNames() {
        return additionProductNames;
    }

    public void setAdditionProductNames(ProductPair additionProductNames) {
        this.additionProductNames = additionProductNames;
    }


}
