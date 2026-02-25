package network.sql;

public class PriceOfEachProject {
    private String projectName;
    private int price;

    public PriceOfEachProject(String projectName, int price) {
        this.projectName = projectName;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "PriceOfEachProject{" +
                "projectName='" + projectName + '\'' +
                ", price=" + price +
                '}';
    }
}
