import java.util.*;

public class Main {

    private static Map<Integer,City> cities = new HashMap<>();
    private static Map<Integer,Road> roads = new HashMap<>();
    private static Map<Integer, String> modelsNumber = new HashMap<>();
    private static Map<Integer, String> actions = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initActions();
        initModels();
        while (true) {
            printMenu();
            int actionNumber = scanner.nextInt();
            if (!isValidInput(actionNumber)) {
                System.out.println("Invalid input. Please enter 1 for more info.");
                continue;
            }

            switch (actions.get(actionNumber)) {
                case "Help":
                    helpAction();
                    break;
                case "Add":
                    addAction(scanner);
                    break;
                case "Delete":
                    deleteAction(scanner);
                    break;
                case "Path":
                    pathAction(scanner);
                    break;
                case "Exit":
                    return;
            }
        }
    }

    private static boolean isValidInput(int actionNumber) {
        return actionNumber < 6 && actionNumber > 0;
    }

    private static void pathAction(Scanner scanner) {
        String[] citiesId = scanner.next().split(":");
        findRoadBetween(Integer.parseInt(citiesId[0]), Integer.parseInt(citiesId[1]));
    }

    private static void deleteAction(Scanner scanner) {
        printModels();
        deleteModel(modelsNumber.get(scanner.nextInt()), scanner.nextInt());
    }

    private static void addAction(Scanner scanner) {
        printModels();
        addModel(modelsNumber.get(scanner.nextInt()), scanner);
    }

    private static void helpAction() {
        System.out.println("Select a number from shown menu and enter. For example 1 is for help.");
    }

    private static void initActions() {
        actions.put(1, "Help");
        actions.put(2, "Add");
        actions.put(3, "Delete");
        actions.put(4, "Path");
        actions.put(5, "Exit");
    }

    private static void findRoadBetween(int sourceCityId, int destinationCityId) {
        for (Road road : roads.values()) {
            List<Integer> roadCities = road.getThrough();
            int indexOfFromCity = roadCities.indexOf(sourceCityId);
            if (indexOfFromCity > 0) {
                int indexOfToCity = roadCities.indexOf(destinationCityId);
                if (indexOfToCity > 0) {
                    if (road.isBiDirectional() || indexOfFromCity < indexOfToCity) {
                        System.out.println(cities.get(sourceCityId).getName() + ":" + cities.get(destinationCityId).getName() + " via Road " + road.getName() + ": Takes " + road.getTime());
                    }
                }
            }
        }
    }

    private static void deleteModel(String modelName, int modelId) {
        switch (modelName) {
            case "City":
                printDeleteResult(cities.remove(modelId), modelName, modelId);
                break;
            case "Road":
                printDeleteResult(roads.remove(modelId), modelName, modelId);
                break;
        }
    }

    private static void printDeleteResult(Object result, String modelName, int modelId) {
        if (result != null) {
            System.out.println(modelName + ":" + modelId + " deleted!");
        } else {
            System.out.println(modelName + " with id " + modelId + " not found!");
        }
    }

    private static void initModels() {
        modelsNumber.put(1, "City");
        modelsNumber.put(2, "Road");
    }

    private static void addRoad(Scanner scanner) {
        Road road = new Road();
        System.out.println("id=?");
        road.setId(scanner.nextInt());
        System.out.println("name=?");
        road.setName(scanner.next());
        System.out.println("from=?");
        road.setFrom(scanner.nextInt());
        System.out.println("to=?");
        road.setTo(scanner.nextInt());
        System.out.println("through=?");
        extractCities(scanner.next(), road.getFrom(), road.getTo(), road.getThrough());
        System.out.println("speedLimit=?");
        road.setSpeedLimit(scanner.nextInt());
        System.out.println("length=?");
        road.setLength(scanner.nextInt());
        System.out.println("biDirectional=?");
        road.setBiDirectional(scanner.nextInt() == 1);
        roads.put(road.getId(), road);
        printAfterAdd("Road", road.getId(), scanner);

    }

    private static void extractCities(String through, int from, int to, List<Integer> roadThrough) {
        String[] citiesId = through.substring(1, through.length() - 1).split(",");
        for (String cityId : citiesId)
            roadThrough.add(Integer.valueOf(cityId));

        if (roadThrough.get(0) != from)
            roadThrough.add(0, from);
        if (roadThrough.get(roadThrough.size() - 1) != to)
            roadThrough.add(to);
    }

    private static void addCity(Scanner scanner) {
        City city = new City();
        System.out.println("id=?");
        city.setId(scanner.nextInt());
        System.out.println("name=?");
        city.setName(scanner.next());
        cities.put(city.getId(), city);
        printAfterAdd("City", city.getId(), scanner);
    }

    private static void printAfterAdd(String model, int id, Scanner scanner) {
        System.out.println(model + " with id=" + id + " added!");
        System.out.println("Select your next action");
        System.out.println("1. Add another " + model);
        System.out.println("2. Main Menu");
        if (scanner.nextInt() == 1) {
            addModel(model, scanner);
        }
    }

    private static void addModel(String modelName, Scanner scanner) {
        switch (modelName) {
            case "City":
                addCity(scanner);
                break;
            case "Road":
                addRoad(scanner);
                break;
        }
    }

    private static void printModels() {
        System.out.println("Select model:");
        System.out.println("1. City");
        System.out.println("2. Road");
    }

    private static void printMenu() {
        System.out.println("Main Menu - Select an action:");
        System.out.println("1. Help");
        System.out.println("2. Add");
        System.out.println("3. Delete");
        System.out.println("4. Path");
        System.out.println("5. Exit");
    }
}
