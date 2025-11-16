CREATE DATABASE IF NOT EXISTS meritu;
USE meritu;

-- =================================================================================================
-- Tables
-- =================================================================================================

CREATE TABLE team (
    id BIGINT NOT NULL AUTO_INCREMENT,
    is_active BOOLEAN NOT NULL,
    name VARCHAR(255),
    manager_id BIGINT UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE employee (
    id BIGINT NOT NULL AUTO_INCREMENT,
    balance INTEGER,
    email VARCHAR(255),
    employee_role INTEGER,
    is_active BOOLEAN NOT NULL,
    name VARCHAR(255),
    surname VARCHAR(255),
    team_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE goal (
    id BIGINT NOT NULL AUTO_INCREMENT,
    update_date DATETIME(6),
    is_achieved BOOLEAN NOT NULL,
    is_active BOOLEAN NOT NULL,
    name VARCHAR(255),
    reward_credits INTEGER,
    team_goal_id BIGINT,
    DTYPE VARCHAR(31) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE individual_goal (
    reward_team_points INTEGER,
    employee_id BIGINT,
    id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE team_goal (
    target_amount_goal_points INTEGER,
    reward_team_points INTEGER,
    team_id BIGINT,
    id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE app_user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    is_active BOOLEAN NOT NULL,
    password VARCHAR(255),
    user_role INTEGER,
    username VARCHAR(255),
    employee_id BIGINT UNIQUE,
    PRIMARY KEY (id)
);

-- =================================================================================================
-- Foreign Keys
-- =================================================================================================

ALTER TABLE team
    ADD CONSTRAINT FK_team_employee
    FOREIGN KEY (manager_id)
    REFERENCES employee(id);

ALTER TABLE employee
    ADD CONSTRAINT FK_employee_team
    FOREIGN KEY (team_id)
    REFERENCES team(id);

ALTER TABLE goal
    ADD CONSTRAINT FK_goal_goal
    FOREIGN KEY (team_goal_id)
    REFERENCES goal(id);

ALTER TABLE individual_goal
    ADD CONSTRAINT FK_individual_goal_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee(id);

ALTER TABLE individual_goal
    ADD CONSTRAINT FK_individual_goal_goal
    FOREIGN KEY (id)
    REFERENCES goal(id);

ALTER TABLE team_goal
    ADD CONSTRAINT FK_team_goal_team
    FOREIGN KEY (team_id)
    REFERENCES team(id);

ALTER TABLE team_goal
    ADD CONSTRAINT FK_team_goal_goal
    FOREIGN KEY (id)
    REFERENCES goal(id);

ALTER TABLE app_user
    ADD CONSTRAINT FK_app_user_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee(id);
