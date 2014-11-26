DROP TABLE users CASCADE;
DROP TABLE subscriptions CASCADE;
DROP TABLE messages CASCADE;
DROP TABLE boards CASCADE;
DROP TABLE tags;
DROP TABLE user_permissions;
CREATE TABLE users (
    username VARCHAR(30) PRIMARY KEY,
    passwd  VARCHAR(30) NOT NULL
);

CREATE TABLE boards(
    bid SERIAL PRIMARY KEY,
    name VARCHAR(127) NOT NULL,
    motd TEXT,
    passwd VARCHAR(30) default NULL,
    visible int default 1,
	topic VARCHAR(127) default 'No Topic'
);

CREATE TABLE subscriptions (
    username VARCHAR(30) REFERENCES users(username) ON DELETE CASCADE,
    bid INT REFERENCES boards(bid) ON DELETE CASCADE,
    type INT default 1,
    PRIMARY KEY (bid, username)
);	


CREATE TABLE tags (
    bid INT REFERENCES boards(bid) ON DELETE CASCADE,
    tag VARCHAR(30),
    PRIMARY KEY (bid, tag)
);
    
CREATE TABLE messages (
    mid SERIAL PRIMARY KEY,
    bid INT REFERENCES boards(bid) ON DELETE CASCADE,    
    date_posted TIMESTAMP,
    username VARCHAR(30) REFERENCES users(username),
    visibility INT default 1,    
    contents TEXT
);

CREATE TABLE user_permissions(
    username VARCHAR(30) REFERENCES users(username) ON DELETE CASCADE PRIMARY KEY,
    permission INT default 0
);


	

