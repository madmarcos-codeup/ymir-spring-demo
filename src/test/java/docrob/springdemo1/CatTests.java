package docrob.springdemo1;

import docrob.springdemo1.models.Cat;
import docrob.springdemo1.models.PetOwner;
import docrob.springdemo1.models.Toy;
import docrob.springdemo1.repositories.CatRepository;
import docrob.springdemo1.repositories.PetOwnerRepository;
import docrob.springdemo1.repositories.ToyRepository;
import docrob.springdemo1.services.S3Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@Transactional(rollbackFor = { SQLException.class })
@Rollback(false)
class CatTests {

    @Autowired
    private CatRepository catDao;

    @Autowired
    private PetOwnerRepository ownerDao;

    @Autowired
    private ToyRepository toyDao;

    @Autowired
    private S3Service s3Service;

    @Test
    public void fetchCats() {
        List<Cat> cats = catDao.findAll();
        System.out.println(cats);
    }

    @Test
    public void deleteCat() {
        catDao.deleteById(1L);

    }

    @Test
    public void getLolaACat() {
        // find the owner's record in the db
        PetOwner lola = ownerDao.findById(2L).get();
        Cat scruffy = new Cat();
        scruffy.setGender("Male");
        scruffy.setName("Scruffy");
        scruffy.setAge(1);
        scruffy.setOwner(lola);

        catDao.save(scruffy);
    }

    @Test
    public void giveThatCatSomeToys() {
        Toy toy1 = toyDao.findById(1L).get();
        Toy toy3 = toyDao.findById(3L).get();

        Cat lolasCat = catDao.findById(3L).get();
        lolasCat.getToys().add(toy1);
        lolasCat.getToys().add(toy3);

        catDao.save(lolasCat);
    }

    @Test
    public void uploadFile() {
        String s3FileName = s3Service.uploadFile("/Users/markrobinson/Desktop/baby.gif");
        System.out.println("filename to store in the db is " + s3FileName);
        System.out.println(s3Service.getSignedURL(s3FileName));
    }

    @Test
    public void downloadFile() {
//        s3Service.uploadFile();
        System.out.println(s3Service.getSignedURL("dude.gif"));
    }
}
