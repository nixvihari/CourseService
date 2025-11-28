
-- Drop tables if they exist
DROP TABLE IF EXISTS enrolled;
DROP TABLE IF EXISTS courses;

-- Create courses table
CREATE TABLE courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    created_by CHAR(36) NOT NULL -- UUID stored as string
);

-- Create enrolled table
CREATE TABLE enrolled (
    enrollment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT NOT NULL,
    student_id CHAR(36) NOT NULL, -- UUID stored as string
    enrolled_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);
