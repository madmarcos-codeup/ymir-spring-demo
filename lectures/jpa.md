explain jpa

explain orm
    the important parts are the domain object (entity) and the db table (where the data persist)
show the Dog pojo

add pom dependencies

add the application.properties info 
-   IMPORTANT: check .gitignore

add @Entity to Dog
-   run the project first and see what happens
-   map the class to a specific table using @Table(name="dogs") under @Entity
-   add @Id  and @GeneratedValue(strategy = GenerationType.IDENTITY) to a primary key field like id

add @Column annotation to the name field
    - run it and see what happens
    - add name, length, nullable, and unique to the name field

try an ownerName field if need be


NOW: how to use JPA to get a record from the db and give us an object from the data?
Repositories

declare the repository
    public interface AdRepository extends JpaRepository<Ad, Long> {
        explain the parts

inject it into the DogController
    manually specify constructor
        explain injection
    see if Lombok can do it for you

in a controller, use the repository to fetch all dogs
    sout it
    then send it to the view

show getById, save, and delete
    make controller endpoints for these

NEXT create some custom queries for findByName and maybe findByOwnerName
    make a controller endpoint. prolly use query parameters for name and ownername
    show used in the controller

make a custom query use @Param
    @Query("from Dog d where d.name like %:dogName%")
    List<Dog> searchByName(@Param("dogName") String dogName);

explain spring.jpa.hibernate.ddl-auto=update
    try to show consistency issues (renaming an object field, deleting an object field)