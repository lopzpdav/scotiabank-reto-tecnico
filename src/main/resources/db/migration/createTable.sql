CREATE TABLE IF NOT EXISTS student
(
    id  INT  NOT NULL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    status VARCHAR(1) NOT NULL,
    age INT NOT NULL,
    created  timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP
    );