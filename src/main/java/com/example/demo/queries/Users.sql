
create table sharad.Users
(
    user_id          INT                                          NOT NULL primary key auto_increment,
    user_firstname   varchar(255)                                 NOT  NULL,
    user_lastname    varchar(255)                                 NULL,
    user_type        enum ('Admin', 'Normal', 'Special') NOT NULL default 'Normal',
    email            varchar(255)                                 NOT NULL,
    password         varchar(255)                                 NOT NULL,
    active_status    TINYINT(1) default 1 						  NOT NULL,
    approved_status  TINYINT(1) default 1                         NOT NULL,
    latest_updated   timestamp default CURRENT_TIMESTAMP          NOT NULL,
    constraint email
        unique (email)
);