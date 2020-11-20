create table sharad.Gesture(
        config_id INT NOT NULL primary key auto_increment,
        last_logged_in timestamp default CURRENT_TIMESTAMP NOT NULL,
         gesture_user_id default 0 int NOT NULL,
        FOREIGN KEY (gesture_user_id) REFERENCES sharad.Users(user_id),
        config_json_data MEDIUMTEXT
);