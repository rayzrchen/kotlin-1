CREATE TABLE PERSON (
  ID    RAW(16) DEFAULT RANDOM_UUID() PRIMARY KEY,
  FIRST VARCHAR(100),
  LAST  VARCHAR(100)
)