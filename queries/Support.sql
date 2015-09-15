CREATE TABLE Support
(
    Id INT 
        NOT NULL 
        PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    Software VARCHAR(200),
    Os VARCHAR(200),
    Issue VARCHAR(200),
    AccountId INT REFERENCES Accounts(Id)
)