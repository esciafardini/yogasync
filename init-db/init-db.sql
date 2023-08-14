CREATE EXTENSION "uuid-ossp";

CREATE TABLE teachers (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    description VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    teacher_id UUID REFERENCES teachers(id),
    admin BOOLEAN NOT NULL
);

CREATE TABLE recurring_events (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    start_time time NOT NULL,
    end_time time NOT NULL,
    description VARCHAR(555),
    teacher_id UUID REFERENCES teachers(id),
    days_of_week text[]
);

CREATE TABLE single_events (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    starts date,
    ends date,
    description VARCHAR(555),
    teacher_id UUID REFERENCES teachers(id)
);


INSERT INTO teachers (description, full_name)
VALUES ('a powerful zen master who loves mobility work', 'Keith Golden');

INSERT INTO users (username, password, teacher_id, admin)
VALUES ('keithgolden', 'monarch77', (select id from teachers where full_name = 'Keith Golden'), false);

INSERT INTO users (username, password, admin)
VALUES ('toddcage', 'password', TRUE);

INSERT INTO recurring_events (start_time, end_time, teacher_id, days_of_week)
VALUES
    ('9:00:00', '10:30:00', (select id from teachers where full_name = 'Keith Golden'), ARRAY['1', '3']),
    ('13:30:00', '15:00:00', (select id from teachers where full_name = 'Keith Golden'), ARRAY['2', '3']),
    ('10:00:00', '11:30:00', (select id from teachers where full_name = 'Keith Golden'), ARRAY['7']);
