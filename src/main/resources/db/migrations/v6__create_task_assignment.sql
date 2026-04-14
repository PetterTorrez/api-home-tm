CREATE TABLE task_assignment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_id INT NOT NULL,
    assigned_to INT NOT NULL,
    CONSTRAINT uq_task_assignment UNIQUE (task_id, assigned_to),
    CONSTRAINT fk_task_assignment_task FOREIGN KEY (task_id) REFERENCES task(id),
    CONSTRAINT fk_task_assignment_user FOREIGN KEY (assigned_to) REFERENCES user(id)
);
