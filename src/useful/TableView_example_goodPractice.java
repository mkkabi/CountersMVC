
package useful;


//import customers.*;
//import products.*;
//import javafx.collections.*;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.*;
//import products.Product;

public class TableView_example_goodPractice {
/*
TableView tableView;
CustomerDatabase customers;
ProductDatabase products;
String setTable;

public LeftMenuNew() {
    tableView = new TableView<>();
    setTable = "";
}

public void setCustomersTable() {
    tableView.getColumns().clear();
    
    // Customer Number
    TableColumn<Customer, Integer> customerNumberColumn = new TableColumn<>("Cust #");
    customerNumberColumn.setPrefWidth(70);
    customerNumberColumn.setCellValueFactory(new PropertyValueFactory<>("customerNumber"));

    // Customer Name
    TableColumn<Customer, String> customerNameColumn = new TableColumn<>("Customer Name");
    customerNameColumn.setPrefWidth(230);
    customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

    tableView.setItems(getCustomers());
    tableView.getColumns().addAll(customerNumberColumn, customerNameColumn);
    tableView.getSortOrder().add(customerNameColumn);
    setTable = "customers";
}

public void setProductsTable() {
    tableView.getColumns().clear();
    
    // Product Number
    TableColumn<Product, Integer> productNumberColumn = new TableColumn<>("Prod #");
    productNumberColumn.setPrefWidth(70);
    productNumberColumn.setCellValueFactory(new PropertyValueFactory<>("productNumber"));

    // Product Name
    TableColumn<Product, String> productNameColumn = new TableColumn<>("Product Name");
    productNameColumn.setPrefWidth(230);
    productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

    tableView.setItems(getProducts());
    tableView.getColumns().addAll(productNumberColumn, productNameColumn);
    tableView.getSortOrder().add(productNameColumn);
    setTable = "products";
}

private ObservableList<Customer> getCustomers() {
    customers = new CustomerDatabase();
    customers.readCustomerFile();
    ObservableList<Customer> observableCustomers = FXCollections.observableArrayList(customers.getCustomerList());
    return observableCustomers;
}

private ObservableList<Product> getProducts() {
    products = new ProductDatabase();
    products.readProductFile();
    ObservableList<Product> observableProducts = FXCollections.observableArrayList(products.getProductList());
    return observableProducts;
}

public TableView getTableView() {
    return tableView;
}

public String getSetTable() {
    return setTable;
}
*/
}