DROP TABLE IF EXISTS UrlInfo;
 
CREATE TABLE UrlInfo (
  id INT PRIMARY KEY,
  baseValue VARCHAR(7) NOT NULL,
  url VARCHAR(250) NOT NULL
  
);

INSERT INTO UrlInfo (id, baseValue, url) VALUES (6785443, '34mD', 'https://found.ee/super');