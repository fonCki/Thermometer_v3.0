package model;
public class Temperature
{
   private final String id;
   private final double value;
   private final DateTime time;

   public Temperature(String id, double value)
   {
      this.id = id;
      this.value = value;
      time = new DateTime();
   }
   public double getValue()
   {
      return value;
   }
   public String getId() {
      return id;
   }

   public DateTime getTime() {
      return time;
   }
   public String toString() {
      return String.format("%s: %.1f (%s)", id, value, time.getTimestamp());
   }
}
