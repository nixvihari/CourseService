# Course Service

### Spark Learning CloudLMS Course Service that handles

- Courses Management
    - Maintain Course Details & records
    - Create, get, update and delete courses
    - Course Enrollment
- Course Material
    - Add, get or delete course material
    - Maintain course material records and links

### Course Service API Index

#### Course Controller

- /api/courses - BASE API URL [GET]
- /enrolledCourses        [GET]
- /addCourse
- /{id}                 [GET]
- /updateCourse/{id}    [PUT]
- /deleteCourse/{id}    [DELETE] // fix needed for url in controller
- /enroll/{courseId}    [POST]


