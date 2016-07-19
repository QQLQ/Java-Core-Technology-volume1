import com.horstmann.corejava.Employee;

/**
 * This program demonstrates the use of packages.
 * @author cay
 * @version 1.11 2004-02-19
 * @author Administrator
 *
 */
public class PackageTest {

	public static void main(String[] args) {
		// because of the import statement, we don't have to use com.horstmann.corejava.Employee here
		Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		
		harry.raiseSalary(5);
		
		// because of the static import statement, we don't have to use System.out here
		System.out.println("name=" + harry.getName() + ", salary=" + harry.getSalary());
	}
}
