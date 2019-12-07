package pt.ubi.di.pmd.peddypaper;

public class Point {
    private String Name;
    private String Description;


    public Point(String fName,String fDescription){
        Name = fName;
        Description = fDescription;

    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }
}
