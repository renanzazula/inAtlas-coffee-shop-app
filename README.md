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
To execute the application we can run the command line bellow with one of profiles (local or mySql).

- local (we use H2 memory database)

`mvnw spring-boot:run -Dmaven.test.skip=true -Dspring-boot.run.profiles=local`
- mySql (we use local database on docker) configuration below:

`docker run -p 3307:3306 --name standard-my-sql -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server:latest` 
 

The application when starts will create the database and insert a list of product bellow 

    - Product Name 
    - Latte $ 5.3 
    - Espresso $ 4
    - Sandwich $10.10
    - Milk $ 1
    - Cake Slice $ 9
    - Cappuccino $ 8
    - Tea $ 6.1

 - To check the database access the URL below:

`http://localhost:8080/standard-coffeeShopApp/h2-console`

- User Name: root
- Password: root


 - To access the functionalities we can check the swagger at URL below: 

`http://localhost:8080/standard-coffeeShopApp/swagger-ui/#/`
