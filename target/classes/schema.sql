DROP TABLE IF EXISTS TBL_EMPLOYEES;
 
CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

CREATE TABLE `vehicle_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `vehcile_name` varchar(255) DEFAULT NULL,
  `vehcile_no` varchar(255) DEFAULT NULL,
  `vehicle_model` varchar(255) DEFAULT NULL,
  `employee_entity_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK937jqxnxndkgnl8s33ip02tog` (`employee_entity_id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci