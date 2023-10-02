import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        FlightDatabase database = new FlightDatabase();
        // wywołanie getCities (lista)
        ArrayList<String> cities = database.getCities(); //
        System.out.println("Available cities: " + cities); // to jest super sprawa

        System.out.print("Where do you want to fly from? ");
        Scanner city1 = new Scanner(System.in);
        String departureCity = city1.nextLine();
        System.out.print("Where do you want to go? ");
        Scanner city2 = new Scanner(System.in);
        String arrivalCity = city2.nextLine();
        System.out.println("Looking for a flight from " + departureCity + " to " + arrivalCity + ".");

        city1.close();
        city2.close();

        // database.checkIfFlightExist("Paris", "Madrid");
        database.checkIfFlightExist(departureCity, arrivalCity);
        System.out.println("Departures:");
        database.displayFlightFromCity(departureCity);
        System.out.println("Arrivals:");
        database.displayFlightToCity(arrivalCity);

        // System.out.println("^nie zadzialalo");
        System.out.print("\n");

        ArrayList<Flight> fromCity = database.getFlightsFromCity(departureCity);
        ArrayList<Flight> toCity = database.getFlightsToCity(arrivalCity);
        System.out.println("Departures:");
        database.displayFlights(fromCity);
        System.out.println("Arrivals:");
        database.displayFlights(toCity);

        Flight cheapestFlight = database.getCheapestFlight();
        System.out.println("*The cheapest flight: " + cheapestFlight.getDetails() + "*");

        Flight cheapestFlightFromCity = database.getCheapestFlightFromCity(departureCity);
        System.out.println("The cheapest flight from your departure city: " + cheapestFlightFromCity.getDetails());

        ArrayList<Journey> journeys = database.getFlights(departureCity, arrivalCity);
        System.out.println(journeys);
    }

}

class Flight {
    String departure;
    String arrival;
    int price;

    public Flight(String departure, String arrival, int price) {
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }

    public String getDetails() {
        return "Flight from " + this.departure + " to " + this.arrival + " costs " + this.price;
    }
}

class FlightDatabase {
    ArrayList<Flight> flights = new ArrayList<Flight>();

    public FlightDatabase() {
        this.flights.add(new Flight("Berlin", "Tokyo", 1800));
        this.flights.add(new Flight("Warsaw", "Chicago", 2500));
        this.flights.add(new Flight("Praga", "Madrid", 900));
        this.flights.add(new Flight("Lisbona", "Kopenhaga", 1000));
        this.flights.add(new Flight("Paris", "Warsaw", 750));
        this.flights.add(new Flight("Madrid", "Warsaw", 2000));
        this.flights.add(new Flight("Warsaw", "Paris", 600));
        this.flights.add(new Flight("Paris", "Berlin", 700));
        this.flights.add(new Flight("Tokyo", "Madrid", 2500));
    }

    public void checkIfFlightExist(String start, String end) {
        // boolean notExist = true;
        for (Flight flight : this.flights) {

            if (start.equals(flight.departure) && end.equals(flight.arrival)) {
                System.out.println("Flight exists");
                return; // funkcja void, ale możemy użyć return jako break
            }
        }
        System.out.println("Direct flight does not exist");
    }

    public void displayFlights(ArrayList<Flight> results) { // do sprawdzenia
        if (results.isEmpty()) {
            System.out.println("Flight not found!");
        }
        for (int i = 0; i < results.size(); i++) {
            Flight flight = results.get(i);
            System.out.println(flight.getDetails());
        }
    }

    public ArrayList<Flight> getFlightsFromCity(String city) {
        ArrayList<Flight> results = new ArrayList<Flight>();
        for (Flight flight : this.flights) {
            if (city.equals(flight.departure)) {
                results.add(flight);
            }
        }
        return results;
    }

    public ArrayList<Flight> getFlightsToCity(String city) {
        ArrayList<Flight> results = new ArrayList<Flight>();
        for (int i = 0; i < this.flights.size(); i++) {
            Flight flight = this.flights.get(i);
            if (city.equals(flight.arrival)) {
                results.add(flight);
            }
        }
        return results;
    }

    public void displayFlightFromCity(String city) {
        ArrayList<Flight> results = getFlightsFromCity(city);
        displayFlights(results);
    }

    public void displayFlightToCity(String city) // do sprawdzenia(?)
    {
        ArrayList<Flight> results = getFlightsToCity(city);
        displayFlights(results);
    }

    public ArrayList<String> getCities() {
        ArrayList<String> cities = new ArrayList<>();
        for (Flight flight : this.flights) // pętla for each
        {
            if (!cities.contains(flight.departure)) {
                cities.add(flight.departure);
            }
            if (!cities.contains(flight.arrival)) {
                cities.add(flight.arrival);
            }
        }
        return cities;
    }

    public Flight getCheapestFlight() {
        Flight cheapestFlight = null;
        for (Flight flight : this.flights) {
            if (cheapestFlight == null || flight.price < cheapestFlight.price) {
                cheapestFlight = flight;
            }
        }
        return cheapestFlight;
    }

    public Flight getCheapestFlightFromCity(String city) {
        ArrayList<Flight> fromCity = getFlightsFromCity(city);
        Flight cheapestFlight = null;
        for (Flight flight : fromCity) {
            if (cheapestFlight == null || flight.price < cheapestFlight.price) {
                cheapestFlight = flight;
            }
        }
        return cheapestFlight;
    }

    public ArrayList<Journey> getFlights(String start, String end) {
        ArrayList<Flight> starting = getFlightsFromCity(start);
        ArrayList<Flight> ending = getFlightsToCity(end);
        ArrayList<Journey> results = new ArrayList<Journey>();
        for (Flight first : starting) {
            for (Flight second : ending) {
                if (first.arrival.equals(second.departure)) {
                    results.add(new Journey(first, second));
                }
            }
        }
        return results;
    }

}

class Journey {
    Flight first;
    Flight second;

    public Journey(Flight first, Flight second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "Flight from " + first.departure + " to " + second.arrival + " with stop at " + first.arrival + " costs "
                + (first.price + second.price);
    }

}

    /* 
    public void getFlightsFromCity(String city) {
        boolean isNotFound = true;
        for (int i = 0; i < this.flights.size(); i++) {
            Flight flight = this.flights.get(i);
            if (city.equals(flight.departure)) {
                System.out.println(flight.getDetails());
                isNotFound = false;
            }
        }
        if (isNotFound) {
            System.out.println("Flight not found");
        }
    }

    public void getFlightsToCity(String city)
    {
        boolean isNotFound = true;
        for(int i=0; i<this.flights.size(); i++)
        {
            Flight flight = this.flights.get(i);
            if(city.equals(flight.arrival))
            {
                System.out.println(flight.getDetails());
                return;
            }
        }
        System.out.println("Destination not found");
    }
*/
