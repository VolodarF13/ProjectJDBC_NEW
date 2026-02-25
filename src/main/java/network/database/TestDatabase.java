package network.database;

import network.sql.*;

import java.util.List;

public class TestDatabase {
        public static void main(String[] args) {

            List<MaxProjectCountClient> maxProjectCountClient = new DatabaseQueryService().findMaxProjectClient();
            System.out.println("maxProjectCountClient = " + maxProjectCountClient.toString());

            List<LongestProject> longestProjects = new DatabaseQueryService().findLongestProject();
            System.out.println("longestProjects.toString() = " + longestProjects.toString());

            List<MaxWorkerCountSalary> maxWorkerCountSalaries = new DatabaseQueryService().findMaxWorkerSalary();
            System.out.println("maxWorkerCountSalaries.toString() = " + maxWorkerCountSalaries.toString());

            List<YoungestEldestWorker> youngestEldestWorkers = new DatabaseQueryService().findYoungestEldestWorker();
            System.out.println("youngestEldestWorkers.toString() = " + youngestEldestWorkers.toString());

            List<PriceOfEachProject> priceOfEachProjects = new DatabaseQueryService().findProjectPrices();
            System.out.println("priceOfEachProjects.toString() = " + priceOfEachProjects.toString());

        }

}
