import java.util.ArrayList;

class Main{
    public static void main(String[] args)
    {
    FlightDatabase database = new FlightDatabase();
    database.checkIfFlightExist("Paris", "Madrid");
    ArrayList<Flight> fromCity = database.getFlightsFromCity("Paris");
    ArrayList<Flight> toCity = database.getFlightsToCity("Madrid");
    database.displayFlights(fromCity);
    database.displayFlights(toCity);
    }

}

class Flight{
    String departure;
    String arrival;

    public Flight(String departure, String arrival)
    {
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getDetails(){
        return "Flight from " + this.departure + " to " + this.arrival;
    }
}

class FlightDatabase {
    ArrayList<Flight> flights = new ArrayList<Flight>();

    public FlightDatabase() {
        this.flights.add(new Flight("Berlin", "tokyo"));
        this.flights.add(new Flight("Warsaw", "Chicago"));
        this.flights.add(new Flight("Praga", "Madrid"));
        this.flights.add(new Flight("Lisbona", "Kopenhaga"));
        this.flights.add(new Flight("Paris", "Warsaw"));
        this.flights.add(new Flight("Madrid", "Warsaw"));
        this.flights.add(new Flight("Warsaw", "Paris"));
        this.flights.add(new Flight("Paris", "Berlin"));
        this.flights.add(new Flight("Tokyo", "Madrid"));
    }

    public void checkIfFlightExist(String start, String end) {
        boolean notExist = true;
        for (int i = 0; i < this.flights.size(); i++) {
            Flight flight = this.flights.get(i);
            if (start.equals(flight.departure) && end.equals(flight.arrival)) {
                System.out.println("Flight exists");
                return;                                                     //funkcja void, ale możemy użyć return jako break
            }
        }
        System.out.println("This flight does not exist");
    }

    public void displayFlights(ArrayList<Flight> results) {
        if (results.isEmpty()) {
            System.out.println("Ni ma takiego");
        }
        for (int i = 0; i < results.size(); i++) {
            Flight flight = results.get(i);
            System.out.println(flight.getDetails());
        }
    }

    public ArrayList<Flight> getFlightsFromCity(String city) {
        ArrayList<Flight> results = new ArrayList<Flight>();
        for (int i = 0; i < this.flights.size(); i++) {
            Flight flight = this.flights.get(i);
            if (city.equals(flight.departure)) {
                results.add(flight);
            }
        }
        return results;
    }
    public ArrayList<Flight> getFlightsToCity(String city)
    {
        ArrayList<Flight> results = new ArrayList<Flight>();
        for(int i=0; i< this.flights.size(); i++)
        {
            Flight flight = this.flights.get(i);
            if(city.equals(flight.arrival))
            {
                results.add(flight);
            }
        }
        return results;
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
}




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
}
*/