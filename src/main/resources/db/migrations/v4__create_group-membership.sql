CREATE TABLE group_membership (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    group_id INT NOT NULL,
    role_id INT NOT NULL,
    CONSTRAINT uq_group_membership UNIQUE (user_id, group_id),
    CONSTRAINT fk_group_membership_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_group_membership_group FOREIGN KEY (group_id) REFERENCES `group`(id),
    CONSTRAINT fk_group_membership_role FOREIGN KEY (role_id) REFERENCES role(id)
);
