CREATE TABLE `user_group` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_by INT NOT NULL,
    CONSTRAINT uq_group_name_creator UNIQUE (name, created_by),
    CONSTRAINT fk_group_created_by FOREIGN KEY (created_by) REFERENCES user(id)
);
