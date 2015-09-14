CREATE TABLE Users
(
    Id INT 
        NOT NULL 
        PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1)
    FirstName VARCHAR(200),
    LastName VARCHAR(200),
    Email VARCHAR(200),
    Phone VARCHAR(200),
    Software VARCHAR(200),
    Os VARCHAR(200),
    Issue VARCHAR(200)
)