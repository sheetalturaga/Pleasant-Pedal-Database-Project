package app.tools;

import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

import app.dal.*;
import app.model.*;
import app.model.Person;
import app.dal.*;




/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
@SuppressWarnings("unused")
public class Inserter {

	public static void main(String[] args) throws SQLException {
		PersonDao personDao = PersonDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		AdminDao adminDao = AdminDao.getInstance();
		BikeCounterDAO bikeCounterDao = BikeCounterDAO.getBikeCounterInstance();
		CrimeCounterDAO crimeCounterDao = CrimeCounterDAO.getCrimeCounterInstance();
		
		
		
		
		/**Person person = new Person("captMarvel", "abc", "julie", "drummer", "jdrummer@gmall.com", "919625382");
		person = personDao.create(person);
		Person person1 = new Person("tinkerbell", "abc1", "cece", "jones", "cjones@gmall.com", "9196253324");
		person1 = personDao.create(person1);
		Person person2 = new Person("mightyTiny", "abc2","dave", "hart", "daveh@gmall.com", "839625382");
		person2 = personDao.create(person2);
		
		String dateStr = "1980-12-03";
	    Date date = Date.valueOf(dateStr);
	    
		Users u1 = new Users("bu1", "xyz", "bruce", "chhay", "b1chhay@gmall.com", "919625382", date , "bio1", "address1", Users.levelStatus.BEGINNER);
		u1 = usersDao.create(u1);
		Users u2 = new Users("bu2", "abc", "bruce", "chhay", "b2chhay@gmall.com", "9196253324", date, "bio2", "address2", Users.levelStatus.ADVANCED);
		u2 = usersDao.create(u2);
		Users u3 = new Users("bu3", "lmno", "bruce", "chhay",  "b3chhay@gmall.com", "839625382", date, "bio3", "address3", Users.levelStatus.INTERMEDIATE);
		u3 = usersDao.create(u3);
		
		Person p1 = personDao.getPersonFromUserName("b1");
		List<Person> pList1 = personDao.getPersonsFromFirstName("bruce");
		System.out.format("Reading person: u:%s f:%s l:%s e:%s p:%s \n",
			p1.getUserName(), p1.getFirstName(), p1.getLastName(), p1.getEmail(), p1.getPhoneNumber());
		for(Person p : pList1) {
			System.out.format("Looping persons: u:%s f:%s l:%s e:%s p:%s \n",
				p.getUserName(), p.getFirstName(), p.getLastName());
		}
		
		Users user1 = usersDao.getUsersFromUserName("bu");
		List<Users> uList1 = usersDao.getUsersFromFirstName("bruce");
		System.out.format("Reading blog user: u:%s p:%s, f:%s l:%s e:%s p:%s d:%s bio:%s add:%s, level:%s \n",
				user1.getUserName(), user1.getPassword(), user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getPhoneNumber(),
				user1.getDateOfBirth(), user1.getBio(), user1.getAddress(), user1.getLevelStatus().name());
		for(Users u : uList1) {
			System.out.format("Looping blog users: u:%s p:%s f:%s l:%s e:%s p:%s d:%s bio:%s add:%s, level:%s \n",
					u.getUserName(),u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhoneNumber(),
					u.getDateOfBirth(), u.getBio(), u.getAddress(), u.getLevelStatus().name());
		}
		*/
		
	}
}