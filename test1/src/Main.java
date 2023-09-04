import java.util.ArrayList;

class Main{
    public static void main(String[] args)
    {
    FlightDatabase database = new FlightDatabase();
    database.checkIfFlightExist("Paris", "Madrid");
    database.displayFlightFromCity("Paris");
    database.displayFlightToCity("Mardid");

    System.out.println("^nie zadzialalo");


    ArrayList<Flight> fromCity = database.getFlightsFromCity("Paris");
    ArrayList<Flight> toCity = database.getFlightsToCity("Madrid");
    database.displayFlights(fromCity);
    database.displayFlights(toCity);
    //wywołanie getCities (lista)
    ArrayList<String> cities = database.getCities();                    //
    System.out.println(cities);                                         //to jest super sprawa
    Flight cheapestFlight = database.getCheapestFlight();
    System.out.println("The cheapest flight: "+cheapestFlight.getDetails());

    Flight cheapestFlightFromCity = database.getCheapestFlightFromCity("Paris");
    System.out.println("The cheapest flight: "+cheapestFlightFromCity.getDetails());

    ArrayList<Journey> journeys = database.getFlights("Paris", "Tokyo");
    System.out.println(journeys);
    }

}

class Flight{
    String departure;
    String arrival;
    int price;

    public Flight(String departure, String arrival, int price)
    {
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }

    public String getDetails(){
        return "Flight from " + this.departure + " to " + this.arrival + " costs " + this.price;
    }
}

class FlightDatabase {
    ArrayList<Flight> flights = new ArrayList<Flight>();

    public FlightDatabase() {
        this.flights.add(new Flight("Berlin", "Tokyo", 1800));
        this.flights.add(new Flight("Warsaw", "Chicago", 2500 ));
        this.flights.add(new Flight("Praga", "Madrid", 900));
        this.flights.add(new Flight("Lisbona", "Kopenhaga", 1000));
        this.flights.add(new Flight("Paris", "Warsaw", 750));
        this.flights.add(new Flight("Madrid", "Warsaw", 2000));
        this.flights.add(new Flight("Warsaw", "Paris", 600));
        this.flights.add(new Flight("Paris", "Berlin", 700));
        this.flights.add(new Flight("Tokyo", "Madrid", 2500));
    }

    public void checkIfFlightExist(String start, String end) {
        //boolean notExist = true;
        for (Flight flight : this.flights) {
            
            if (start.equals(flight.departure) && end.equals(flight.arrival)) {
                System.out.println("Flight exists");
                return;                                                     //funkcja void, ale możemy użyć return jako break
            }
        }
        System.out.println("This flight does not exist");
    }

    public void displayFlights(ArrayList<Flight> results) {                 //lub tu to nie działa
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
    public ArrayList<Flight> getFlightsToCity(String city)
    {
        ArrayList<Flight> results = new ArrayList<Flight>();
        for(int i=0; i<this.flights.size(); i++)
        {
            Flight flight = this.flights.get(i);
            if(city.equals(flight.arrival))
            {
                results.add(flight);
            }
        }
        return results;
    }
    public void displayFlightFromCity(String city)
    {
        ArrayList<Flight> results = getFlightsFromCity(city);
        displayFlights(results);
    }
    public void displayFlightToCity(String city)                        //ale to nie działa
    {
        ArrayList<Flight> results = getFlightsToCity(city);
        displayFlights(results);
    }

    public ArrayList<String> getCities()
    {
        ArrayList<String> cities = new ArrayList<>();
        for(Flight flight : this.flights)                           //pętla for each
        {
            if(!cities.contains(flight.departure))
            {
                cities.add(flight.departure);
            }
            if(!cities.contains(flight.arrival))
            {
                cities.add(flight.arrival);
            }
        }
        return cities;
    }
    public Flight getCheapestFlight()
    {
        Flight cheapestFlight = null;
        for(Flight flight : this.flights)
        {
            if(cheapestFlight==null || flight.price<cheapestFlight.price)
            {
                cheapestFlight = flight;
            }
        }
        return cheapestFlight;
    }
    public Flight getCheapestFlightFromCity(String city)
    {
        ArrayList<Flight> fromCity = getFlightsFromCity(city);
        Flight cheapestFlight = null;
        for(Flight flight : fromCity)
        {
            if(cheapestFlight==null || flight.price<cheapestFlight.price)
            {
                cheapestFlight = flight;
            }
        } 
        return cheapestFlight;
    }

    public ArrayList<Journey> getFlights(String start, String end)
    {
        ArrayList<Flight> starting = getFlightsFromCity(start);
        ArrayList<Flight> ending = getFlightsToCity(end);
        ArrayList<Journey> results = new ArrayList<Journey>();
        for(Flight first : starting)
        {
            for(Flight second : ending)
            {
                if(first.arrival.equals(second.departure))
                {
                    results.add(new Journey(first, second));
                }
            }
        }
        return results;
    }

}

class Journey
{
    Flight first;
    Flight second;

    public Journey(Flight first, Flight second)
    {
        this.first = first;
        this.second = second;
    }
    public String toString()
    {
        return "Flight from "+ first.departure + " to " + second.arrival + " with stop at " + first.arrival + " costs " + (first.price+second.price);
    }
}


/*
    public void getFlightsFromCity(String city)
    {
        boolean isNotFound = true;
        for(int i=0; i< this.flights.size(); i++)
        {
            Flight flight = this.flights.get(i);
            if(city.equals(flight.departure))
            {
                System.out.println(flight.getDetails());
                isNotFound=false;
            }
        }
        if(isNotFound)
        {
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





/*
  public void divided(int min, int max)
  {
    for(int i=min; i<=max; i++)
      {
        if(i%3==0 || i%5==0)
        {
          System.out.println(i);
        }
      }
  }

  public void getEvenSum(int min, int max)
  {
    int sum = 0;
    for(int i=min; i<=max; i++)
      {
        if(i%2==0)
        {
          System.out.println(i);
          sum+=i;
        }
      }
    System.out.println("Sum = "+ sum);

  }
  public void getNumber(int max, int min)
  {
    for(int i=max; i>=min; i--)
      {
        System.out.println(i);
      }
  }

*/