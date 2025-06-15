-- liquibase formatted sql

-- changeset sekoraga:20250615-01
CREATE TABLE IF NOT EXISTS c_exercise_video (
    video_id bigserial PRIMARY KEY,
    category_id int8 NULL,
    title varchar(100) NOT NULL,
    description varchar(255) NULL,
    youtube_url varchar(255) NOT NULL,
    thumbnail_url varchar(255) NULL,
    CONSTRAINT c_exercise_video_category_id_fkey FOREIGN KEY (category_id) REFERENCES c_exercise_category(category_id)
);
