### Purpose of this repo

+ Demo transaction rollback with checked and unchecked exception

+ Show how to @Transactional work (@Transactional workflow)

+ Self-invocation in @Transactional

+ Demo transaction propagation: required, requiresNew

+ Demo transaction isolation level

+ Demo read only case in @Transactional

+ Demo case: Redundant @Transactional such as below (assume we are using Spring JPA)

    ```java
        @Transactional
        public void changeName(long id, String name) {
            User user = userRepository.getById(id);
            user.setName(name);
            userRepository.save(user);         // redundant code
        }
    ```