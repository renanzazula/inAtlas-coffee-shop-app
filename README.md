## Standard Coffee Shop - API

### Functionalities
  - Manage products.
    - Add 
    - Update
    - Delete 
    - Get by ID
    - Get all
  - Manage promotions(Discount).
    - Add 
    - Update
    - Delete
    - Get by ID
    - Get all 
  - Manage Order.
    - openOrder
    - addProduct
    - removeProduct
    - closeOrder
    - printOrder
    - reopenOrder
    - delete
    - getAllOrders
    - getOrderById
****
### Execute the application
To execute the application we can run the command line bellow with local profile    

`mvnw spring-boot:run -Dmaven.test.skip=true -Dspring-boot.run.profiles=local`

The application when starts will create H2 memory database and insert a list of product bellow 

    - Product Name 
    - Latte $ 5.3 
    - Espresso $ 4
    - Sandwich $10.10
    - Milk $ 1
    - Cake Slice $ 9
    - Capuccino $ 8
    - Tea $ 6.1

 - To check the database access the URL below:

`http://localhost:8080/standard-coffeeShopApp/h2-console`

- User Name: root
- Password: root


 - To access the functionalities we can check the swagger at URL below: 

`http://localhost:8080/inAtlas-coffeeShopApp/swagger-ui/#/`

