CREATE TABLE IF NOT EXISTS "user"(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    mobile VARCHAR(255),
    pan VARCHAR(255),
    managerId INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL,
    isActive BOOLEAN DEFAULT TRUE
);