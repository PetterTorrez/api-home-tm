CREATE TABLE task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    points INT NOT NULL,
    status ENUM('PENDING','COMPLETED','APPROVED','REJECTED') NOT NULL DEFAULT 'PENDING',
    created_by INT NOT NULL,
    group_id INT NOT NULL,
    CONSTRAINT fk_task_user FOREIGN KEY (created_by) REFERENCES user(id),
    CONSTRAINT fk_task_group FOREIGN KEY (group_id) REFERENCES user_group(id),
    CONSTRAINT chk_task_points CHECK (points BETWEEN 1 AND 5)
);
