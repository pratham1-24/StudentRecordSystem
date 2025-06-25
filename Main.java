public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.loadFromFile();      // Load records at startup
        manager.showMenu();          // Show main CLI menu
        manager.saveToFile();        // Save records before exit
    }
}
