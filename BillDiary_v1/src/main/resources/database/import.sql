--create table if not exists user2(userid int,username varchar(20),password varchar(20),role varchar(20),primary key(userid));
--insert into user2 values(1,'Gajanan','Gajanan','admin');
--insert into user2 values(2,'Gajanan','Gajanan','admin');
create table if not exists user(id int, username varchar(20),password varchar(20),role varchar(20),primary key(id));
--insert into user values(1,'Gajanan','Gajanan','admin');
--create SEQUENCE idOfProduct1 increment by 1 start with 101;
create table if not exists product
								(
									product_id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 100, INCREMENT BY 1)primary key
									,product_name varchar(30)
									,product_category varchar(30)
									,description varchar(30)
									,wholesale_price varchar(30)
									,retail_price varchar(30)
									,wholeSale_GST varchar(30)
									,wholeSale_GST_percentage double
									,retail_GST varchar(30)
									,retail_GST_percentage double
									,discount varchar(30)
									,stock varchar(30)
								);

								
								
--insert into product(
--						 product_id
--						,product_name
--						,description
--						,wholesale_price
--						,retail_price
--						,discount
--						,stock
--					) 
--					values
--					(
--						null
--						,'mobile'
--						,'0'
--						,'apple'
---						,'30000'
--						,'5'
--						,'26500'
--					);
										
--insert into product values(105,'Mobile','Apple',30000,26500.50,0,5);
--insert into product values(102,'Camera','Sony',35000,29000.50,5,5);
--insert into product values(103,'Mobile','Samsung',20000,18000.50,0,5);
--insert into product values(104,'Laptop','Apple',65000,60000.50,0,5);

--create table if not exists customer(customer_id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 100,INCREMENT BY 1) primary key,customer_name varchar(20));
create table if not exists customer
					(
						 customer_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1)primary key
						,customer_name varchar(30)
						,customer_group varchar(30)
						,address varchar(30)
						,email_id varchar(30)
						,mobile_no varchar(30)
						,addtional_info varchar(30)
						,regDate Date
						,city varchar(30)
						,state varchar(30)
						,country varchar(30)
						,zipCode varchar(30)
						,anniversary_Date date
						,birth_Date date
						
					);

--insert into customer(customer_id,customer_name,address,mobile_no,city,country) values(101,'Gajanan','kfc baner','7709635664','pune','India');
--insert into customer(customer_id,customer_name,address,mobile_no,city,country) values(102,'Harshal','kfc baner','7709635664','pune','India');
--insert into customer(customer_id,customer_name) values(IDENTITY()+1,'Gajanan');
--insert into customer(customer_id,customer_name) values(IDENTITY()+1,'Gajanan');

--insert into customer values(null,'kfc baner','pune','India','Vidya','gajanangai','7709656986');
--insert into customer values(null,'kfc baner','Baramati','India','Gajanan','gajanangaikwad999@gmail.com','7709635664');

create table if not exists shopDetails
					(
						 shop_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1)primary key
						,shop_name varchar(30)
						,email_id varchar(30)
						,address varchar(30)
						,country varchar(30)
						,website varchar(30)
						,city varchar(30)
						,state varchar(30)
						,pincode varchar(30)
						,phone varchar(30)
						,GSTIN varchar(30)
						,logo varchar(30)						
					);					
					
create table if not exists supplier
					(
						supplier_id  INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1)primary key
						,supplier_name varchar(30)
						,supplier_company varchar(30)
						,supplier_gov_id varchar(30)
						,supplier_emailid varchar(30)
						,supplier_phoneno varchar(30)
						,supplier_mobileno varchar(30)
						,supplier_faxno varchar(30)
						,supplier_website varchar(30)
						,supplier_unpaid_balance double
						,supplier_asofdate date 
						,supplier_account_no varchar(30) 
						,supplier_tax_reg_no varchar(30)
						,supplier_billing_rate double
						,supplier_other varchar(30)
						,address_id BIGINT 
						,foreign key (address_id) references address(address_id)
					);
					
create table if not exists address
					(
						 address_id BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1)primary key
						,street1 varchar(30)
						,street2 varchar(30)
						,city varchar(30)
						,state varchar(30)
						,country varchar(30)
						,zipcode varchar(30)
					);
					
create table if not exists invoice
					(
						 invoice_id BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1)primary key
						,customer_id integer
						,product_sale_qty integer
						,final_Amount double
						,paid_Amount double
						,amount_Due double
						,invoice_Date date
						,invoice_Due_Date date
						,last_Amount_Paid_Date date
						,foreign key(customer_id) references customer(customer_id)
					);
					

					
					