
-- Courses table
CREATE TABLE courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    course_duration INT NOT NULL,
    created_by VARCHAR(36) NOT NULL -- UUID of teacher
);

-- Learning objectives table
CREATE TABLE course_learning_objectives (
    course_id BIGINT NOT NULL,
    learning_objectives VARCHAR(255),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- Enrollment table
CREATE TABLE enrolled (
    enrollment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT NOT NULL,
    student_id VARCHAR(36) NOT NULL, -- UUID of student
    enrolled_at TIMESTAMP NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id)
);
