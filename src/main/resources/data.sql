
-- Insert sample courses
INSERT INTO courses (title, description, created_by) VALUES
('Java Basics', 'Introduction to Java programming', '550e8400-e29b-41d4-a716-446655440000'),
('Spring Boot Fundamentals', 'Learn how to build REST APIs using Spring Boot', '550e8400-e29b-41d4-a716-446655440001'),
('Advanced Java', 'Deep dive into Java concurrency and streams', '550e8400-e29b-41d4-a716-446655440002'),
('Microservices Architecture', 'Design and implement microservices using Spring Cloud', '550e8400-e29b-41d4-a716-446655440003'),
('Database Design', 'Learn relational database design and normalization', '550e8400-e29b-41d4-a716-446655440004'),
('React for Beginners', 'Build interactive UIs using React.js', '550e8400-e29b-41d4-a716-446655440005'),
('Angular Essentials', 'Master Angular framework basics', '550e8400-e29b-41d4-a716-446655440006'),
('Python for Data Science', 'Learn Python libraries for data analysis', '550e8400-e29b-41d4-a716-446655440007'),
('Machine Learning Basics', 'Introduction to ML algorithms and concepts', '550e8400-e29b-41d4-a716-446655440008'),
('Docker & Kubernetes', 'Containerization and orchestration fundamentals', '550e8400-e29b-41d4-a716-446655440009');

-- Insert sample enrollments
INSERT INTO enrolled (course_id, student_id, enrolled_at) VALUES
(1, '660e8400-e29b-41d4-a716-446655440010', CURRENT_TIMESTAMP),
(1, '660e8400-e29b-41d4-a716-446655440011', CURRENT_TIMESTAMP),
(2, '660e8400-e29b-41d4-a716-446655440012', CURRENT_TIMESTAMP),
(2, '660e8400-e29b-41d4-a716-446655440013', CURRENT_TIMESTAMP),
(3, '660e8400-e29b-41d4-a716-446655440014', CURRENT_TIMESTAMP),
(4, '660e8400-e29b-41d4-a716-446655440015', CURRENT_TIMESTAMP),
(5, '660e8400-e29b-41d4-a716-446655440016', CURRENT_TIMESTAMP),
(6, '660e8400-e29b-41d4-a716-446655440017', CURRENT_TIMESTAMP),
(7, '660e8400-e29b-41d4-a716-446655440018', CURRENT_TIMESTAMP),
(8, '660e8400-e29b-41d4-a716-446655440019', CURRENT_TIMESTAMP),
(9, '660e8400-e29b-41d4-a716-446655440020', CURRENT_TIMESTAMP),
(10, '660e8400-e29b-41d4-a716-446655440021', CURRENT_TIMESTAMP);
