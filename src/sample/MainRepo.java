package sample;

import java.io.IOException;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainRepo {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/ShopDatabase";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "abcd";

    private static final String GET_PRODUCTS = "SELECT * FROM products";
    //todo add these
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE idProduct = ?";
    private static final String GET_PRODUCT_BY_NAME = "SELECT * FROM products WHERE nameProduct = ?";
    private static final String GET_ADDITIONS = "SELECT * FROM additions";
    private static final String GET_ADDITION_BY_ID = "SELECT * FROM additions WHERE idAddition = ?";
    private static final String GET_ADDITION_BY_DATE = "SELECT * FROM additions WHERE dateAddition = ?";

    public MainRepo() {
    }

    private static final String CREATE_NEW_PRODUCT = "INSERT INTO products(idProduct,nameProduct,priceProduct,quantityProduct,totalProduct)" +
            " values (NULL,?,?,?,?)";

    private static final String CREATE_NEW_ADDITION =
            "INSERT INTO additions(idAddition,dateAddition,nameProduct,quantityProduct)" +
                    " values (NULL,?,?,?,?)";

    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM products WHERE idProduct = ?";
    private static final String DELETE_ADDITION_BY_ID = "DELETE FROM additions WHERE idAddition = ?";
    private static final String DELETE_PRODUCT_BY_NAME = "DELETE FROM products WHERE nameProduct = ?";
    private static final String DELETE_ADDITION_BY_DATE = "DELETE FROM products WHERE dateAddition = ?";

    private static final String CHANGE_PRODUCT_NAME_BY_ID = "UPDATE products SET nameProduct(nameProduct) WHERE idProduct=(idProduct)" +
            "values (?,?)";
    private static final String CHANGE_PRODUCT_NAME_BY_NAME = "UPDATE products SET nameProduct(nameProductNew) WHERE nameProduct=(nameProductOld)" +
            "values (?,?)";

    private static final String CHANGE_PRODUCT_PRICE_BY_ID = "UPDATE products SET priceProduct(priceProduct) WHERE idProduct=(idProduct)" +
            "values (?,?)";
    private static final String CHANGE_PRODUCT_QUANTITY_BY_ID = "UPDATE products SET quantityProduct(quantityProduct) WHERE idProduct=(idProduct)" +
            "values (?,?)";
    private static final String CHANGE_PRODUCT_TOTAL_BY_ID = "UPDATE products SET totalProduct(totalProduct) WHERE idProduct=(idProduct)" +
            "values (?,?)";

    private static final String CHANGE_PRODUCT_PRICE_BY_NAME = "UPDATE products SET priceProduct(priceProduct) WHERE nameProduct=(nameProduct)" +
            "values (?,?)";
    private static final String CHANGE_PRODUCT_QUANTITY_BY_NAME = "UPDATE products SET quantityProduct(quantityProduct) WHERE nameProduct=(nameProduct)" +
            "values (?,?)";
    private static final String CHANGE_PRODUCT_TOTAL_BY_NAME = "UPDATE products SET totalProduct(totalProduct) WHERE nameProduct=(nameProduct)" +
            "values (?,?)";



    public static Connection getDbConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
    }

    public ArrayList<Product> getProducts() throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_PRODUCTS);
        ResultSet rs = preparedStatement.executeQuery();
        ArrayList<Product> products=new ArrayList<Product>();
        while(rs.next()){
            String nameProduct = rs.getString(1);
            Float priceProduct=rs.getFloat(2);
            Float quantityProduct=rs.getFloat(3);
            Product product = new Product(nameProduct,priceProduct,quantityProduct);
            products.add(product);
        }
        return products;
    }

    public ArrayList<Addition> getAdditions() throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_ADDITIONS);
        ResultSet rs = preparedStatement.executeQuery();
        ArrayList<Addition> additions=new ArrayList<Addition>();
        while(rs.next()){
            //todo dateAddedProduct
            Date dateAddedProduct=rs.getDate(1);
            String nameAddedProduct = rs.getString(2);
            Float quantityProduct=rs.getFloat(2);
            ProductPair productPair=new ProductPair(nameAddedProduct,quantityProduct);
            Addition addition = new Addition(dateAddedProduct,productPair);
            additions.add(addition);
        }
        return additions;
    }

    public boolean addProduct(Product product) throws  SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CREATE_NEW_PRODUCT);
        preparedStatement.setString(1,product.getName());
        preparedStatement.setFloat(2,product.getPrice());
        preparedStatement.setFloat(3,product.getQuantity());
        preparedStatement.setFloat(4,product.getTotal());
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean addAddition(Addition addition) throws  SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CREATE_NEW_ADDITION);
        preparedStatement.setDate(1,addition.getAdditionDate());
        preparedStatement.setString(2,addition.getAdditionProductNames().getName());
        preparedStatement.setFloat(3,addition.getAdditionProductNames().getQuantity());
        return preparedStatement.executeUpdate() > 0;
    }

    public Product getProductById(int idProduct) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_PRODUCT_BY_ID);
        ResultSet rs = preparedStatement.executeQuery();
        String nameProduct = rs.getString(1);
        Float priceProduct=rs.getFloat(2);
        Float quantityProduct=rs.getFloat(3);
        return new Product(nameProduct,priceProduct,quantityProduct);
    }

    public boolean getProductByName(String nameProduct) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_PRODUCT_BY_NAME);
        preparedStatement.setString(1,nameProduct);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean getAdditionById(int idAddition) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_ADDITION_BY_ID);
        preparedStatement.setInt(1,idAddition);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean getAdditionByDate(Date dateAddition) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_ADDITION_BY_DATE);
        preparedStatement.setDate(1,dateAddition);
        return preparedStatement.executeUpdate() > 0;
    }


    /*public List<Spectacol> getSpectacole(Teatru teatru) throws SQLException, IOException {
        List<Spectacol> spectacole=new ArrayList<>();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(GET_SPECTACOLE);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()){
            String data=rs.getString(4);
            String ora=rs.getString(5);
            String durataSpectacol=rs.getString(6);
            //String sfarsit=rs.getString(7);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataSpectacol = LocalDate.parse(data, formatter);
            String oraSpectacol=ora.substring(0,2);
            String minutSpectacol=ora.substring(3,5);
            LocalTime oraSpectacolFormatata=LocalTime.of(Integer.parseInt(oraSpectacol),Integer.parseInt(minutSpectacol),0);
            durataSpectacol=String.format("PT%sM",durataSpectacol);
            Duration durataSpectacolFormatata=Duration.parse(durataSpectacol);
            LocalTime oraSfarsitSpectacol=oraSpectacolFormatata.plus(durataSpectacolFormatata);

            Spectacol s = new Spectacol(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),dataSpectacol,
                    oraSpectacolFormatata,durataSpectacolFormatata,oraSfarsitSpectacol,
                    rs.getInt(9),rs.getInt(10));
            spectacole.add(s);
        }
        for(Spectacol spec:spectacole) {
            teatru.addSpectacol(spec);
        }
        return spectacole;
    }
    */

    public boolean deleteProductById(int idProduct) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_PRODUCT_BY_ID);
        preparedStatement.setInt(1,idProduct);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteAdditionById(int idAddition) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_ADDITION_BY_ID);
        preparedStatement.setInt(1,idAddition);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteProductByName(String nameProduct) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_PRODUCT_BY_NAME);
        preparedStatement.setString(1,nameProduct);
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteAdditionByDate(Date dateAddition) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_ADDITION_BY_DATE);
        preparedStatement.setDate(1,dateAddition);
        return preparedStatement.executeUpdate() > 0;
    }


    public boolean changeProductNameById(int idProduct,String nameProductNew) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CHANGE_PRODUCT_NAME_BY_ID);
        preparedStatement.setString(1,nameProductNew);
        preparedStatement.setInt(2,idProduct);
        return preparedStatement.executeUpdate()>0;
    }

    public boolean changeProductPriceById(int idProduct,int priceProductNew) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CHANGE_PRODUCT_PRICE_BY_ID);
        preparedStatement.setFloat(1,priceProductNew);
        preparedStatement.setInt(2,idProduct);
        ////todo automatic total update
        Boolean updateOk=preparedStatement.executeUpdate()>0;
        Product product = getProductById(idProduct);

        //changeProductTotalById(idProduct,product.g);
    }

    public boolean changeProductQuantityById(int idProduct,int quantityProductNew) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CHANGE_PRODUCT_QUANTITY_BY_ID);
        preparedStatement.setFloat(1,quantityProductNew);
        preparedStatement.setInt(2,idProduct);
        //todo automatic total update
        return preparedStatement.executeUpdate()>0;
    }

    public boolean changeProductTotalById(int idProduct,int totalProductNew) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CHANGE_PRODUCT_TOTAL_BY_ID);
        preparedStatement.setFloat(1,totalProductNew);
        preparedStatement.setInt(2,idProduct);
        return preparedStatement.executeUpdate()>0;
    }

    public boolean changeProductNameByName(String nameProductOld,String nameProductNew) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CHANGE_PRODUCT_NAME_BY_NAME);
        preparedStatement.setString(1,nameProductNew);
        preparedStatement.setString(2,nameProductOld);
        return preparedStatement.executeUpdate()>0;
    }

    public boolean changeProductPriceByName(String nameProduct,int priceProductNew) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CHANGE_PRODUCT_PRICE_BY_NAME);
        preparedStatement.setFloat(1,priceProductNew);
        preparedStatement.setString(2,nameProduct);
        //todo automatic total update
        return preparedStatement.executeUpdate()>0;
    }

    public boolean changeProductQuantityByName(String nameProduct,int quantityProductNew) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CHANGE_PRODUCT_QUANTITY_BY_NAME);
        preparedStatement.setFloat(1,quantityProductNew);
        preparedStatement.setString(2,nameProduct);
        //todo automatic total update
        return preparedStatement.executeUpdate()>0;
    }

    public boolean changeProductTotalByName(String nameProduct,int totalProductNew) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CHANGE_PRODUCT_TOTAL_BY_NAME);
        preparedStatement.setFloat(1,totalProductNew);
        preparedStatement.setString(2,nameProduct);
        //todo automatic total update
        return preparedStatement.executeUpdate()>0;
    }

    /*
    public boolean mutaSpectacol(int idSpectacol,String dataNoua) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CHANGE_SPECTACOl);
        preparedStatement.setString(1,dataNoua);
        preparedStatement.setInt(2,idSpectacol);
        return preparedStatement.executeUpdate()>0;
    }

    public boolean mutaBilet(int idSpectacol,int idBilet) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CHANGE_BILET);
        preparedStatement.setInt(1,idSpectacol);
        preparedStatement.setInt(2,idBilet);
        return preparedStatement.executeUpdate()>0;
    }
    */
}
