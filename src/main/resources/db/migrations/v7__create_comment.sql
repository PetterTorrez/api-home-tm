CREATE TABLE comment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text TEXT NOT NULL,
    task_id INT NOT NULL,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_task FOREIGN KEY (task_id) REFERENCES task(id),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user(id)
);
