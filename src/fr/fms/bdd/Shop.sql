
  --  base de donneé 
DROP DATABASE IF EXISTS Shop;
CREATE DATABASE Shop;
USE Shop;



 -- table Articles  
DROP TABLE IF EXISTS T_Articles;
CREATE TABLE T_Articles (
IdArticle   int(4) PRIMARY KEY AUTO_INCREMENT,
Description  varchar(30) NOT NULL ,
Brand  varchar(30) NOT NULL ,
UnitaryPrice float(8) NOT NULL 
) ENGINE = InnoDB;

-- inserer des articles 
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('Souris','Logitoch',65);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('Clavier','Microhard',49.5);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('Systeme d''exploitation','Fenetres Vistouille',150);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('Tapis souris','Chapeau Bleu',5);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('Cle Usb 8','Syno',8);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('Laptop','PH',1199);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('CD x 500','CETME',250);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('DVD-R x 100','CETME',99);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('DVD-R x 100','CETME',105);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('Batterie Laptop','PH',80);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('Casque audio','Syno',105);
INSERT INTO T_Articles (Description,Brand,UnitaryPrice) VALUES ('Webcam','Logitoch',755);




-- table categories 
DROP TABLE IF EXISTS T_Categories;
CREATE TABLE T_Categories (
IdCategory   int(4) PRIMARY KEY AUTO_INCREMENT,
CatName varchar(30) not null ,
Description  varchar(100) NOT NULL 
) ENGINE = InnoDB;

-- changer la table articles
ALTER TABLE T_Articles ADD COLUMN IdCategory INT(4);
ALTER TABLE T_Articles ADD FOREIGN KEY(IdCategory) REFERENCES T_Categories(IdCategory);


-- inserer des categories 
INSERT INTO T_Categories (CatName, Description) VALUES ('Materiel info','Indispensables à un pc');
INSERT INTO T_Categories (CatName, Description) VALUES ('Logiciel','SE Antivir,Ide..');
INSERT INTO T_Categories (CatName, Description) VALUES ('PC','Laptop et micro ordi');

-- ajouter une categorie à un article 
UPDATE T_Articles set IdCategory=1	 where idArticle IN  ( 1,2,4,7,8)  ;
UPDATE T_Articles set IdCategory=2	 where idArticle=3  ;
UPDATE T_Articles set IdCategory=3	 where idArticle IN  ( 6,12)  ;

-- creer la table user 
DROP TABLE IF EXISTS T_Users;
CREATE TABLE T_Users (
IdUser int(4) PRIMARY KEY AUTO_INCREMENT,
Login varchar(20) NOT NULL,
Password varchar(20) NOT NULL
) ENGINE = InnoDB;

-- insérer des utilisateurs
INSERT INTO T_Users (Login,Password) VALUES ('Toto_le_poto','totocemoi');
INSERT INTO T_Users (Login,Password) VALUES ('pipo','pipocemoi');
INSERT INTO T_Users (Login,Password) VALUES ('relou','reloucepasmoi');


-- creer la table client 
DROP TABLE IF EXISTS T_Customers;
CREATE TABLE T_Customers (
IdCustomer int(4) PRIMARY KEY AUTO_INCREMENT,
LastName varchar(20) NOT NULL,
FirstName varchar(20) NOT NULL,
Email varchar(20) NOT NULL,
Adress varchar(20) NOT NULL,
IdUser int(4),
FOREIGN KEY(IdUser) REFERENCES T_Users(IdUser)
) ENGINE = InnoDB;


-- inserer des clients 
INSERT INTO T_Customers (LastName,FirstName,Email,Adress,IdUser) VALUES ('Mehdioui','Ayyoub','myEmail@yahoo.fr','rue de la cata 31000',3);
INSERT INTO T_Customers (LastName,FirstName,Email,Adress,IdUser) VALUES ('Doe','John','Doe@yahoo.fr','rue marcel 31200',1);

-- creer la table commande
DROP TABLE IF EXISTS T_Orders;
CREATE TABLE T_Orders (
  IdOrder int(4) PRIMARY KEY AUTO_INCREMENT,
  OrderDate Date NOT NULL,
  IdCustomer int (4),
  FOREIGN KEY(IdCustomer)  REFERENCES T_Customers(IdCustomer)
)ENGINE = InnoDB;



-- creer la table commande item
DROP TABLE IF EXISTS T_OrderItems;
CREATE TABLE T_OrderItems (
  IdOrderItem int(4) PRIMARY KEY AUTO_INCREMENT,
  IdArticle int (4),
  FOREIGN KEY(IdArticle) REFERENCES T_Articles(IdArticle),
  Quantity int(4) NOT NULL,
  IdOrder int (4),
  FOREIGN KEY(IdOrder) REFERENCES T_Orders(IdOrder)
)ENGINE = InnoDB;