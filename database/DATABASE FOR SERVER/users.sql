DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS subscriptions CASCADE;
DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS boards CASCADE;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS user_permissions;
CREATE TABLE users (
    username VARCHAR(30) PRIMARY KEY,
    passwd  VARCHAR(30)
);

CREATE TABLE boards(
    bid SERIAL PRIMARY KEY,
    name VARCHAR(127),
    motd TEXT,
    passwd VARCHAR(30) default NULL,
    visible int default 1

);

CREATE TABLE subscriptions (
    username VARCHAR(30) references users(username) ON DELETE CASCADE,
    bid INT references boards(bid) ON DELETE CASCADE,
    type INT default 1,
    PRIMARY KEY (bid, username)
);	


CREATE TABLE tags (
    bid INT references boards(bid) ON DELETE CASCADE,
    tag VARCHAR(30),
    PRIMARY KEY (bid, tag)
);
    
CREATE TABLE messages (
    mid SERIAL PRIMARY KEY,
    bid INT references boards(bid) ON DELETE CASCADE,    
    date_posted TIMESTAMP,
    username VARCHAR(30) references users(username),
    visibility INT default 1,    
    contents TEXT
);

CREATE TABLE user_permissions(
    username VARCHAR(30) references users(username) ON DELETE CASCADE PRIMARY KEY,
    permission INT default 0
);


	

