import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        
        ArrayList<Category> categories = new ArrayList<>();
        
        //Category CategoryName = new Category("CategoryName", {list of grades}, weight as a decimal (.15 = 15%))
        //change {} to {grade1, grade2, grade3} etc.
        //change weight to the weights of assignment categories for any given class (ex classA has finals take 40% of your grade, so set weight to 0.4 for finals)
        //to make a new category just copy any of these lines and change the name, grades array, and weight accordingly. Don't forget to then add your new category to the categories arraylist!! (categories.add(CategoryName))
        //if you dont have one of these categories in your class, make sure to remove it here
        Category Assignments = new Category("Assignments", new double[]{}, 0.1);
        Category GroupProject = new Category("Group Project", new double[]{}, 0.1);
        Category Labs = new Category("Labs", new double[]{}, 0.1);
        Category Quizzes = new Category("Quizzes", new double[]{}, 0.2);
        Category Midterm = new Category("Midterm", new double[]{}, 0.2);
        Category Final = new Category("Final", new double[]{}, 0.2);
        Category Attendance = new Category("Attendance", new double[]{}, 0.1);
        
        //add every category to the big list of categories
        categories.add(Assignments);
        categories.add(GroupProject);
        categories.add(Labs);
        categories.add(Quizzes);
        categories.add(Midterm);
        categories.add(Final);
        categories.add(Attendance);
        
        //optionally, grade values and weights can be changed later
        /*
        double assignmentGrades[] = {55, 73, 100};
        Assignments.setGrades(assignmentGrades);
        Assignments.setWeight(0.1); //set assignment weight to 0.1 (10%)
        
        Quizzes.setGrades(new double[]{86, 52}); //sets quiz grades to 86 and 52
        */
        
        //print everything
        displayInfo(categories);
    }
    
    public static void displayInfo(ArrayList<Category> categories){
        System.out.println(CategoryAverages(categories));
        System.out.println("Final grade: " + format(calcGrade(categories)) + "%");
    }
    
    public static boolean checkWeights(ArrayList<Category> categories){
        double totalWeight = 0;
        for (Category c : categories){
            totalWeight += c.getWeight();
        }
        //account for decimal errors when using doubles
        if (Math.abs(totalWeight - 1.0) < 0.000001){
            return true;
        }
        System.out.println("You weights total " + totalWeight + " (" + format(totalWeight*100) + "%)" + " instead of 1.0 (100%), so your calculation may be innacurate");
        return false;
    }
    
    public static String CategoryAverages(ArrayList<Category> categories){
        String total = "";
        for (Category c: categories){
            if (c.getGrades().length == 0){
                total += c.getName() + ": " + format(c.getAverage()) + "% (no items in category, not counted towards final grade)\n";
            }
            else{
                total += c.getName() + ": " + format(c.getAverage()) + "%\n";
            }
            
        }
        
        return total;
    }
    
    public static double calcGrade(ArrayList<Category> categories){
        checkWeights(categories);
        double weightedTotal = 0;
        double activeWeight = 0;

        for (Category c : categories) {
            if (c.getGrades().length > 0) {
                weightedTotal += c.getAverage() * c.getWeight();
                activeWeight += c.getWeight();
            }
        }
        
        //prevent 0 division
        if (activeWeight == 0) {
            System.out.println("no grades in gradebook");
            return -1;
        }

        return weightedTotal / activeWeight;
    }
    
    public static String format(double value){
        //format to have 2 decimals if there is a decimal value, otherwise show none
        if (Math.abs(value - Math.round(value)) < 0.000001){
            return String.format("%.0f", value);
        }
        return String.format("%.2f", value);
    }
}


//stores grade categories (attendance, quizzes, final, etc)
class Category{
    private String name;
    private double[] grades;
    private double weight;
    private double average;
    
    //constructor
    public Category(String name, double[] grades, double weight){
        this.name = name;
        this.grades = grades;
        this.weight = weight;
        this.average = avg(grades);
    }
    
    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setGrades(double[] grades){
        this.grades = grades;
        this.average = avg(grades);
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    
    //getters    
    public double getWeight(){
        return weight;
    }
    public double getAverage(){
        return average;
    }
    public double[] getGrades(){
        return grades;
    }
    public String getName(){
        return name;
    }
    
    //return the average of the grades for this category
    private double avg(double[] grades){
        if (grades.length == 0){
            return 0;
        }
        
        double total = 0;
        for (int i = 0; i < grades.length; i++){
            total += grades[i];
        }
        return total / grades.length;
    }
    
}