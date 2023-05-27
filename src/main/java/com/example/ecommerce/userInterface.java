package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class userInterface {
    GridPane loginPage;
    HBox  Header;

    HBox footerBar;

    Button signIn;

    Label welcomeLabel;

    VBox body;

    Customer loggedInCustomer;

    ProductList productList = new ProductList();
    VBox productPage;

    VBox prodPage;



    Button OrderButton = new Button("Place Order");

    ObservableList<Product>items= FXCollections.observableArrayList();
     public BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPrefSize(800,600);
        root.setCenter(loginPage);
        root.setTop(Header);

        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
         root.setCenter(body);
        productPage= productList.getAllProd();

        body.getChildren().add(productPage);

        root.setBottom(footerBar);

        return root;
    }
    public userInterface(){
         createLoginPage();
         createHeader();
         createfooterBar();
    }

   private void createLoginPage(){
       Text userNameText = new Text("User Name");
       Text passwordText= new Text("Password");

       TextField userName = new TextField("er.moiz2102@gmail.com");
       userName.setPromptText("Enter your username here");
       PasswordField password = new PasswordField();
       password.setText("abc123");
       password.setPromptText("Enter your password here");

       Label messageLabel= new Label("Hi");
       Button login= new Button("Login");

       loginPage = new GridPane();
       //loginPage.setStyle("-fx-background-color:grey;");
       loginPage.setAlignment(Pos.CENTER);
       loginPage.setHgap(10);
       loginPage.setVgap(10);


       loginPage.add(userNameText,0,0);
       loginPage.add(userName,1,0);
       loginPage.add(passwordText,0,1);
       loginPage.add(password,1,1);
       loginPage.add(messageLabel,0,2);
       loginPage.add(login,1,2);

       login.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               String name = userName.getText();
               String pass = password.getText();
               Login login = new Login();
               loggedInCustomer= login.customerLogin(name,pass);
               if(loggedInCustomer!=null){
                   messageLabel.setText("Welcome "+ loggedInCustomer.getName());
                   welcomeLabel.setText("Welcome!!! "+ loggedInCustomer.getName());
                   Header.getChildren().add(welcomeLabel);
                   body.getChildren().clear();
                   body.getChildren().add(productPage);
               }
               else{
                   messageLabel.setText("Login Failed !! please give correct user name and password");
               }
           }
       });




    }
    private void createHeader(){
         Button homeButton = new Button();
         Image image = new Image("C:\\Users\\dell\\IdeaProjects\\mini\\E-Commerce\\src\\img.png");
        ImageView imageView= new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(60);
        homeButton.setGraphic(imageView);
         TextField searchBar = new TextField();
         searchBar.setPromptText("Search here");
         searchBar.setPrefWidth(280);
         Button searchButton = new Button("Search");
          signIn= new Button("Sign In");
          welcomeLabel= new Label();

          Button cart = new Button("Cart");

          Button orderButton = new Button("Orders");




         Header = new HBox(10);
         Header.setPadding(new Insets(10));

         Header.setAlignment(Pos.CENTER);
         Header.getChildren().addAll(homeButton,searchBar,searchButton,signIn,cart,orderButton);

         signIn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 body.getChildren().clear();
                 body.getChildren().add(loginPage);
                 Header.getChildren().remove(signIn);
             }
         });
         cart.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 body.getChildren().clear();
                 VBox prodPage= productList.getProductsInCart(items);
                 prodPage.setAlignment(Pos.CENTER);
                 prodPage.setSpacing(10);
                 prodPage.getChildren().add(OrderButton);
                 body.getChildren().add(prodPage);
                 footerBar.setVisible(false);
             }
         });
         OrderButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {

                 if(items==null){
                     showDisplay("Please add some products in the cart to place order!");
                     return;

                 }
                 if(loggedInCustomer==null){
                     showDisplay("Please login first,to place the Order");
                 }
                 int count = Order.placeMultipleOrder(loggedInCustomer,items);
                 if(count!=0)
                     showDisplay("Order for "+count+" products placed Successfully!!");
                 else showDisplay("Order is Failed!!!");


             }
         });
         homeButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 body.getChildren().clear();
                 body.getChildren().add(productPage);
                 footerBar.setVisible(true );
                 if(loggedInCustomer==null&&Header.getChildren().indexOf(signIn)==-1)
                     Header.getChildren().add(signIn);
             }
         });



    }
    private void createfooterBar(){

        Button buyNow = new Button("BuyNow");
        Button addtoCart = new Button("Add to Cart");

        footerBar = new HBox(10);
        footerBar.setPadding(new Insets(10));

        footerBar.setAlignment(Pos.CENTER);
        footerBar.getChildren().addAll(buyNow,addtoCart);
        buyNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product= productList.getSelectedProduct();
                if(product==null){
                    showDisplay("Please select a product first, to place an order");
                    return;

                }
                if(loggedInCustomer==null){
                    showDisplay("Please login first,to place the Order");
                }
                boolean status = Order.placeOrder(loggedInCustomer,product);
                if(status)
                    showDisplay("Order placed Successfully!!");
                else showDisplay("Order is Failed!!!");
            }
        });

        addtoCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product= productList.getSelectedProduct();
                if(product==null){
                    showDisplay("Please select a product first, to add to cart");
                    return;

                }
                items.add(product);
                showDisplay("Selected Item has been added successfully added to the cart");

            }
        });


            }
            private void showDisplay(String message){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setHeaderText(null);
         alert .setContentText(message);
         alert.setTitle("Message");
         alert.showAndWait();
            }



    }

