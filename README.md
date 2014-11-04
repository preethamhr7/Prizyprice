Prizyprice
==========
Please see the config.properties file for the db user name and password.

And If you want to change the formula to calculate the ideal price of the product.

I have divided the formula to three parts.

you can specify the three values in config.properties and use it dynamically.

					The Prizy Pricer


1. Please dump the sql file in the Mysql database.

 Please deploy the war file tomcat server.

Formula to calculate the Ideal Price.

I have divided the formula into three parts

Three fields are dynamic now.
Number of Highest values removal.
Number of Lowest values removal.
Adding percentage to average.

Please make sure about the number of entries you will provide before calculating the ideal price should be more than the sum of number of highest removal and number of lowest removal.

To get the list of all the products. I have given a api. Which will json as a result.

Home page has options to select

Product Loader - To enter the prices
Product Lister - To get all the products
Product Viewer - To get the ideal price for the particular product by giving Bar Code Id as input.
