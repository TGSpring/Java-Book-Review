public class EmployeeMain {
    public static void main(String[] args) {
      System.out.println("Marketer: ");
      Marketer employee1 = new Marketer();
      employee1.applyForVacation();
      employee1.showHours();
      employee1.showSalary();
      employee1.showVacation();
      employee1.advertise();

      System.out.println("Employee: ");
      Employee employee2 = new Employee();
      employee2.applyForVacation();
      employee2.showHours();
      employee2.showSalary();

      System.out.println("Janitor");
      Janitor janitor = new Janitor();
      janitor.applyForVacation();
      janitor.showHours();
      janitor.showSalary();
      janitor.showVacation();
      janitor.clean();
    }
}
