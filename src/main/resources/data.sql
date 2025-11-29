
-- Insert courses with teacher UUIDs
INSERT INTO courses (title, description, course_duration, created_by)
VALUES
('Java Basics', 'Learn fundamentals of Java programming', 30, '111e8400-e29b-41d4-a716-446655440000'),
('Spring Boot Essentials', 'Build REST APIs using Spring Boot', 45, '222e8400-e29b-41d4-a716-446655440001'),
('React for Beginners', 'Learn React fundamentals and hooks', 25, '333e8400-e29b-41d4-a716-446655440002');

-- Insert learning objectives for each course
INSERT INTO course_learning_objectives (course_id, learning_objectives) VALUES
(1, 'Variables'),
(1, 'Loops'),
(1, 'Methods'),
(1, 'OOP'),
(1, 'Collections'),
(1, 'Exceptions'),
(2, 'Spring Core'),
(2, 'Spring MVC'),
(2, 'JPA'),
(2, 'REST'),
(2, 'Security'),
(2, 'Testing'),
(3, 'Components'),
(3, 'Props'),
(3, 'State'),
(3, 'Hooks'),
(3, 'Routing'),
(3, 'Forms');

-- Insert enrollments with student UUIDs
INSERT INTO enrolled (course_id, student_id, enrolled_at) VALUES
(1, '550e8400-e29b-41d4-a716-446655440010', CURRENT_TIMESTAMP),
(1, '550e8400-e29b-41d4-a716-446655440011', CURRENT_TIMESTAMP),
(2, '550e8400-e29b-41d4-a716-446655440012', CURRENT_TIMESTAMP),
(2, '550e8400-e29b-41d4-a716-446655440013', CURRENT_TIMESTAMP),
(2, '550e8400-e29b-41d4-a716-446655440014', CURRENT_TIMESTAMP),
(3, '550e8400-e29b-41d4-a716-446655440015', CURRENT_TIMESTAMP);
